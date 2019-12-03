package ru.kirill.weatherinformer.repository;

import ru.kirill.weatherinformer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

// Use just get and save method. Delete entity proceed in data base automatically.
@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, UUID> {

    @Override
    @Transactional
    User save(User user);
}
