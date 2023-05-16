package br.com.estudo.alurachallengebackendsemana1.servicies;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Video;
import br.com.estudo.alurachallengebackendsemana1.dtos.VideoDTO;
import br.com.estudo.alurachallengebackendsemana1.repositories.VideoRepository;
import br.com.estudo.alurachallengebackendsemana1.servicies.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    VideoRepository repository;

    public Video save(Video video) {
        return repository.save(video);
    }

    public List<Video> findAll() {
        return repository.findAllByActiveTrue();
    }

    public Video findById(Long id) {
        Optional<Video> video = repository.findByIdAndActiveTrue(id);
        return video.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }

    public void delete(Long id){
        Video video = findById(id);
        video.setActive(false);
    }
}
