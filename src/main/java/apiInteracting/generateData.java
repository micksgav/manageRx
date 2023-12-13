package apiInteracting;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.*;

import java.util.ArrayList;

public class generateData {

    public static ArrayList<String> prescriptionDrugs = new ArrayList<String>();


    static int n = 0;
    static String[][] drugs;

    public static void main(String[] args) throws IOException, InterruptedException {

        generatePrescriptionList();
        getDrugs();
    }

    public static void getDrugs() throws IOException {

        //get drugs JSON
        String result = "";
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
                result = result + line;
            }

            br.close();
        } catch (Exception e) {
            System.out.println("There was an error in getting the data from Health Canada. Error Code: " + e.getMessage());

        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("data\\drugs\\drugData.txt"));
        try {
            JSONArray jsonArray = new JSONArray(result);

            int j = 0;
            drugs = new String[jsonArray.length()][5];
            for (int i = 0; i < jsonArray.length(); i++) {
                System.out.println(i + " / " + jsonArray.length());
                JSONObject record = jsonArray.getJSONObject(i);
                String className = record.optString("class_name");
                String drugCode = record.optString("drug_code");
                String DIN = record.optString("drug_identification_number");
                String name = record.optString("brand_name");
                String company = record.optString("company_name");
                if ("Human".equals(className)) {
                    if (check(drugCode)) {
                        j++;
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

        String RXCUI = "";
        BufferedWriter errors = new BufferedWriter(new FileWriter("data\\drugs\\errors.txt"));
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
                result = result + line;
            }


            br.close();
            result = result.substring(1, result.length() - 1);

            JSONObject record = new JSONObject(result);
            String atc = record.optString("tc_atc_number");

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
                result2 = result2 + line2;
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
            errors.write("DC" + drugCode + " " + e.getMessage() + "\n");
            n++;
            if (n % 10 == 0) {
                errors.close();
                errors = new BufferedWriter(new FileWriter("data\\drugs\\errors.txt"));
            }
        }
        errors.close();
        return RXCUI;

    }

    private static void scraper() throws InterruptedException, IOException {
        // TODO Auto-generated method stub
        BufferedWriter writer;
        String drugID = "";
        String DIN = "";
        URL url;
        BufferedReader br;
        String result;

        for (int i = 0; i < drugs.length; i++) {
            Thread.sleep(50);
            drugID = drugs[i][2];
            DIN = drugs[i][0];

            if (drugs[i][2].compareTo("") != 0) {

                writer = new BufferedWriter(new FileWriter("data\\drugs\\drugInteractions\\" + DIN + ".xml"));

                url = new URL("https://rxnav.nlm.nih.gov/REST/interaction/interaction?rxcui=" + drugID);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/xml");

                br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                while ((result = br.readLine()) != null) {
                    writer.write(result + "\n");
                }
                writer.close();
                System.out.println("Data for " + drugs[i][1] + " has been scraped.");
            }
        }

    }


    public static boolean check(String drugCode) throws IOException {
        for (String prescriptionDrug : prescriptionDrugs) {
            if (drugCode.equals(prescriptionDrug)) {
                return true;
            }

        }
        return false;
    }
    public static void generatePrescriptionList() {
        String result = "";
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
                result = result + line;
            }

            br.close();
        } catch (Exception e) {
            System.out.println("There was an error in getting the data from Health Canada. Error Code: " + e.getMessage());

        }

        try {
            JSONArray jsonArray = new JSONArray(result);

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

}






