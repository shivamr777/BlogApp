package com.Blogger.Service;

import com.Blogger.Entity.Comment;
import com.Blogger.Entity.Post;
import com.Blogger.Exception.ResourceNotFoundException;
import com.Blogger.Payload.CommentDto;
import com.Blogger.Repository.CommentRepository;
import com.Blogger.Repository.PostRepository;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentrepo;
    @Autowired
    PostRepository postrepo;

    @Transactional
    @Override
    public void DeleteComment(long postId) {
        List<Comment> allByPostId = commentrepo.findAllByPostId(postId);
        if(allByPostId.isEmpty())
        {
            throw new ResourceNotFoundException("Post ID not found");
        }
        else
        {
            commentrepo.deleteCommentByPostId(postId);
        }

    }

    @Override
    public CommentDto createComment(long postId, CommentDto Dto) {
        Post post = postrepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post ID not Found " + postId)
        );
        Comment c=new Comment();
        c.setId(Dto.getId());
        c.setBody(Dto.getBody());
        c.setName(Dto.getName());
        c.setEmail(Dto.getEmail());
        c.setPost(post);

        Comment savecomment = commentrepo.save(c);
        CommentDto dto=new CommentDto();
        dto.setBody(savecomment.getBody());
        dto.setId(savecomment.getId());
        dto.setName(savecomment.getName());
        dto.setEmail(savecomment.getEmail());


        return dto;
    }

    @Override
    public List<CommentDto> getAllCommentByPostId(long postId) {
        List<Comment> commentlist = commentrepo.findAllByPostId(postId);
        List<CommentDto> commentDtos = commentlist.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public List<CommentDto> getAllComment() {
        List<Comment> commentlist= commentrepo.findAll();
        List<CommentDto> collect = commentlist.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return collect;
    }

    public CommentDto mapToDto(Comment c)
    {
        CommentDto dto=new CommentDto();
        dto.setEmail(c.getEmail());
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setBody(c.getBody());

        return dto;
    }


}
