package com.Blogger.Controller;


import com.Blogger.Entity.Post;
import com.Blogger.Payload.CommentDto;
import com.Blogger.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Blog7/Comment/Api")
public class CommentController {

    @Autowired
    CommentService commentService;
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestParam(name="post_id") long post_id, @RequestBody CommentDto Dto)
    {
        CommentDto commentDto1 = commentService.createComment(post_id, Dto);

        return  new ResponseEntity<>(commentDto1, HttpStatus.CREATED);
    }

    @DeleteMapping("/{postId}")
    public  ResponseEntity<String> DeleteCommentByPostId(@PathVariable long postId)
    {
        commentService.DeleteComment(postId);
        return new ResponseEntity<>("Deleted Sucessfully",HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComment()
    {
          List<CommentDto> DtoList=commentService.getAllComment();
          return  new ResponseEntity<>(DtoList,HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getByPostId(@PathVariable long postId)
    {
        List<CommentDto> dtoList= commentService.getAllCommentByPostId(postId);
        return new ResponseEntity<>(dtoList,HttpStatus.OK);
    }
}
