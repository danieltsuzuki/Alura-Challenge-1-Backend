package br.com.estudo.alurachallengebackendsemana1.controllers;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Category;
import br.com.estudo.alurachallengebackendsemana1.domain.entities.Video;
import br.com.estudo.alurachallengebackendsemana1.dtos.video.VideoDTOInsert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DataJpaTest
class VideoControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestEntityManager manager;

    @Autowired
    private JacksonTester<VideoDTOInsert> videoDTOInsertJSON;

    @Test
    @DisplayName("It should return code 404, when the searched id does not exist")
    void findByIdCase1() throws Exception {
        Long id = 9999999999999l;
        mvc.perform(get("/video/{id}", id)).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("It should return code 200, when the searched id video exists")
    void findByIdCase2() throws Exception {
        Long id = 1l;
        mvc.perform(get("/video/{id}", id)).andExpect(status().isOk());
    }

    @Test
    @DisplayName("It should return code 200, when the searched title exists")
    void findByTitleCase1() throws Exception {
        String title = "Lucas Neto for little ones";

        var response = mvc.perform(get("/video/?search=" + title))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Should return code 404, when the searched title does not exist")
    void findByTitleCase2() throws Exception {
        String title = "One Pierce";

        var response = mvc.perform(get("/video/?search=" + title))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("Deveria devolver código http 200 quando informações estiverem válidas")
    void postCase1() throws Exception{
        Video video = new Video(10l, "Video Insert Test", "Test", "http://www.test.com", true, new Category());
        VideoDTOInsert videoDTO = new VideoDTOInsert(video);

        var response = mvc.perform(
                        post("/video")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(videoDTOInsertJSON.write(
                                        videoDTO
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

}