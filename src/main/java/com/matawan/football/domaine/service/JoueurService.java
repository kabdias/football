package com.matawan.football.domaine.service;

import com.matawan.football.domaine.dto.JoueurDTO;
import com.matawan.football.domaine.mapper.JoueurMapper;
import com.matawan.football.domaine.model.Joueur;
import com.matawan.football.domaine.port.out.JoueurRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class JoueurService {

    private final JoueurRepository joueurRepository;
    private final JoueurMapper joueurMapper;

    public JoueurService(JoueurRepository joueurRepository, JoueurMapper joueurMapper) {
        this.joueurRepository = joueurRepository;
        this.joueurMapper = joueurMapper;
    }


    /**
     * Ajoute un joueur à la base de données.
     * @param joueurDTO le joueur à ajouter
     */
    public JoueurDTO addJoueur(JoueurDTO joueurDTO) {
        Joueur joueurEntity = joueurMapper.toEntity(joueurDTO);
        Joueur saved = joueurRepository.save(joueurEntity);
        return joueurMapper.toDto(saved);
    }

    /**
     * Récupère un joueur par son identifiant unique.
     *
     * @param id l'identifiant du joueur
     * @return un Optional contenant le joueur s'il existe ou vide sinon
     */
    public Optional<JoueurDTO> getJoueurById(Long id) {
        return joueurRepository.findById(id)
                .map(joueurMapper::toDto);
    }

    /**
     * Récupère tous les joueurs enregistrés dans la base de données.
     *
     * @return une liste de tous les joueurs
     */
    public List<Joueur> getAllJoueurs() {
        return joueurRepository.findAll();
    }

    public Optional<JoueurDTO> getJoueurByLastName(String lastName) {
        return joueurRepository.findByLastName(lastName)
                .map(joueurMapper::toDto);
    }
}
