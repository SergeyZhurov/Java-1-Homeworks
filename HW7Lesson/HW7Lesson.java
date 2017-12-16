/**
 * Java 1. Lesson 6. Homework
 *
 * @author Sergey Zhurov
 * @version dated Dec 16, 2017
 */

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class HW7Lesson {
    static Cat[] cat = new Cat[5];
    static Plate plate = new Plate(50);

    public static void main(String[] args) {
        Window window = new Window();
        int[] appetite = {15, 25, 10, 15, 40};
        cat[0] = new Cat("Tisha", appetite[0]);
        cat[1] = new Cat("Vaska", appetite[1]);
        cat[2] = new Cat("Murzik", appetite[2]);
        cat[3] = new Cat("Kisa", appetite[3]);
        cat[4] = new Cat("Baton", appetite[4]);
        printCats();
        System.out.println(plate);
    }
    // Makes all cats hungry
    public static void makeHungry() {                                         
        for (int i = 0; i < cat.length; i++) cat[i].setFull(false);
    }
    //Tries to feed random cats untill all cats are full or no cat can get full from eating anymore
    public static void feedCats() {                                           
        Random rand = new Random();                                             
        int minHungryAppetite;
        do {                                                    // feeding cats
            minHungryAppetite = 0;                              // getting hungry cat with the minimum appetite
            for (int i = 0; i < cat.length; i++) {
                if(cat[i].getFull() == false){
                    minHungryAppetite = cat[i].getAppetite();
                    break;
                }
            }
            for (int j = 1; j < cat.length; j++)                // end cycle if all cats are full or no cat can get full
                if((cat[j].getAppetite() < minHungryAppetite) && (cat[j].getFull() == false))
                    minHungryAppetite = cat[j].getAppetite();
            int fullCats = 0;                                                           // Checking if all cats are full
            for(int j = 0; j < cat.length; j++) if(cat[j].getFull()) fullCats++;        
            if(minHungryAppetite > plate.getFood() || (fullCats == cat.length)) break;
            do {
                int pick = rand.nextInt(cat.length);            // Picking random cat
                if(cat[pick].getFull()) continue;               // If cat is full - trying to pick another one
                else {
                    cat[pick].eat(plate);                       // else feed the cat
                    break;
                }
            }while(true);
        }while(true);
        HW7Lesson.printCats();
        System.out.println(plate);
    }
// Prints all the information about cats
    public static void printCats() {
        for(int i = 0; i < cat.length; i++)
            System.out.println(cat[i].getName() + "(appetite: " + cat[i].getAppetite() + ")"
                    + " is " + ((cat[i].getFull())? "full." : "hungry."));
    }
}

class Window extends JFrame {
    public Window() {
        setTitle("Feeding cats.");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 420, 68);
        JButton jbsFeed = new JButton("Feed cats");
        JButton jbsAddFood = new JButton("Add food to plate");
        JButton jbsMakeHungry = new JButton("Make cats hungry");
        setLayout(null);
        jbsFeed.setBounds(0, 0, 135, 30);
        jbsAddFood.setBounds(135, 0, 135, 30);
        jbsMakeHungry.setBounds(270, 0, 135, 30);
        add(jbsFeed);
        add(jbsAddFood);
        add(jbsMakeHungry);
        jbsFeed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HW7Lesson.feedCats();
            }
        });
        jbsAddFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HW7Lesson.plate.addFood();
                System.out.println(HW7Lesson.plate);
            }
        });
        jbsMakeHungry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HW7Lesson.makeHungry();
                HW7Lesson.printCats();
                System.out.println(HW7Lesson.plate);
            }
        });
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

    int getAppetite() {
        return appetite;
    }

    boolean getFull() {
        return full;
    }

    void setFull(boolean full) {
        this.full = full;
    }

    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "(appetite: " + appetite + ")"
                + " is " + ((full)? "full." : "hungry.");
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

    void addFood() {
        if (food + 10 <= maxFood) food += 10;
        else System.out.println("plate is full");
    }

    void decreaseFood(int food) {
        this.food -= food;
    }

    @Override
    public String toString() {
        return "Plate: " + food;
    }
}