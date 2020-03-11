package com.example.planningproject;

public class ListActivite {

    private String Nom_Activite;
    private String Nom_Salle;
    private String Horaires_Debut;
    private String Horaires_Fin;

    public String getNom_Activite() {
        return Nom_Activite;
    }

    public void setNom_Activite(String nom_Activite) {
        Nom_Activite = nom_Activite;
    }

    public String getNom_Salle() {
        return Nom_Salle;
    }

    public void setNom_Salle(String nom_Salle) {
        Nom_Salle = nom_Salle;
    }

    public String getHoraires_Debut() {
        return Horaires_Debut;
    }

    public void setHoraires_Debut(String horaires_Debut) {
        Horaires_Debut = horaires_Debut;
    }

    public String getHoraires_Fin() {
        return Horaires_Fin;
    }

    public void setHoraires_Fin(String horaires_Fin) {
        Horaires_Fin = horaires_Fin;
    }

    public ListActivite(String nom_Activite, String nom_Salle, String horaires_Debut, String horaires_Fin) {
        Nom_Activite = nom_Activite;
        Nom_Salle = nom_Salle;
        Horaires_Debut = horaires_Debut;
        Horaires_Fin = horaires_Fin;
    }

    public ListActivite() {

    }

    @Override
    public String toString() {
        return "ListActivite{" +
                "Nom_Activite='" + Nom_Activite + '\'' +
                ", Nom_Salle='" + Nom_Salle + '\'' +
                ", Horaires_Debut='" + Horaires_Debut + '\'' +
                ", Horaires_Fin='" + Horaires_Fin + '\'' +
                '}';
    }
}
