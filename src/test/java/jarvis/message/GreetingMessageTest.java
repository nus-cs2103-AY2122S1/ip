package jarvis.message;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GreetingMessageTest {
    private static final String DEFAULT_GREETING_MESSAGE = String.format(
            "%s\n%s",
            "Hello! I'm Jarvis!",
            "What can I do for you?"
    );

    @Test
    public void greetingMessage_emptyConstructor_defaultGreetingMessage() {
        GreetingMessage greetingMessage = new GreetingMessage();
        assertEquals(greetingMessage.getMessage(), DEFAULT_GREETING_MESSAGE);
    }

    @Test
    public void greetingMessage_emptyConstructor_defaultGreetingMessageFormatted() {
        GreetingMessage greetingMessage = new GreetingMessage();
        assertEquals(greetingMessage.getFormattedMessage(), String.format("%s\n", DEFAULT_GREETING_MESSAGE));
    }

    @Test
    public void greetingMessage_nonemptyConstructor_customGreetingMessage() {
        String customMessage = "Custom greeting message!";
        GreetingMessage greetingMessage = new GreetingMessage(customMessage);
        assertEquals(greetingMessage.getMessage(), customMessage);
    }
}
