package ru.geekbrain.java3.dz.dz1.ZhurovSergey;
import ru.geekbrain.java3.dz.dz1.ZhurovSergey.Task3.*;

public class Main {
    public static void main(String[] args) {

        String[] testString = {"zero", "first", "second", "third"};     // test Task1
        Integer[] testInt = {0, 1, 2, 3};

        System.out.println("Task1 :");
        Task1.switchPlaces(testString, 0, 3);
        Task1.switchPlaces(testInt, 1, 2);
        for (String s : testString)
            System.out.print(s + " ");
        System.out.println();
        for (Integer i : testInt)
            System.out.print(i + " ");
        System.out.println();

        System.out.println("Task2 :");                                  // test Task2
        System.out.println(Task2.toArrayList(testString).getClass());
        System.out.println(Task2.toArrayList(testString));

        Box<Apple> appleBox1 = new Box<>();                             // test Task3
        Box<Apple> appleBox2 = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        Orange[] orangeArray = new Orange[2];

        appleBox1.add(new Apple(), new Apple(), new Apple());
        System.out.println("appleBox1 weight 3?: " + appleBox1.getWeight());

        orangeBox.add(orangeArray);
        System.out.println("orangeBox weight 3?: " + orangeBox.getWeight());

        System.out.println("Compare... true?: " + appleBox1.compare(orangeBox));
        orangeBox.add(new Orange());
        System.out.println("Compare... false?: " + appleBox1.compare(orangeBox));

        appleBox2.add(new Apple());
        appleBox1.moveContentTo(appleBox2);
        System.out.println("Weight of appleBox1 0?: " + appleBox1.getWeight());
        System.out.println("Weight of appleBox2 4?: " + appleBox2.getWeight());
    }
}
