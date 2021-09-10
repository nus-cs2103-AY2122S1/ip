package message;

import java.util.Arrays;

/**
 * Encapsulates the base output message that Duke replies to the user.
 * It includes methods to format the output message.
 */
public class Message {
    private String[] texts;

    /**
     * Instantiates a Message holding texts to be output to the user.
     *
     * @param texts Texts are vararg of strings.
     */
    public Message(String ...texts) {
        assert texts.length > 0 : "A message should have least one string";

        this.texts = texts;
    }

    /**
     * Gets string representation of `Message`.
     * Each chunk of text in a `Message` is separated by a new line.
     *
     * @return string representation of `Message`.
     */
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
