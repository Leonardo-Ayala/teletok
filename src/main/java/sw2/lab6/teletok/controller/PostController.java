package sw2.lab6.teletok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sw2.lab6.teletok.entity.PostComment;
import sw2.lab6.teletok.entity.User;
import sw2.lab6.teletok.repository.PostRepository;

import javax.servlet.http.HttpSession;

@Controller
public class PostController {

    @Autowired
    PostRepository postRepository;

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

    @GetMapping("/post/{id}")
    public String viewPost(@RequestParam("id") int id,Model model) {
        model.addAttribute("post",postRepository.findById(id));
        return "post/view";
    }
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
