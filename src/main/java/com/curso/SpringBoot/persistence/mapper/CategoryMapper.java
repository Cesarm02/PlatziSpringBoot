package com.curso.SpringBoot.persistence.mapper;

import com.curso.SpringBoot.domain.Category;
import com.curso.SpringBoot.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    //Aca definimos la conversión de categoria a Category
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active")

    })
    Category toCategory(Categoria categoria);

    //Con esta anotación se hace lo contrario de category a categoria
    //Pero no es necesario mappear campo por campo
    @InheritInverseConfiguration
    //Con esta anotación ignoramos el campo que no va en el mappeo
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);

}
