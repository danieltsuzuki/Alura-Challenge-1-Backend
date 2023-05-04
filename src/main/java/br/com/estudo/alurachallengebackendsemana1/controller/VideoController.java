package br.com.estudo.alurachallengebackendsemana1.controller;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Video;
import br.com.estudo.alurachallengebackendsemana1.servicies.VideoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/videos")
public class VideoController {

    @Autowired
    VideoService service;

    @PostMapping()
    @Transactional
    public ResponseEntity<Video> post(@RequestBody Video video, UriComponentsBuilder uriBuilder) {
        service.save(video);

        URI uri = uriBuilder.path("/{id}").buildAndExpand(video.getId()).toUri();

        return ResponseEntity.created(uri).body(video);
    }
}
