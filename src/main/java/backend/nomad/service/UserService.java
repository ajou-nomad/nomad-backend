package backend.nomad.service;

import backend.nomad.domain.User;
import backend.nomad.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(User user) {
        userRepository.save(user);
        return user.getId();
    }

    public List<User> findMembers() {
        return userRepository.findAll();
    }

    public User findOne(Long memberId) {
        return userRepository.findOne(memberId);
    }
}
