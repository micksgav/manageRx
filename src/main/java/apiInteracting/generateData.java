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
        filterDrugs();
        getATCs();


    }

    public static void getDrugs() {
        try {
            URL url = new URL("https://health-products.canada.ca/api/drug/drugproduct/?lang=en&type=json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            BufferedWriter bw = new BufferedWriter(new FileWriter("data\\drugs\\rawDrugs.json"));
            String line;

            while ((line = br.readLine()) != null) {
                bw.write(line);
            }

            bw.close();
            br.close();
        } catch (Exception e) {
            System.out.println("There was an error in getting the data from Health Canada. Error Code: " + e.getMessage());
        }
    }

    public static void filterDrugs() throws IOException {
        List<JSONObject> filteredRecords = new ArrayList<>();

        try {
            String content = new String(Files.readAllBytes(Paths.get("data\\drugs\\rawDrugs.json")));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject record = jsonArray.getJSONObject(i);
                String className = record.optString("class_name");
                if ("Human".equals(className)) {
                    filteredRecords.add(record);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("data\\drugs\\allDrugs.json"));
        filteredRecords.forEach(record -> {
            try {
                bw.write(record.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
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

    public static void DINtoATC(String DIN) {

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
