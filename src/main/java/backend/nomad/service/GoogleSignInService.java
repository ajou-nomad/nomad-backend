package backend.nomad.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Service;

@Service
public class GoogleSignInService {

    public void verifyToken(String idToken) throws FirebaseAuthException {
        FirebaseToken decodedToken =
                FirebaseAuth.getInstance().verifyIdToken(idToken);
        String uid = decodedToken.getUid();
        System.out.println(uid);
    }
}