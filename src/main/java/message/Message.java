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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < texts.length; i++) {
            stringBuilder.append(texts[i]);
            stringBuilder.append("\n");
        }

        return stringBuilder.toString().trim();
    }
}
