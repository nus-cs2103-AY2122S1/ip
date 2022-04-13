package bot.assembly.function;

import java.util.Arrays;

/**
 * A class in charge of printing system response to the console
 */
public class BotPrinter {
    /**
     * Constructor
     */
    public BotPrinter() {}

    /**
     * A method that helps to join all strings together as one string
     * @param strings to be joined together
     * @return one single connected string
     */
    public String stringBuilder(String... strings) {
        StringBuilder newString = new StringBuilder();
        Arrays.stream(strings).forEach(newString::append);
        return newString.toString();
    }
}
