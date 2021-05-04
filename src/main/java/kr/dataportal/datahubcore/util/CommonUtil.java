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
        if (field.isAnnotationPresent(Column.class)) {
            return new DataSetColumnDesc(
                    field.getAnnotation(Column.class).name(),
                    field.getAnnotation(Column.class).columnDefinition(),
                    field.getType().getSimpleName(),
                    field.getAnnotation(Description.class).value()
            );
        } else {
            return new DataSetColumnDesc(
                    field.getName(),
                    "-",
                    field.getType().getSimpleName(),
                    field.getAnnotation(Description.class).value()
            );
        }
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
