package sw2.lab6.teletok.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sw2.lab6.teletok.entity.User;
import sw2.lab6.teletok.repository.UserRepository;

import java.util.HashMap;

@RestController
@CrossOrigin
public class WebServiceAutenticación {


    @Autowired
    UserRepository userRepository;

    @PostMapping(value = "/ws/user/signIn",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity SignIn(@RequestBody User user){
        HashMap<String, Object> hashMap = new HashMap<>();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        User usuario = userRepository.findByUsername(user.getUsername());

        if(usuario.getPassword() == user.getPassword()){
            hashMap.put("status","AUTHENTICATED");
            hashMap.put("token","#");
            status=HttpStatus.OK;
        }else{
            hashMap.put("error","AUTH_FAILED");
        }
        return new ResponseEntity(hashMap,status);
    }


}
