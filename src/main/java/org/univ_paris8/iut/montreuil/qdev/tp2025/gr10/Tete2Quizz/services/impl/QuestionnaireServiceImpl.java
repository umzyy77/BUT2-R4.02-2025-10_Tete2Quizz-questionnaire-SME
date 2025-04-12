package org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.services.impl;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.entities.dto.QuestionDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.entities.dto.QuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.services.interfaces.QuestionnaireService;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.enums.DifficulteEnum;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.enums.LangEnum;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.exceptions.load.*;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class QuestionnaireServiceImpl implements QuestionnaireService {
    private Map<Integer, QuestionnaireDTO> questionnaires;

    @Override
    public List<QuestionnaireDTO> fournirListeQuestionnaires(String file) throws IOException, InvalidFileException, EmptyValueException, InvalidIdException, InvalidLangException, InvalidDifficultyException {
        if (questionnaires == null) chargerFichier(file);
        return questionnaires.values().stream().toList();
    }

    private void chargerFichier(String file) throws IOException, InvalidFileException, EmptyValueException, InvalidIdException, InvalidLangException, InvalidDifficultyException {
        try {
            InputStream inputStream = getClass().getResourceAsStream(file);
            if (inputStream == null) {
                throw new FileNotFoundException("Impossible de trouver la ressource : " + file);
            }

            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader reader = new CSVReaderBuilder(new InputStreamReader(inputStream)).withCSVParser(parser).build();

            questionnaires = new HashMap<>();

            String[] ligne;
            while ((ligne = reader.readNext()) != null) {
                System.out.println(Arrays.toString(ligne));
                System.out.println(ligne.length);
                if (ligne.length != 8) {
                    throw new InvalidFileException("Format CSV invalide : nombre de colonnes insuffisant.");
                }

                String idQuestionnaireStr = ligne[0];
                String numQuestionStr = ligne[1];
                String langueStr = ligne[2];
                String libelleQuestion = ligne[3];
                String reponse = ligne[4];
                String difficulteStr = ligne[5];

                if (idQuestionnaireStr == null || idQuestionnaireStr.trim().isEmpty()) {
                    throw new EmptyValueException("L'ID de questionnaire est vide ou non renseigné.");
                }
                if (numQuestionStr == null || numQuestionStr.trim().isEmpty()) {
                    throw new EmptyValueException("Le numéro de question est vide.");
                }
                if (langueStr == null || langueStr.trim().isEmpty()) {
                    throw new EmptyValueException("La langue est vide.");
                }
                if (libelleQuestion == null || libelleQuestion.trim().isEmpty()) {
                    throw new EmptyValueException("Le libellé de la question est vide.");
                }
                if (reponse == null || reponse.trim().isEmpty()) {
                    throw new EmptyValueException("La réponse est vide.");
                }
                if (difficulteStr == null || difficulteStr.trim().isEmpty()) {
                    throw new EmptyValueException("La difficulté est vide.");
                }

                int idQuestionnaire;
                try {
                    idQuestionnaire = Integer.parseInt(idQuestionnaireStr.trim().replace("\uFEFF", ""));
                }
                catch (NumberFormatException e) {
                    throw new InvalidIdException(
                            "ID de questionnaire invalide : " + idQuestionnaireStr
                    );
                }

                int idQuestion;
                try {
                    idQuestion = Integer.parseInt(numQuestionStr.trim().replace("\uFEFF", ""));
                }
                catch (NumberFormatException e) {
                    throw new InvalidIdException("Le numéro de question n'est pas un entier valide : " + numQuestionStr);
                }

                LangEnum langue;
                try {
                    langue = LangEnum.valueOf(langueStr.toUpperCase());
                }
                catch (IllegalArgumentException e) {
                    throw new InvalidLangException("Langue invalide : " + langueStr);
                }

                DifficulteEnum difficulte;
                try {
                    difficulte = DifficulteEnum.values()[Integer.parseInt(difficulteStr.trim().replace("\uFEFF", "")) - 1];
                } catch (Exception e) {
                    throw new InvalidDifficultyException("Difficulté invalide : " + difficulteStr);
                }

                QuestionDTO questionDto = new QuestionDTO(idQuestion, libelleQuestion, reponse, difficulte);

                if (!questionnaires.containsKey(idQuestionnaire)) {
                    QuestionnaireDTO questionnaire = new QuestionnaireDTO(idQuestionnaire, langue);
                    questionnaire.addQuestion(questionDto);

                    questionnaires.put(idQuestionnaire, questionnaire);
                }
                else {
                    QuestionnaireDTO existingQ = questionnaires.get(idQuestionnaire);

                    if (!existingQ.getLangue().equals(langue)) {
                        throw new InvalidLangException(
                                "Langue incohérente pour le questionnaire ID " + idQuestionnaire
                                        + " : attendu " + existingQ.getLangue()
                                        + " mais trouvé " + langueStr
                        );
                    }

                    existingQ.addQuestion(questionDto);
                }
            }
        }
        catch (CsvValidationException e) {
            questionnaires = null;
            throw new InvalidFileException("Fichier csv invalide");
        }

        if (questionnaires.isEmpty()) {
            questionnaires = null;
            throw new InvalidFileException("Fichier vide");
        }
    }
}
