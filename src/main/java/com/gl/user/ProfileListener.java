package com.gl.user;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProfileListener {

    private static final String LISTENER_TOPIC_NAME = "reservation_topic";

    @Autowired
    private ProfileRepository repository;

    @KafkaListener(topics = LISTENER_TOPIC_NAME, groupId = "profile")
    @SendTo
    public Message<String> consume(String userId) {
        log.info(String.format("Message received -> %s", userId));
        UserProfile profile = getUserProfile(userId);

        JSONObject profileResponse = new JSONObject(profile);

        return MessageBuilder.withPayload(profileResponse.toString())
                .build();
    }

    private UserProfile getUserProfile(String userId) {
        Optional<UserProfile> userProfile = repository.findById(userId);
        if (userProfile.isPresent()) {
            return userProfile.get();
        }
        return new UserProfile();
    }
}
