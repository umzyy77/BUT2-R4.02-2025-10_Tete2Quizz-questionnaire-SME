package org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.services.mock;

import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.entities.dto.QuestionDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.entities.dto.QuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.services.interfaces.QuestionnaireService;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.enums.DifficulteEnum;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.enums.LangEnum;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.exceptions.load.InvalidIdException;

import java.util.List;

public class ChargementCSVValideMockImpl implements QuestionnaireService {

    @Override
    public List<QuestionnaireDTO> fournirListeQuestionnaires(String file) throws InvalidIdException {
        QuestionnaireDTO questionnaire = new QuestionnaireDTO(1, LangEnum.FR);

        QuestionDTO q1 = new QuestionDTO(
                1,
                "De quel petit objet se munit le golfeur pour surélever sa balle avant de la frapper ?",
                "Tee",
                DifficulteEnum.SIMPLE
        );

        QuestionDTO q2 = new QuestionDTO(
                2,
                "Quel sport de raquette porte le nom de la ville anglaise où il fut inventé ?",
                "Badminton",
                DifficulteEnum.SIMPLE
        );

        questionnaire.addQuestion(q1);
        questionnaire.addQuestion(q2);

        return List.of(questionnaire);
    }
}
