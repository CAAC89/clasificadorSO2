package com.example.classifier.controller;

import com.example.classifier.consumer.Consumer;
import com.example.classifier.dto.CategoryNewsDTO;
import com.example.classifier.dto.ClassifierDTO;
import com.example.classifier.dto.OperationDTO;
import com.example.classifier.dto.ResultDTO;
import com.example.classifier.util.DictionaryTokensUtil;
import com.example.classifier.util.NewsClassifierUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RestController
public class Controller {

    private KafkaTemplate<String, String> template;
    private Consumer consumer;

    public Controller(KafkaTemplate<String, String> template, Consumer consumer) {
        this.template = template;
        this.consumer = consumer;
    }

    @PostMapping("/news/process")
    public void newsProcess(@RequestParam String url, @RequestParam String portal) {
        String json =  "{\n" +
                "\t\t\t\"news_url\": \""+url+"\",\n" +
                "\t\t}";
        switch (portal) {
            case "teletica":
                template.send("newsTeletica", json);
                break;
            case "repretel":
                template.send("newsrepretel", json);
                break;
            case "crhoy":
                template.send("newscrhoy", json);
                break;
            case "diarioextra":
                template.send("newsdiarioextra", json);
                break;
        }

    }

    @PostMapping("/sendDictionaryTokens")
    public void sendDictionaryTokens() {
        template.send("dictionaryTokensTopic", DictionaryTokensUtil.getDictionaryTokens().toString());
    }

    @GetMapping("/getDictionaryTokens")
    public List<String> getMessagesDictionaryTokens() {
        return consumer.getMessagesDictionaryTokens();
    }

    @PostMapping("/sendNewsClassifier")
    public void sendNewsClassifier() throws Exception {
        String json = "{\"title\":\"Salud informa que las vacunas de covid19 ya no hacen efecto\",\"dictionary\":\"{Politics: 1, Events: 4, Entertainment: 5, Sports: 0, Health: 9, Economy: 2}\"}";

        @SuppressWarnings("deprecation")
		JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        @SuppressWarnings("unused")
		String title = jsonObject.get("title").getAsString();
        JsonElement dictionary = jsonObject.get("dictionary");
        @SuppressWarnings("deprecation")
		JsonObject jsonDictionary = new JsonParser().parse(dictionary.getAsString()).getAsJsonObject();

        int politics = jsonDictionary.get("Politics").getAsInt();
        int events = jsonDictionary.get("Events").getAsInt();
        int entertainment = jsonDictionary.get("Entertainment").getAsInt();
        int sports = jsonDictionary.get("Sports").getAsInt();
        int health = jsonDictionary.get("Health").getAsInt();
        int economy = jsonDictionary.get("Economy").getAsInt();

        int totalNews = politics+events+entertainment+sports+health+economy;

        CategoryNewsDTO categoryNewsPoliticsDTO = new CategoryNewsDTO("Politics",politics,1);
        CategoryNewsDTO categoryNewsEventsDTO = new CategoryNewsDTO("Events",events,1);
        CategoryNewsDTO categoryNewsEntertainmentDTO = new CategoryNewsDTO("Entertainment",entertainment,100);
        CategoryNewsDTO categoryNewsSportsDTO = new CategoryNewsDTO("Sports",sports,1);
        CategoryNewsDTO categoryNewsHealthDTO = new CategoryNewsDTO("Health",health,1);
        CategoryNewsDTO categoryNewsEconomyDTO = new CategoryNewsDTO("Economy",economy,1);

        
        ArrayList<CategoryNewsDTO> categoryNewsDTOArrayList = new ArrayList<>();
        categoryNewsDTOArrayList.add(categoryNewsPoliticsDTO);
        categoryNewsDTOArrayList.add(categoryNewsEventsDTO);
        categoryNewsDTOArrayList.add(categoryNewsEntertainmentDTO);
        categoryNewsDTOArrayList.add(categoryNewsSportsDTO);
        categoryNewsDTOArrayList.add(categoryNewsHealthDTO);
        categoryNewsDTOArrayList.add(categoryNewsEconomyDTO);
        
        ClassifierDTO classifier = new ClassifierDTO();
        classifier.setTotalNews(totalNews);
        classifier.setCategoryNewsDTOArrayList(categoryNewsDTOArrayList);
        
        
        List<ResultDTO> resultTotalListDTO = new ArrayList<>();
        
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

        

        template.send("newsClassifierTopic", classifier.toString());
    }

    @GetMapping("/getNewsClassifier")
    public List<String> getMessagesNewsClassifier() {
        return consumer.getMessagesNewsClassifier();
    }

}
