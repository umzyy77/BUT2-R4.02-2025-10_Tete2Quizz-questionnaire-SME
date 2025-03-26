package org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.entities.dto.QuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.enums.LangEnum;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.exceptions.load.*;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.services.mock.*;

import java.io.IOException;
import java.util.List;

public class FournirListeQuestionnaireServiceTest {

    @Test
    public void testChargementValide() throws Exception {
        var service = new ChargementCSVValideMockImpl();
        List<QuestionnaireDTO> liste = service.fournirListeQuestionnaires("questionnaireQuizz_V1.1.csv");

        assertNotNull(liste);
        assertEquals(2, liste.size());

        QuestionnaireDTO q1 = liste.get(0);
        assertEquals(1, q1.getId());
        assertEquals(LangEnum.FR, q1.getLangue());
        assertEquals(0, q1.getNombreQuestions());  // Ou adapte si ton mock ajoute des questions
    }

    @Test
    public void testIOException() {
        var service = new ChargementIOExceptionMockImpl();
        assertThrows(IOException.class, () ->
                service.fournirListeQuestionnaires("questionnaireIOException.csv")
        );
    }

    @Test
    public void testInvalidFileException() {
        var service = new ChargementInvalidFileMockImpl();
        assertThrows(InvalidFileException.class, () ->
                service.fournirListeQuestionnaires("questionnaireInvalidFile.csv")
        );
    }

    @Test
    public void testEmptyValueException() {
        var service = new ChargementEmptyValueMockImpl();
        assertThrows(EmptyValueException.class, () ->
                service.fournirListeQuestionnaires("questionnaireEmptyValue.csv")
        );
    }

    @Test
    public void testInvalidIdException() {
        var service = new ChargementInvalidIdMockImpl();
        assertThrows(InvalidIdException.class, () ->
                service.fournirListeQuestionnaires("questionnaireInvalidId.csv")
        );
    }

    @Test
    public void testInvalidLangException() {
        var service = new ChargementInvalidLangMockImpl();
        assertThrows(InvalidLangException.class, () ->
                service.fournirListeQuestionnaires("questionnaireInvalidLang.csv")
        );
    }

    @Test
    public void testInvalidDifficultyException() {
        var service = new ChargementInvalidDifficultyMockImpl();
        assertThrows(InvalidDifficultyException.class, () ->
                service.fournirListeQuestionnaires("questionnaireInvalidDifficulty.csv")
        );
    }
}
