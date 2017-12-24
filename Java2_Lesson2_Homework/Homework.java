/**
 * Java 2. Homework 2.
 *
 * @author Sergey Zhurov
 * @version dated Dec 23, 2017
 * @link https://github.com/
 */

import java.util.*;
import java.io.*;

class Homework {
    public static void main(String[] args) {
        final String FILE_TO_READ = "Input.txt";                           // *** TASK 4 ***
        ArrayList<ArrayList<String>> finalArray = new ArrayList<ArrayList<String>>();
        ArrayList<String> columns = new ArrayList<String>();
        StringBuffer readBuffer = new StringBuffer();
        int currentRead;
        char c;
        try (FileReader reader = new FileReader(FILE_TO_READ)) {                // Reading from file
            do {
                if((currentRead = reader.read()) == -1) {                       // if file ended
                    columns.add(readBuffer.toString());                         // finish adding all we have read since
                    finalArray.add(new ArrayList<String>(columns));             // last iteration
                    break;                                                      // and break fileRead operation
                }
                c = (char)currentRead;
                if(c == ' ') {                                                  // if current word has ended
                    columns.add(readBuffer.toString());                         // add it to collumns array
                    readBuffer.delete(0, readBuffer.length());                  // and clear readBuffer
                } else if(currentRead == '\r') {
                    columns.add(readBuffer.toString());                         // if line ended
                    finalArray.add(new ArrayList<String>(columns));             // add columns Array to final Array
                    columns.clear();                                            // clear everything for the next line
                    readBuffer.delete(0, readBuffer.length());
                } else if(currentRead == '\n') {                                // this one we just skip (doubles '\r')
                    continue;
                } else readBuffer.append(c);     // with no special symbols - just add what we've read to StringBuffer
            } while(true);
        } catch (IOException ex) {
            System.out.println("File: " + FILE_TO_READ + " not found.");
        }
        try {
            System.out.println(arrayExceptionMethod(finalArray));               // *** TASK 3 ***
        } catch (ArrayIndexOutOfBoundsException ex) {                           // *** TASK 1 ***
            ex.printStackTrace();
        } catch (NumberFormatException ex) {                                    // *** TASK 2 ***
            ex.printStackTrace();
        }
    }

    public static int arrayExceptionMethod(ArrayList<ArrayList<String>> stringToCheck)
                                                        throws ArrayIndexOutOfBoundsException, NumberFormatException {
        for(int i = 0; i < 4; i++)
            if(stringToCheck.get(i).size() != 4) throw new ArrayIndexOutOfBoundsException();
        int result = 0, i = 0, j = 0;
        try {
            for(i = 0; i < 4; i++)
                for(j = 0; j < 4; j++)
                    result += Integer.parseInt(stringToCheck.get(i).get(j));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("cell [" + i + " : " + j + "]"); 
        }
        return result;
    }
}