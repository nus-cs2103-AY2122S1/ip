package duke.exception;

import duke.Ui.Descriptors;
import duke.Ui.UserCommands;

/**
 * Represents an exception caused by user providing the wrong descriptor.
 */
public class WrongDescriptorException extends DukeException {
    /**
     * Constructor for WrongDescriptorException.
     *
     * @param descriptor The expected descriptor.
     * @param userCommand UserCommand for which wrong descriptor was provided.
     */
    public WrongDescriptorException(Descriptors descriptor, UserCommands userCommand) {
        super("Wrong descriptor used. Descriptor for " + userCommand.getCommand()
                + " should be " + descriptor.getDescriptor() + ".");
    }
}
