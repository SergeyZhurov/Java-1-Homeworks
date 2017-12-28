/**
 * Java 2. Lesson 3. Homework
 * 
 * @ author Sergey Zhurov
 * @ vertion dated Dec 28 2017
 * @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
*/

import java.io.*;
import java.util.*;

public class PhoneBook {                                                // *** TASK 2 ***
    Map<String, String> phoneBook = new HashMap<String, String>();

    void add (String name, String number) {
        phoneBook.put(number, name);
    }

    ArrayList<String> get (String name) {
        ArrayList<String> resultList = new ArrayList<>();

        for (HashMap.Entry<String, String> pair : phoneBook.entrySet())
            if (pair.getValue().equals(name)) resultList.add(pair.getKey());
        return resultList;
    }

    void export (String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (HashMap.Entry<String, String> pair : phoneBook.entrySet()) {
                writer.write(pair.getKey() + " ");
                writer.write(pair.getValue() + ";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
