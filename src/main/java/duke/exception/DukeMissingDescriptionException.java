package duke.exception;

import duke.util.Instruction;

/**
 * Exception for missing description in Duke program.
 *
 * @author Chng Zi Hao
 */
public class DukeMissingDescriptionException extends DukeException {
    /**
     * Constructor for DukeMissingDescriptionException
     *
     * @param instruction Command executed.
     */
    public DukeMissingDescriptionException(Instruction instruction) {
        super(String.format("Error: OOPS!!! The description of %s cannot be empty.",
                instruction.label));
    }
}
