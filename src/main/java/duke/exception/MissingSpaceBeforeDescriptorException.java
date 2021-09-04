package duke.exception;

import duke.Ui.Descriptors;

/**
 * Represents an exception caused by user not providing a space before a descriptor.
 */
public class MissingSpaceBeforeDescriptorException extends DukeException {
    /**
     * Constructor for MissingSpaceBeforeDescriptorException.
     *
     * @param descriptor Descriptor for which space is missing before it.
     */
    public MissingSpaceBeforeDescriptorException(Descriptors descriptor) {
        super("There is a missing space before the descriptor " + descriptor.getDescriptor() + ".");
    }
}
