package com.kaebit.boardbackend.domain;

import com.kaebit.boardbackend.domain.BoardComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardCommentRepository extends CrudRepository<BoardComment, Integer> {

    @Override
    <S extends BoardComment> S save(S s);
}
