package br.com.estudo.alurachallengebackendsemana1.controllers;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Category;
import br.com.estudo.alurachallengebackendsemana1.domain.entities.Video;
import br.com.estudo.alurachallengebackendsemana1.dtos.video.VideoDTO;
import br.com.estudo.alurachallengebackendsemana1.dtos.video.VideoDTOInsert;
import br.com.estudo.alurachallengebackendsemana1.dtos.video.VideoDTOList;
import br.com.estudo.alurachallengebackendsemana1.dtos.video.VideoDTOUpdate;
import br.com.estudo.alurachallengebackendsemana1.servicies.VideoService;
import br.com.estudo.alurachallengebackendsemana1.servicies.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureJsonTesters
class VideoControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VideoService service;

    @Autowired
    private JacksonTester<VideoDTOInsert> videoDTOInsertJSON;

    @Autowired
    private JacksonTester<VideoDTOUpdate> videoDTOUpdateJSON;

    @Autowired
    private JacksonTester<Video> videoJSON;

    @Autowired
    private JacksonTester<VideoDTO> videoDTOJSON;

    @Autowired
    private JacksonTester<List<VideoDTO>> listDTOJSON;

    @Autowired
    private JacksonTester<List<VideoDTOList>> listDTOListJSON;

    @Test
    @DisplayName("Should return http code 404, when searched id does not exist")
    void findByIdCase1() throws Exception {
        Long id = 9999999999999l;
        mvc.perform(get("/video/{id}", id)).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("It should return code 200, when the searched id video exists")
    void findByIdCase2() throws Exception {
        Long id = 1l;

        var response = mvc.perform(get("/video/{id}", id))
                .andReturn().getResponse();

        VideoDTO Video = new VideoDTO(service.findById(id));
        var searchedVideo = videoDTOJSON.write(Video);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(searchedVideo.getJson());
    }

    @Test
    @DisplayName("It should return code 200, when the searched title exists")
    void findByTitleCase1() throws Exception {
        String title = "Lucas Neto for";

        var response = mvc.perform(get("/video/?search=" + title))
                .andReturn().getResponse();

        List<VideoDTO> list = service.findByTitle(title).stream().map(VideoDTO::new).toList();
        var expectedList = listDTOJSON.write(list);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedList.getJson());
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
    @DisplayName("It should return http code 200 when the information is valid")
    @Transactional
    void postCase1() throws Exception {
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

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Should return http code 400 when information when not valid")
    @Transactional
    void postCase2() throws Exception {
        Video video = new Video(10l, " ", " ", "http://www.test.com", true, new Category());
        VideoDTOInsert videoDTO = new VideoDTOInsert(video);

        var response = mvc.perform(
                        post("/video")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(videoDTOInsertJSON.write(
                                        videoDTO
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("It should return http code 200 when the information is valid")
    @Transactional
    void updateCase1() throws Exception {
        VideoDTOUpdate videoDTOUpdate = new VideoDTOUpdate();
        videoDTOUpdate.setDescription("TEST123");
        Long id = 1l;

        var response = mvc.perform(
                        put("/video/{i}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(videoDTOUpdateJSON.write(
                                        videoDTOUpdate
                                ).getJson())
                )
                .andReturn().getResponse();

        Video searchedVideo = service.findById(id);
        var updatedVideo = videoJSON.write(searchedVideo);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(updatedVideo.getJson());

    }

    @Test
    @DisplayName("It should return http code 400 when the information is not valid")
    @Transactional
    void updateCase2() throws Exception {
        VideoDTOUpdate videoDTOUpdate = new VideoDTOUpdate();
        Long id = 1l;

        var response = mvc.perform(
                        put("/video/{i}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(videoDTOUpdateJSON.write(
                                        videoDTOUpdate
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return http code 404 when id not found")
    @Transactional
    void updateCase3() throws Exception {
        VideoDTOUpdate videoDTOUpdate = new VideoDTOUpdate();
        videoDTOUpdate.setTitle("Title updated");
        Long id = 1000l;

        var response = mvc.perform(
                        put("/video/{i}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(videoDTOUpdateJSON.write(
                                        videoDTOUpdate
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }


    @Test
    @DisplayName("should return http code 204 when video is disabled")
    @Transactional
    void deleteCase1() throws Exception {
        Long id = 1L;

        var response = mvc.perform(delete("/video/{i}", id))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @DisplayName("should return http code 404 when id not found")
    @Transactional
    void deleteCase2() throws Exception {
        Long id = 999999L;

        var response = mvc.perform(delete("/video/{i}", id))
                .andReturn().getResponse();

        assertThat((response.getStatus())).isEqualTo(HttpStatus.NOT_FOUND.value());

        System.out.println(response.getStatus());
    }

    @Test
    @DisplayName("should return http code 200")
    void getAll() throws Exception {
        var response = mvc.perform(get("/video"))
                .andReturn().getResponse();

        List<VideoDTOList> list = service.findAll().stream().map(VideoDTOList::new).toList();
        var expectedList = listDTOListJSON.write(list);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(expectedList.getJson());
    }
}