import java.util.Objects;

public class Processor {
    /**
     * Processes the user's input based on the commands contained in the input string.
     *
     * @param input the user's input
     */
    public static void process(String input) {
        if (Objects.equals(input, "list")) {
            Memory.print();
            return;
        }
        Memory.add(input);
    }
}
