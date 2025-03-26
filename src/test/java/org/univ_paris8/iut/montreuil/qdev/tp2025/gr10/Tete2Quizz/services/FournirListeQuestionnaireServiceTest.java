package org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.entities.dto.QuestionDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.entities.dto.QuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.services.impl.QuestionnaireServiceImpl;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.services.interfaces.QuestionnaireService;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.enums.DifficulteEnum;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.enums.LangEnum;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.exceptions.load.*;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.services.mock.*;

import java.io.IOException;
import java.util.List;

public class FournirListeQuestionnaireServiceTest {

    QuestionnaireService service;

    @Test
    public void testChargementValide() throws Exception {
        service = new QuestionnaireServiceImpl();
        List<QuestionnaireDTO> liste = service.fournirListeQuestionnaires("/questionnaireQuizz_V1.1.csv");

        assertNotNull(liste);
        assertEquals(1, liste.size());

        QuestionnaireDTO q = liste.get(0);
        assertEquals(1, q.getId());
        assertEquals(LangEnum.FR, q.getLangue());
        assertEquals(2, q.getNombreQuestions());

        QuestionDTO question1 = q.getQuestions().get(0);
        assertEquals("Tee", question1.getReponse());
        assertEquals(DifficulteEnum.SIMPLE, question1.getDifficulte());

        QuestionDTO question2 = q.getQuestions().get(1);
        assertEquals("Badminton", question2.getReponse());
        assertEquals(DifficulteEnum.SIMPLE, question2.getDifficulte());
    }

    @Test
    public void testIOException() {
        service = new QuestionnaireServiceImpl();
        assertThrows(IOException.class, () ->
                service.fournirListeQuestionnaires("/questionnaireIOException.csv")
        );
    }

    @Test
    public void testInvalidFileException() {
        service = new QuestionnaireServiceImpl();
        assertThrows(InvalidFileException.class, () ->
                service.fournirListeQuestionnaires("/questionnaireInvalidFile.csv")
        );
    }

    @Test
    public void testEmptyValueException() {
        service = new QuestionnaireServiceImpl();
        assertThrows(EmptyValueException.class, () ->
                service.fournirListeQuestionnaires("/questionnaireEmptyValue.csv")
        );
    }

    @Test
    public void testInvalidIdException() {
        service = new QuestionnaireServiceImpl();
        assertThrows(InvalidIdException.class, () ->
                service.fournirListeQuestionnaires("/questionnaireInvalidId.csv")
        );
    }

    @Test
    public void testInvalidLangException() {
        service = new QuestionnaireServiceImpl();
        assertThrows(InvalidLangException.class, () ->
                service.fournirListeQuestionnaires("/questionnaireInvalidLang.csv")
        );
    }

    @Test
    public void testInvalidDifficultyException() {
        service = new QuestionnaireServiceImpl();
        assertThrows(InvalidDifficultyException.class, () ->
                service.fournirListeQuestionnaires("/questionnaireInvalidDifficulty.csv")
        );
    }
}
