package backend.nomad.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Service
public class FirebaseInit {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    public void initialize() throws IOException {
        try {
            FileInputStream refreshToken = new FileInputStream("\\rn-fooddeliveryapp-c2ae6-firebase-adminsdk-4w1d8-c6bd8b4f1b.json");
//            ClassPathResource Path = new ClassPathResource("rn-fooddeliveryapp-c2ae6-firebase-adminsdk-4w1d8-c6bd8b4f1b.json");
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .build();

            FirebaseApp.initializeApp(options);
            logger.info("Firebase application has been initialized");
        } catch (Exception e) {
            logger.info(e.getMessage());
            logger.info("init fail");
        }
    }

}