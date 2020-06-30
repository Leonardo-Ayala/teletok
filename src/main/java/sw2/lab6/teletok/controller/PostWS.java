package sw2.lab6.teletok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sw2.lab6.teletok.entity.Post;
import sw2.lab6.teletok.entity.PostComment;
import sw2.lab6.teletok.entity.Token;
import sw2.lab6.teletok.entity.User;
import sw2.lab6.teletok.repository.PostRepository;
import sw2.lab6.teletok.repository.TokenRepository;
import sw2.lab6.teletok.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/ws")
public class PostWS {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    @GetMapping(value = {"", "/"})
    public String listPost(Model model){
        model.addAttribute("listapost", postRepository.findAll());
        return "post/list";
    }

    @GetMapping("/post/new")
    public String newPost(){

        return "post/new";
    }

    @PostMapping("/post/save")
    public String savePost() {
        return "redirect:/";
    }

    @GetMapping("/post/file/{media_url}")
    public String getFile() {
        return "";
    }

    @GetMapping(value = "/post/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity obtenerPost(@PathVariable("id") String idPost, @RequestParam("tokenCodigo") String tokenCodigo) {

        HashMap<String, Object> responseMap = new HashMap<>();
        if (tokenCodigo != null) {
            Token  token = tokenRepository.findByCode(tokenCodigo);
            User u = token.getUserid();
            try {
                int idP = Integer.parseInt(idPost);
                Optional<Post> opt = postRepository.findById(idP);
                if (opt.isPresent()) {
                    responseMap.put("estado", "ok");
                    responseMap.put("post", opt.get());
                    return new ResponseEntity(responseMap, HttpStatus.OK);
                } else {
                    responseMap.put("estado", "error");
                    responseMap.put("msg", "no se encontró el post con id: " + idPost);//////cambiar con logica de token
                    return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                responseMap.put("estado", "error");
                responseMap.put("msg", "El ID del post debe ser un número");//////cambiar con logica de token
                return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
        }

    }

    /*@GetMapping("/post/{id}")
    public String viewPost(@RequestParam("id") int id,Model model) {
        model.addAttribute("post",postRepository.findById(id));
        return "post/view";
    }*/
    @PostMapping("/post/comment")
    public String postComment(@RequestParam("comment") String comment, HttpSession session) {
        PostComment postComment = new PostComment();
        postComment.setMessage(comment);
        User user = (User) session.getAttribute("user");
        postComment.setUser(user);
        return "redirect:/";
    }

    @PostMapping("/post/like")
    public String postLike() {
        return "";
    }
}
