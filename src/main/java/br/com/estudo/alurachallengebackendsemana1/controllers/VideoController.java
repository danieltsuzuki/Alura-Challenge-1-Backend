package br.com.estudo.alurachallengebackendsemana1.controllers;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Video;
import br.com.estudo.alurachallengebackendsemana1.dtos.VideoDTO;
import br.com.estudo.alurachallengebackendsemana1.repositories.VideoRepository;
import br.com.estudo.alurachallengebackendsemana1.servicies.VideoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/videos")
public class VideoController {

    @Autowired
    private VideoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<Video> post(@RequestBody @Valid Video video, UriComponentsBuilder uriBuilder) {
        service.save(video);

        URI uri = uriBuilder.path("/{id}").buildAndExpand(video.getId()).toUri();

        return ResponseEntity.created(uri).body(video);
    }

    @GetMapping
    public ResponseEntity<List<Video>> getAll(){
        var videos = service.findAll();
        return ResponseEntity.ok().body(videos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VideoDTO> findById(@PathVariable Long id){
        VideoDTO videoDTO = new VideoDTO(service.findById(id));

        return ResponseEntity.ok().body(videoDTO);
    }

}
