/**
 * Java 1. Lesson 6. Homework
 *
 * @author Sergey Zhurov
 * @version dated Dec 15, 2017
 */

import java.util.*;
import javax.swing.*;

class HW7Lesson {

    public static void main(String[] args) {
        Window window = new Window();
        Cat[] cat = new Cat[5];
        cat[0] = new Cat("Tisha", 15);
        cat[1] = new Cat("Vaska", 25);
        cat[2] = new Cat("Murzik", 10);
        cat[3] = new Cat("Kisa", 15);
        cat[4] = new Cat("Baton", 40);
        Plate plate = new Plate(50);
        System.out.println(plate);
        Random rand = new Random();
        for(int i = 0; i < cat.length; i++) {                                                               // Trying to feed cats as many times as there are cats
            int fullCats = 0;                                                                               // Checking if all cats are full
            for(int j = 0; j < cat.length; j++) if(cat[j].getFull()) fullCats++;                            //
            if(fullCats == cat.length) break;                                                               //
            do {
                int pick = rand.nextInt(cat.length);                                                        // Picking random cat
                if(cat[pick].getFull()) continue;                                                           // If cat is full - trying to pick another one
                else {
                    cat[pick].eat(plate);
                    break;
                }
            }while(true);
        }
        for(int i = 0; i < cat.length; i++)
            System.out.println(cat[i].getName() + " is " + ((cat[i].getFull())? "full." : "hungry."));
    }
}

class Window extends JFrame {
    public Window() {
        setTitle("Feeding cats.");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);
        setVisible(true);
    }
}

class Cat {
    private String name;
    private int appetite;
    boolean full;

    Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    void eat(Plate plate) {
        if(plate.getFood() >= appetite) {
            plate.decreaseFood(appetite);
            full = true;
        }
    }

    boolean getFull() {
        return full;
    }
    
    String getName() {
        return name;
    }
}

class Plate {
    private int food, maxFood;

    Plate(int food) {
        this.food = food;
        this.maxFood = food;
    }

    int getFood() {
        return food;
    }
    
    void fillPlate() {
        food = maxFood;
    }

    void decreaseFood(int food) {
        this.food -= food;
    }

    @Override
    public String toString() {
        return "Plate: " + food;
    }
}