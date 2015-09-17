package homework.reflection.first;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
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

                    String keySetName = (String) iter.next();
                    Field field = clazz.getDeclaredField(keySetName);
                    String fieldName = field.getName();

                    int mods = field.getModifiers();
                    if (Modifier.isAbstract(mods) | Modifier.isFinal(mods) | Modifier.isPrivate(mods)) {
                        field.setAccessible(true);
                    }

                    Class fieldType = field.getType();
                    String fieldTypeAllName = fieldType.getName();
                    String[] str = fieldTypeAllName.split("\\.");
                    String nameOfFieldType = str[str.length - 1];
                    System.out.println("Field " + nameOfFieldType + " " + keySetName + " = " + fields.get(keySetName));
                    if (fieldName.equals(keySetName)) {
                        field.set(obj, fields.get(fieldName));
                    }

                }
            }
        } catch (IllegalAccessException | NoSuchFieldException | ClassNotFoundException var) {
            System.out.println(var.getMessage());
            var.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return clazz;
    }

    public static Object getFieldsFromTestMethod(String className, Map<String, Object> methodzz) {

        Class clazz = null;

        try {
            clazz = Class.forName(className);
            Object obj1 = clazz.newInstance();
            String myClassName = clazz.getName();
            System.out.println(myClassName);
            Set methodNames = methodzz.keySet();
            Iterator iter = methodNames.iterator();

            for (int i = 0; i < methodNames.size(); ++i) {
                if (iter.hasNext()) {
                    String keySetName = (String) iter.next();
                    Method[] methods = clazz.getDeclaredMethods();
                    for (Method method : methods) {
                        int mods = method.getModifiers();
                        if (Modifier.isPrivate(mods) | Modifier.isProtected(mods) | Modifier.isAbstract(mods)) {
                            method.setAccessible(true);
                        }

                        Class returnType = method.getReturnType();
                        String retTypeName = returnType.getTypeName();

                        Class[] paramTypes = method.getParameterTypes();
                        String methName = method.getName();
                        if (methName.equals(keySetName)) {
                            method.invoke(obj1, methodzz.get(keySetName));
                        }
                        System.out.println(retTypeName + " " + methName + " " + "(" + Arrays.toString(paramTypes) +" = "+ methodzz.get(keySetName)+ ");");

                    }
                }
            }
        } catch (IllegalAccessException | ClassNotFoundException | InstantiationException | InvocationTargetException var15) {
            System.out.println(var15.getMessage());
            var15.printStackTrace();
        }

        return clazz;
    }
}
