package duke.ui;

/**
 * The Parser class encapsulates the logic for parsing the input from the user.
 *
 * @author cai
 */
public class Parser {
    /**
     * Parses the input from the user into a UserInput object.
     * The input is split into keyword and arguments.
     *
     * @param raw The input as-is from the user.
     * @return UserInput object encapsulating the keyword and arguments of the input.
     */
    public static UserInput parse(String raw) {
        String[] splitInput = raw.trim().split("\\s+", 2);
        String keyword = splitInput[0], args = splitInput.length > 1 ? splitInput[1] : "";
        return new UserInput(raw, keyword, args);
    }
}
