package com.example.cameltest.service;

import com.example.cameltest.model.User;
import com.example.cameltest.repository.UserRepository;
import com.example.cameltest.repository.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сервис взаимодействия объекта {@link User} с базой данных
 *
 * @author Evgeniy Nochkin
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void addUser(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(user.getLogin() + " in DB");
        userEntity.setPassword(user.getPassword() + "PASS");
        userRepository.save(userEntity);
    }

    @Transactional
    public UserEntity getById(long id) {
        return userRepository.getById(id);
    }
}
