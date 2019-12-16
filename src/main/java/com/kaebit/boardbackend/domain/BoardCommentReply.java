package com.kaebit.boardbackend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "boardCommentReply")
public class BoardCommentReply {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "board_id")
    private Integer board_id;

    @Column(name = "boardComment_id")
    private Integer boardComment_id;

    @Column(name = "author")
    private String author;

    @Column(name = "content")
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public BoardCommentReply(String user_id, Integer board_id, Integer boardComment_id, String author, String content) {
        this.user_id = user_id;
        this.board_id = board_id;
        this.boardComment_id = boardComment_id;
        this.author = author;
        this.content = content;
    }
}
