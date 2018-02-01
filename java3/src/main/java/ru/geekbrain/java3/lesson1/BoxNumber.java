package ru.geekbrain.java3.lesson1;

import java.io.Serializable;

public class BoxNumber<T extends Number & Serializable> {
    private T obj;

    public BoxNumber(T obj) {
        this.obj = obj;
//        this.obj = new T();//Error
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public void info(){
        System.out.println("obj: " + obj);
        System.out.println("type: " + obj.getClass());
    }

    public int rang() {
        return obj.intValue();
    }
}
