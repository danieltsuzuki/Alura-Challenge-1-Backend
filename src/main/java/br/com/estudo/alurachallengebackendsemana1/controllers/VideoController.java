package br.com.estudo.alurachallengebackendsemana1.controllers;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Video;
import br.com.estudo.alurachallengebackendsemana1.dtos.video.VideoDTO;
import br.com.estudo.alurachallengebackendsemana1.dtos.video.VideoDTOInsert;
import br.com.estudo.alurachallengebackendsemana1.dtos.video.VideoDTOList;
import br.com.estudo.alurachallengebackendsemana1.dtos.video.VideoDTOUpdate;
import br.com.estudo.alurachallengebackendsemana1.servicies.VideoService;
import br.com.estudo.alurachallengebackendsemana1.servicies.exception.ResourceNotFoundException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/video")
public class VideoController {

    @Autowired
    private VideoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<VideoDTOInsert> post(@RequestBody @Valid VideoDTOInsert videoDTO, UriComponentsBuilder uriBuilder) {
        Video videoInsert = service.save(new Video(videoDTO));
        URI uri = uriBuilder.path("/{id}").buildAndExpand(videoInsert.getId()).toUri();

        return ResponseEntity.created(uri).body(new VideoDTOInsert(videoInsert));
    }

    @GetMapping
    public ResponseEntity<Page<VideoDTOList>> getAll(@PageableDefault(size = 5, sort = "title") Pageable pageable,
                                                     @RequestParam(value = "search", required = false) String title) {
        if (title != null) {
            Page<VideoDTOList> list = service.findByTitle(title, pageable).map(VideoDTOList::new);
            return ResponseEntity.ok(list);
        }

        Page<Video> videos = service.findAll(pageable);
        Page<VideoDTOList> videosDTO = videos.map(VideoDTOList::new);


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

    @PutMapping(value = "/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable Long id, @RequestBody VideoDTOUpdate videoDTOUpdate) {
        Video videoUpdated = service.update(id, videoDTOUpdate);

        return ResponseEntity.ok(videoUpdated);
    }

    @GetMapping(value = "/free")
    public ResponseEntity free(){
        List<VideoDTO> videosFree = service.findByFree()
                .stream()
                .map(VideoDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(videosFree);
    }

}
