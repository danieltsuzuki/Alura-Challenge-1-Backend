package br.com.estudo.alurachallengebackendsemana1.dtos.category;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTOSimple {

    private Long id;

    public CategoryDTOSimple(Category category){
        this.id = category.getId();
    }
}
