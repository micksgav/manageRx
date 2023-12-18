package apiInteracting;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class atcprocess {
    static String[][] atcConv = new String[2224][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data\\drugs\\RXCUIList.txt"));
        BufferedWriter bw;

        String line;
        int i = 0;
        while ((line = br.readLine()) != null) {
            if (line.substring(line.indexOf(" ")+1).compareTo("0") != 0) {
                atcConv[i][0] = line.substring(0, line.indexOf(" "));
                atcConv[i][1] = line.substring(line.indexOf(" ")+1);
            }
        }


        File path = new File("data\\drugs\\atcInteractions\\");
        File [] files = path.listFiles();


        String fn;
        assert files != null;
        for (File file : files) {
            fn = file.toString().substring(file.toString().indexOf("atcInteractions") + 16, file.toString().indexOf("."));
            String[][] temp = getInteractions(fn);
            bw = new BufferedWriter(new FileWriter("data\\drugs\\cleanAtcInteractions\\" + fn + ".txt"));
            for (String[] strings : temp) {
                bw.write(Arrays.toString(strings) + "\n");
            }
            System.out.println("File Added");
            bw.close();
        }
    }

    public static String[][] getInteractions(String ATC) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("data\\drugs\\atcInteractions\\" + ATC + ".xml"));

            NodeList commentNodes = doc.getElementsByTagName("comment");
            List<String> components = new ArrayList<>();
            for (int i = 0; i < commentNodes.getLength(); i++) {
                String comment = commentNodes.item(i).getTextContent();
                int start = comment.indexOf("to ") + 3;
                String search = comment.substring(start);
                int end = search.indexOf(" (");
                String name = search.substring(0, end);
                components.add(name);
            }

            NodeList interactionPairNodes = doc.getElementsByTagName("interactionPair");
            List<String[]> interactions = new ArrayList<>();
            for (int i = 0; i < interactionPairNodes.getLength(); i++) {
                Node interactionPairNode = interactionPairNodes.item(i);
                NodeList interactionConceptNodes = ((Element) interactionPairNode).getElementsByTagName("interactionConcept");
                String drug1 = ((Element) interactionConceptNodes.item(0)).getElementsByTagName("name").item(0).getTextContent();
                String drug2 = ((Element) interactionConceptNodes.item(1)).getElementsByTagName("name").item(0).getTextContent();
                String description = ((Element) interactionPairNode).getElementsByTagName("description").item(0).getTextContent();
                String RXCUI;
                String RXCUI1 = ((Element) interactionPairNode).getElementsByTagName("rxcui").item(0).getTextContent();
                String RXCUI2 = ((Element) interactionPairNode).getElementsByTagName("rxcui").item(1).getTextContent();


                boolean firstDrug = true;
                for (String component : components) {
                    if (drug1.compareTo(component) == 0) {
                        firstDrug = false;
                        break;
                    }
                }

                String interactingDrug;

                if (firstDrug) {
                    interactingDrug = drug1;
                    RXCUI = RXCUI1;
                } else {
                    interactingDrug = drug2;
                    RXCUI = RXCUI2;
                }

                String interactATC = getATC(RXCUI);
                if (!Objects.equals(interactATC, "")) {
                    interactions.add(new String[]{interactATC, interactingDrug, description});
                }

            }

            String[][] result = new String[interactions.size()][3];
            for (int i = 0; i < interactions.size(); i++) {
                result[i] = interactions.get(i);
            }

            return result;
        } catch (Exception e) {
            return new String[0][0];
        }
    }
    public static String getATC(String RXCUI) {
        try {
            Thread.sleep(55);
            String ATC = "";
            BufferedReader br;

            URL url;
            String result;
            url = new URL("https://rxnav.nlm.nih.gov/REST/rxcui/" + RXCUI + "/allProperties.json?prop=codes");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            StringBuilder total = new StringBuilder();
            while ((result = br.readLine()) != null) {
                total.append(result);
            }

            JSONObject jsonObject = new JSONObject(total.toString());
            JSONObject propConceptGroup = jsonObject.getJSONObject("propConceptGroup");
            JSONArray jsonArray = propConceptGroup.getJSONArray("propConcept");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject record = jsonArray.getJSONObject(i);
                String name = record.optString("propName");
                String ATCimp = record.optString("propValue");
                if ("ATC".equals(name)) {
                    ATC = ATCimp;
                }
            }
            return ATC;
        } catch (Exception e) {
            return "";
        }
    }

}
