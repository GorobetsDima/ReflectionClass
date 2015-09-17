package homework.reflection.first;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Gorobets Dmitriy on 17.09.2015.
 */

public class ReflectionClass {


    public static Object getObject(String className, Map<String, Object> fields) {
        TestClass test = new TestClass();
        Class clazz = null;

        try {
            clazz = Class.forName(className);
            String e = clazz.getName();
            System.out.println(e);
            Set fildNames = fields.keySet();
            Iterator iter = fildNames.iterator();

            for(int i = 0; i < fildNames.size(); ++i) {
                if(iter.hasNext()) {
                    String fieldName = (String)iter.next();
                    Field field = clazz.getDeclaredField(fieldName);
                    int mods = field.getModifiers();
                    if(Modifier.isAbstract(mods) | Modifier.isFinal(mods) | Modifier.isPrivate(mods)) {
                        field.setAccessible(true);
                    }

                    Class fieldType = field.getType();
                    String fieldTypeAllName = fieldType.getName();
                    String[] str = fieldTypeAllName.split("\\.");
                    String nameOfFieldType = str[str.length - 1];
                    System.out.println("Field " + nameOfFieldType + " " + fieldName + " = " + fields.get(fieldName));
                    if(fieldTypeAllName.equals("java.lang.String")) {
                        field.set(test, fields.get(fieldName));
                    } else if(fieldTypeAllName.equals("java.lang.Integer")) {
                        field.set(test, fields.get(fieldName));
                    } else if(fieldTypeAllName.equals("java.lang.Float")) {
                        field.set(test, fields.get(fieldName));
                    } else if(fieldTypeAllName.equals("java.lang.Double")) {
                        field.setDouble(test, ((Double)fields.get(fieldName)).doubleValue());
                    } else {
                        field.set(test, fields.get(fieldName));
                    }
                }
            }
        } catch (IllegalAccessException | NoSuchFieldException | ClassNotFoundException var15) {
            System.out.println(var15.getMessage());
            var15.printStackTrace();
        }

        return clazz;
    }
}
