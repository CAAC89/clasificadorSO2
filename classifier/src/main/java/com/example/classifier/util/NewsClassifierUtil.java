package com.example.classifier.util;

import com.example.classifier.dto.CategoryNewsDTO;
import com.example.classifier.dto.ClassifierDTO;
import com.example.classifier.dto.ResultDTO;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;

public class NewsClassifierUtil {
    private static final String USER_AGENT = "Mozilla/5.0";

    public static int sumClassifier(ClassifierDTO classifier){
        int sumClassifiers = 0;
        for(int i=0;i<classifier.getCategoryNewsDTOArrayList().size();i++){
            sumClassifiers = sumClassifiers + classifier.getCategoryNewsDTOArrayList().get(i).getClassifiers();
        }
        return sumClassifiers;

    }

    public static boolean equalTotalNewsToSumClassifier(int totalNews, int sumClassifier){
        if(totalNews ==  sumClassifier){
            return true;
        }
        return false;
    }

    public static ResultDTO getMaxValueByResultDTOList(List<ResultDTO> resultTotalListDTO){
        return resultTotalListDTO.stream().max(Comparator.comparing(v -> v.getCalculatePriorProb())).get();
    }



    public static CategoryNewsDTO getMaxValueByCategoryNewsDTOList(List<CategoryNewsDTO> resultTotalListDTO){
        return resultTotalListDTO.stream().max(Comparator.comparing(v -> v.getProbability())).get();
    }

    public static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

    public static void sendingPostRequest(String urlData,String json) throws Exception {

        URL url = new URL(urlData);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");


        byte[] out = json.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }


}
