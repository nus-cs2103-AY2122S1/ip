import java.util.Scanner;

public class Processor {
    private Scanner input;
    private String command;
    private String message;

    /**
     * Constructor of the class `Processor`.
     */
    public Processor() {
        this.input = new Scanner(System.in);
    }

    /**
     * Reads and processes a command, updates the message to be printed.
     */
    public void process() {
        this.command = this.input.nextLine();
        this.message = this.command;
    }

    /**
     * Returns a command's result as a string.
     *
     * @return String representation of the result of processing a command.
     */
    @Override
    public String toString() {
        return "____________________________________________________________\n" +
                this.message + "\n" +
                "____________________________________________________________\n";
    }
}
