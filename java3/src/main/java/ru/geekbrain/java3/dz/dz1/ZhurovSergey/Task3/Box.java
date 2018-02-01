package ru.geekbrain.java3.dz.dz1.ZhurovSergey.Task3;

import java.util.ArrayList;
import java.util.Arrays;

public class Box <T extends Fruit> {
    private ArrayList<T> box = new ArrayList<>();

    public final void add(T... obj) {
        box.addAll(Arrays.asList(obj));
    }

    public float getWeight() {
        if (box.size() == 0) return 0.0f;
        else if(box.get(0) instanceof Apple)
            return 1.0f * box.size();
        else return 1.5f * box.size();
    }

    public boolean compare (Box obj) {
        return (Float.compare(obj.getWeight(), this.getWeight()) == 0);
    }

    public void moveContentTo(Box<T> obj) {
        for (T f : box) obj.add(f);
        box.clear();
     }
}
