/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

/**
 *
 * @author Piranut
 */
public class Game {

    public static final int MAX_MISSES = 7;
    private String mAnswer;
    private String mHits;
    private String mMisses;

    public Game(String answer) {
        mAnswer = answer;
        mHits = "";
        mMisses = "";
    }

    private char validateGuess(char letter) {
//        If character is not a letter, throw an exception.
        if (!Character.isLetter(letter)) {
            throw new IllegalArgumentException("A letter is required");
        }
        letter = Character.toLowerCase(letter);
//        If character is in mMisses or mHits, letter has already been guessed.
        if (mMisses.indexOf(letter) >= 0 || mHits.indexOf(letter) >= 0) {
            throw new IllegalArgumentException(letter + " has already been guessed");
        }
        return letter;
    }

    public boolean applyGuess(String letters) {
        if (letters.length() == 0) {
            throw new IllegalArgumentException("No letter found");
        }
        return applyGuess(letters.charAt(0));
    }
    
    // check if the letter is in mAnswer, if it is add it to mHits else add it to mMisses
    public boolean applyGuess(char letter) {
        letter = validateGuess(letter);
        boolean isHit = mAnswer.indexOf(letter) >= 0;
        if (isHit) {
            mHits += letter;
        } else {
            mMisses += letter;
        }
        return isHit;
    }
    
    public String getCurrentProgress() {
        String progress = "";
        for (char letter : mAnswer.toCharArray()) {  //for each letter in the array of characters in mAnswer
            char display = '-';
            // if there is a letter in mHits
            if (mHits.indexOf(letter) >= 0) {
                display = letter;
            }
            progress += display;
        }
        return progress;
    }

    public int getRemainingTries() {
        return MAX_MISSES - mMisses.length();
    }

    public String getAnswer() {
        return mAnswer;
    }

    public boolean isSolved() {
        return getCurrentProgress().indexOf('-') == -1;
    }

}
