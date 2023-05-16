package br.com.estudo.alurachallengebackendsemana1.controllers;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Video;
import br.com.estudo.alurachallengebackendsemana1.dtos.VideoDTO;
import br.com.estudo.alurachallengebackendsemana1.dtos.VideoDTOInsert;
import br.com.estudo.alurachallengebackendsemana1.dtos.VideoDTOList;
import br.com.estudo.alurachallengebackendsemana1.servicies.VideoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/videos")
public class VideoController {

    @Autowired
    private VideoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<VideoDTOInsert> post(@RequestBody @Valid VideoDTOInsert videoDTO, UriComponentsBuilder uriBuilder) {
        Video videoInsert = service.save(new Video(videoDTO));
        URI uri = uriBuilder.path("/{id}").buildAndExpand(videoInsert.getId()).toUri();

        return ResponseEntity.created(uri).body(videoDTO);
    }

    @GetMapping
    public ResponseEntity<List<VideoDTOList>> getAll() {
        List<Video> videos = service.findAll();
        List<VideoDTOList> videosDTO = videos.stream().map(VideoDTOList::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(videosDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VideoDTO> findById(@PathVariable Long id) {
        VideoDTO videoDTO = new VideoDTO(service.findById(id));

        return ResponseEntity.ok().body(videoDTO);
    }

    @DeleteMapping(value = "/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}
