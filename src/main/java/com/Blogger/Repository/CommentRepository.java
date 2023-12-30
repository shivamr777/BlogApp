package com.Blogger.Repository;

import com.Blogger.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    void deleteCommentByPostId(long postId);
    List<Comment> findAllByPostId(long postId);
    Comment findByPostId(long postId);
}
