package duke.ui;

import java.util.Objects;

/**
 * The UserInput class encapsulates the components of a user's input, namely the keyword and the arguments.
 *
 * @author cai
 */
public class UserInput {
    private final String raw;
    private final String keyword;
    private final String args;

    UserInput(String raw, String keyword, String args) {
        this.raw = raw;
        this.keyword = keyword;
        this.args = args;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public String getArgs() {
        return this.args;
    }

    public String getRaw() {
        return this.raw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserInput userInput = (UserInput) o;
        return Objects.equals(raw, userInput.raw)
                && Objects.equals(keyword, userInput.keyword)
                && Objects.equals(args, userInput.args);
    }
}
