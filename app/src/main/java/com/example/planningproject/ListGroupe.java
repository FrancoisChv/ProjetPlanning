package com.example.planningproject;

import com.google.firebase.database.Exclude;
import java.util.HashMap;
import java.util.Map;

/**
 * ListGroupe est la classe representant les groupes du planning.
 * De plus, c'est cette classe qui affiche le planning associe a chacun des groupes.
 *
 * @author CHAUVEAU Francois / OZTOPRAK David / REMAUD Remi / TASSI Kevin
 * @version 1.0
 */

public class ListGroupe {

    /**
     * Identifiant du groupe
     */
    private String Id_Groupe;

    /**
     * Nom de l'activite auquel participe le groupe.
     */
    private String Nom_Activite;

    /**
     * Horaire du debut d'une activite
     */
    private String Horaires_Debut;

    /**
     * Horaire de fin d'une activite
     */
    private String Horaires_Fin;

    /**
     * Numero du groupe.
     */
    private String Numero;

    /**
     * Retourne le numero de groupe.
     *
     * @return Numero de groupe.
     */
    public String getNumero() {
        return Numero;
    }

    /**
     * Met a jour le nouveau numero de groupe.
     *
     * @param numero le nouveau numero de groupe.
     */
    public void setNumero(String numero) {
        Numero = numero;
    }

    /**
     * Retourne l'identifiant du groupe.
     *
     * @return Identifiant du groupe.
     */
    public String getId_Groupe() {
        return Id_Groupe;
    }

    /**
     * Met a jour l'identifiant de groupe.
     *
     * @param id_Groupe le nouvel identifiant de groupe.
     */
    public void setId_Groupe(String id_Groupe) {
        Id_Groupe = id_Groupe;
    }

    /**
     * Retourne le nom d'une activite.
     *
     * @return Nom d'une activite.
     */
    public String getNom_Activite() {
        return Nom_Activite;
    }

    /**
     * Met a jour le nouveau nom d'une activite.
     *
     * @param nom_Activite le nouveau nom d'activite.
     */
    public void setNom_Activite(String nom_Activite) {
        Nom_Activite = nom_Activite;
    }

    /**
     * Retourne l'horaire de debut d'une activite.
     *
     * @return Horaire de debut d'une activite.
     */
    public String getHoraires_Debut() {
        return Horaires_Debut;
    }

    /**
     * Met a jour l'horaire de debut d'une activite.
     *
     * @param horaires_Debut le nouvel horaire de debut d'une activite.
     */
    public void setHoraires_Debut(String horaires_Debut) {
        Horaires_Debut = horaires_Debut;
    }

    /**
     * Retourne l'horaire de fin d'une activite.
     *
     * @return Horaire de fin d'une activite.
     */
    public String getHoraires_Fin() {
        return Horaires_Fin;
    }

    /**
     * Met a jour l'horaire de fin d'une activite.
     *
     * @param horaires_Fin le nouvel horaire de fin d'une activite.
     */
    public void setHoraires_Fin(String horaires_Fin) {
        Horaires_Fin = horaires_Fin;
    }

    /**
     * Constructeur de la classe ListGroupe vide (necessaire);
     */
    public ListGroupe() {

    }

    /**
     * Constructeur de la classe ListGroupe
     *
     * @param id_Groupe Identifiant du groupe.
     * @param nom_Activite Nom de l'activite auquel le groupe participe.
     * @param horaires_Debut Horaire de debut de l'activite.
     * @param horaires_Fin Horaire de fin de l'activite.
     * @param numero Numero unique du groupe.
     */
    public ListGroupe(String id_Groupe, String nom_Activite, String horaires_Debut, String horaires_Fin, String numero) {
        Id_Groupe = id_Groupe;
        Nom_Activite = nom_Activite;
        Horaires_Debut = horaires_Debut;
        Horaires_Fin = horaires_Fin;
        Numero = numero;
    }

    /**
     * Fonction toString permettant d'afficher un groupe.
     *
     * @return L'ensemble des attributs d'un groupe pour le planning.
     */
    @Override
    public String toString() {
        return  this.Id_Groupe +"\n" +
                this.Nom_Activite +"\n" +
                this.Horaires_Debut + "\n" +
                this.Horaires_Fin +  "\n" +
                this.Numero;
    }

    /**
     * Fonction permettant de transformer les attributs afin de les incorporer dans la base de donnees.
     *
     * @return HashMap lisible par la base de donnees Firebase.
     */
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Id_Groupe", Id_Groupe);
        result.put("Nom_Activite", Nom_Activite);
        result.put("Horaires_Debut", Horaires_Debut);
        result.put("Horaires_Fin", Horaires_Fin);
        result.put("Numéro", Numero);

        return result;
    }



}