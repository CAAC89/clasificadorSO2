package com.example.demo.rest;

import com.example.demo.dto.ClassifierDTO;
import com.example.demo.dto.OperationDTO;
import com.example.demo.dto.ResultDTO;
import com.example.demo.utils.NewsClassifierUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


@RestController
@RequestMapping("/response")
public class NewsClassifier {
   /* @PostMapping(
            value = "/newsClassifier",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ClassifierDTO> postBody(@RequestBody ClassifierDTO classifier) throws Exception {
        int sumClassifiers = NewsClassifierUtil.sumClassifier(classifier);

        boolean validate = NewsClassifierUtil.equalTotalNewsToSumClassifier(classifier.getTotalNews(), sumClassifiers);

        List<ResultDTO> resultTotalListDTO = new ArrayList<>();

        if(!validate){
            throw new Exception();
        }else{
            IntStream.range(0, classifier.getCategoryNewsDTOArrayList().size()).parallel().forEach(
                    i -> {
                        ResultDTO resultTempTotalDTO = new ResultDTO();
                        resultTempTotalDTO.setCategoryNews(classifier.getCategoryNewsDTOArrayList().get(i).getCategoryNews());
                        OperationDTO operationDividedADTO = new OperationDTO(classifier.getCategoryNewsDTOArrayList().get(i).getClassifiers()
                                ,classifier.getTotalNews());
                        OperationDTO operationDividedBDTO = new OperationDTO(classifier.getCategoryNewsDTOArrayList().get(i).getTokens()
                                ,classifier.getCategoryNewsDTOArrayList().get(i).getClassifiers());

                        OperationDTO operationMultiplyDTO = new OperationDTO(operationDividedADTO.getDivide(),operationDividedBDTO.getDivide());

                        resultTempTotalDTO.setCalculatePriorProb(operationMultiplyDTO.getMultiply());
                        resultTotalListDTO.add(resultTempTotalDTO);

                    }
            );

            IntStream.range(0, classifier.getCategoryNewsDTOArrayList().size()).parallel().forEach(
                    i -> {
                        if(NewsClassifierUtil.getMaxValueByResultDTOList(resultTotalListDTO).getCategoryNews().equals(classifier.getCategoryNewsDTOArrayList().get(i).getCategoryNews())){
                            classifier.setTotalNews(classifier.getTotalNews()+1);
                            classifier.getCategoryNewsDTOArrayList().get(i).setCategoryNews(classifier.getCategoryNewsDTOArrayList().get(i).getCategoryNews());
                            classifier.getCategoryNewsDTOArrayList().get(i).setClassifiers(classifier.getCategoryNewsDTOArrayList().get(i).getClassifiers()+1);
                        }else{
                            classifier.getCategoryNewsDTOArrayList().get(i).setCategoryNews(classifier.getCategoryNewsDTOArrayList().get(i).getCategoryNews());
                            classifier.getCategoryNewsDTOArrayList().get(i).setClassifiers(classifier.getCategoryNewsDTOArrayList().get(i).getClassifiers());
                        }
                        classifier.getCategoryNewsDTOArrayList().get(i).setTokens(classifier.getCategoryNewsDTOArrayList().get(i).getTokens());

                    }
            );
        }
        return ResponseEntity.ok(classifier);
    }*/

    @PostMapping(
            value = "/newsClassifier",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ClassifierDTO> postBody(String title, int numTokens) throws Exception {

        return ResponseEntity.ok(null);
    }
}
