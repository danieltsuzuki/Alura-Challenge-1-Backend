package br.com.estudo.alurachallengebackendsemana1.servicies;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Video;
import br.com.estudo.alurachallengebackendsemana1.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService  {

    @Autowired
    VideoRepository repository;

    public void save(Video video){
        repository.save(video);
    }
}
