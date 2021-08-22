package duke.exception;

import duke.util.Instruction;

public class DukeMissingIndexException extends DukeException {
    public DukeMissingIndexException(Instruction instruction) {
        super(String.format("Error: OOPS!!! The index argument for %s cannot be empty.",
                instruction.label));
    }
}
