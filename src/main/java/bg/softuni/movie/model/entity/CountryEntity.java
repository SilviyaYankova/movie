package bg.softuni.movie.model.entity;

import bg.softuni.movie.model.entity.enums.CountryEnum;

import javax.persistence.*;

@Entity
@Table(name = "countries")
public class CountryEntity extends BaseEntity {

    private CountryEnum name;

    public CountryEntity() {
    }

    @Enumerated(value = EnumType.STRING)
    public CountryEnum getName() {
        return name;
    }

    public CountryEntity setName(CountryEnum name) {
        this.name = name;
        return this;
    }
}
