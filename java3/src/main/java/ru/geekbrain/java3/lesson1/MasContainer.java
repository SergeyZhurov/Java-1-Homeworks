package ru.geekbrain.java3.lesson1;

public class MasContainer<T extends Number> {
    private T[] mas;

    public double avg() {
        if (mas.length == 0) {
            return 0.0;
        }
        double sum = 0.0;
        for (T item : mas) {
            sum += item.doubleValue();
        }
        return sum / mas.length;
    }

//    public boolean equalsAvg(MasContainer<? extends Number> masContainer) {
//        return avg() == masContainer.avg();
//    }

    public <M extends Number> boolean equalsAvg(MasContainer<M> masContainer) {
        return avg() == masContainer.avg();
    }

    public MasContainer(T... mas) {
        this.mas = mas;
//        this.mas = new T[3]; // Error
    }

    public T[] getMas() {
        return mas;
    }

    public void setMas(T[] mas) {
        this.mas = mas;
    }
}
