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

