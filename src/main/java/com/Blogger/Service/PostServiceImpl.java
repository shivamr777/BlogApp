package com.Blogger.Service;

import com.Blogger.Entity.Post;
import com.Blogger.Exception.ResourceNotFoundException;
import com.Blogger.Payload.PostDto;
import com.Blogger.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository repo;

    @Override
    public PostDto createPost(PostDto dto) {
        Post p=new Post();
        p.setContent(dto.getContent());
        p.setDescription(dto.getDescription());
        p.setTitle(dto.getTitle());
        repo.save(p);
        return dto;
    }

    @Override
    public void deletePost(long id) {
        Optional<Post> byId = repo.findById(id);
        if(byId.isPresent())
        {
            repo.deleteById(id);
        }
        else
        {
            throw new ResourceNotFoundException("Id not found "+id);
        }


    }

    @Override
    public List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sortorder = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageble = PageRequest.of(pageNo, pageSize, sortorder);
        Page<Post> page = repo.findAll(pageble);
        List<Post> all = page.getContent();
        List<PostDto> dtolist = all.stream().map(p->mapToDto(p)).collect(Collectors.toList());

/*        List<PostDto> dtolist=new ArrayList<>();
        for (Post p:all) {
            PostDto dto=new PostDto();
             dto.setTitle(p.getTitle());
             dto.setDescription(p.getDescription());
             dto.setContent(p.getContent());
             dtolist.add(dto);

        }*/
        return dtolist;
    }

    @Override
    public PostDto updatePost(long id, PostDto dto) {
        Optional<Post> byId = repo.findById(id);
        if(byId.isPresent())
        {
            Post p=new Post();
            p.setId(id);
            p.setTitle(dto.getTitle());
            p.setDescription(dto.getDescription());
            p.setContent(dto.getContent());
            repo.save(p);
        }
        else
        {
            throw new ResourceNotFoundException("Id not found id "+id);
        }
        dto.setMessage("Post updated successfully ");
        return dto;
    }

    public static PostDto mapToDto(Post p)
    {
        PostDto dto=new PostDto();
        dto.setTitle(p.getTitle());
        dto.setDescription(p.getDescription());
        dto.setContent(p.getContent());
        return dto;
    }
}
