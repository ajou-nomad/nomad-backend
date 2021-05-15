package backend.nomad.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
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
    public void initialize() throws IOException, FirebaseAuthException {
        try {
            FileInputStream refreshToken = new FileInputStream("\\rn-fooddeliveryapp-c2ae6-firebase-adminsdk-4w1d8-c6bd8b4f1b.json");
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .build();

            FirebaseApp.initializeApp(options);
            logger.info("Firebase application has been initialized");
        } catch (Exception e) {
            logger.info(e.getMessage());
            logger.info("init fail");
        }

        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken("eyJhbGciOiJSUzI1NiIsImtpZCI6IjUzNmRhZWFiZjhkZDY1ZDRkZTIxZTgyNGI4OTlhMWYzZGEyZjg5NTgiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoi7JWE7KO87JWE7KO87J2066aEIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS9hL0FBVFhBSnllUE04XzVEdHRSdDNrWDctMWlNcWhueXlHNzRYeGpMaE5kWmNNPXM5Ni1jIiwiaXNzIjoiaHR0cHM6Ly9zZWN1cmV0b2tlbi5nb29nbGUuY29tL3JuLWZvb2RkZWxpdmVyeWFwcC1jMmFlNiIsImF1ZCI6InJuLWZvb2RkZWxpdmVyeWFwcC1jMmFlNiIsImF1dGhfdGltZSI6MTYyMTA2NDgyOSwidXNlcl9pZCI6IjhNYWVwc0Z0NjdTcnNzWlgxenhBOHM5NlMwazEiLCJzdWIiOiI4TWFlcHNGdDY3U3Jzc1pYMXp4QThzOTZTMGsxIiwiaWF0IjoxNjIxMDY0ODI5LCJleHAiOjE2MjEwNjg0MjksImVtYWlsIjoiYWpvdTIwMjEyMDIxQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7Imdvb2dsZS5jb20iOlsiMTEzMjkyMzYxMTc1NzEzMjc5ODI5Il0sImVtYWlsIjpbImFqb3UyMDIxMjAyMUBnbWFpbC5jb20iXX0sInNpZ25faW5fcHJvdmlkZXIiOiJnb29nbGUuY29tIn19.B3mIAyESbpfY_Yek2bLoUBu3FDyJUXVIhJq4wGj0l6eczKVh4PKk7hOOVOW5Gx3e1nZo_wd9gCyBeO8zbqcZ_We2_eN7FBZtKZbswOB1bvY5v54QP0f3qcOWzYElyvCvCE_lhbHUE7YghcT8xkpOkLCjFc900SB5jCyOLArFXEyHPrIhQ1a7q9PPXfR6D5wijHIg-VTVhsT2CZLPIjZ7ui1HF_N0jqy9GfAid0xAPMr7T-kbuHHQ4Hiqu-BN7wXm4hh5EYkVEJ80cA7XUlHxyDhX6jqUF8F9pKRiOXcsQBRTQQAD39WzOocG9Sk08qE-2vKkJo8vbmdIJPa7CNla_w");
        String uid = decodedToken.getUid();
        System.out.println(uid);
    }

}