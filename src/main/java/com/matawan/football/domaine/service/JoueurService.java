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
    public final JoueurMapper joueurMapper;

    public JoueurService(JoueurRepository joueurRepository, JoueurMapper joueurMapper) {
        this.joueurRepository = joueurRepository;
        this.joueurMapper = joueurMapper;
    }


    /**
     * Ajoute un joueur à la base de données.
     * @param joueurDTO le joueur à ajouter
     */
    public void addJoueur(JoueurDTO joueurDTO) {
        Joueur joueurEntity = joueurMapper.toEntity(joueurDTO);
        joueurRepository.save(joueurEntity);
    }

    /**
     * Récupère un joueur par son identifiant unique.
     *
     * @param id l'identifiant du joueur
     * @return un Optional contenant le joueur s'il existe ou vide sinon
     */
    public Optional<Joueur> getJoueurById(Long id) {
        return joueurRepository.findById(id);
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
        List<Joueur> joueurs = joueurRepository.findByLastName(lastName);

        if (joueurs.isEmpty()) {
            return Optional.empty();
        } else if (joueurs.size() > 1) {
            throw new IllegalStateException("Plusieurs joueurs trouvés pour le nom : " + lastName);
        } else {
            return Optional.of(joueurMapper.toDto(joueurs.get(0)));
        }
    }
}
