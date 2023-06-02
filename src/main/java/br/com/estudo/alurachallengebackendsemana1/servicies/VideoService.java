package br.com.estudo.alurachallengebackendsemana1.servicies;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Category;
import br.com.estudo.alurachallengebackendsemana1.domain.entities.Video;
import br.com.estudo.alurachallengebackendsemana1.dtos.video.VideoDTOUpdate;
import br.com.estudo.alurachallengebackendsemana1.repositories.CategoryRepository;
import br.com.estudo.alurachallengebackendsemana1.repositories.VideoRepository;
import br.com.estudo.alurachallengebackendsemana1.servicies.exception.AtLeastOneFieldNeedToBeFillException;
import br.com.estudo.alurachallengebackendsemana1.servicies.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    @Autowired
    VideoRepository repository;

    @Autowired
    CategoryRepository categoryRepository;

    public Video save(Video video) {
        return repository.save(checkCategory(video));
    }

    public Page<Video> findAll(Pageable pageable) {
        return repository.findAllByActiveTrue(pageable);
    }

    public Video findById(Long id) {
        Optional<Video> video = repository.findByIdAndActiveTrue(id);
        return video.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }

    public void delete(Long id) {
        Video video = findById(id);
        video.setActive(false);
    }

    public Video update(Long id, VideoDTOUpdate videoDTOUpdate) {
        Video videoOld = findById(id);

        if (videoDTOUpdate.getDescription() != null) {
            videoOld.setDescription(videoDTOUpdate.getDescription());
        }

        if (videoDTOUpdate.getTitle() != null) {
            videoOld.setTitle(videoDTOUpdate.getTitle());
        }

        if (videoDTOUpdate.getDescription() == null && videoDTOUpdate.getTitle() == null) {
            throw new AtLeastOneFieldNeedToBeFillException("Resource not update, at least one field need to be fill");
        }

        return repository.save(videoOld);
    }

    public Page<Video> findByTitle(String title, Pageable pageable) {
        Page<Video> list = repository.findByTitleContaining(title, pageable);

        if (list.isEmpty()) {
            throw new ResourceNotFoundException("Resource not found");
        }
        return list;
    }

    public List<Video> findByFree(){
        return repository.findByFree();
    }

    private Video checkCategory(Video video) {
        Long categoryId = video.getCategory().getId() == null ? 1L : video.getCategory().getId();
        Category category = categoryRepository.findById(categoryId).get();
        video.setCategory(category);

        return video;
    }

}
