package com.example.demo.rest;

import com.example.demo.kafta.MyTopicConsumer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class KafkaController {
    private KafkaTemplate<String, String> template;
    private MyTopicConsumer myTopicConsumer;

    public KafkaController(KafkaTemplate<String, String> template, MyTopicConsumer myTopicConsumer) {
        this.template = template;
        this.myTopicConsumer = myTopicConsumer;
    }



    @PostMapping("/news/process")
    public void produce(@RequestParam String url, @RequestParam String portal) {
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

    @GetMapping("/kafka/produce")
    public void produce(@RequestParam String message) {
        template.send("myTopic", message);
    }

    @GetMapping("/kafka/messages")
    public List<String> getMessages() {
        return myTopicConsumer.getMessages();
    }


}
