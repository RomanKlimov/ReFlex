package ru.itis.reflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.reflex.models.Key;
import ru.itis.reflex.models.User;

import java.util.List;

public interface KeyRepository extends JpaRepository<Key, Integer> {

    @Query(value = "SELECT * FROM key", nativeQuery = true)
    List<Key> findKeys();

    List<Key> getAllByHead(User user);

    Key getByEmail(String email);
    Key getByValue(String value);


}
