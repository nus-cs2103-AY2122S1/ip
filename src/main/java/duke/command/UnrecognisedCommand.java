package duke.command;

/**
 * This class encapsulates the command dealing with any invalid/unrecognised commands issued.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class UnrecognisedCommand extends Command {
    @Override
    public String getResponse(String input) {
        return "Hmm.. I didn't catch that. Perhaps you made a typo in your command?";
    }
}
