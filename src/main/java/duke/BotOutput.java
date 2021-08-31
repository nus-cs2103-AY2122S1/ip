package duke;

/**
 * BotOutput class encapsulates responses from duke.
 */
public class BotOutput {
    private final String botOutput;
    private final boolean isExit;

    public BotOutput(String botOutput, boolean isExit) {
        this.botOutput = botOutput;
        this.isExit = isExit;
    }

    public String getBotOutput() {
        return botOutput;
    }

    public boolean isExit() {
        return isExit;
    }
}