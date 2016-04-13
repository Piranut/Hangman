/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *
 * @author Piranut
 */
public class Hangman {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner i = new Scanner(System.in);
        File file = new File("randomWords.txt");

        try {
            Scanner input = new Scanner(file);
            String word;
            while (input.hasNextLine()) {
                word = input.nextLine();
                System.out.println("**** WELCOME TO HANGMAN ****");
                System.out.print("Enter 's'(start)/ 'q'(quit) : ");
                String answer = i.nextLine();
                if (answer.equals("s")) {
                    Game game = new Game(String.format("%s", word));
                    Prompter prompter = new Prompter(game);
                    prompter.play();
                    System.out.println();
                } else if (answer.equals("q")) {
                    break;
                } else {
                    System.out.println();
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.print(ex.getMessage());
        }
    }
}
