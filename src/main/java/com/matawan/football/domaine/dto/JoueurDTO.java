package com.matawan.football.domaine.dto;

import com.matawan.football.domaine.model.Position;

public class JoueurDTO {
    private String firstName;
    private String lastName;
    private Position position;

    public JoueurDTO() {
    }

    public JoueurDTO( String firstName, String lastName, Position position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
