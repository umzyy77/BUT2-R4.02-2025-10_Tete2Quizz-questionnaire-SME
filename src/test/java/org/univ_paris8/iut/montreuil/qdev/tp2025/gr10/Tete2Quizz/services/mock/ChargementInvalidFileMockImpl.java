package org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.services.mock;

import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.exceptions.load.InvalidFileException;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.entities.dto.QuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.services.interfaces.QuestionnaireService;

import java.util.List;

public class ChargementInvalidFileMockImpl implements QuestionnaireService {
    @Override
    public List<QuestionnaireDTO> fournirListeQuestionnaires(String file) throws InvalidFileException {
        throw new InvalidFileException("Fichier invalide simulé");
    }
}
