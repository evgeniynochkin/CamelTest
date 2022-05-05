package com.example.cameltest.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Сущность пользователь для работы с базой данных
 *
 * @author Evgeniy Nochkin
 */
@Entity
@Table(name = "usertabel")
@Setter
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String login;

    @Column
    private String password;
}
