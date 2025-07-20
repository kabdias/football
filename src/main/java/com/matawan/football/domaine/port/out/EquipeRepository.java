package com.matawan.football.domaine.port.out;


import com.matawan.football.domaine.dto.EquipeDTO;
import com.matawan.football.domaine.model.Equipe;
import com.matawan.football.domaine.model.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {
    Optional<Equipe> findByNameEquipe(String nameEquipe);
}
