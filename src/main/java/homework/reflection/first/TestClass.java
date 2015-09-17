package homework.reflection.first;

/**
 * @ author
 * Created by Gorobets Dmitriy on 17.09.2015.
 * тестовый класс,который используется для работы методов класса ReflectionClass
 */



public class TestClass {
    private String name;
    private String type;
    private double price;
    private Integer hight;
    private Float length;
    private int age;


  //private String s = getMethodFields(" 2");

//    public String getS() {
//        return s;
//    }

    private String getMethodFields(String s1) {
        String name1;
        String type1;
        double price1;
        Integer hight1;
        Float length1;
        int age1;
        String result = s1 ;
        System.out.println(result);
        return result;

    }
}