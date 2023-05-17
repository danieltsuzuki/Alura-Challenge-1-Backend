package br.com.estudo.alurachallengebackendsemana1.servicies;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Category;
import br.com.estudo.alurachallengebackendsemana1.repositories.CategoryRepository;
import br.com.estudo.alurachallengebackendsemana1.servicies.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category findById(Long id){
        Optional<Category> category = repository.findById(id);
        return category.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }

    public Category save(Category category){
        return repository.save(category);
    }

    public void delete(Long id){
        Category category = findById(id);
        repository.delete(category);
    }
}
