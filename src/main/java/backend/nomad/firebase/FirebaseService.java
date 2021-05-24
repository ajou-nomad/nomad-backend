package backend.nomad.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Service
public class FirebaseService {

//    @Value("${project.properties.firebase-create-scoped}")
//    String fireBaseCreateScoped;

    private FirebaseMessaging instance;

    public void sendTargetMessage(String targetToken, String title, String body) throws FirebaseMessagingException {
        this.sendTargetMessage(targetToken, title, body, null);
    }
    public void sendTargetMessage(String targetToken, String title, String body, String image) throws FirebaseMessagingException {
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .setImage(image)
                .build();
        Message msg = Message.builder()
                .setToken(targetToken)
                .setNotification(notification)
                .build();
        sendMessage(msg);
    }

    public String sendMessage(Message message) throws FirebaseMessagingException {
        return this.instance.send(message);
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    public void initialize() throws IOException, FirebaseAuthException {
        try {
            FileInputStream refreshToken = new FileInputStream("/rn-fooddeliveryapp-c2ae6-firebase-adminsdk-4w1d8-c6bd8b4f1b.json");
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .build();

            FirebaseApp app = FirebaseApp.initializeApp(options);

            this.instance = FirebaseMessaging.getInstance(app);
            logger.info("Firebase application has been initialized");
        } catch (Exception e) {
            logger.info(e.getMessage());
            logger.info("init fail");
        }
    }

}