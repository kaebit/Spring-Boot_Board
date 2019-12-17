package com.kaebit.boardbackend.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardCommentReplyRepository extends CrudRepository<BoardCommentReply, Integer> {

    @Override
    <S extends BoardCommentReply> S save(S s);
}
