package com.example.planningproject;

/**
 * ListCode est la classe representant les codes d'acces au planning.
 *
 * @author CHAUVEAU Francois / OZTOPRAK David / REMAUD Remi / TASSI Kevin
 * @version 1.0
 */

public class ListCode {

    /**
     *  Code d'acces a l'application.
     */
    private String Code_Acces;

    /**
     * Identifiant du groupe associe au code d'acces.
     */
    private String Id_Groupe;

    /**
     * Retourne le nom de l'activite.
     *
     * @return Nom de l'activite.
     */
    public String getCode_Acces() {
        return Code_Acces;
    }

    /**
     * Met a jour le code d'acces du groupe.
     *
     * @param code_Acces le nouveau code d'acces.
     */
    public void setCode_Acces(String code_Acces) {
        Code_Acces = code_Acces;
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
     * Met a jour l'identifiant du groupe.
     *
     * @param id_Groupe le nouvel identifiant du groupe.
     */
    public void setId_Groupe(String id_Groupe) {
        Id_Groupe = id_Groupe;
    }

    /**
     * Constructeur de la classe ListCode vide (necessaire).
     */
    public ListCode() {

    }

    /**
     * Constructeur de la classe ListCode.
     *
     * @param code_Acces Code d'acces a l'application.
     * @param id_Groupe Identifiant du groupe associe au code d'acces.
     */
    public ListCode(String code_Acces, String id_Groupe) {
        Code_Acces = code_Acces;
        Id_Groupe = id_Groupe;
    }

    /**
     * Fonction toString permettant d'afficher un code d'acces.
     *
     * @return L'ensemble des attributs d'un code d'acces.
     */
    @Override
    public String toString() {
        return "ListCode{" +
                "Code_Acces='" + Code_Acces + '\'' +
                ", Id_Groupe='" + Id_Groupe + '\'' +
                '}';
    }
}
