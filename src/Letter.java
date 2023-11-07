public class Letter {
    private char letter;
    private LetterColor color;

    public Letter(char letter){
        this.letter = letter;
        this.color = LetterColor.UNDECIDED;
    }

    public char getLetter(){
        return letter;
    }

    public void setColor(LetterColor newColor){
        this.color = newColor;
    }

    public LetterColor getColor(){
        return color;
    }
}
