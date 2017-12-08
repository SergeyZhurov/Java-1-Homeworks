/**
 * Java 1. Lesson 5. Homework tasks 1-5. Task 6 is in the TicTacToe.java file
 * 
 * @ author Sergey Zhurov
 * @ vertion dated Dec 7 20178
 * @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
 */

class HW5Lesson {
    public static void main(String[] args) {
        Person[] person = new Person[5];                                                         // Task 4
        person[0] = new Person("Ivan", "Software developer", "8(131)265-9986", 80000, 25);
        person[1] = new Person("Nick", "Marketing specialist", "8(131)459-8622", 80000, 37);
        person[2] = new Person("George", "CIO", "8(131)459-8211", 120000, 46);
        person[3] = new Person("Olga", "Human Resources", "8(131)167-8455", 90000, 35);
        person[4] = new Person("Katy", "Security", "8(131)156-6477", 65000, 40);
        System.out.println("Employees older than 40 :");
        for (int i = 0; i < person.length; i++)                                                  // Task 5
            if (person[i].getAge() >= 40) {
                System.out.println();
                person[i].print();
            }
    }
 }
 
class Person {                                                                                   // Task 1

    private String name, job, email, tel;
    private int salary, age;

    Person(String name, String job, String tel, int salary, int age) {                           // Task 2
        this.name = name;
        this.job = job;
        this.tel = tel;
        this.salary = salary;
        this.age = age;
    }

    void print() {                                                                              // Task 3
        System.out.println("Employee : " + name);
        System.out.println("Job      : " + job);
        System.out.println("Tel      : " + tel);
        System.out.println("Salary   : " + salary);
        System.out.println("Age      : " + age);
    }
    
    int getAge() {
        return age;
    }
}