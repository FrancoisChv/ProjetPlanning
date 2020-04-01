package com.example.planningproject;

/**
 * ListEtudiant est la classe representant les etudiants du planning.
 *
 * @author CHAUVEAU Francois / OZTOPRAK David / REMAUD Remi / TASSI Kevin
 * @version 1.0
 */

public class ListEtudiant {

    /**
     * Numero d'un etudiant.
     */
    private String Numero_Etudiant;

    /**
     * Nom d'un etudiant.
     */
    private String Nom_Etudiant;

    /**
     * Prenom d'un etudiant.
     */
    private String Prenom_Etudiant;

    /**
     * Niveau d'un etudiant (L3, M1 ou M2).
     */
    private String Niveau_Etudiant;

    /**
     * Identifiant du groupe associe a un etudiant.
     */
    private String Id_Groupe;

    /**
     * Retourne le numero d'etudiant.
     *
     * @return Numero d'etudiant.
     */
    public String getNumero_Etudiant() {
        return Numero_Etudiant;
    }

    /**
     * Met a jour le numero d'etudiant
     *
     * @param numero_Etudiant le nouveu numero d'etudiant.
     */
    public void setNumero_Etudiant(String numero_Etudiant) {
        Numero_Etudiant = numero_Etudiant;
    }

    /**
     * Retourne le nom de l'etudiant.
     *
     * @return Nom de l'etudiant.
     */
    public String getNom_Etudiant() {
        return Nom_Etudiant;
    }

    /**
     * Met a jour le nom de l'etudiant.
     *
     * @param nom_Etudiant le nouveau nom d'etudiant.
     */
    public void setNom_Etudiant(String nom_Etudiant) {
        Nom_Etudiant = nom_Etudiant;
    }

    /**
     * Retourne le prenom de l'etudiant.
     *
     * @return Prenom de l'etudiant.
     */
    public String getPrenom_Etudiant() {
        return Prenom_Etudiant;
    }

    /**
     * Met a jour le nouveau prenom de l'etudiant.
     *
     * @param prenom_Etudiant le nouveau prenom de l'etudiant.
     */
    public void setPrenom_Etudiant(String prenom_Etudiant) {
        Prenom_Etudiant = prenom_Etudiant;
    }

    /**
     * Retourne le niveau de l'etudiant.
     *
     * @return Niveau de l'etudiant.
     */
    public String getNiveau_Etudiant() {
        return Niveau_Etudiant;
    }

    /**
     * Met a jour le niveau de l'etudiant.
     *
     * @param niveau_Etudiant le nouveau niveau de l'etudiant.
     */
    public void setNiveau_Etudiant(String niveau_Etudiant) {
        Niveau_Etudiant = niveau_Etudiant;
    }

    /**
     * Retourne l'identifiant du groupe auquel appartient l'etudiant.
     *
     * @return Identifiant du groupe.
     */
    public String getId_Groupe() {
        return Id_Groupe;
    }

    /**
     * Met a jour l'identfiiant du groupe.
     *
     * @param id_Groupe le nouvel identifiant du groupe.
     */
    public void setId_Groupe(String id_Groupe) {
        Id_Groupe = id_Groupe;
    }

    /**
     * Constructeur de la classe ListEtudiant vide (necessaire).
     */
    public ListEtudiant() {

    }

    /**
     * Constructeur de la classe ListEtudiant.
     *
     * @param numero_Etudiant Numero d'etudiant de l'etudiant.
     * @param nom_Etudiant Nom de l'etudiant.
     * @param prenom_Etudiant Prenom de l'etudiant.
     * @param niveau_Etudiant Niveau de l'etudiant.
     */
    public ListEtudiant(String numero_Etudiant, String nom_Etudiant, String prenom_Etudiant, String niveau_Etudiant) {
        Numero_Etudiant = numero_Etudiant;
        Nom_Etudiant = nom_Etudiant;
        Prenom_Etudiant = prenom_Etudiant;
        Niveau_Etudiant = niveau_Etudiant;
    }

    /**
     * Fonction toString permettant d'afficher un etudiant.
     *
     * @return L'ensemble des attributs d'un etudiant.
     */
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
