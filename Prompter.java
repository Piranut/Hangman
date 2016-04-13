/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.util.Scanner;

/**
 *
 * @author Piranut
 */
public class Prompter {

    private Game mGame;

    public Prompter(Game game) {
        mGame = game;
    }

    public void play() {
        while (mGame.getRemainingTries() > 0 && !mGame.isSolved()) {
            displayProgress();
            promptForGuess();
        }
        if (mGame.isSolved()) {
            System.out.printf("Congratulations!! the word is '%s', you won with %d tries remaining.\n",mGame.getAnswer(), mGame.getRemainingTries());
        } else {
            System.out.printf("Bummer the word was '%s'.  :(\n", mGame.getAnswer());
        }
    }

    public boolean promptForGuess() {
        Scanner input = new Scanner(System.in);
        boolean isHit = false;
        boolean isValidGuess = false;

        while (!isValidGuess) {
            System.out.print("Enter a letter: ");
            String guessAsString = input.nextLine();
            try {
                isHit = mGame.applyGuess(guessAsString);
                isValidGuess = true;
            } catch (IllegalArgumentException e) {
                System.out.printf("%s. Please try again.\n", e.getMessage());
            }
        }
        return isHit;
    }

    public void displayProgress() {
        System.out.printf("You have %d tries left to solve: %s\n",
                mGame.getRemainingTries(),
                mGame.getCurrentProgress());
    }
}
