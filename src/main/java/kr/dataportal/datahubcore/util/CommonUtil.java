package kr.dataportal.datahubcore.util;

import jdk.jfr.Description;
import kr.dataportal.datahubcore.domain.dataset.cctv.DataSetCCTV;
import kr.dataportal.datahubcore.domain.dataset.gwanbo.DataSetGwanbo;
import kr.dataportal.datahubcore.dto.dataset.DataSetColumnDesc;

import javax.persistence.Column;
import javax.persistence.Embedded;
import java.lang.reflect.Field;
import java.util.*;


public class CommonUtil {
    private static final Map<String, String> classMapping
            = new HashMap<String, String>() {{
        put("DATASETCCTV", DataSetCCTV.class.getName());
        put("DATASETGWANBO", DataSetGwanbo.class.getName());
    }};

    public static Optional<Class<?>> getClassByClassName(String name) {
        try {
            return Optional.of(Class.forName(classMapping.get(name.toUpperCase())));
        } catch (ClassNotFoundException | NullPointerException e) {
            return Optional.empty();
        }
    }

    private static DataSetColumnDesc createDataSetColumnDesc(Field field) {
        String en = field.getName(),
                kr = "-",
                type = field.getType().getSimpleName(),
                desc = "";
        int length = -1;
        boolean nullable = false;

        if (field.isAnnotationPresent(Column.class)) {
            en = field.getAnnotation(Column.class).name();
            kr = field.getAnnotation(Column.class).columnDefinition();
            length = field.getAnnotation(Column.class).length();
            nullable = field.getAnnotation(Column.class).nullable();
        }
        if (field.isAnnotationPresent(Description.class)) {
            desc = field.getAnnotation(Description.class).value();
        }
        return new DataSetColumnDesc(en, kr, type, length, nullable, desc);
    }

    public static List<DataSetColumnDesc> parseClassProperty(Class<?> target) {
        List<DataSetColumnDesc> ret = new ArrayList<>();

        for (Field field : target.getDeclaredFields()) {
            if (field.isAnnotationPresent(Embedded.class)) {
                try {
                    Class<?> aClass = Class.forName(field.getType().getName());
                    ret.addAll(parseClassProperty(aClass));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return new ArrayList<>();
                }
            } else {
                ret.add(createDataSetColumnDesc(field));
            }
        }
        return ret;
    }

    public static List<DataSetColumnDesc> parseClassProperty(String target) {
        if (!classMapping.containsKey(target.toUpperCase())) {
            return new ArrayList<>();
        }
        try {
            return parseClassProperty(Class.forName(classMapping.get(target.toUpperCase())));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Function to covert camel case
    // string to snake case string
    public static String camelToSnake(String str) {

        // Empty String
        String result = "";

        // Append first character(in lower case)
        // to result string
        char c = str.charAt(0);
        result = result + Character.toLowerCase(c);

        // Tarverse the string from
        // ist index to last index
        for (int i = 1; i < str.length(); i++) {

            char ch = str.charAt(i);

            // Check if the character is upper case
            // then append '_' and such character
            // (in lower case) to result string
            if (Character.isUpperCase(ch)) {
                result = result + '_';
                result
                        = result
                        + Character.toLowerCase(ch);
            }

            // If the character is lower case then
            // add such character into result string
            else {
                result = result + ch;
            }
        }

        // return the result
        return result;
    }

    public static String generateRandomAlphaNumber(int targetStringLength) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
