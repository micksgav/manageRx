package apiInteracting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class getInteractions {

    public static String[][] listInteractions(String DIN) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("data\\drugs\\cleanDrugInteractions\\" + DIN + ".txt"));

            String line;
            String[] temp;

            int length = 0;
            while ((line = br.readLine()) != null) {
                length++;
            }
            br.close();

            br = new BufferedReader(new FileReader("data\\drugs\\cleanDrugInteractions\\" + DIN + ".txt"));
            String[][] drugInteractions = new String[length][3];

            for (int i = 0; i < length; i++) {
                line = br.readLine();
                drugInteractions[i] = new String[] {line.substring(1, line.length()-1)};
            }

            return drugInteractions;

        } catch (Exception e) {
            System.out.println("There was an error.");
        }
        return null;
    }

    public static String[] search(String DIN1, String DIN2) {
        String[][] allInteractions = listInteractions(DIN1);

        for (int i = 0; i < allInteractions.length; i++) {
            if (allInteractions[i][0].compareTo(DIN2) == 0) {
                return allInteractions[i];
            }
        }
        return null;
    }

    public static String[][] arraySearch(String[] DINS) {
        ArrayList<String[]> interactions = new ArrayList<String[]>();
        for (int i = 0; i < DINS.length; i++) {
            for (int j = 0; j < DINS.length; j++) {
                if (DINS[j].compareTo(DINS[j]) != 0){
                    interactions.add(search(DINS[i], DINS[j]));
                }
            }
        }

        String[][] array = new String[interactions.size()][3];
        for (int k = 0; k < interactions.size(); k++) {
            array[k] = interactions.get(k);
        }

        return array;

    }

}
