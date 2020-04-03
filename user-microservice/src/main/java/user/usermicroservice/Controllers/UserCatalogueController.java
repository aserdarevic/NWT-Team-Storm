package user.usermicroservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import user.usermicroservice.DTO.KatalogDTO;
import user.usermicroservice.Servisi.UserServis;

import java.nio.charset.Charset;
import java.util.Collections;


@RestController
@RequestMapping("/katalog")
public class UserCatalogueController {
    @Autowired
    UserServis userServis;

    @PutMapping(value="/create-katalog", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Long kreirajKatalogKorisniku(@RequestBody KatalogDTO noviKatalog){
        //okinuti endpoint za pravljenje kataloga
        RestTemplate obj = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        //request body
        HttpEntity<KatalogDTO> entity = new HttpEntity<>(noviKatalog, headers);
        ResponseEntity<Long> response = obj.postForEntity("http://localhost:8082/katalog/novi", entity, Long.class);
        return response.getBody();
    }

}
