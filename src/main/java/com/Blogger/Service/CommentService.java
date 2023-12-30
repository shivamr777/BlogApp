package com.Blogger.Service;

import com.Blogger.Payload.CommentDto;

import java.util.List;


public interface CommentService {

    void DeleteComment(long id);

    CommentDto createComment(long postId, CommentDto Dto);


    List<CommentDto> getAllCommentByPostId(long postId);

    List<CommentDto> getAllComment();
}
