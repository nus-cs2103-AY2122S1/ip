package bot.assembly.function;

/**
 * A class in charge of printing system response to the console
 */
public class BotPrinter {

    private final String HORIZONTAL_LINE =
                    "\t  .--.      .-'.      .--.      .--.      .--.      .--.      .`-.      .--.\n" +
                    "\t:::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\\n" +
                    "\t'      `--'      `.-'      `--'      `--'      `--'      `-.'      `--'      `";

    /**
     * Constructor
     */
    public BotPrinter(){}

    /**
     * A method that prints each system response message within the two horizontal divider
     * @param input system feedback
     */
    public void print(String input){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + input);
        System.out.println(HORIZONTAL_LINE);
    }
}
