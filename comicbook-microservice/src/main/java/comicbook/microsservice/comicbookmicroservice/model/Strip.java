package comicbook.microsservice.comicbookmicroservice.model;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.util.List;

@Table(name="strip")
@Entity
public class Strip {
    @Id
    @GeneratedValue
    private Integer id;
    private String naziv;
    private String opis;
    private String slika;
    private float ukupni_rating;
    private Integer ukupno_komentara;
    @Nullable
    private Integer izdanje;

    //relationships
    @ManyToMany
    private List<Autor> autori;

    //foreign keys
    private Integer id_zanr;
    private Integer id_izdavac;

    //getters and setters
    public Integer getId_zanr() {
        return id_zanr;
    }
    public Integer getId() {
        return id;
    }
    public String getNaziv() {
        return naziv;
    }
    public String getOpis() {
        return opis;
    }
    public float getUkupni_rating() {
        return ukupni_rating;
    }
    public Integer getUkupno_komentara() {
        return ukupno_komentara;
    }
    public Integer getIzdanje() {
        return izdanje;
    }
    public String getSlika() {
        return slika;
    }
    public List<Autor> getAutori() {
        return autori;
    }
    public Integer getId_izdavac() {
        return id_izdavac;
    }

    //konstruktor
    protected Strip() { }
    public Strip(String naziv, String opis, String slika, float ukupni_rating, Integer ukupno_komentara, Integer izdanje, List<Autor> autori, Integer id_izdavac, Integer id_zanr){
        this.naziv = naziv;
        this.opis = opis;
        this.slika = slika;
        this.ukupni_rating = ukupni_rating;
        this.ukupno_komentara = ukupno_komentara;
        this.izdanje = izdanje;
        this.autori = autori;
        this.id_izdavac = id_izdavac;
        this.id_zanr = id_zanr;

    }
}