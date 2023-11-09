/*
Reference:
    https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
    https://gist.github.com/fnky/458719343aabd01cfb17a3a4f7296797
 */
enum LetterColor{
    UNDECIDED(""),
    GREEN("\033[0;32m"),
    YELLOW("\033[0;33m"),
    RESET("\033[0m");

    private final String code;

    LetterColor(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}