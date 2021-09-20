package jarvis.message;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ExitMessageTest {
    private static final String DEFAULT_EXIT_MESSAGE = "Bye Bye! I am always ready when you need me!";

    @Test
    public void exitMessage_emptyConstructor_defaultExitMessage() {
        ExitMessage exitMessage = new ExitMessage();
        assertEquals(exitMessage.getMessage(), DEFAULT_EXIT_MESSAGE);
    }

    @Test
    public void exitMessage_emptyConstructor_defaultExitMessageFormatted() {
        ExitMessage exitMessage = new ExitMessage();
        assertEquals(exitMessage.getFormattedMessage(), String.format("%s\n", DEFAULT_EXIT_MESSAGE));
    }

    @Test
    public void exitMessage_nonemptyConstructor_customExitMessage() {
        String customMessage = "Custom exit message!";
        ExitMessage exitMessage = new ExitMessage(customMessage);
        assertEquals(exitMessage.getMessage(), customMessage);
    }
}
