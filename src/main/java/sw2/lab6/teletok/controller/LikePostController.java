package sw2.lab6.teletok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sw2.lab6.teletok.entity.PostClassTemp;
import sw2.lab6.teletok.entity.PostComment;
import sw2.lab6.teletok.entity.PostLike;
import sw2.lab6.teletok.entity.Token;
import sw2.lab6.teletok.repository.PostCommentRepository;
import sw2.lab6.teletok.repository.PostLikeRepository;
import sw2.lab6.teletok.repository.TokenRepository;

import java.util.HashMap;
import java.util.Optional;

public class LikePostController {


    @RestController
    @CrossOrigin
    public class PostCommetController {

        @Autowired
        TokenRepository tokenRepository;

        @Autowired
        PostCommentRepository postCommentRepository;

        @Autowired
        PostLikeRepository postLikeRepository;

        @PostMapping(value = "/ws/post/like",produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity postCommet(@RequestBody PostClassTemp postClassTemp){
            HashMap<String, Object> hashMap = new HashMap<>();
            HttpStatus status = HttpStatus.BAD_REQUEST;
            Token tokentemp = tokenRepository.findByCode(postClassTemp.getToken());
            Optional<PostComment> optpost = postCommentRepository.findById(postClassTemp.getPostId());

            if(tokentemp.getUserid() != null && optpost.isPresent()){
                PostLike postLike = new PostLike();
                postLike.setUser(tokentemp.getUserid());
                postLike.getPost().setId(postClassTemp.getPostId());
                postLikeRepository.save(postLike);
                hashMap.put("likeId",postLike.getId());
                hashMap.put("status","LIKE_CREATED");
                status = HttpStatus.OK;
            }else if (tokentemp.getUserid() == null){
                hashMap.put("error", "TOKEN_INVALID");
            }else{
                hashMap.put("error", "POST_NOT_FOUND");
            }
            return new ResponseEntity(hashMap,status);

        }
    }
}
