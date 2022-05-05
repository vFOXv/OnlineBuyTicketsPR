package com.example.OnlineBuyTickets.services;

import com.example.OnlineBuyTickets.models.Role;
import com.example.OnlineBuyTickets.models.User;
import com.example.OnlineBuyTickets.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
//Задача UserService по имени пользователя(username) предоставить самого пользователя
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //обертка над методом findByUsername(username) из Repository
    public User findByUserName(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    //UserDetails содержит мин необходимых данных для аунтификации пользователя
    //коллекцию прав доступа, password and username
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //получаем из DB user по имени
        User user = findByUserName(username);
        //если в DB такого user  нет кидаеться Exception
        if(user==null){
            throw new UsernameNotFoundException(String.format("User '%s' not found!", username));
        }
        //возвращаем UserDetails как SpringSecurityUser(НАШ user.getUsername(),
        // user.getPassword(), коллекция прав доступа)
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    //метод преобразующий коллекцию ролей(в нашем user)
    // в коллекцию прав доступа(Security user) для UserDetails
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }

    //получение списка users
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //сохранение изменений user
    public void saveUser(User user){
        userRepository.save(user);
    }

}
