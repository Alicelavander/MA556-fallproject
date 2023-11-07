public class Word {
    private final Letter[] letters;

    public Word(String word, int length){
        letters = new Letter[length];
        for(int i = 0; i < 5; i++){
            char letter = word.toCharArray()[i];
            letters[i] = new Letter(letter);
        }
    }

    public String checkWithAnswer(String answer){
        for(int i = 0; i < answer.length(); i++){
            char answerLetter = answer.toCharArray()[i];
            boolean colorDecided = false;

            //check for green color
            for(int j = 0; j < this.length(); j++){
                Letter guessLetter = this.getLetters()[j];

                if(answerLetter == guessLetter.getAlphabet() & i == j){
                    guessLetter.setColor(LetterColor.GREEN);
                    colorDecided = true;
                }
            }

            //check for yellow color
            for (int j = 0; j < this.length(); j++){
                Letter guessLetter = this.getLetters()[j];

                if(!colorDecided && answerLetter == guessLetter.getAlphabet()){
                    if(guessLetter.getColor() != LetterColor.UNDECIDED) continue;
                    guessLetter.setColor(LetterColor.YELLOW);
                    colorDecided = true;
                }
            }
        }

        //set default color for all other letters
        for(Letter l: this.getLetters()){
            if(l.getColor() == LetterColor.UNDECIDED) l.setColor(LetterColor.RESET);
        }

        return this.toColoredString();
    }

    public String toColoredString(){
        StringBuilder s = new StringBuilder();

        for(Letter letter: letters){
            s.append(letter.getColor());
            s.append(letter.getAlphabet());
        }
        s.append(LetterColor.RESET);

        return s.toString();
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
            if(letter.getColor() == LetterColor.GREEN) n++;
        }

        return n;
    }
}

class Letter {
    private final char letter;
    private LetterColor color;

    public Letter(char letter){
        this.letter = letter;
        this.color = LetterColor.UNDECIDED;
    }

    public char getAlphabet(){
        return letter;
    }

    public void setColor(LetterColor newColor){
        this.color = newColor;
    }

    public LetterColor getColor(){
        return color;
    }
}
