package br.com.estudo.alurachallengebackendsemana1.servicies;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Category;
import br.com.estudo.alurachallengebackendsemana1.dtos.category.CategoryDTOUpdate;
import br.com.estudo.alurachallengebackendsemana1.repositories.CategoryRepository;
import br.com.estudo.alurachallengebackendsemana1.servicies.exception.AtLeastOneFieldNeedToBeFillException;
import br.com.estudo.alurachallengebackendsemana1.servicies.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category findById(Long id) {
        Optional<Category> category = repository.findById(id);
        return category.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }

    public Category save(Category category) {
        return repository.save(category);
    }

    public void delete(Long id) {
        Category category = findById(id);
        repository.delete(category);
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category update(Long id, CategoryDTOUpdate category) {
        Category oldCategory = findById(id);
        if (category.getColour() != null) {
            oldCategory.setColour(category.getColour());
        }
        if (category.getTitle() != null) {
            oldCategory.setTitle(category.getTitle());
        }
        if (category.getTitle() == null && category.getColour() == null){
            throw  new AtLeastOneFieldNeedToBeFillException("Resource not update, at least one field need to be fill");
        }
        return repository.save(oldCategory);
    }
}
