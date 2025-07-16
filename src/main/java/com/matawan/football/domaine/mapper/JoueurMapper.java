package com.matawan.football.domaine.mapper;

import com.matawan.football.domaine.dto.JoueurDTO;
import com.matawan.football.domaine.model.Joueur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JoueurMapper {
    JoueurDTO toDto(Joueur joueur);
    Joueur toEntity(JoueurDTO dto);
}
