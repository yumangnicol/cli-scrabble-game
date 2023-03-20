package pij.main.utils;

/**
 * A helper class that defines all constant values used in the project
 */
public class Constants {
    /**
     * The path to the defaultBoard file
     */
    public static final String DEFAULT_BOARD_FILE = "defaultBoard.txt";

    /**
     * The path to the wordlist file
     */
    public static final String DEFAULT_WORD_LIST_FILE = "wordlist.txt";

    /**
     * The regex pattern to match premium square values
     */
    public static final String PREMIUM_SQUARE_REGEX_FORMAT = "\\(\\-[1-9]|\\{\\-[1-9]|\\([0-9]{2}|\\{[0-9]{2}|\\([0-9]\\)|\\{[0-9]\\}";

    /**
     * The regex pattern to match move string values
     */
    public static final String MOVE_STRING_REGEX_FORMAT = "^[a-zA-Z]*$";

    /**
     * The regex pattern to match a board tile
     */
    public static final String BOARD_TILE_REGEX_FORMAT = "[a-zA-Z][0-9]{1,2}";

    /**
     * The minimum board size
     */
    public static final int MIN_BOARD_SIZE = 12;

    /**
     * The maximum board size
     */
    public static final int MAX_BOARD_SIZE = 26;

    /**
     * The decimal value of the character A
     */
    public static final int CHAR_CAPITAL_LETTER_A_INT_VALUE = 65;

    /**
     * The decimal value of the character Z
     */
    public static final int CHAR_CAPITAL_LETTER_Z_INT_VALUE = 90;

    /**
     * The decimal value of the character 0
     */
    public static final int CHAR_NUMBER_0_INT_VALUE = 48;

    /**
     * The decimal value of the character 9
     */
    public static final int CHAR_NUMBER_9_INT_VALUE = 57;

    /**
     * The bonus value for 7 letter moves
     */
    public static final int SEVEN_LETTER_MOVE_BONUS = 70;

    /**
     * The decimal value of the character value before A
     */
    public static final int CHAR_INT_VALUE_BEFORE_SMALL_LETTER_A = 96;

    /**
     * The maximum rack size
     */
    public static final int MAX_RACK_SIZE = 7;

    /**
     * Divider for printing
     */
    public static final String DIVIDER = "------------------------------------------------------------------------";
}
