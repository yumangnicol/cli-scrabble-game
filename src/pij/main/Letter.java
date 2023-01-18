package pij.main;
import pij.alphabet.Alphabet;

public class Letter {
    private char letter;
    private int value;

    public Letter(char letter) {
        this.letter = letter;
        this.value = Alphabet.getLetterValue().get(letter);
    }
    public char getLetter() {
        return letter;
    }

    public int getValue() {
        return value;
    }
}
