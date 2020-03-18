package catalogue.microsservice.cataloguemicroservice.api;

import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Korisnik;
import catalogue.microsservice.cataloguemicroservice.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/korisnik")
public class KorisnikController {

    @Autowired
    KorisnikRepository korisnikRepository;

    @GetMapping
    public Optional<Korisnik> nadjiKorisnika(@RequestParam Integer id){
       return korisnikRepository.findById(id);
    }

    @GetMapping(value="/all")
    public List<Korisnik> sviKorisnici(){
        return korisnikRepository.findAll();
    }

    @PostMapping(value="/add")
    public Integer dodajKorisnika(@RequestBody Korisnik korisnik){
        //privremeno
        //kasnije dodati logiku da se zove korisnik servis za id korisnika
        korisnikRepository.save(korisnik);
        return korisnik.getId();
    }

    @PutMapping(value="/update")
    public Integer dodajKatalogUListu(@RequestBody Katalog katalog, @Param("id_korisnik") Integer id_korisnik){
        var korisnik = korisnikRepository.getOne(id_korisnik);
        var katalozi = korisnik.getKatalozi();
        katalozi.add(katalog);
        korisnik.setKatalozi(katalozi);
        korisnikRepository.save(korisnik);
        return katalog.getId(); //vracamo id dodanog kataloga
    }

}
