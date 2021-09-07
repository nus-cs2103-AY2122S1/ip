package duke;

/**
 * This class deals with user's input by parsing them and convert each instruction
 * into different commands.
 */
public class Parser {
    /**
     * Enum class for keyword in user input.
     */
    public enum KeyWord {
        END("bye"),
        DELETE("delete"),
        LIST("list"),
        GET("get"),
        SEARCH("find"),
        DONE("done"),
        ADD("add");

        private final String inputKeyword;

        KeyWord(String k) {
            this.inputKeyword = k;
        }

        public String getInputKeyword() {
            return inputKeyword;
        }
    }

    /**
     * Assesses the input from the user line by line.
     *
     * @param next next line to be assessed.
     * @return an int indicating next step to be taken.
     */
    public KeyWord parse(String next) {
        if (next.equals(KeyWord.END.getInputKeyword())) {
            return KeyWord.END;
        } else if (next.equals(KeyWord.LIST.getInputKeyword())) {
            return KeyWord.LIST;
        } else if (next.length() > 3 && next.substring(0, 4).equals(KeyWord.DONE.getInputKeyword())) {
            return KeyWord.DONE;
        } else if (next.length() > 5 && next.substring(0, 6).equals(KeyWord.DELETE.getInputKeyword())) {
            return KeyWord.DELETE;
        } else if (next.length() > 2 && next.substring(0, 3).equals(KeyWord.GET.getInputKeyword())) {
            return KeyWord.GET;
        } else if (next.length() > 3 && next.substring(0, 4).equals(KeyWord.SEARCH.getInputKeyword())) {
            return KeyWord.SEARCH;
        } else {
            return KeyWord.ADD;
        }
    }
}
