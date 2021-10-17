package in.codersage.securitydemo.service;

import in.codersage.securitydemo.model.User;
import in.codersage.securitydemo.repository.RoleRepository;
import in.codersage.securitydemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    BCryptPasswordEncoder bCrypt;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void save(User user) {
        user.setPassword(bCrypt.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findUserByName(String username) {
        return userRepository.findUserByUsername(username);
    }
}
