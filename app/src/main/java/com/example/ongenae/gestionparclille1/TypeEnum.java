package com.example.ongenae.gestionparclille1;

public enum TypeEnum {
    CARVE_TREE("Arbre à tailler"),
    TO_CUT_TREE("Arbre à abattre"),
    RUBBISH("Détritus"),
    CARVE_HEDGE("Haie à tailler"),
    WEED("Mauvaise herbe"),
    OTHER("Autre");

    private final String text;

    /**
     * Constructeur de l'énum
     * @param text, le texte de l'énumération
     */
    TypeEnum(String text) {
        this.text = text;
    }

    @Override
    public final String toString() {
        return this.text;
    }

}