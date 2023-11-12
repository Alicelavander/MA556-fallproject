import java.awt.*;

public class Word {
    private final Letter[] letters;

    public Word(String word){
        letters = new Letter[5];
        for(int i = 0; i < 5; i++){
            char letter = word.toCharArray()[i];
            letters[i] = new Letter(letter);
        }
    }

    public void checkWithAnswer(String answer){
        for(int i = 0; i < answer.length(); i++){
            char answerLetter = answer.toCharArray()[i];
            boolean colorDecided = false;

            //check for green color
            for(int j = 0; j < this.length(); j++){
                Letter guessLetter = this.getLetters()[j];

                if(answerLetter == guessLetter.getAlphabet() & i == j){
                    guessLetter.setColor(Color.GREEN.darker());
                    colorDecided = true;
                }
            }

            //check for yellow color
            for (int j = 0; j < this.length(); j++){
                Letter guessLetter = this.getLetters()[j];

                if(!colorDecided && answerLetter == guessLetter.getAlphabet()){
                    if(guessLetter.getColor() != null) continue;
                    guessLetter.setColor(Color.YELLOW.darker());
                    colorDecided = true;
                }
            }
        }

        //set default color for all other letters
        for(Letter l: this.getLetters()){
            if(l.getColor() == null) l.setColor(Color.GRAY);
        }
    }

    public Letter[] getLetters(){
        return letters;
    }

    public int length(){
        return letters.length;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();

        for(Letter letter: letters){
            s.append(letter.getAlphabet());
        }

        return s.toString();
    }

    public int correctLetters(){
        int n = 0;

        for(Letter letter: letters){
            if(letter.getColor() == Color.GREEN) n++;
        }

        return n;
    }
}

class Letter {
    private final char letter;
    private Color color;

    public Letter(char letter){
        this.letter = letter;
        this.color = null;
    }

    public char getAlphabet(){
        return letter;
    }
    public Color getColor(){ return color; }

    public void setColor(Color newColor){ this.color = newColor; }
}
