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

        if (field.isAnnotationPresent(Column.class)) {
            en = field.getAnnotation(Column.class).name();
            kr = field.getAnnotation(Column.class).columnDefinition();
        }
        if (field.isAnnotationPresent(Description.class)) {
            desc = field.getAnnotation(Description.class).value();
        }
        return new DataSetColumnDesc(en, kr, type, desc);
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
}
