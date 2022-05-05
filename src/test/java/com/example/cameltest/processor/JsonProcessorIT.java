package com.example.cameltest.processor;

import com.example.cameltest.AbstractIntegrationTest;
import com.example.cameltest.model.User;
import com.example.cameltest.repository.entity.UserEntity;
import com.example.cameltest.service.UserService;
import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тест записи объекта {@link User} в базу данных
 *
 * @author Evgeniy Nochkin
 */
class JsonProcessorIT extends AbstractIntegrationTest {

    @Autowired
    ProducerTemplate producerTemplate;

    @Autowired
    UserService userService;

    /**
     * Тест записи в базу данных с использованием тестконтейнера объекта {@link User} заданного в теле теста
     */
    @Test
    @Transactional
    void addEntityInDB() {
        User user = new User();
        user.setLogin("TestLogin1");
        user.setPassword("TestPassword1");
        user.setUserProperty(null);

        userService.addUser(user);

        UserEntity userEntity = userService.getById(1L);

        assertEquals(userEntity.getId(), 1L);
        assertEquals(userEntity.getLogin(), "TestLogin1 in DB");
        assertEquals(userEntity.getPassword(), "TestPassword1PASS");
    }

    /**
     * Тест записи в базу данных с использованием тестконтейнера объекта {@link User}
     * полученного с использованием платформы Camel Apache.
     * Сообщение эмулировано текстовым файлом содержащим данные в формате Json.
     */
    @Test
    @Transactional
    void jsonProcessorTest() {
        String json = getJsonWithObjects();
        producerTemplate.sendBody("direct:test", json);

        UserEntity userEntity = userService.getById(1L);

        assertEquals(userEntity.getId(), 1L);
        assertEquals(userEntity.getLogin(), "JsonLogin1 in DB");
        assertEquals(userEntity.getPassword(), "JsonPassword1PASS");
    }

    /**
     * Чтение тестового JSON-файла
     * @return содержимое тестового файла в String-формате
     */
    String getJsonWithObjects() {
        Path path = Paths.get("src\\test\\resources\\JSONForTest.txt");

        StringBuilder sb = new StringBuilder();

        try (Stream stream = Files.lines(path)) {
            stream.forEach(s -> sb.append(s).append("\n"));
        } catch (IOException e) {
        }

        return sb.toString();
    }
}