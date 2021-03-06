package comicbook.microsservice.comicbookmicroservice.DTO;

public class StripRatingInfo {

	private Long id;
	private Integer ukupnoKomentara;
	private Double ukupniRating;

	public StripRatingInfo(Long id, Integer ukupnoKomentara, Double ukupniRating) {
		this.id = id;
		this.ukupnoKomentara = ukupnoKomentara;
		this.ukupniRating = ukupniRating;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUkupniRating(Double ukupniRating) {
		this.ukupniRating=ukupniRating;
	}
	public void setUkupnoKomentara(Integer ukupnoKomentara) {
		this.ukupnoKomentara=ukupnoKomentara;
	}
	public Double getUkupniRating() {
		return ukupniRating;
	}
	public Integer getUkupnoKomentara() {
		return ukupnoKomentara;
	}


}
