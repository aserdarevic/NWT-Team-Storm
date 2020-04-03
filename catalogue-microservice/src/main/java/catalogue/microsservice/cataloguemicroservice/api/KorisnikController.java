package catalogue.microsservice.cataloguemicroservice.api;

import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Korisnik;
import catalogue.microsservice.cataloguemicroservice.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/katalog")
public class KorisnikController {

    @Autowired
    KorisnikService korisnikService;

    @PostMapping(value="/novi-korisnik")
    public Long dodajKorisnika(@RequestBody Korisnik korisnik){
        return korisnikService.dodajKorisnika(korisnik);
    }

    @PutMapping(value="/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Long dodajKatalogUListu(@RequestBody Katalog katalog)  {
        return korisnikService.dodajKatalogUListu(katalog);
    }

}
