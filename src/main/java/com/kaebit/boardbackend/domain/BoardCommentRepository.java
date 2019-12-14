package com.kaebit.boardbackend.domain;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface BoardCommentRepository extends CrudRepository<BoardComment, Integer> {

    @Override
    Optional<BoardComment> findById(Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE board_comment SET content = :content WHERE id = :id", nativeQuery = true)
    void updateById(Integer id, String content);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM board_comment WHERE id = :id", nativeQuery = true)
    void deleteById(Integer id);

    @Override
    <S extends BoardComment> S save(S s);
}
