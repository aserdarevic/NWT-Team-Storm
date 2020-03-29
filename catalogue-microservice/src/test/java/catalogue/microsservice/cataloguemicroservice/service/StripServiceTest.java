package catalogue.microsservice.cataloguemicroservice.service;

import catalogue.microsservice.cataloguemicroservice.exception.ApiRequestException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class StripServiceTest {

    @Autowired
    StripService stripService;

    @Test
    void sviIzJednogKataloga() {
        assertThat(stripService.sviIzJednogKataloga((long) 1, 0, 5)).size().isEqualTo(0);
        assertThat(stripService.sviIzJednogKataloga((long) 3, 0, 5)).size().isEqualTo(4);
    }

    @Test
    void jedanStrip(){
        //sve okej
        assertThat(stripService.jedanStrip((long) 1).getIdStrip()).isEqualTo(1);
        //strip ne postoji
        ApiRequestException nemaStripa = assertThrows(
                ApiRequestException.class,
                ()->stripService.jedanStrip((long)1232),
                "Trebalo bi baciti"
        );
        assertThat(nemaStripa.getMessage().contains("ne postoji"));

    }

    @Test
    void postojiUKatalogu(){
        //postoji
        assertThat(stripService.postojiUKatalogu((long) 1, (long) 3)).isTrue();
        //ne postoji
        assertThat(stripService.postojiUKatalogu((long) 1, (long) 1)).isFalse();
    }
}