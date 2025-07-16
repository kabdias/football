package com.matawan.football.domaine.model;

import jakarta.persistence.*;

@Entity
@Table(name = "joueur")
public class Joueur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJoeur;
    private String firstName;
    private String lastName;
    private Position position;
    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    public Joueur() {
    }
    public Joueur(Long idJoueur, String firstName, String lastName, Position position) {
        this.idJoeur =  idJoueur;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;

    }

    public Position getPosition() {
        return position;
    }

    public Long getIdJoeur() {
        return idJoeur;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Equipe getEquipe() {return equipe;}

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public void setIdJoeur(Long idJoeur) {
        this.idJoeur = idJoeur;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
