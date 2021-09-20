package jarvis.message;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GreetingMessageTest {
    private static final String DEFAULT_GREETING_MESSAGE = String.format(
            "%s\n%s\n%s\n\n%s",
            "Starting Jarvis Neural Network...",
            "Downloading Start Industries Database...",
            "Jarvis up and running...",
            "Hello, I'm Jarvis! How can I help you?"
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
