package homework.reflection.first;

import java.util.HashMap;

/**
 * Created by Gorobets Dmitriy on 17.09.2015.
 */

public class ReflectRunner {
    public ReflectRunner() {
    }

    public static void main(String[] args) {
        byte fieldNumber = 6;
        HashMap fields = new HashMap(fieldNumber);
        fields.put("name", "NameOfTestClass000");
        fields.put("type", "Furniture000");
        fields.put("price", Double.valueOf(2580.8D));
        fields.put("hight", Integer.valueOf(100));
        fields.put("length", Float.valueOf(150.0F));
        fields.put("age", Integer.valueOf(10));
        ReflectionClass.getObject("homework.reflection.first.TestClass", fields);
    }
}