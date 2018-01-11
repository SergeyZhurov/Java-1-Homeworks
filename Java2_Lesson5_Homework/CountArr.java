/**
 * Java 2. Lesson 5. Homework
 *
 * @ author Sergey Zhurov
 * @ vertion dated Jan 11 2018
 * @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
 */

public class CountArr extends Thread {
    private float[] arr;
    private int shift;

    CountArr(float[] arr, int shift) {
        this.arr = arr;
        this.shift = shift;
        start();
    }

    public void run() {
        Hw5.calculations(arr, shift);
    }
}

