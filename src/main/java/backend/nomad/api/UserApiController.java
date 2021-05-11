package backend.nomad.api;

import backend.nomad.domain.User;
import backend.nomad.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public CreateUserResponse savaUser(@RequestBody @Valid CreateUserRequest request) {
        User user = new User();
//        user.setNickName(request.getNickName());
//        user.setNickname(request.getNickname());
//        user.setUserEmail(request.userEmail);
//        user.setPhoneNumber(request.phoneNumber);
//        user.setCreated(request.created);
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        String f = user.getFirstname();
        String l = user.getLastname();
        Long id = userService.join(user);

        return new CreateUserResponse(id, f, l);

//        return new CreateUserResponse(id);
    }
//    @PostMapping("/user")
//    public CreateUser x(@RequestBody @Valid CreateUserRequest request) {
//        User user = new User();
//        user.setFirstname(request.firstname);
//        user.setLastname(request.lastname);
//
//        Long id = userService.join(user);
//        return new CreateUser(id);
//    }
//
//    @Data
//    static class CreateUser {
//        private Long id;
//
//        public CreateUser(Long id) {
//            this.id = id;
//        }
//    }
//    @Data
//    static class CreateUserRequest {
//        private Long id;
//        private String firstname;
//        private String lastname;
//    }


    @Data
    static class CreateUserRequest {
//        private Long id;
//        private String nickName;
//        private String userEmail;
//        private String phoneNumber;
//        private Date created;
//        private String token;
//        private Long Point;
        private String firstname;
        private String lastname;
    }
//
    @Data
    static class CreateUserResponse {
        private Long id;
        private String firstname;
        private String lastname;


        public CreateUserResponse(Long id, String firstname, String lastname) {
            this.id = id;
            this.firstname = firstname;
            this.lastname = lastname;

        }
//
//
    }
}
