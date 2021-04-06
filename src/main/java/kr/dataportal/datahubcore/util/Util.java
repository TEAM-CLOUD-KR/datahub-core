package kr.dataportal.datahubcore.util;

import javax.persistence.Column;
import javax.persistence.Embedded;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<String> parseClassProperty(Class<?> target) {
        List<String> ret = new ArrayList<String>();

        for (Field field : target.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                ret.add(field.getAnnotation(Column.class).name());
            } else if (field.isAnnotationPresent(Embedded.class)) {
                try {
                    Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass(field.getType().getName());
                    ret.addAll(parseClassProperty(aClass));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                ret.add(field.getName());
            }
        }
        return ret;
    }
}
