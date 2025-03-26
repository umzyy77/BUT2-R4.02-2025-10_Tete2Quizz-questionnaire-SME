package org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.services.mock;

import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.entities.dto.QuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.services.interfaces.QuestionnaireService;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.enums.LangEnum;

import java.util.List;

public class ChargementCSVValideMockImpl implements QuestionnaireService {
    @Override
    public List<QuestionnaireDTO> fournirListeQuestionnaires(String file) {
        QuestionnaireDTO q1 = new QuestionnaireDTO(1, LangEnum.FR);
        QuestionnaireDTO q2 = new QuestionnaireDTO(2, LangEnum.EN);
        return List.of(q1, q2);
    }
}
