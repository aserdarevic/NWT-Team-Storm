package com.example.ratingservice.repozitorij;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ratingservice.modeli.User;
import com.example.ratingservice.modeli.Rating;

@Repository
public interface RatingRepozitorij extends JpaRepository<Rating,Long> {

	List<Rating> findAll();
	Optional<Rating> findById(Long id);
	Optional<Rating> findByKorisnik(Optional<User> korisnik);
	Rating getOne(Long id);
	void deleteByid(Long id);
}
