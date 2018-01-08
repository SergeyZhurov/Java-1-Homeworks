/**
 * Java 2. Lesson 4. Homework
 *
 * @ author Sergey Zhurov
 * @ vertion dated Jan 8 2018
 * @ GitHub link https://github.com/SergeyZhurov/Java-1-Homeworks.git
 */

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Log implements OpenOption {

    private String logFileName;

    Log (String logFileName) throws IOException {
        this.logFileName = logFileName;
    }

    public void write(String s) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(logFileName), StandardCharsets.UTF_8,
                StandardOpenOption.APPEND);
        writer.write("\n" + s);
        writer.close();
    }
}
