package message;

import java.util.Arrays;

/**
 * Encapsulates the base output message that Duke replies to the user.
 * It includes methods to format the output message.
 */
public class Message {
    private final String[] texts;

    public Message(String ...texts) {
        this.texts = texts;
    }

    /**
     * Prints the formatted message for the user to see.
     */
    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(this.texts).forEach((text) -> {
            stringBuilder.append(text);
            stringBuilder.append("\n");
        });

        return stringBuilder.toString().trim();
    }
}
