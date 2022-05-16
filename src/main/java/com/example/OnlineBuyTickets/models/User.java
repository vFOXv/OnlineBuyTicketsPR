package com.example.OnlineBuyTickets.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    //Обязательно Username это имя юзает Spring Security
    @Size(min = 4, message = "Name min 4 char!!!")
    @NotEmpty(message = "String must not be empty!!!")
    private String username;
    @Size(min = 4, message = "Password min 4 char!!!")
    @NotEmpty(message = "String must not be empty!!!")
    private String password;

    //поле для проверки правильности ввода пароля при регистрации
    //@Transient анатация указывает, что поле не присутствует в DB
    @Transient
    @Size(min = 4, message = "Password min 4 char!!!")
    //если поставить анотацию - при изменении объекта падает с ошибкой(пустое поле) - а этого поля в DB нет
    //@NotEmpty(message = "String must not be empty!!!")
    private String passwordConfirm;
    @Email(message = "The field must be in the format of an email address!!!")
    @NotEmpty(message = "String must not be empty!!!")
    private String email;

    private Boolean active;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="users_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Collection<Role> roles;
}
