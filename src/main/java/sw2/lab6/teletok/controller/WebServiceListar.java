package sw2.lab6.teletok.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sw2.lab6.teletok.entity.Post;
import sw2.lab6.teletok.entity.PostComment;
import sw2.lab6.teletok.repository.PostCommentRepository;
import sw2.lab6.teletok.repository.PostLikeRepository;
import sw2.lab6.teletok.repository.PostRepository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin
public class WebServiceListar {

    @Autowired
    PostRepository postRepository;
    @Autowired
    PostLikeRepository postLikeRepository;

    @GetMapping(value = "/ws/post/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listarPost(@RequestParam(value = "query") String query) {
        List<Post> listaPost = null;
        if(query != null){
            listaPost = postRepository.findAll();
            return new ResponseEntity(listaPost, HttpStatus.OK);
        }
        return new ResponseEntity(listaPost, HttpStatus.BAD_REQUEST);
    }



}
