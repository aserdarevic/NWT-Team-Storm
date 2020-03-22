package comicbook.microsservice.comicbookmicroservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "zanr")
public class Zanr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Naziv zanra ne smije biti prazan!")
    private String naziv;

    //veze
    @OneToMany
    @JoinColumn(name="id_zanr", referencedColumnName = "id")
    private List<Strip> stripovi;

    //getters and setters
    public Long getId() {
        return id;
    }
    public String getNaziv() {
        return naziv;
    }
    public List<Strip> getStripovi() {
        return stripovi;
    }

    //konstruktor
    protected Zanr(){}
    public Zanr(String naziv) {
        this.naziv = naziv;
    }
}
