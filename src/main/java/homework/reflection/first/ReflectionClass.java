package homework.reflection.first;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Gorobets Dmitriy on 17.09.2015.
 */

public class ReflectionClass {


    public static Object getObject(String className, Map<String, Object> fields) {
        Class clazz = null;

        try {
            clazz = Class.forName(className);
            Object obj = clazz.newInstance();
            String e = clazz.getName();
            System.out.println(e);
            Set fildNames = fields.keySet();
            Iterator iter = fildNames.iterator();

            for (int i = 0; i < fildNames.size(); ++i) {
                if (iter.hasNext()) {

                    String fieldName = (String) iter.next();
                    Field field = clazz.getDeclaredField(fieldName);
                    String fname = field.getName();

                    int mods = field.getModifiers();
                    if (Modifier.isAbstract(mods) | Modifier.isFinal(mods) | Modifier.isPrivate(mods)) {
                        field.setAccessible(true);
                    }

                    Class fieldType = field.getType();
                    String fieldTypeAllName = fieldType.getName();
                    String[] str = fieldTypeAllName.split("\\.");
                    String nameOfFieldType = str[str.length - 1];
                    System.out.println("Field " + nameOfFieldType + " " + fieldName + " = " + fields.get(fieldName));
                    if (fname.equals(fieldName)) {
                        field.set(obj, fields.get(fname));
                    }

                }
            }
        } catch (IllegalAccessException | NoSuchFieldException | ClassNotFoundException var15) {
            System.out.println(var15.getMessage());
            var15.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return clazz;
    }

    public static Object getFieldsFromTestMethod(String className, Map<String, Object> fields) {

        Class clazz = null;

        try {
            clazz = Class.forName(className);
            Object obj1 = clazz.newInstance();
            String myClassName = clazz.getName();
            System.out.println(myClassName);
            Set fildNames = fields.keySet();
            Iterator iter = fildNames.iterator();

            for (int i = 0; i < fildNames.size(); ++i) {
                if (iter.hasNext()) {
                    String fieldName = (String) iter.next();
                    Method[] methods = clazz.getDeclaredMethods();
                    for (Method method : methods) {
                        method.setAccessible(true);
                        String methName = method.getName();

                    }
                    Field field = clazz.getDeclaredField(fieldName);
                    String fname = field.getName();
                    System.out.println(fname);
                    int mods = field.getModifiers();
                    if (Modifier.isAbstract(mods) | Modifier.isFinal(mods) | Modifier.isPrivate(mods)) {
                        field.setAccessible(true);
                    }

                    Class fieldType = field.getType();
                    String fieldTypeAllName = fieldType.getName();
                    String[] str = fieldTypeAllName.split("\\.");
                    String nameOfFieldType = str[str.length - 1];
                    System.out.println("Field " + nameOfFieldType + " " + fieldName + " = " + fields.get(fieldName));
                    if (fieldTypeAllName.equals("java.lang.Double")) {
                        field.setDouble(obj1, (Double) fields.get(fieldName));
//                    field.set(obj1, fields.get(fieldName));
//                } else if(fieldTypeAllName.equals("java.lang.Integer")) {
//                    field.set(obj1, fields.get(fieldName));
//                } else if(fieldTypeAllName.equals("java.lang.Float")) {
//                    field.set(obj1, fields.get(fieldName));
//                } else if(fieldTypeAllName.equals("java.lang.Double")) {
//                    field.setDouble(obj1, (Double) fields.get(fieldName));
                    } else {
                        field.set(obj1, fields.get(fieldName));

                    }
                }
            }
        } catch (IllegalAccessException | NoSuchFieldException | ClassNotFoundException var15) {
            System.out.println(var15.getMessage());
            var15.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return clazz;
    }
}
