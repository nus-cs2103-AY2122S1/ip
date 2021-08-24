/**
 * Represents a processor that stops the duke program.
 */
public class ExitProcessor extends Processor {
    /** Message to be printed when the program exits */
    private static final String EXITING_MESSAGE = "Bye. Hope to see you again soon!\n";

    /**
     * Constructor of the class `ExitProcessor`.
     */
    public ExitProcessor(Duke duke) {
        super("bye", duke);
    }

    @Override
    public boolean process() {
        this.message = ExitProcessor.EXITING_MESSAGE;
        return false;
    }
}
