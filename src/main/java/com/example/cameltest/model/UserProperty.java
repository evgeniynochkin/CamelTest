package com.example.cameltest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * Модель свойств пользователя
 *
 * @author Evgeniy Nochkin
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProperty {
    private String firstName;
    private String secondName;
    private int age;
}
