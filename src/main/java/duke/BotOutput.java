package duke;

/**
 * BotOutput class encapsulates responses from duke.
 */
public class BotOutput {
    private final String BOT_OUTPUT;
    private final boolean isExit;

    public BotOutput(String botOutput, boolean isExit) {
        this.BOT_OUTPUT = botOutput;
        this.isExit = isExit;
    }
    
    public String getBotOutput() {
        return BOT_OUTPUT;
    }

    public boolean isExit() {
        return isExit;
    }
}
