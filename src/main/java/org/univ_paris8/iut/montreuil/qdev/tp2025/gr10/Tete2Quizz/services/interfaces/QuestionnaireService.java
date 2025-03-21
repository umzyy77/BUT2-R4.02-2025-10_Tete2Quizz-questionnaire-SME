package org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.services.interfaces;

import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.entities.dto.QuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.exceptions.load.NoQuestionnaireAvailableException;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface QuestionnaireService {
    List<QuestionnaireDTO> fournirListeQuestionnaires() throws IOException, ParseException, NoQuestionnaireAvailableException;
}
