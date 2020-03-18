package com.example.planningproject;

public class ListCode {

    private String Code_Acces;
    private String Id_Groupe;

    public String getCode_Acces() {
        return Code_Acces;
    }

    public void setCode_Acces(String code_Acces) {
        Code_Acces = code_Acces;
    }

    public String getId_Groupe() {
        return Id_Groupe;
    }

    public void setId_Groupe(String id_Groupe) {
        Id_Groupe = id_Groupe;
    }

    public ListCode() {

    }

    public ListCode(String code_Acces, String id_Groupe) {
        Code_Acces = code_Acces;
        Id_Groupe = id_Groupe;
    }

    @Override
    public String toString() {
        return "ListCode{" +
                "Code_Acces='" + Code_Acces + '\'' +
                ", Id_Groupe='" + Id_Groupe + '\'' +
                '}';
    }
}
