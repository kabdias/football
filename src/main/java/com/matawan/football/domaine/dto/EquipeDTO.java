package com.matawan.football.domaine.dto;

import com.matawan.football.domaine.model.Joueur;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

public class EquipeDTO {

    private Long idEquipe;

    private String nameEquipe;
    private String acronyme;

    private List<JoueurDTO> joueurs;

    private int budget;

    public EquipeDTO() {
    }

    public EquipeDTO(Long idEquipe, String nameEquipe, String acronyme, List<JoueurDTO> joueurs, int budget) {
        this.idEquipe = idEquipe;
        this.nameEquipe = nameEquipe;
        this.acronyme = acronyme;
        this.joueurs = joueurs;
        this.budget = budget;
    }



    public void setIdEquipe(Long idEquipe) {
        this.idEquipe = idEquipe;
    }

    public void setNameEquipe(String nameEquipe) {
        this.nameEquipe = nameEquipe;
    }

    public void setAcronyme(String acronyme) {
        this.acronyme = acronyme;
    }

    public void setJoueurs(List<JoueurDTO> joueurs) {
        this.joueurs = joueurs;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public Long getIdEquipe() { return idEquipe; }
    public String getNameEquipe() { return nameEquipe; }
    public String getAcronyme() { return acronyme; }
    public List<JoueurDTO> getJoueurs() { return joueurs; }
    public int getBudget() { return budget; }
}
