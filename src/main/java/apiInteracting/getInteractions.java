package apiInteracting;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class getInteractions {

    public static void main(String[] args) throws IOException {
        //List Example
        String DIN = "02289598";
        String[][] list = listInteractions(DIN);

        if (list == null) System.out.println("No Interactions");
        else for (int i = 0; i < list.length; i++) System.out.println(list[i][0].toString() + " | " + list[i][1].toString() + " | " + list[i][2].toString());

        //Check Example


    }

    public static String[][] listInteractions(String DIN) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("data\\drugs\\interactions.txt"));
            long[] lineNums = getLineNum(DIN);
            long start = lineNums[0];
            int length = (int) (lineNums[1]-lineNums[0]);
            int pos = 0;
            String[][] interactions = new String[length][3];

            while (pos <= start-1) {
                br.readLine();
                pos++;
            }
            String line;
            String temp;

            for (int i = 0; i < length; i++) {
                line = br.readLine();
                if (line == null) return null;

                interactions[i][0] = line.substring(1, line.indexOf(","));
                temp = line.substring(line.indexOf(",") + 2);
                interactions[i][1] = temp.substring(0, temp.indexOf(","));
                temp = temp.substring(temp.indexOf(",") + 2);
                interactions[i][2] = temp.substring(0, temp.length() - 1);
            }
            return interactions;
        }  catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
            }
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

    private static long[] getLineNum (String DIN) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data\\drugs\\index.txt"));
        long[] lineNums = new long[2];

        String line;
        String line2;
        String impDIN;

        while ((line = br.readLine()) != null) {
            impDIN = line.substring(0, line.indexOf(" "));

            if (impDIN.compareTo(DIN) == 0) {
                lineNums[0] = Long.parseLong(line.substring(line.indexOf(" ")+1));
                line2 = br.readLine();
                if (line2 == null) lineNums[1] = -1;
                lineNums[1] = Long.parseLong(line2.substring(line2.indexOf(" ") +1));
                break;

            }
        }
        return lineNums;
    }

    private static void DINtoATC(String DIN) {



    }


}
