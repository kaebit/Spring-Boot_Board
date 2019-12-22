package com.kaebit.boardbackend.domain;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface BoardCommentReplyRepository extends CrudRepository<BoardCommentReply, Integer> {

    @Override
    <S extends BoardCommentReply> S save(S s);

    @Modifying
    @Transactional
    @Query(value = "UPDATE board_comment_reply SET content = :content WHERE id = :id", nativeQuery = true)
    void updatedById(Integer id, String content);
}
