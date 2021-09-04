package duke.exception;

import duke.Ui.Descriptors;
import duke.Ui.UserCommands;

public class MissingDescriptorException extends DukeException {
    /**
     * Constructor for MissingDescriptorException.
     *
     * @param descriptor The missing descriptor.
     * @param userCommand UserCommand for which descriptor is missing.
     */
    public MissingDescriptorException(Descriptors descriptor, UserCommands userCommand) {
        super("/" + descriptor.getDescriptor()
                + " must be provided and not empty for " + userCommand.getCommand() + ".");
    }
}
