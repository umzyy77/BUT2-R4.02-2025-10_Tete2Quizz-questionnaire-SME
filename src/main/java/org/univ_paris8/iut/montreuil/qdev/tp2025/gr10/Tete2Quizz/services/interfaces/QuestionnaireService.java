package org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.services.interfaces;

import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.entities.dto.QuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.exceptions.load.*;

import java.io.IOException;
import java.util.List;

public interface QuestionnaireService {
    List<QuestionnaireDTO> fournirListeQuestionnaires(String file) throws IOException, InvalidFileException, EmptyValueException, InvalidIdException, InvalidLangException, InvalidDifficultyException;
}
