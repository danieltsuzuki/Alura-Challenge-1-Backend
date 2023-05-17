package br.com.estudo.alurachallengebackendsemana1.domain.entities;

import br.com.estudo.alurachallengebackendsemana1.domain.entities.enums.Colour;
import br.com.estudo.alurachallengebackendsemana1.dtos.category.CategoryDTOInsert;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Category")
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private Colour colour;

    public Category(CategoryDTOInsert categoryDTOInsert){
        this.title = categoryDTOInsert.getTitle();
        this.colour = categoryDTOInsert.getColour();
    }
}
