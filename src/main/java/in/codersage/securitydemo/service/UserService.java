package in.codersage.securitydemo.service;

import in.codersage.securitydemo.model.User;


public interface UserService {
    void save(User user);
    User findUserByName(String username);
}
