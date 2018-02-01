package ru.geekbrain.java3.lesson1;

public class Box {
    private Object obj;

    public Box(Object obj) {
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public void info(){
        System.out.println("obj: " + obj);
        System.out.println("type: " + obj.getClass());
    }
}
