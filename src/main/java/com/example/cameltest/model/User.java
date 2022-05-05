package com.example.cameltest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * Модель пользователя
 *
 * @author Evgeniy Nochkin
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String login;
    private String password;
    private UserProperty userProperty;
}
