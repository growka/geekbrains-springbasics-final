package ru.geekbrains.geekbrainsspringdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.geekbrainsspringdata.model.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);

}
