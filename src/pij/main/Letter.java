package pij.main;

import pij.alphabet.Alphabet;

public class Letter {
    char letter;
    int value;

    public Letter(char letter) {
        this.letter = letter;
        this.value = Alphabet.getLetterValue().get(letter);
    }
}
