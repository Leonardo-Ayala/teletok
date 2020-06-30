package sw2.lab6.teletok.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sw2.lab6.teletok.repository.PostLikeRepository;
import sw2.lab6.teletok.repository.PostRepository;

@RestController
@RequestMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostWebService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostLikeRepository postLikeRepository;

    @GetMapping("/list")
    public ResponseEntity listarPost() {
        return new ResponseEntity(postRepository.findAll(), HttpStatus.OK);
    }

}
