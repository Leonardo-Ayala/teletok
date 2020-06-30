package sw2.lab6.teletok.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sw2.lab6.teletok.entity.PostClassTemp;
import sw2.lab6.teletok.entity.PostComment;
import sw2.lab6.teletok.entity.Token;
import sw2.lab6.teletok.repository.TokenRepository;

import java.util.HashMap;

@RestController
@CrossOrigin
public class PostCommetController {
    
    @Autowired
    TokenRepository tokenRepository;

    @PostMapping(value = "/product",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postCommet(@RequestBody PostClassTemp postClassTemp){
        HashMap<String, Object> hashMap = new HashMap<>();
        HttpStatus status = HttpStatus.CREATED;
        Token tokentemp = tokenRepository.findByCode(postClassTemp.getToken());
        if(tokentemp){
            hashMap.put("id",product.getId());
        }
        hashMap.put("estado","creado");
        return new ResponseEntity(hashMap,status);
    }
}
