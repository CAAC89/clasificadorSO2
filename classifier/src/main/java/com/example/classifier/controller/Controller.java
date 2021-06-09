package com.example.classifier.controller;

import com.example.classifier.consumer.Consumer;
import com.example.classifier.dto.*;
import com.example.classifier.util.DictionaryTokensUtil;
import com.example.classifier.util.NewsClassifierUtil;
import com.google.gson.*;
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
    public void sendNewsClassifier(String json) throws Exception {
        //JSON DE PRUEBA UTILIZADO
        //String json = "{\"title\":\"Salud informa que las vacunas de covid19 ya no hacen efecto\",\"dictionary\":\"{Politics: 1, Events: 4, Entertainment: 5, Sports: 0, Health: 9, Economy: 2}\"}";


		JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        @SuppressWarnings("unused")
		String title = jsonObject.get("title").getAsString();
        JsonElement dictionary = jsonObject.get("dictionary");

		JsonObject jsonDictionary = new JsonParser().parse(dictionary.getAsString()).getAsJsonObject();

        int politics = jsonDictionary.get("Politics").getAsInt();
        int events = jsonDictionary.get("Events").getAsInt();
        int entertainment = jsonDictionary.get("Entertainment").getAsInt();
        int sports = jsonDictionary.get("Sports").getAsInt();
        int health = jsonDictionary.get("Health").getAsInt();
        int economy = jsonDictionary.get("Economy").getAsInt();

        int totalNews = politics+events+entertainment+sports+health+economy;

        //Obtener de url get la cantidad de tokens
        String getDataDictionary = NewsClassifierUtil.readUrl("http://localhost:8080/getDictionaryTokens");
        JsonArray jsonDictionaryArray = new JsonParser().parse(getDataDictionary).getAsJsonArray();
        JsonObject jsonDictionaryObject = new JsonParser().parse(jsonDictionaryArray.get(0).getAsString()).getAsJsonObject();

        JsonArray tokensPolitics =jsonDictionaryObject.getAsJsonArray("politics");
        JsonArray tokensEvents =jsonDictionaryObject.getAsJsonArray("events");
        JsonArray tokensEnter =jsonDictionaryObject.getAsJsonArray("entertainment");
        JsonArray tokensHealth =jsonDictionaryObject.getAsJsonArray("health");
        JsonArray tokensSports =jsonDictionaryObject.getAsJsonArray("sports");
        JsonArray tokensEconomy =jsonDictionaryObject.getAsJsonArray("economy");


        CategoryNewsDTO categoryNewsPoliticsDTO,categoryNewsEventsDTO,categoryNewsEntertainmentDTO,categoryNewsSportsDTO,categoryNewsHealthDTO,categoryNewsEconomyDTO;

        if(politics==0){
            categoryNewsPoliticsDTO = new CategoryNewsDTO("Politics",politics,0);
        }else {
            categoryNewsPoliticsDTO = new CategoryNewsDTO("Politics",politics,tokensPolitics.size());
        }

        if(events==0){
            categoryNewsEventsDTO = new CategoryNewsDTO("Events",events,0);
        }else {
            categoryNewsEventsDTO = new CategoryNewsDTO("Events",events,tokensEvents.size());
        }

        if(health==0){
            categoryNewsHealthDTO = new CategoryNewsDTO("Health",health,0);
        }else {
            categoryNewsHealthDTO = new CategoryNewsDTO("Health",health,tokensHealth.size());
        }

        if(economy==0){
            categoryNewsEconomyDTO = new CategoryNewsDTO("Economy",economy,0);
        }else {
            categoryNewsEconomyDTO = new CategoryNewsDTO("Economy",economy,tokensEconomy.size());
        }

        if(sports==0){
            categoryNewsSportsDTO = new CategoryNewsDTO("Sports",sports,0);
        }else {
            categoryNewsSportsDTO = new CategoryNewsDTO("Sports",sports,tokensSports.size());
        }

        if(entertainment==0){
            categoryNewsEntertainmentDTO = new CategoryNewsDTO("Entertainment",entertainment,0);
        }else {
            categoryNewsEntertainmentDTO = new CategoryNewsDTO("Entertainment",entertainment,tokensEnter.size());
        }


        //Se agregan a lista a ser procesada solo casos donde los diccionarios sean distinto de 0 para cada tipo de noticia
        ArrayList<CategoryNewsDTO> categoryNewsDTOArrayList = new ArrayList<>();
        if(politics!=0){
            categoryNewsDTOArrayList.add(categoryNewsPoliticsDTO);
        }
        if(events!=0){
            categoryNewsDTOArrayList.add(categoryNewsEventsDTO);
        }
        if(entertainment!=0){
            categoryNewsDTOArrayList.add(categoryNewsEntertainmentDTO);
        }
        if(sports!=0){
            categoryNewsDTOArrayList.add(categoryNewsSportsDTO);
        }
        if(health!=0){
            categoryNewsDTOArrayList.add(categoryNewsHealthDTO);
        }
        if(economy!=0){
            categoryNewsDTOArrayList.add(categoryNewsEconomyDTO);
        }


        
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
                    classifier.getCategoryNewsDTOArrayList().get(i).setProbability(resultTotalListDTO.get(i).getCalculatePriorProb());
                }
        );

        //Responde de forma ordenada con probabilidad mas alta pero sin el dato
        template.send("newsClassifierTopic", classifier.toString());
    }

    @GetMapping("/getNewsClassifier")
    public List<String> getMessagesNewsClassifier() {
        return consumer.getMessagesNewsClassifier();
    }

}
