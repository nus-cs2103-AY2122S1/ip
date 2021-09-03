package duke.command;

/**
 * This class encapsulates the command dealing with any invalid/unrecognised commands issued.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class UnrecognisedCommand extends Command {

    private static final String INVALID_COMMAND_MESSAGE =
            "Hmm.. I didn't catch that. Perhaps you made a typo in your command?";

    @Override
    public String getResponse(String input) {
        assert !input.matches("todo|event|deadline|list|exit|find|filter|done|delete");
        return INVALID_COMMAND_MESSAGE;
    }
}
