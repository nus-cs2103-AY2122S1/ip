public class BotPrinter {

    private final String HORIZONTAL_LINE =
                    "\t  .--.      .-'.      .--.      .--.      .--.      .--.      .`-.      .--.\n" +
                    "\t:::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\\n" +
                    "\t'      `--'      `.-'      `--'      `--'      `--'      `-.'      `--'      `";

    // Constructor
    BotPrinter(){}

    /**
     * prints each input message within the two horizontal divider
     * @param input
     */
    public void print(String input){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + input);
        System.out.println(HORIZONTAL_LINE);
    }
}
