package com.example.classifier.util;

import com.example.classifier.dto.ClassifierDTO;
import com.example.classifier.dto.ResultDTO;
import java.io.*;
import java.net.URL;
import java.util.Comparator;
import java.util.List;

public class NewsClassifierUtil {

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

}
