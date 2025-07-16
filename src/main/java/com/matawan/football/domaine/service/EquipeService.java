package com.matawan.football.domaine.service;

import com.matawan.football.domaine.dto.EquipeDTO;
import com.matawan.football.domaine.mapper.EquipeMapper;
import com.matawan.football.domaine.model.Equipe;
import com.matawan.football.domaine.port.out.EquipeRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipeService {
    private final EquipeRepository equipeRepository;
    private final EquipeMapper equipeMapper;

    public EquipeService(EquipeRepository equipeRepository, EquipeMapper equipeMapper) {
        this.equipeRepository = equipeRepository;
        this.equipeMapper = equipeMapper;
    }

    public void addEquipe(EquipeDTO dto) {
        Equipe entity = equipeMapper.toEntity(dto);
        if (entity.getJoueurs() != null) {
            entity.getJoueurs().forEach(joueur -> joueur.setEquipe(entity));
        }
        equipeRepository.save(entity);
    }
    public Optional<Equipe> getEquipeById(Long id) {
        return equipeRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Equipe> getAllEquipe() {
        return equipeRepository.findAll();
    }

}
