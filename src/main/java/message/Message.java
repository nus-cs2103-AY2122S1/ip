package message;

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

    /**
     * Gets string representation of `Message`.
     * Each chunk of text in a `Message` is separated by a new line.
     *
     * @return string representation of `Message`.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String text : this.texts) {
            stringBuilder.append(text);
            stringBuilder.append("\n");
        }

        return stringBuilder.toString().trim();
    }
}
