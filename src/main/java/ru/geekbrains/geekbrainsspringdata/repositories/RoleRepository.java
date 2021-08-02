package ru.geekbrains.geekbrainsspringdata.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.geekbrainsspringdata.model.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

}
