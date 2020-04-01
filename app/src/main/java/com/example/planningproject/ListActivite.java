package com.example.planningproject;

/**
 * ListActivite est la classe representant les activites du planning.
 *
 * @author CHAUVEAU Francois / OZTOPRAK David / REMAUD Remi / TASSI Kevin
 * @version 1.0
 */

public class ListActivite {

    /**
     * Nom de l'activite.
     */
    private String Nom_Activite;

    /**
     * Nom de la salle oe se deroule l'activite.
     */
    private String Nom_Salle;

    /**
     * Horaire ou peut commencer l'activite.
     */
    private String Horaires_Debut;

    /**
     * Horaire ou l'activite devra etre fini.
     */
    private String Horaires_Fin;

    /**
     * Retourne le nom de l'activite.
     *
     * @return Nom de l'activite.
     */
    public String getNom_Activite() {
        return Nom_Activite;
    }

    /**
     * Met a jour le nom de l'activite.
     *
     * @param nom_Activite le nouveau nom de l'activite.
     */
    public void setNom_Activite(String nom_Activite) {
        Nom_Activite = nom_Activite;
    }

    /**
     * Retourne le nom de la salle.
     *
     * @return Nom de la salle.
     */
    public String getNom_Salle() {
        return Nom_Salle;
    }

    /**
     * Met a jour le nom de la salle.
     *
     * @param nom_Salle La nouvelle salle.
     */
    public void setNom_Salle(String nom_Salle) {
        Nom_Salle = nom_Salle;
    }

    /**
     * Retourne l'horaire du debut de l'activite.
     *
     * @return Horaire du debut de l'activite.
     */
    public String getHoraires_Debut() {
        return Horaires_Debut;
    }

    /**
     * Met a jour l'horaire de debut de l'activite.
     *
     * @param horaires_Debut Le nouvel horaire de debut.
     */
    public void setHoraires_Debut(String horaires_Debut) {
        Horaires_Debut = horaires_Debut;
    }

    /**
     * Retourne l'horaire de fin de l'activite.
     *
     * @return Horaire de fin de l'activite.
     */
    public String getHoraires_Fin() {
        return Horaires_Fin;
    }

    /**
     * Met a jour l'horaire de fin de l'activite.
     *
     * @param horaires_Fin Le nouvel horaire de fin.
     */
    public void setHoraires_Fin(String horaires_Fin) {
        Horaires_Fin = horaires_Fin;
    }

    /**
     * Constructeur de la classe ListActivite.
     *
     * @param nom_Activite Nom de l'activite.
     * @param nom_Salle Nom de la salle.
     * @param horaires_Debut Horaire du debut.
     * @param horaires_Fin Horaire de fin.
     */
    public ListActivite(String nom_Activite, String nom_Salle, String horaires_Debut, String horaires_Fin) {

        Nom_Activite = nom_Activite;
        Nom_Salle = nom_Salle;
        Horaires_Debut = horaires_Debut;
        Horaires_Fin = horaires_Fin;
    }

    /**
     * Contructeur de la classe ListACtivite vide (necessaire).
     */
    public ListActivite() {

    }

    /**
     * Fonction toString permettant d'afficher une activite.
     *
     * @return L'ensemble des attributs d'une activite.
     */
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
