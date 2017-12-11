/**
 * Java 1. Lesson 6. Homework tasks 1-4. Task 5 is in the TicTacToe.java file
 * 
 * @ author Sergey Zhurov
 * @ vertion dated Dec 11 20178
 * @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
*/

class HW6Lesson {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Cat cat = new Cat();
        for (float i = 0.25f; i <= 500.25f; i += 500) {
            System.out.format("%nRunning, jumping and swimming to " + i + "m%n");
            System.out.println("CAT: ");
            cat.run(i);
            cat.jump(i);
            cat.swim(i);
            System.out.println("DOG: ");
            dog.run(i);
            dog.jump(i);
            dog.swim(i);
        }
    }
}

abstract class Animal {                                                             // Task 1
    float limitRun, limitJump, limitSwim;

    public void run(float a) {                                                      // Task 2
        System.out.println("run: " + ((a > 0) && (a <= limitRun)));                 // Task 4
    }

    public void swim(float a) {                                                     // Task 2
        System.out.println("swim: " + ((a > 0) && (a <= limitSwim)));               // Task 4
    }

    public void jump(float a) {                                                     // Task 2
        System.out.println("jump: " + ((a > 0) && (a <= limitJump)));               // Task 4
    }
}

class Dog extends Animal {                                                          // Task 1
    Dog() {
        this.limitRun = 500;                                                        // Task 3
        this.limitJump = 0.5f;
        this.limitSwim = 10;
    }
}

class Cat extends Animal {                                                          // Task 1
    Cat() {
        this.limitRun = 200;                                                        // Task 3
        this.limitJump = 2;
        this.limitSwim = 0;
    }
}

