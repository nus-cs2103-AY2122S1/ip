package duke.exception;

import duke.Ui.Descriptors;

/**
 * Represents an exception caused by user not providing a space after a descriptor.
 */
public class MissingSpaceAfterDescriptorException extends DukeException {
    /**
     * Constructor for MissingSpaceBeforeDescriptorException.
     *
     * @param descriptor Descriptor for which space is missing before it.
     */
    public MissingSpaceAfterDescriptorException(Descriptors descriptor) {
        super("There is a missing space after the descriptor " + descriptor.getDescriptor() + ".");
    }
}
