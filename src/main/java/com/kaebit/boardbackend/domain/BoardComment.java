package com.kaebit.boardbackend.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "boardComment")
@Data
@NoArgsConstructor
public class BoardComment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "board_id")
    private Integer board_id;

    @Column(name = "author")
    private String author;

    @Column(name = "content")
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public BoardComment(String user_id, Integer board_id, String author, String content) {
        this.user_id = user_id;
        this.board_id = board_id;
        this.author = author;
        this.content = content;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "boardComment_id")
    private Collection<BoardCommentReply> boardCommentReplies;
}
