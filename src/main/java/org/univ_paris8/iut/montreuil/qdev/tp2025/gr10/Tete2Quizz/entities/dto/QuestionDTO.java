package org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.entities.dto;

import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.enums.DifficulteEnum;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.enums.LangEnum;

public class QuestionDTO {
    private int idQuestion;
    private String libelle;
    private String reponse;
    private DifficulteEnum difficulte;

    public QuestionDTO(int idQuestion, String libelle, String reponse, DifficulteEnum difficulte) {
        this.idQuestion = idQuestion;
        this.libelle = libelle;
        this.reponse = reponse;
        this.difficulte = difficulte;
    }

    public int getIdQuestion() {
        return idQuestion;
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
}
