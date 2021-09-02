package bot.assembly.function;

import java.util.Arrays;

/**
 * A class in charge of printing system response to the console
 */
public class BotPrinter {

    private final String HORIZONTAL_LINE =
                    "\t  .--.      .-'.      .--.      .--.      .--.      .--.      .`-.      .--.\n"
                    + "\t:::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\\n"
                    + "\t'      `--'      `.-'      `--'      `--'      `--'      `-.'      `--'      `";

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

    /**
     * A method that prints each system response message within the two horizontal divider
     * @param input system feedback
     */
    public void print(String input) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + input);
        System.out.println(HORIZONTAL_LINE);
    }
}
