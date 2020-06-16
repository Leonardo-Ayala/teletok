package sw2.lab6.teletok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sw2.lab6.teletok.entity.Post;
import sw2.lab6.teletok.entity.User;
import sw2.lab6.teletok.repository.PostRepository;
import sw2.lab6.teletok.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class BuscadorController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @PostMapping("/BuscarUD")
    public String buscarTransportista(@RequestParam("searchField") String searchField,
                                      Model model) {

        Optional<User> listaUsuarios = Optional.ofNullable(userRepository.findByUsername(searchField));
        Optional<Post> listaPosts = Optional.ofNullable(postRepository.findByDescription(searchField));


        model.addAttribute("listaUsuarios", listaUsuarios);
        model.addAttribute("listaPosts", listaPosts);
        return "post/list";
    }
}
