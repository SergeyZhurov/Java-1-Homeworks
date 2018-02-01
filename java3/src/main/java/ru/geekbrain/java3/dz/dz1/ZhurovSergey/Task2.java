package ru.geekbrain.java3.dz.dz1.ZhurovSergey;

import java.util.ArrayList;
import java.util.List;

public class Task2 {

    static <T> ArrayList<T> toArrayList (T[] arr) {
        ArrayList<T> result = new ArrayList<>();
        for (T temp : arr)
            result.add(temp);
        return result;
    }
}
