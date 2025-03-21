package org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.services.impl;



import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.entities.dto.QuestionDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.entities.dto.QuestionnaireDTO;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.services.interfaces.QuestionnaireService;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.enums.DifficulteEnum;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.exceptions.load.FileNotFoundException;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.exceptions.load.NoQuestionnaireAvailableException;
import org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.exceptions.load.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionnaireServiceImpl implements QuestionnaireService {
    private static final String FICHIER_CSV = "questionsQuizz_Vx.csv";
    private List<QuestionnaireDTO> questionnaires = new ArrayList<>();

    @Override
    public List<QuestionnaireDTO> fournirListeQuestionnaires()
            throws FileNotFoundException,
            org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.exceptions.load.IOException,
            ParseException,
            NoQuestionnaireAvailableException {

        if (!questionnaires.isEmpty()) {
            return questionnaires;
        }

        File fichier = new File(FICHIER_CSV);
        if (!fichier.exists()) {
            throw new FileNotFoundException("Fichier de questions introuvable !");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            List<QuestionDTO> questions = new ArrayList<>();
            int lastQuestionnaireId = -1;
            QuestionnaireDTO questionnaire = null;

            while ((ligne = br.readLine()) != null) {
                String[] valeurs = ligne.split(";");
                if (valeurs.length < 7) throw new ParseException("Format invalide du fichier CSV !", 0);

                int id = Integer.parseInt(valeurs[0]);
                String langue = valeurs[1];
                String libelle = valeurs[2];
                String reponse = valeurs[3];
                DifficulteEnum difficulte = DifficulteEnum.valueOf(valeurs[4].toUpperCase());
                String explication = valeurs[5];
                String reference = valeurs[6];

                QuestionDTO question = new QuestionDTO(id, langue, libelle, reponse, difficulte, explication, reference);

                if (lastQuestionnaireId != id) {
                    if (questionnaire != null) {
                        questionnaire = new QuestionnaireDTO(
                                questionnaire.getId(),
                                questionnaire.getLangue(),
                                questionnaire.getTitre(),
                                questionnaire.getQuestions().size(),
                                questionnaire.getQuestions()
                        );
                        questionnaires.add(questionnaire);
                    }
                    questionnaire = new QuestionnaireDTO(id, langue, "Titre inconnu", 0, new ArrayList<>());
                    lastQuestionnaireId = id;
                }

                if (questionnaire != null) {
                    questionnaire.getQuestions().add(question);
                } else {
                    throw new ParseException("Erreur interne : le questionnaire est null lors de l'ajout d'une question.", 0);
                }
            }

            if (questionnaire != null) {
                questionnaire = new QuestionnaireDTO(
                        questionnaire.getId(),
                        questionnaire.getLangue(),
                        questionnaire.getTitre(),
                        questionnaire.getQuestions().size(),
                        questionnaire.getQuestions()
                );
                questionnaires.add(questionnaire);
            }

        } catch (java.io.FileNotFoundException e) {
            throw new FileNotFoundException("Fichier non trouvé : " + e.getMessage());
        } catch (java.io.IOException e) {
            throw new org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.exceptions.load.IOException("Erreur de lecture du fichier CSV : " + e.getMessage());
        }

        if (questionnaires.isEmpty()) {
            throw new NoQuestionnaireAvailableException("Aucun questionnaire valide trouvé !");
        }

        return questionnaires;
    }
}
