package classes;

import processing.core.PApplet;

import java.util.HashSet;
import java.util.Set;

public class Hangman {
    private String secretWord;
    private char[] guessedLetters;
    private Set<Character> wrongGuesses;
    private int guessesLeft;

    public Hangman(String word){
        secretWord = word.toLowerCase();
        guessedLetters = new char[secretWord.length()];
        wrongGuesses = new HashSet<>();
        guessesLeft = 6;

        //init partial word, and set all
        //letters to '_'
        guessedLetters = new char[word.length()];
        for (int i = 0; i < guessedLetters.length; i++){
            guessedLetters[i] = '_';
        }
    }

    public char[] getGuessedLetters(){
        return guessedLetters;
    }

    public boolean guess(char letter){
        char lowerCase = Character.toLowerCase(letter);

        boolean letterInSecretWord = false;
        boolean alreadyGuessed = false;

        for (int i = 0; i < secretWord.length(); i++){
            if (secretWord.charAt(i) == lowerCase){
                guessedLetters[i] = lowerCase;
                letterInSecretWord = true;
            }
        }

        if (!letterInSecretWord) {
            if (wrongGuesses.contains(lowerCase)) {
                alreadyGuessed = true;
            } else {
                wrongGuesses.add(lowerCase);
                guessesLeft--;
            }
        }

        return letterInSecretWord && !alreadyGuessed;
    }

    public void drawGuessedLetters(PApplet p){
        //draw word on screen
        p.textSize(32);
        for (int i = 0; i < guessedLetters.length; i++){
            //draw guessed letter in center of the screen
            p.text(guessedLetters[i], p.width/2+20*i-
                    guessedLetters.length*20/2, p.height/2);
        }
    }

    public boolean isAlive(){
        return guessesLeft > 0;
    }

    public boolean winGame() {
        for (char letter : guessedLetters) {
            if (letter == '_') {
                return false;
            }
        }
        return true;
    }
}
