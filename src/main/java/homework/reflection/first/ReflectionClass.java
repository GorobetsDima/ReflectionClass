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
 * @ author
 * Created by Gorobets Dmitriy on 17.09.2015.
 * <p>
 * метод "getObject" сетит  private поля класса TestClass
 * @parame className - имя  любого искомого класса
 * @parame fields - мапа состоящая из названий полей и значений этих полей
 * <p>
 * clazz = обьект типа Class
 * obj = обьект типа TestClass
 * e - имя класа TestClass
 * fildNames -множество ключей из мапы fields
 * iter - итератор по множеству ключей мапы
 * keySetName - значение ключа из множества ключей
 * field - обьект поля полученый из класса TestClass
 * fieldName- имя поля
 * mods - число которое соответствует типу модифекатора получаемого из класса TestClass поля
 * fieldType - тип получаемого поля
 * @except IllegalAccessException -выбрасывается ,если программа пытается получить доступ к закрытым полям
 * @except NoSuchFieldException -выбрасывается ,если программа не нашла соответствующего поля в заданном классе
 * @except ClassNotFoundException -выбрасывается ,если программа не нашла соответствующего класса
 * @except InstantiationException -выбрасывается ,если программа не не может создать новый экземпляр класса TestClass
 * <p>
 * метод "getFieldsFromTestMethod" сетит параметры в  private метод класса TestClass
 * <p>
 * returnType - тип возвращаемого значения метода
 * paramTypes - массив параметров метода
 * @exept InvocationTargetException - выбрасывается если вызов метода не возможен
 * все остольное анологично методу "getObject", только заместь полей действия производятся над методами!
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
        } catch (IllegalAccessException | NoSuchFieldException | ClassNotFoundException | InstantiationException var) {
            System.out.println(var.getMessage());
            var.printStackTrace();
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
                        System.out.println(retTypeName + " " + methName + " " + "(" + Arrays.toString(paramTypes) + " = " + methodzz.get(keySetName) + ");");

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
