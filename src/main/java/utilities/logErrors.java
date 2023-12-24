package utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class logErrors {

    public static void log(String message) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true));
            //https://www.javatpoint.com/java-get-current-date
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            writer.write(dtf.format(now) + " " + message + "\n");
        } catch (Exception e) {
            System.out.println("Error Printing to Log! -> " + e.getMessage());
            System.out.println("message");
        }
    }
}
