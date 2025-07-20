package com.matawan.football.domaine.service;

import com.matawan.football.domaine.dto.EquipeDTO;
import com.matawan.football.domaine.mapper.EquipeMapper;
import com.matawan.football.domaine.model.Equipe;
import com.matawan.football.domaine.model.Joueur;
import com.matawan.football.domaine.port.out.EquipeRepository;
import com.matawan.football.domaine.port.out.JoueurRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipeService {
    private final EquipeRepository equipeRepository;
    private final EquipeMapper equipeMapper;
    private final JoueurRepository joueurRepository;

    public EquipeService(EquipeRepository equipeRepository, EquipeMapper equipeMapper, JoueurRepository joueurRepository) {
        this.equipeRepository = equipeRepository;
        this.equipeMapper = equipeMapper;
        this.joueurRepository = joueurRepository;
    }

    /**
     * Ajoute une nouvelle équipe en base de données à partir d'un DTO.
     *
     * @param dto le DTO contenant les informations de l'équipe à créer.
     */
    @Transactional
    public void addEquipe(EquipeDTO dto) {
        Equipe entity = equipeMapper.toEntity(dto);
        entity.setJoueurs(new ArrayList<>());

        Equipe equipeEnregistree = equipeRepository.save(entity);

        List<Joueur> joueursValides = dto.getJoueurs().stream()
                .map(joueurDto -> {
                    List<Joueur> resultats = joueurRepository.findByLastName(joueurDto.getLastName());
                    if (resultats != null && resultats.size() == 1) {
                        return resultats.get(0);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        for (Joueur joueur : joueursValides) {
            joueur.setEquipe(equipeEnregistree);
            joueurRepository.save(joueur);
        }
        equipeEnregistree.setJoueurs(joueursValides);
        equipeRepository.save(equipeEnregistree);
    }


    /**
     * Récupère la liste complète des équipes en base.
     * @return  une liste d'entités Equipe.
     */
    @Transactional(readOnly = true)
    public List<Equipe> getAllEquipe() {
        return equipeRepository.findAll();
    }

    /**
     * Recherche une équipe par son nom.
     *
     * @param name le nom de l'équipe recherchée.
     * @return un Optional contenant le DTO de l'équipe si trouvée, sinon vide.
     */
    public Optional<EquipeDTO> findByNameEquipe(String name) {
        return equipeRepository.findByNameEquipe(name)
                .map(equipeMapper::toDto);
    }
}
