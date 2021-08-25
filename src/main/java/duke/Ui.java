package duke;


import java.util.Scanner;

/**
 * Class to handle monitoring and taking in input.
 */
public class Ui {

    final private Parser parser;

    protected Ui(Parser parser) {
        this.parser = parser;
    }

    protected void monitor() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            this.parser.takeInput(input);
            if (input.strip().equalsIgnoreCase("bye")) {
                break;
            }
        }
        sc.close();
    }

    /**
     * Function to print indented, outlined output to console.
     *
     * @param output Multi-line or single line string.
     */
    public void renderOutput(String output) {
        System.out.println("    ____________________________________________________________");
        output.lines().map(x -> "     " + x).forEach(System.out::println);
        System.out.println("    ____________________________________________________________");
    }
}
