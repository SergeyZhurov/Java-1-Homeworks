/**
 * Java 2. Lesson 5. Homework
 *
 * @ author Sergey Zhurov
 * @ vertion dated Jan 11 2018
 * @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
 */

public class Hw5 {
    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr = new float[size];

    public static void main(String[] args) throws InterruptedException {
        setDefaultArr(arr);

        long a = System.currentTimeMillis();
        calculations(arr);
        System.out.println(System.currentTimeMillis() - a);

        countInThreads();
    }

    public static void countInThreads() throws InterruptedException {
        float[] a1 = new float[h];
        float[] a2 = new float[h];

        setDefaultArr(arr);

        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        Thread arr1Thread = new CountArr(a1);
        Thread arr2Thread = new CountArr(a2);
        arr1Thread.join();
        arr2Thread.join();
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println(System.currentTimeMillis() - a);
    }

    public static void setDefaultArr(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
    }

    public static synchronized void calculations(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}