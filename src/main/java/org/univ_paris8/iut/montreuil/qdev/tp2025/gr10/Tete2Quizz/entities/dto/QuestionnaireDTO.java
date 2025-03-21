package org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.entities.dto;

import java.util.List;

public class QuestionnaireDTO {
    private int id;
    private String langue;
    private String titre;
    private int nombreQuestions;
    private List<QuestionDTO> questions;

    public QuestionnaireDTO(int id, String langue, String titre, int nombreQuestions, List<QuestionDTO> questions) {
        this.id = id;
        this.langue = langue;
        this.titre = titre;
        this.nombreQuestions = nombreQuestions;
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public String getLangue() {
        return langue;
    }

    public String getTitre() {
        return titre;
    }

    public int getNombreQuestions() {
        return nombreQuestions;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }
}

