package br.com.estudo.alurachallengebackendsemana1.controllers;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Category;
import br.com.estudo.alurachallengebackendsemana1.domain.entities.Video;
import br.com.estudo.alurachallengebackendsemana1.dtos.category.CategoryDTO;
import br.com.estudo.alurachallengebackendsemana1.dtos.category.CategoryDTOInsert;
import br.com.estudo.alurachallengebackendsemana1.dtos.category.CategoryDTOUpdate;
import br.com.estudo.alurachallengebackendsemana1.servicies.CategoryService;
import br.com.estudo.alurachallengebackendsemana1.servicies.exception.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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

import static br.com.estudo.alurachallengebackendsemana1.domain.entities.enums.Colour.GREEN;
import static br.com.estudo.alurachallengebackendsemana1.domain.entities.enums.Colour.YELLOW;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureJsonTesters
class CategoryControllerTest {
    @Autowired
    private MockMvc mvc;
    @Mock
    private CategoryService categoryService;
    @Autowired
    private JacksonTester<CategoryDTOInsert> categoryDTOInsertJSON;
    @Autowired
    private JacksonTester<CategoryDTOUpdate> categoryDTOUpdateJSON;

    @Test
    @DisplayName("It should return code 200 when the searched id category exists")
    void findByIdCase1() throws Exception {
        Long id = 1L;

        var response = mvc.perform(get("/category/{i}", id))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("It should return code 404 when the searched id category doesn't exists")
    void findByIdCase2() throws Exception {
        Long id = 9999999L;

        var response = mvc.perform(get("/category/{i}", id))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("It should return http code 201 when the information is valid")
    @Transactional
    void postCase1() throws Exception {
        Category category = new Category();
        category.setTitle("Title");
        category.setColour(GREEN);
        CategoryDTOInsert categoryDTO = new CategoryDTOInsert(category);

        var response = mvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryDTOInsertJSON.write(
                        categoryDTO
                ).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("It should return http code 400 when information when not valid")
    @Transactional
    void postCase2() throws Exception {
        Category category = new Category();
        category.setTitle("Title");
        CategoryDTOInsert categoryDTO = new CategoryDTOInsert(category);

        var response = mvc.perform(post("/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryDTOInsertJSON.write(
                        categoryDTO
                ).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("It should return http code 200 when the information is valid")
    @Transactional
    void updateCase1() throws Exception {
        CategoryDTOUpdate categoryDTO = new CategoryDTOUpdate();
        categoryDTO.setColour(YELLOW);
        Long id = 1L;

        var response = mvc.perform(put("/category/{i}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryDTOUpdateJSON.write(
                        categoryDTO
                ).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("It should return http code 400 when the information isn't valid")
    @Transactional
    void updateCase2() throws Exception {
        CategoryDTOUpdate categoryDTO = new CategoryDTOUpdate();
        Long id = 1L;

        var response = mvc.perform(put("/category/{i}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryDTOUpdateJSON.write(
                        categoryDTO
                ).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("It should return http code 404 when the information isn't valid")
    @Transactional
    void updateCase3() throws Exception {
        CategoryDTOUpdate categoryDTO = new CategoryDTOUpdate();
        categoryDTO.setColour(YELLOW);
        Long id = 99999L;

        var response = mvc.perform(put("/category/{i}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryDTOUpdateJSON.write(
                        categoryDTO
                ).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("It should return http code 200")
    void findAll() throws Exception {
        List<CategoryDTO> list = categoryService.findAll().stream().map(CategoryDTO::new).toList();

        var response = mvc.perform(get("/category"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }


    @Test
    @DisplayName("It should return http code 200 when the information is valid")
    void getAllVideosByCategoryCase1() throws Exception {
        Long id = 1L;
        List<Video> list = categoryService.findAllVideosByCategory(id);

        var response = mvc.perform(get("/category/{i}/videos", id))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("It should return http code 404 when the information is not valid")
    void getAllVideosByCategoryCase2() throws Exception {
        Long id = 999999999L;
        when(categoryService.findAllVideosByCategory(id)).thenThrow(new ResourceNotFoundException("Resource not found"));

        var response = mvc.perform(get("/category/{i}/videos", id))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @DisplayName("It should return http code 204 when the category could be deleted")
    @Transactional
    void deleteCase1() throws Exception {
        Long id = 3L;

        var response = mvc.perform(delete("/category/{i}", id))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }


    @Test
    @DisplayName("It should return http code 400 when the category couldn't be deleted")
    @Transactional
    void deleteCase2() throws Exception {
        Long id = 1L;

        var response = mvc.perform(delete("/category/{i}", id))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}