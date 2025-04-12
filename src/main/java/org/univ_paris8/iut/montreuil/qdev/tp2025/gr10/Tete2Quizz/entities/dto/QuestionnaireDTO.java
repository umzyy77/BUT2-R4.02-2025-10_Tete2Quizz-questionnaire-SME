package org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.entities.dto;

import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.enums.LangEnum;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.exceptions.load.InvalidIdException;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireDTO {
    private int id;
    private LangEnum langue;
    private int nombreQuestions;
    private List<QuestionDTO> questions;

    public QuestionnaireDTO(int id, LangEnum langue) {
        this.id = id;
        this.langue = langue;
        this.nombreQuestions = 0;
        this.questions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public LangEnum getLangue() {
        return langue;
    }

    public int getNombreQuestions() {
        return nombreQuestions;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void addQuestion(QuestionDTO question) throws InvalidIdException {
        for (QuestionDTO existing : questions) {
            if (existing.getIdQuestion() == question.getIdQuestion()) {
                throw new InvalidIdException(
                        "Une question avec l'ID " + question.getIdQuestion() + " existe déjà."
                );
            }
        }

        questions.add(question);
        nombreQuestions++;
    }
}

