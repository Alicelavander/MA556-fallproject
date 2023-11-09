import graphics.Game;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static String word; //actual word
    static ArrayList<Word> guesses; //list of words guessed by the user
    static int attempts; // equivalent to the size of the array "guessed"
    static boolean endGame;

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);

        //setup new game
        Game game = new Game();

        word = newWord();
        guesses = new ArrayList<>();
        attempts = 0;

//        System.out.println("Welcome to Wordle!");
//
//        while(!endGame){
//            System.out.print("guess: ");
//            //TODO: validation for <5 letter inputs
//            //cuts anything over 6 letters to 5.
//            String response = s.nextLine().substring(0, 5);
//            Word guess = new Word(response, 5);
//            guesses.add(0, guess);
//
//            System.out.println(guess.checkWithAnswer(word));
//
//            if(guess.correctLetters() == guess.length()) endGame = true;
//        }
//
//        System.out.println("Correct! Well done. Number of attempts: " + guesses.size());
    }

    public static String newWord() {
        /* select a word randomly from a list of five-letter words
        Original List retrieved from: https://www-cs-faculty.stanford.edu/~knuth/sgb.html
        Reference:
            https://docs.oracle.com/javase/jp/8/docs/api/java/io/FileReader.html
            https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html
            https://stackoverflow.com/questions/30465507/how-to-grab-a-random-line-from-a-text-file-and-print-the-line
         */

        final File file = new File("src/sgb-words.txt");

        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));

            //add all words to an array
            String line;
            ArrayList<String> array = new ArrayList<>();
            while((line = reader.readLine()) != null) array.add(line);

            //selecting random line
            Random random = new Random();
            int randomIndex = random.nextInt(array.size());

            return array.get(randomIndex);

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "error";
    }
}
