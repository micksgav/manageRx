package apiInteracting;

import java.io.*;

public class process {

    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("data\\drugs\\index.txt"));
        BufferedWriter writer2 = new BufferedWriter(new FileWriter("data\\drugs\\interactions.txt"));
        BufferedReader br;

        File path = new File("data\\drugs\\cleanDrugInteractions\\");
        String fn;
        File [] files = path.listFiles();
        int lineNum = 0;

        for (int j = 0; j < files.length; j++) {
            fn = files[j].toString().substring(files[j].toString().indexOf("cleanDrugInteractions") + 22, files[j].toString().indexOf("."));
            br = new BufferedReader(new FileReader("data\\drugs\\cleanDrugInteractions\\" + fn + ".txt"));

            String line;
            while ((line = br.readLine()) != null) {
                writer2.write(line + "\n");
                lineNum++;
            }
            writer.write(fn + " " + lineNum + "\n");
            br.close();
        }
        writer.close();
        writer2.close();
    }
}
