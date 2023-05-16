package br.com.estudo.alurachallengebackendsemana1.dtos.category;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Category;
import br.com.estudo.alurachallengebackendsemana1.domain.entities.enums.Colour;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private Long id;

    private String title;

    private Colour colour;

    public CategoryDTO(Category category){
        this.id = category.getId();
        this.title = category.getTitle();
        this.colour = category.getColour();
    }
}
