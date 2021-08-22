package duke.exception;

import duke.util.Instruction;

public class DukeMissingDescriptionException extends DukeException {
    public DukeMissingDescriptionException(Instruction instruction) {
        super(String.format("Error: OOPS!!! The description of %s cannot be empty.",
                instruction.label));
    }
}
