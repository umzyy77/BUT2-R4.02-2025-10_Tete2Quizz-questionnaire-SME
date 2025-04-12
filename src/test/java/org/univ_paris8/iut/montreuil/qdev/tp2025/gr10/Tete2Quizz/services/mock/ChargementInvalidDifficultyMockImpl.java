package org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.services.mock;

import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.exceptions.load.InvalidDifficultyException;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.entities.dto.QuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.services.interfaces.QuestionnaireService;

import java.util.List;

public class ChargementInvalidDifficultyMockImpl implements QuestionnaireService {
    @Override
    public List<QuestionnaireDTO> fournirListeQuestionnaires(String file) throws InvalidDifficultyException {
        throw new InvalidDifficultyException("Difficulté invalide simulée");
    }
}
