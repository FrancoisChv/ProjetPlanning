package com.example.planningproject;

public class ListItem {

    private String Numero_Etudiant;
    private String Nom_Etudiant;
    private String Prenom_Etudiant;
    private String Niveau_Etudiant;
    private String Id_Groupe;

    public String getNumero_Etudiant() {
        return Numero_Etudiant;
    }

    public void setNumero_Etudiant(String numero_Etudiant) {
        Numero_Etudiant = numero_Etudiant;
    }

    public String getNom_Etudiant() {
        return Nom_Etudiant;
    }

    public void setNom_Etudiant(String nom_Etudiant) {
        Nom_Etudiant = nom_Etudiant;
    }

    public String getPrenom_Etudiant() {
        return Prenom_Etudiant;
    }

    public void setPrenom_Etudiant(String prenom_Etudiant) {
        Prenom_Etudiant = prenom_Etudiant;
    }

    public String getNiveau_Etudiant() {
        return Niveau_Etudiant;
    }

    public void setNiveau_Etudiant(String niveau_Etudiant) {
        Niveau_Etudiant = niveau_Etudiant;
    }

    public String getId_Groupe() {
        return Id_Groupe;
    }

    public ListItem() {

    }

    public ListItem(String numero_Etudiant, String nom_Etudiant, String prenom_Etudiant, String niveau_Etudiant) {
        Numero_Etudiant = numero_Etudiant;
        Nom_Etudiant = nom_Etudiant;
        Prenom_Etudiant = prenom_Etudiant;
        Niveau_Etudiant = niveau_Etudiant;
    }

    public void setId_Groupe(String id_Groupe) {
        Id_Groupe = id_Groupe;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "Numero_Etudiant='" + Numero_Etudiant + '\'' +
                ", Nom_Etudiant='" + Nom_Etudiant + '\'' +
                ", Prenom_Etudiant='" + Prenom_Etudiant + '\'' +
                ", Niveau_Etudiant='" + Niveau_Etudiant + '\'' +
                ", Id_Groupe='" + Id_Groupe + '\'' +
                '}';
    }
}
