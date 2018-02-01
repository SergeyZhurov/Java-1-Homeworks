package ru.geekbrain.java3.dz.dz1.ZhurovSergey;

class Task1 {

    static <N> void switchPlaces (N[] obj, int first, int second) {
        N temp;
        if ((obj.length > 0) && (first <= obj.length) && (second <= obj.length) && (first > -1) && (second > -1)) {
            temp = obj[first];
            obj[first] = obj[second];
            obj[second] = temp;
        }
    }
}
