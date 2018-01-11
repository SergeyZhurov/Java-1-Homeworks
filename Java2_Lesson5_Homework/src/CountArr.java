public class CountArr extends Thread {
    private float[] arr;

    CountArr(float[] arr) {
        this.arr = arr;
        start();
    }

    public void run() {
        Hw5.calculations(arr);
    }
}

