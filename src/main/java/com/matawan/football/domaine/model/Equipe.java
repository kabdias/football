package com.matawan.football.domaine.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "equipe")
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipe;

    private String nameEquipe;
    private String acronyme;

    @OneToMany(mappedBy = "equipe", fetch = FetchType.EAGER)
    private List<Joueur> joueurs;

    private int budget;

    public Equipe() {
    }

    public Equipe(Long idEquipe, String nameEquipe, String acronyme, List<Joueur> joueurs, int budget) {
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

    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public Long getIdEquipe() { return idEquipe; }
    public String getNameEquipe() { return nameEquipe; }
    public String getAcronyme() { return acronyme; }
    public List<Joueur> getJoueurs() { return joueurs; }
    public int getBudget() { return budget; }

}
