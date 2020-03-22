package catalogue.microsservice.cataloguemicroservice.api;

import catalogue.microsservice.cataloguemicroservice.model.Strip;
import catalogue.microsservice.cataloguemicroservice.repository.StripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/strip")
public class StripController {

    private int brojStripovaNaStranici = 5;

    @Autowired
    StripRepository stripRepozitorij;

    @GetMapping(value = "/")
    public List<Strip> getAll(){
        return stripRepozitorij.findAll();
    }

    //stripovi u jednom katalogu sa paginacijom
    @GetMapping(value="/from-catalogue/{id_katalog}")
    public List<Long> sviIzJednogKataloga(@PathVariable("id_katalog") Long id_katalog, @Param("brojStranice") int brojStranice){
        List<Long> stripovi = new ArrayList<>();
        stripRepozitorij.findByKatalozi_Id(id_katalog, PageRequest.of(brojStranice, brojStripovaNaStranici)).forEach(strip->stripovi.add(strip.getIdStrip()));
        return stripovi;
    }



}
