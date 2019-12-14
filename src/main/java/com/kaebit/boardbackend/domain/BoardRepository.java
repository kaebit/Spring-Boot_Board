package com.kaebit.boardbackend.domain;

import com.kaebit.boardbackend.domain.Board;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends CrudRepository<Board, Integer> {

    List<Board> findAll();

    @Query(value = "SELECT * from board WHERE id = :id", nativeQuery = true)
    Optional<Board> findById(Integer id);

    @Query(value = "SELECT * FROM board WHERE user_id = :user_id", nativeQuery = true)
    List<Board> findByUserId(String user_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM board WHERE id = :id", nativeQuery = true)
    void deleteById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE board SET title = :title, content = :content WHERE id = :id", nativeQuery = true)
    void updateById(Integer id, String title, String content);

    @Override
    <S extends Board> S save(S s);
}

