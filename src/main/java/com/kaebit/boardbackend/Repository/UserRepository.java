package com.kaebit.boardbackend.Repository;

import com.kaebit.boardbackend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findAll();

    @Query("SELECT user FROM User user WHERE user.pk = pk")
    User findByPk(String pk);

    @Query("DELETE FROM User user WHERE user.pk = pk")
    void deleteByPk(String pk);

    @Query("UPDATE User user SET user = user WHERE user.pk = pk")
    User updateByPk(String pk, User user);
}
