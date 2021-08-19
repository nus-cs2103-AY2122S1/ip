import java.util.Scanner;

/**
 * Waits for and processes a User Input
 */
public class DukeListener {
    final Scanner sc;
    final DukeParser parser;

    /** Constructor for Listener that takes instantiates a Scanner and a Parser object
     *
     * @param taskList The TaskList that the parser should manage
     */
    public DukeListener(TaskList taskList) {
        sc = new Scanner(System.in);
        parser = new DukeParser(taskList);
    }

    /**Waits for user input.
     * Transfers input to the parser, and/or stops terminates the listening.
     */
    public void startListen() {
        while(true) {
            // Receive Input text
            String input = sc.nextLine();
            System.out.println(Ui.LINE);

            // Stop listening if "gubbai" is mentioned
            if (input.equals("gubbai")) { break; }

            parser.parseInput(input);
            System.out.println(Ui.LINE);
        }

        // Quit the program after listening stops
        System.out.println(
                Ui.OUTPUT_DISPLAY + "kimi no unmei no hito wa boku jyanai\n"
                + Ui.LINE
        );

        sc.close();
    }
}
