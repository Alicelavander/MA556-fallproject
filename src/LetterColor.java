/*
ADD REFERENCE
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