package org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.enums;

public enum DifficulteEnum {
    SIMPLE(1),
    INTERMEDIAIRE(2),
    EXPERT(3);

    private final int niveau;

    DifficulteEnum(int niveau) {
        this.niveau = niveau;
    }

    public int getNiveau() {
        return niveau;
    }
}
