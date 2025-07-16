package com.matawan.football.domaine.model;

public enum Position {
    GARDIEN(1, "gardien"),
    DEFENSEUR(2,"defensseur");

    private final  int code;
    private final String positionName;
    Position( int code, String position) {
        this.code = code;
        this.positionName = position;
    }

    public int getCode() {
        return code;
    }
    public String getPositionName() {
        return positionName;
    }

    public static Position getPosition(String str){
        return GARDIEN.getPositionName().equals(str) ? GARDIEN : DEFENSEUR;
    }
}