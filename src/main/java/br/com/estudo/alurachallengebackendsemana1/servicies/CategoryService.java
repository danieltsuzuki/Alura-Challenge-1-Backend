package br.com.estudo.alurachallengebackendsemana1.servicies;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Category;
import br.com.estudo.alurachallengebackendsemana1.domain.entities.Video;
import br.com.estudo.alurachallengebackendsemana1.dtos.category.CategoryDTOUpdate;
import br.com.estudo.alurachallengebackendsemana1.repositories.CategoryRepository;
import br.com.estudo.alurachallengebackendsemana1.repositories.VideoRepository;
import br.com.estudo.alurachallengebackendsemana1.servicies.exception.AtLeastOneFieldNeedToBeFillException;
import br.com.estudo.alurachallengebackendsemana1.servicies.exception.BadRequestException;
import br.com.estudo.alurachallengebackendsemana1.servicies.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private VideoRepository videoRepository;

    public Category findById(Long id) {
        Optional<Category> category = repository.findById(id);
        return category.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }

    public Category save(Category category) {
        return repository.save(category);
    }

    public void delete(Long id) {
        Category category = findById(id);
        if (category.getList().size() >= 1 || category.getId() == 1) {
            throw new BadRequestException("Can't delete a category with videos or category 'Free' ");
        }
        repository.delete(category);
    }

    public Page<Category> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Category update(Long id, CategoryDTOUpdate category) {
        Category oldCategory = findById(id);
        if (category.getColour() != null) {
            oldCategory.setColour(category.getColour());
        }
        if (category.getTitle() != null) {
            oldCategory.setTitle(category.getTitle());
        }
        if (category.getTitle() == null && category.getColour() == null) {
            throw new AtLeastOneFieldNeedToBeFillException("Resource not update, at least one field need to be fill");
        }
        return repository.save(oldCategory);
    }

    public Page<Video> findAllVideosByCategory(Long id,Pageable pageable) {
        Category category = findById(id);

        return videoRepository.findAllByCategoryIdAndActiveTrue(id, pageable);
    }
}
