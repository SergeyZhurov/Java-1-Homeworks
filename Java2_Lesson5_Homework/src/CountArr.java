public class CountArr extends Thread {
    private float[] arr;
    private int destPos;

    CountArr(float[] arr, int destPos) {
        this.arr = arr;
        this.destPos = destPos;
        start();
    }

    public void run() {
        Hw5.calculation(arr);
        System.arraycopy(arr, 0, Hw5.arr, destPos, arr.length);
    }
}

