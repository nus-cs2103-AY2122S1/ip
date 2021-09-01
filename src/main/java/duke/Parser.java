package duke;

/**
 * This class deals with user's input by parsing them and convert each instruction
 * into different commands.
 */
public class Parser {
    private enum KeyWord {
        END("bye"),
        DELETE("delete"),
        LIST("list"),
        GET("get"),
        SEARCH("find"),
        DONE("done");

        private final String k;

        KeyWord(String k) {
            this.k = k;
        }

        public String getK() {
            return k;
        }
    }

    /**
     * Assesses the input from the user line by line.
     *
     * @param next next line to be assessed.
     * @return an int indicating next step to be taken.
     */
    public int parse(String next) {
        if (next.equals(KeyWord.END.getK())) {
            return 0;
        } else if (next.equals(KeyWord.LIST.getK())) {
            return 1;
        } else if (next.length() > 3 && next.substring(0, 4).equals(KeyWord.DONE.getK())) {
            return 2;
        } else if (next.length() > 5 && next.substring(0, 6).equals(KeyWord.DELETE.getK())) {
            return 3;
        } else if (next.length() > 2 && next.substring(0, 3).equals(KeyWord.GET.getK())) {
            return 4;
        } else if (next.length() > 3 && next.substring(0, 4).equals(KeyWord.SEARCH.getK())) {
            return 6;
        } else {
            return 5;
        }
    }
}
