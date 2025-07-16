package com.matawan.football.domaine.mapper;

import com.matawan.football.domaine.dto.EquipeDTO;
import com.matawan.football.domaine.model.Equipe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipeMapper {

    EquipeDTO toDto(Equipe joueur);
    Equipe toEntity(EquipeDTO dto);
}
