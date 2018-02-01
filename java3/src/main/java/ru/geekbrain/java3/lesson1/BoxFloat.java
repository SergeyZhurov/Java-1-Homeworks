package ru.geekbrain.java3.lesson1;

public class BoxFloat {
    private Float obj;

    public BoxFloat(Float obj) {
        this.obj = obj;
    }

    public Float getObj() {
        return obj;
    }

    public void setObj(Float obj) {
        this.obj = obj;
    }

    public void info(){
        System.out.println("obj: " + obj);
        System.out.println("type: " + obj.getClass());
    }
}
