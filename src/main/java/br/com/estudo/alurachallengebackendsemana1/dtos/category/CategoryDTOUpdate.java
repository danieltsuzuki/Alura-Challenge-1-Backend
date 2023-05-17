package br.com.estudo.alurachallengebackendsemana1.dtos.category;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.Category;
import br.com.estudo.alurachallengebackendsemana1.domain.entities.enums.Colour;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTOUpdate {

    private String title;
    private Colour colour;

    public CategoryDTOUpdate(Category category) {
        this.title = category.getTitle();
        this.colour = category.getColour();
    }
}
