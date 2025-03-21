package org.univ_paris8.iut.montreuil.qdev.tp2025.gr10.Tete2Quizz.utils.exceptions.load;

public class ParseException extends Exception {
    public ParseException(String message, int errorOffset) {
        super(message);
    }
}