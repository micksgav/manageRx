package apiInteracting;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import org.json.*;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

public class generateData {

    public static void main(String[] args) throws IOException {
        getDrugs();


    }

    public static void getDrugs() throws IOException {
        //get drugs JSON
        String result = "";
        try {
            URL url = new URL("https://health-products.canada.ca/api/drug/drugproduct/?lang=en&type=json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = br.readLine()) != null) {
                result = result + line;
            }

            br.close();
        } catch (Exception e) {
            System.out.println("There was an error in getting the data from Health Canada. Error Code: " + e.getMessage());
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("data\\drugs\\drugData.txt"));
        try {
            JSONArray jsonArray = new JSONArray(result);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject record = jsonArray.getJSONObject(i);
                String className = record.optString("class_name");
                String drugCode = record.optString("drug_code");
                String DIN = record.optString("drug_identification_number");
                String name = record.optString("brand_name");
                String company = record.optString("company_name");
                if ("Human".equals(className)) {
                    String RXCUI = CODEtoRXCUI(drugCode);
                    writer.write(name + "," + DIN + "," + RXCUI + "," + drugCode + "," + company + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    writer.close();
    }

    public static void getATCs() {
        try {
        URL url = new URL("https://health-products.canada.ca/api/drug/therapeuticclass/?lang=en&type=json");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        InputStream is = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        BufferedWriter bw = new BufferedWriter(new FileWriter("data\\drugs\\ATCs.json"));
        String line;

        while ((line = br.readLine()) != null) {
            bw.write(line);
        }

        bw.close();
        br.close();


    } catch (Exception e) {
        System.out.println("There was an error in getting the ATCs from Health Canada. Error Code: " + e.getMessage());
    }
    }

    public static String CODEtoRXCUI(String drugCode) {
        try {
            URL url = new URL("https://health-products.canada.ca/api/drug/therapeuticclass/?lang=en&type=json&id=" + drugCode);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String result = "";
            String line;

            while ((line = br.readLine()) != null) {
                result = result+line;
            }


            br.close();

            JSONObject record = new JSONObject(result);
            String atc = record.optString("tc_atc_number");
            System.out.println(atc);
            Thread.sleep(50);
            URL url2 = new URL("https://rxnav.nlm.nih.gov/REST/rxcui.json?idtype=ATC&id=" + atc);
            HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
            conn2.setRequestMethod("GET");
            conn2.setRequestProperty("Accept", "application/json");

            InputStream is2 = conn2.getInputStream();
            BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
            String result2 = "";
            String line2;

            while ((line2 = br2.readLine()) != null) {
                result2 = result2+line2;
            }

            br2.close();

            JSONObject record2 = new JSONObject(result);
            String RXCUI = record.optString("rxnormId");
            System.out.print(RXCUI);

        } catch (Exception e) {
            System.out.println("There was an error in getting the ATCs from Health Canada. Error Code: " + e.getMessage());
        }

        return "A";
    }

    private static void scraper() throws InterruptedException, IOException {
        // TODO Auto-generated method stub
        BufferedReader reader = new BufferedReader(new FileReader("data\\drugs\\drugRCXUIs.txt"));
        BufferedReader br;
        BufferedWriter writer;

        String imported = "";
        String name = "";
        int drugID = 0;
        URL url;
        String result;

        while (true) {
            Thread.sleep(50);
            imported = reader.readLine();
            name = imported.substring(0, imported.indexOf(" "));
            System.out.println(imported);

            if (imported == null) {
                System.out.println("Finished");
                break;
            } else {
                drugID = Integer.parseInt(imported.substring(imported.indexOf(" ") + 1));

                writer = new BufferedWriter(new FileWriter("data\\drugs\\drugInteractions\\" + name + ".xml"));

                url = new URL("https://rxnav.nlm.nih.gov/REST/interaction/interaction?rxcui=" + drugID);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/xml");

                br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                while ((result = br.readLine()) != null) {
                    writer.write(result + "\n");
                }
                writer.close();
                System.out.println("Data for " + name + " has been scraped.");
            }
        }
        reader.close();
    }


}
