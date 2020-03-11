package com.example.planningproject;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class ListGroupes {

    public ListGroupes(String id_Groupe, String nom_Activite, String horaires_Debut, String horaires_Fin) {
        Id_Groupe = id_Groupe;
        Nom_Activite = nom_Activite;
        Horaires_Debut = horaires_Debut;
        Horaires_Fin = horaires_Fin;
    }

    public String getId_Groupe() {
        return Id_Groupe;
    }

    public void setId_Groupe(String id_Groupe) {
        Id_Groupe = id_Groupe;
    }

    public String getNom_Activite() {
        return Nom_Activite;
    }

    public void setNom_Activite(String nom_Activite) {
        Nom_Activite = nom_Activite;
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

    private String Id_Groupe;
    private String Nom_Activite;
    private String Horaires_Debut;
    private String Horaires_Fin;

    @Override
    public String toString() {
        return  this.Id_Groupe +"\n" +
                this.Nom_Activite +"\n" +
                this.Horaires_Debut + "\n" +
                this.Horaires_Fin;
    }

    public ListGroupes() {
        // Default constructor required for calls to DataSnapshot.getValue(ListItem.class)
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Id_Groupe", Id_Groupe);
        result.put("Nom_Activite", Nom_Activite);
        result.put("Horaires_Debut", Horaires_Debut);
        result.put("Horaires_Fin", Horaires_Fin);

        return result;
    }


}