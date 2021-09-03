package message;

/**
 * Encapsulates the base output message that Duke replies to the user.
 * It includes methods to format the output message.
 */
public class Message {
    private final String[] texts;

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
