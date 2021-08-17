import java.util.Scanner;

/**
 * Waits for and processes a User Input
 */
public class DukeListener {

    /** DukeListener has a Scanner to read the input,
     * and a DukeParser to get information from the input
     */
    final Scanner sc;
    final DukeParser parser;

    public DukeListener(TaskList taskList) {
        sc = new Scanner(System.in);
        parser = new DukeParser(taskList);
    }

    public void startListen() {
        while(true) {
            // Receive Input text
            String input = sc.nextLine();
            System.out.println(Ui.LINE);

            if (input.equals("gubbai")) {
                // Stop listening if "gubbai" is mentioned
                break;
            }

            parser.parseInput(input);
            System.out.println(Ui.LINE);
        }

        // Quit the program after listening stops
        System.out.println(
                Ui.OUTPUT_DISPLAY + "kimi no unmei no hito wa boku jyanai\n"
                + Ui.LINE
        );
    }
}
