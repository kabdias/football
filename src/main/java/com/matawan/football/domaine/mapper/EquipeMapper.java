package com.matawan.football.domaine.mapper;

import com.matawan.football.domaine.dto.EquipeDTO;
import com.matawan.football.domaine.model.Equipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EquipeMapper {
    @Mapping(target = "joueurs", ignore = true)
    EquipeDTO toDto(Equipe joueur);
    Equipe toEntity(EquipeDTO dto);
}
