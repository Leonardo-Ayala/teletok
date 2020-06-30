package sw2.lab6.teletok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sw2.lab6.teletok.repository.UserRepository;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserWebService {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity listarProductos() {
        return new ResponseEntity(userRepository.findAll(), HttpStatus.OK);
    }


}
