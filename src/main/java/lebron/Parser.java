package lebron;

/**
 * This class parses the instructions given by the user.
 *
 * @author Nigel Tan
 */
public class Parser {
    //default constructor

    /**
     * Splits the text and returns the first word which is the command word.
     *
     * @param text the text.
     * @return the first word.
     */
    public String parseText(String text) {
        String[] splitWords = text.split(" ", 2);
        String commandWord = splitWords[0];
        return commandWord;
    }

    /**
     * Splits the text into 2 portions.
     * The first word is the first element in the array, the rest is the second element.
     *
     * @param text the text.
     * @return an array where the first element is the first word, and the second element is the rest of the text.
     */
    public String[] split(String text) {
        String[] splitWords = text.split(" ", 2);
        return splitWords;
    }
}
