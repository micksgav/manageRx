package apiInteracting;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class dinListGen {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data\\drugs\\drugData.txt"));

        String line;
        String DIN;
        String ATC;

        while ((line = br.readLine())!= null) {
            DIN = line.substring(0, line.indexOf(" "));


        }



    }


}
