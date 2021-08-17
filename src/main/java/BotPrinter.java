public class BotPrinter {


    final String HORIZONTAL_LINE =
                    "\t  .--.      .-'.      .--.      .--.      .--.      .--.      .`-.      .--.\n" +
                    "\t:::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\::::::::.\\\n" +
                    "\t'      `--'      `.-'      `--'      `--'      `--'      `-.'      `--'      `";

    BotPrinter(){}

    void print(String input){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + input);
        System.out.println(HORIZONTAL_LINE);
    }
}
