package com.example.classifier.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Consumer {
    private final List<String> messagesDictionaryTokens = new ArrayList<>();
    private final List<String> messagesNewsClassifier = new ArrayList<>();
    private final List<String> messagesNewsTitleUrl = new ArrayList<>();

    @KafkaListener(topics = "dictionaryTokensTopic", groupId = "kafka-sandbox-dictionary-tokens-topic")
    public void listenDictionaryToken(String message) {
        synchronized (messagesDictionaryTokens) {
            messagesDictionaryTokens.add(message);
        }
    }

    public List<String> getMessagesDictionaryTokens() {
        return messagesDictionaryTokens;
    }

    @KafkaListener(topics = "newsClassifierTopic", groupId = "kafka-sandbox-news-classifier-topic")
    public void listenNewsClassifier(String message) {
        synchronized (messagesNewsClassifier) {
            messagesNewsClassifier.add(message);
        }
    }

    public List<String> getMessagesNewsClassifier() {
        return messagesNewsClassifier;
    }


    @KafkaListener(topics = "newsTitleUrlTopic", groupId = "kafka-sandbox-news-title-url-topic")
    public void listenNewsTitleUrl(String message) {
        synchronized (messagesNewsTitleUrl) {
            messagesNewsTitleUrl.add(message);
        }
    }

    public List<String> getMessagesNewsTitleUrl() {
        return messagesNewsTitleUrl;
    }

}
