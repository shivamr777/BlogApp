package com.Blogger.Controller;

import com.Blogger.Entity.Post;
import com.Blogger.Payload.PostDto;
import com.Blogger.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Blog7/Api")
public class PostController {

    @Autowired
    PostService service;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto Dto)
    {
        PostDto dto=service.createPost(Dto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id)
    {
       service.deletePost(id);
       return new ResponseEntity<>("Deleted sucesfully",HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public  ResponseEntity<List<PostDto>> getAllPost(
            @RequestParam(name="pageNo",defaultValue ="0",required = false) int pageNo,
            @RequestParam(name="pageSize",defaultValue ="10",required = false) int pageSize,
            @RequestParam(name="sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(name="sortDir",defaultValue = "id",required = false) String sortDir
    ) {

        List<PostDto> list=service.getAllPost(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PutMapping("/{id}")
     public ResponseEntity<PostDto> updatePost(@PathVariable long id,@RequestBody PostDto Dto)
     {
         PostDto dto1=service.updatePost(id,Dto);
         return new ResponseEntity<>(dto1,HttpStatus.OK);
     }
}
