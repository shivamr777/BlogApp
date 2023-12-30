package com.Blogger.Service;

import com.Blogger.Payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto dto);

    void deletePost(long id);

    List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto updatePost(long id, PostDto dto);
}
