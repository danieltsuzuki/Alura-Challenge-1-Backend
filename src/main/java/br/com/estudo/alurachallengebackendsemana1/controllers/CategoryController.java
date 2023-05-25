package br.com.estudo.alurachallengebackendsemana1.controllers;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Category;
import br.com.estudo.alurachallengebackendsemana1.dtos.category.CategoryDTO;
import br.com.estudo.alurachallengebackendsemana1.dtos.category.CategoryDTOInsert;
import br.com.estudo.alurachallengebackendsemana1.dtos.category.CategoryDTOUpdate;
import br.com.estudo.alurachallengebackendsemana1.dtos.video.VideoDTOList;
import br.com.estudo.alurachallengebackendsemana1.servicies.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        CategoryDTO categoryDTO = new CategoryDTO(service.findById(id));
        return ResponseEntity.ok(categoryDTO);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CategoryDTOInsert> post(@RequestBody @Valid CategoryDTOInsert categoryDTO,
                                                  UriComponentsBuilder uriBuilder) {
        Category categoryInsert = service.save(new Category(categoryDTO));

        URI uri = uriBuilder.path("/{id}").buildAndExpand(categoryInsert.getId()).toUri();

        return ResponseEntity.created(uri).body(categoryDTO);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> findAll(@PageableDefault(size = 5, sort = "title") Pageable pageable) {
        Page<CategoryDTO> list = service.findAll(pageable).map(CategoryDTO::new);

        return ResponseEntity.ok(list);
    }

    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable Long id, @RequestBody CategoryDTOUpdate categoryDTO) {
        Category category = service.update(id, categoryDTO);

        return ResponseEntity.ok(category);
    }

    @GetMapping(value = "/{id}/videos")
    public ResponseEntity<Page<VideoDTOList>> getAllVideosByCategory(
            @PageableDefault(size = 5, sort = "title") Pageable pageable, @PathVariable Long id) {

        Page<VideoDTOList> list = service.findAllVideosByCategory(id, pageable).map(VideoDTOList::new);

        return ResponseEntity.ok(list);
    }
}
