package apiInteracting;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class generateData {
    static ArrayList<String> prescriptionDrugs = new ArrayList<>();
    static int n = 0;
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        //generatePrescriptionList();
        //getDrugs();
        //scrapeInteractions();
        BufferedWriter bw;
        File path = new File("data\\drugs\\drugInteractions\\");
        String fn;
        File [] files = path.listFiles();

        int resume = 0;
        for (int j = 0; j < files.length; j++) {
            if (files[j].toString().substring(files[j].toString().indexOf("drugInteractions") + 17, files[j].toString().indexOf(".")).compareTo("02427516") == 0) {
                resume = j;
                break;
            }
        }

        System.out.println(resume);
        for (int i = resume; i < files.length; i++){
            fn = files[i].toString();
            fn = fn.substring(fn.indexOf("drugInteractions") + 17, fn.indexOf("."));
            String[][] result = getInteractions(fn);
            bw = new BufferedWriter(new FileWriter("data\\drugs\\cleanDrugInteractions\\" + fn + ".txt"));
            for (String[] row : result) {
                bw.write(Arrays.toString(row) + "\n");
            }
            bw.close();
            System.out.println(fn + " Added");
        }


//        String[][] result = getInteractions("02248808");
//        for (String[] row : result) {
//
//
//
//            System.out.println(Arrays.toString(row));
//        }
    }

    public static void getDrugs() throws IOException {
        //get drugs JSON
        StringBuilder result = new StringBuilder();
        try {
            Thread.sleep(50);
            URL url = new URL("https://health-products.canada.ca/api/drug/drugproduct/?lang=en&type=json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = br.readLine()) != null) {
                result.append(line);
            }

            br.close();
        } catch (Exception e) {
            System.out.println("There was an error in getting the data from Health Canada. Error Code: " + e.getMessage());
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("data\\drugs\\drugData.txt"));
        try {
            JSONArray jsonArray = new JSONArray(result.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                System.out.println(i + " / " + jsonArray.length());
                JSONObject record = jsonArray.getJSONObject(i);
                String className = record.optString("class_name");
                String drugCode = record.optString("drug_code");
                String DIN = record.optString("drug_identification_number");
                String name = record.optString("brand_name");
                if ("Human".equals(className)) {
                    if (check(drugCode)) {
                        String RXCUI = CODEtoRXCUI(drugCode);
                        writer.write(DIN + " " + name + "\n" + RXCUI + " " + drugCode + "\n");
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        writer.close();
    }

    public static String CODEtoRXCUI(String drugCode) throws IOException {
        String atc = "";
        String RXCUI = "";
        BufferedWriter errors = new BufferedWriter(new FileWriter("data\\drugs\\errors.txt", true));
        try {
            atc = getAtc(drugCode);

            Thread.sleep(50);
            URL url2 = new URL("https://rxnav.nlm.nih.gov/REST/rxcui.json?idtype=ATC&id=" + atc);
            HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
            conn2.setRequestMethod("GET");
            conn2.setRequestProperty("Accept", "application/json");

            InputStream is2 = conn2.getInputStream();
            BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
            StringBuilder result2 = new StringBuilder();
            String line2;

            while ((line2 = br2.readLine()) != null) {
                result2.append(line2);
            }

            br2.close();


            try {
                RXCUI = result2.substring(result2.indexOf(":[") + 3, result2.indexOf("]") - 1);
            } catch (StringIndexOutOfBoundsException e) {
                RXCUI = "0";
                errors.write("ZERO " + drugCode + " " + atc + "\n");
            } catch (NumberFormatException e) {
                RXCUI = result2.substring(result2.indexOf(":[") + 3, result2.indexOf(",") - 1);
            }
            System.out.println(RXCUI + " Added");
        } catch (Exception e) {
            System.out.println("There was an error. Error Code: " + e.getMessage());
            errors.write("DC " + drugCode + " " + atc + "\n");
        }
        errors.close();
        return RXCUI;
    }

    private static String getAtc(String drugCode) throws IOException {
        URL url = new URL("https://health-products.canada.ca/api/drug/therapeuticclass/?lang=en&type=json&id=" + drugCode);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        InputStream is = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder result = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            result.append(line);
        }


        br.close();
        result = new StringBuilder(result.substring(1, result.length() - 1));

        JSONObject record = new JSONObject(result.toString());
        return record.optString("tc_atc_number");
    }

    public static boolean check(String drugCode) {
        for (String prescriptionDrug : prescriptionDrugs) {
            if (drugCode.equals(prescriptionDrug)) {
                return true;
            }

        }
        return false;
    }
    public static void generatePrescriptionList() {
        StringBuilder result = new StringBuilder();
        try {
            Thread.sleep(50);
            URL url = new URL("https://health-products.canada.ca/api/drug/schedule/?lang=en&type=json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = br.readLine()) != null) {
                result.append(line);
            }

            br.close();
        } catch (Exception e) {
            System.out.println("There was an error in getting the data from Health Canada. Error Code: " + e.getMessage());

        }

        try {
            JSONArray jsonArray = new JSONArray(result.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                System.out.println(i + " / " + jsonArray.length());
                JSONObject record = jsonArray.getJSONObject(i);
                String drugCode = record.optString("drug_code");
                String type = record.optString("schedule_name");
                if (type.compareTo("OTC") != 0) {
                    if (type.compareTo("Homeopathic") != 0) {
                        prescriptionDrugs.add(drugCode);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void scrapeInteractions() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data\\drugs\\drugData.txt"));
            BufferedReader br;
            BufferedWriter writer;

            String line;
            String line2;
            String DIN;
            String RXCUI;
            URL url;
            String result;

            while ((line = reader.readLine()) != null) {
                Thread.sleep(50);
                DIN = line.substring(0, line.indexOf(" "));
                line2 = reader.readLine();
                RXCUI = line2.substring(0, line2.indexOf(" "));
                if (RXCUI.compareTo("0") != 0) {
                    if (RXCUI.compareTo("") != 0) {
                        writer = new BufferedWriter(new FileWriter("data\\drugs\\drugInteractions\\" + DIN + ".xml"));

                        url = new URL("https://rxnav.nlm.nih.gov/REST/interaction/interaction?rxcui=" + RXCUI);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setRequestProperty("Accept", "application/xml");

                        br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                        while ((result = br.readLine()) != null) {
                            writer.write(result + "\n");
                        }
                        writer.close();
                        System.out.println("Data for " + line.substring(line.indexOf(" "), line.length()) + " has been scraped.");
                        br.close();
                    }
                }
            }
            reader.close();

        } catch (Exception e) {

        }

    }

    public static String[][] getInteractions(String DIN) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("data\\drugs\\drugInteractions\\" + DIN + ".xml"));

        NodeList commentNodes = doc.getElementsByTagName("comment");
        List<String> components = new ArrayList<>();
        for (int i = 0; i < commentNodes.getLength(); i++) {
            String comment = commentNodes.item(i).getTextContent();
            int start = comment.indexOf("to ")+3;
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
            for (int j = 0; j < components.size(); j++) {
                if (drug1.compareTo(components.get(j)) == 0) {
                    firstDrug = false;
                }
            }

            String interactingDrug = "";

            if (firstDrug == true) {
                interactingDrug = drug1;
                RXCUI = RXCUI1;
            } else {
                interactingDrug = drug2;
                RXCUI = RXCUI2;
            }

            String interactDIN = getDIN(RXCUI);
            if (interactDIN != "") interactions.add(new String[]{interactDIN, interactingDrug, description});
        }

        String[][] result = new String[interactions.size()][3];
        for (int i = 0; i < interactions.size(); i++) {
            result[i] = interactions.get(i);
        }

        return result;
    }

    public static String getDIN(String RXCUI) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data\\drugs\\drugData.txt"));


        String DIN = "";
        String line;
        String line2;
        while ((line = reader.readLine()) != null) {
            line2 = reader.readLine();

            if (line2.substring(0, line2.indexOf(" ")).compareTo(RXCUI) == 0) {
                DIN = line.substring(0, line.indexOf(" "));
            }
        }
        return DIN;
    }

}






