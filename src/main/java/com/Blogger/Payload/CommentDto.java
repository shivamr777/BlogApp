package com.Blogger.Payload;


import com.Blogger.Entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private long id;
    private String Name;
    private String Email;
    private String Body;

}
