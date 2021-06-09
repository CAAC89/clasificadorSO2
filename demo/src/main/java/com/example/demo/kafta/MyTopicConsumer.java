package com.example.demo.kafta;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyTopicConsumer {
    private final List<String> messages = new ArrayList<>();

    @KafkaListener(topics = "myTopic", groupId = "kafka-sandbox")
    public void listen(String message) {
        JsonObject jsonObject = new JsonParser().parse(message).getAsJsonObject();
        String title = jsonObject.get("title").getAsString();
        JsonObject keyWordsList = jsonObject.get("keyWordsList").getAsJsonObject();
        int politics = keyWordsList.get("Politics").getAsInt();
        int Events = keyWordsList.get("Events").getAsInt();
        int Entertainment = keyWordsList.get("Entertainment").getAsInt();
        int Sports = keyWordsList.get("Sports").getAsInt();
        int Health = keyWordsList.get("Health").getAsInt();
        int Economy = keyWordsList.get("Economy").getAsInt();
    }

    public List<String> getMessages() {
        return messages;
    }
}
