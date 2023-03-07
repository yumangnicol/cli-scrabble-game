package pij.main.utils;

public class Constants {
    public static final String DEFAULT_BOARD_FILE = "./resources/defaultBoard.txt";
    public static final String DEFAULT_WORD_LIST_FILE = "./resources/wordlist.txt";
    public static final String PREMIUM_SQUARE_REGEX_FORMAT = "\\(\\-[1-9]|\\{\\-[1-9]|\\([0-9]{2}|\\{[0-9]{2}|\\([0-9]\\)|\\{[0-9]\\}";
    public static final String MOVE_STRING_REGEX_FORMAT = "^[a-zA-Z]*$";
    public static final int MIN_BOARD_SIZE = 12;
    public static final int MAX_BOARD_SIZE = 26;
    public static final int CHAR_CAPITAL_LETTER_A_INT_VALUE = 65;
    public static final int CHAR_CAPITAL_LETTER_Z_INT_VALUE = 90;
    public static final int CHAR_NUMBER_0_INT_VALUE = 48;
    public static final int CHAR_NUMBER_9_INT_VALUE = 57;
    public static final int SEVEN_LETTER_MOVE_BONUS = 70;
    public static final int CHAR_INT_VALUE_BEFORE_SMALL_LETTER_A = 96;
    public static final int MAX_RACK_SIZE = 7;
    public static final String DIVIDER = "---------------------------------------------------";
}
