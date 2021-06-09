package com.example.classifier.util;



import com.example.classifier.dto.ClassifierDTO;
import com.example.classifier.dto.ResultDTO;

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
}
