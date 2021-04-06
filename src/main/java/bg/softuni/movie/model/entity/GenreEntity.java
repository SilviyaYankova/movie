package bg.softuni.movie.model.entity;

import bg.softuni.movie.model.entity.enums.GenreEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "genre")
public class GenreEntity extends BaseEntity{

   private GenreEnum name;

    public GenreEntity() {
    }

    @Enumerated(value = EnumType.STRING)
    public GenreEnum getName() {
        return name;
    }

    public GenreEntity setName(GenreEnum name) {
        this.name = name;
        return this;
    }
}
