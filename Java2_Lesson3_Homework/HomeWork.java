/**
 * Java 2. Lesson 3. Homework
 * 
 * @ author Sergey Zhurov
 * @ vertion dated Dec 28 2017
 * @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
*/

import java.io.*;
import java.util.*;

public class HomeWork {
    public static void main(String[] args) {
        final String FILE_TO_WRITE = "PhoneBook.txt";
        final String FILE_TO_READ = "ToSortList.txt";                           // *** TASK 1 ***
        String tempString;

        List<String> arrayList = new ArrayList<>();
        Set<String> set;
        Map<String, Integer> map = new HashMap<String, Integer>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_TO_READ))) {
            while ((tempString = reader.readLine()) != null)                    // reading Array from file
                arrayList.add(tempString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        set = new HashSet<>(arrayList);                                         // creating list of unique items
        System.out.println("Unique words: " + set);                             // check
        for (String s : arrayList)                                              // counting same words in the 1st list
            if (map.containsKey(s)) map.replace(s, map.get(s) + 1);
            else map.put(s, 1);
        System.out.println("Words count: " + map);                              // check

        PhoneBook book = new PhoneBook();                                       // Task 2 check
        book.add("Сергей","123-45-67");
        book.add("Сергей","159-68-77");
        book.add("Женя","145-96-87");
        book.add("Лена","145-89-77");
        book.add("Ксюша","156-88-75");
        book.add("Юра","145-88-96");
        System.out.println(book.get("Сергей"));
        System.out.println(book.get("Ксюша"));
        book.export(FILE_TO_WRITE);
    }
}
