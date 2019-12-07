package com.kaebit.boardbackend.Repository;

import com.kaebit.boardbackend.Model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    List<User> findAll();

    @Query(value = "SELECT * FROM user WHERE id = :id", nativeQuery = true)
    Optional<User> findById(String id);

    @Query(value = "SELECT * FROM user WHERE user_id = :user_id", nativeQuery = true)
    User findByUser_id(String user_id);

    @Modifying
    @Transactional
    @Query("DELETE FROM User user WHERE user.id = :id")
    void deleteById(String id);

    @Modifying
    @Transactional
    @Query("UPDATE User user SET user.user_id = :user_id WHERE user.id = :id")
    void updateUser_idById(String id, String user_id);

    @Modifying
    @Transactional
    @Query("UPDATE User user SET user.password = :password WHERE user.id = :id")
    void updatePasswordById(String id, String password);

    @Modifying
    @Transactional
    @Query("UPDATE User user SET user.name = :name WHERE user.id = :id")
    void updateNameById(String id, String name);

    @Override
    <S extends User> S save(S s);
}