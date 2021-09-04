package duke.exception;

import duke.util.Instruction;

/**
 * Exception for missing index in Duke program.
 *
 * @author Chng Zi Hao
 */
public class DukeMissingIndexException extends DukeException {
    /**
     * Constructor for DukeMissingIndexException
     *
     * @param instruction Command executed.
     */
    public DukeMissingIndexException(Instruction instruction) {
        super(String.format("Error: OOPS!!! The index argument for %s cannot be empty.",
                instruction.label));
    }
}
