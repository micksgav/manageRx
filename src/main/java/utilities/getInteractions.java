package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class getInteractions {

//    listInteractions example: (null if no interactions)
//    String[][] list = getInteractions.listInteractions(DIN);
//    for (int i = 0; i < list.length; i++) {
//        System.out.println(list[i][0] + " | " + list[i][1] + " | " + list[i][2]);
//    }
    public static String[][] listInteractions(String DIN) throws IOException {
        String ATC = DINtoATC(DIN);
        try {
            BufferedReader br = new BufferedReader(new FileReader("data\\drugs\\interactions\\" + ATC + ".txt"));
            ArrayList<String[]> interactions = new ArrayList<>();

            String line;
            while ((line = br.readLine())!= null) {
                String temp;
                String[] tempArray = new String[3];
                tempArray[0] = line.substring(1, line.indexOf(","));
                temp = line.substring(line.indexOf(",") + 2);
                tempArray[1] = ATC;
                temp = temp.substring(temp.indexOf(",") + 2);
                tempArray[2] = temp.substring(0, temp.length() - 1);
                interactions.add(tempArray);
            }
            String[][] array = new String[interactions.size()][3];
            for (int i = 0; i < interactions.size(); i++) {
                array[i] = interactions.get(i);
            }
            return array;
        }  catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
            }
    }

//    String[] test2 = search(DIN1, DIN2);
//        for (int i = 0; i < test2.length; i++) {
//        System.out.println(test2[i]);
//    }
    public static String[] search(String DIN1, String DIN2) throws IOException {
        String checkATC = DINtoATC(DIN2);
        String checkATC2 = DINtoATC(DIN1);
        String[][] allInteractions = listInteractions(DIN1);
        String[][] allInteractions2 = listInteractions(DIN2);

        assert allInteractions != null;
        for (String[] allInteraction : allInteractions) {
            if (allInteraction[0].compareTo(checkATC) == 0) {
                return allInteraction;
            }
        }
        assert allInteractions2 != null;
        for (String[] strings : allInteractions2) {
            if (strings[0].compareTo(checkATC2) == 0) {
                return strings;
            }
        }
        return null;
    }
//    String[] dins = new String[]{"02171791", "02171880", "02177129", "02188961"};
//    String[][] result = arraySearch(dins);
//        for (int i = 0; i < result.length; i++) {
//        System.out.println(result[i][0] + " | " + result[i][1] + " | " + result[i][2]);
//    }

    public static String[][] arraySearch(String[] DINS) throws IOException {
        boolean add = true;
        ArrayList<String[]> interactions = new ArrayList<>();
        for (int i = 0; i < DINS.length; i++) {
            for (int j = 0; j < DINS.length; j++) {
                if (j != i){
                    String[] result = search(DINS[i], DINS[j]);
                    if (result!= null){
                        for (String[] interaction : interactions) {
                            if (result[2].equals(interaction[2])) {
                                add = false;
                                break;
                            }
                        }
                        if (add) interactions.add(result);
                    }
                }
            }
        }

        String[][] array = new String[interactions.size()][3];
        for (int k = 0; k < interactions.size(); k++) {
            array[k] = interactions.get(k);
        }
        return array;
    }

    //internal usage
    private static String DINtoATC(String DIN) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data\\drugs\\DIN2ATC.txt"));

        String line;
        while ((line = br.readLine())!= null) {
            if (line.substring(0, line.indexOf(" ")).compareTo(DIN) == 0) return line.substring(line.indexOf(" ")+1);
        }
        return "0";
    }
}
