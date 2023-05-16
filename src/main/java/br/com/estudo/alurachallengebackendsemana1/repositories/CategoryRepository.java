package br.com.estudo.alurachallengebackendsemana1.repositories;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
