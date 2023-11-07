import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static String word; //actual word
    static ArrayList<String> guesses; //list of words guessed by the user
    static int attempts; // equivalent to the size of the array "guessed"
    static boolean endGame;

    public static void main(String[] args){
//        Frame frame = new Frame();

        //setup new game
        newGame();
        word = "mania";
        System.out.println("answer: " + word);
        Scanner s = new Scanner(System.in);

        System.out.println("Welcome to Wordle!");

        while(!endGame){
            System.out.print("guess: ");
            String guess = s.nextLine().substring(0, 5);
            guesses.add(0, guess);

//            String response = checkGuess(guess);

            System.out.print(checkGuess(guess, word));

//            if(!isLetterIncluded(currentGuess, word)) {
//                System.out.println("not in word: " + currentGuess);
//                System.out.println("\nOh no!");
//                state++;
//                displayBodyParts(state);
//                System.out.println();
//            }
        }
    }

    public static void newGame() {
        word = newWord();
        guesses = new ArrayList<String>();
        attempts = 0;
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

    public static boolean isLetterIncluded(char guess, String correctSequence){
        boolean included = false;
        for(char letter: correctSequence.toCharArray()){
            if(guess == letter) included = true;
        }
        return included;
    }

    public static String checkGuess(String guess, String answer){
        StringBuilder s = new StringBuilder();
        String[] color = new String[5];

        for(int i = 0; i < answer.length(); i++){
            char letter = answer.toCharArray()[i];

            for (int j = 0; j < guess.length(); j++){
                char wordLetter = guess.toCharArray()[j];

                if(wordLetter == letter){
                    if(i == j) color[j] = "\033[0;32m";
                    if(i != j) color[j] = "\033[0;33m";
                }
            }
        }

        for(int i = 0; i < guess.length(); i++){
            s.append(color[i]);
            s.append(guess.toCharArray()[i]);
        }
        s.append("\033[0m");

        return s.toString();
    }
}
