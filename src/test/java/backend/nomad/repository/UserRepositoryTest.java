package backend.nomad.repository;

import backend.nomad.domain.User;
import backend.nomad.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired UserRepository userRepository;
    @Autowired UserService userService;


    @Test
    public void a() throws Exception {
        //given
        User user = new User();
        user.setNickname("leesk");

        //when
        Long saveId = userService.join(user);

        //then
        assertEquals(user, userRepository.findOne(saveId));
    }

}