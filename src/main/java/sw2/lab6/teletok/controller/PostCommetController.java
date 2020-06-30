package sw2.lab6.teletok.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sw2.lab6.teletok.entity.PostClassTemp;
import sw2.lab6.teletok.entity.PostComment;
import sw2.lab6.teletok.entity.Token;
import sw2.lab6.teletok.repository.PostCommentRepository;
import sw2.lab6.teletok.repository.TokenRepository;

import java.util.HashMap;
import java.util.Optional;

@RestController
@CrossOrigin
public class PostCommetController {
    
    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    PostCommentRepository postCommentRepository;

    @PostMapping(value = "/ws/post/comment",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postCommet(@RequestBody PostClassTemp postClassTemp){
        HashMap<String, Object> hashMap = new HashMap<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Token tokentemp = tokenRepository.findByCode(postClassTemp.getToken());
        Optional<PostComment> optpost = postCommentRepository.findById(postClassTemp.getPostId());
        if(tokentemp.getUserid() != null && optpost.isPresent()){
            PostComment comentario = new PostComment();
            comentario.setMessage(postClassTemp.getMessage());
            comentario.setUser(tokentemp.getUserid());
            postCommentRepository.save(comentario);
            hashMap.put("commentId",comentario.getId());
            hashMap.put("status","COMMENT_CREATED");
            status = HttpStatus.OK;
        }else if (tokentemp.getUserid() == null){
            hashMap.put("error", "TOKEN_INVALID");
        }else{
            hashMap.put("error", "POST_NOT_FOUND");
        }
        return new ResponseEntity(hashMap,status);

    }
}
