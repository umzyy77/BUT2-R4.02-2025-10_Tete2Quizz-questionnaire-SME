package org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.entities.dto;

import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.enums.DifficulteEnum;

public class QuestionDTO {
    private int idQuestion;
    private String langue;
    private String libelle;
    private String reponse;
    private DifficulteEnum difficulte;
    private String explication;
    private String reference;

    public QuestionDTO(int idQuestion, String langue, String libelle, String reponse, DifficulteEnum difficulte, String explication, String reference) {
        this.idQuestion = idQuestion;
        this.langue = langue;
        this.libelle = libelle;
        this.reponse = reponse;
        this.difficulte = difficulte;
        this.explication = explication;
        this.reference = reference;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public String getLangue() {
        return langue;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getReponse() {
        return reponse;
    }

    public DifficulteEnum getDifficulte() {
        return difficulte;
    }

    public String getExplication() {
        return explication;
    }

    public String getReference() {
        return reference;
    }
}
