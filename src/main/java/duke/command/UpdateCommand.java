package duke.command;

import duke.exception.DukeException;
import duke.util.Tasklist;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Description:
 * Class that encapsulates the update task command inputted by the user
 *
 * @author Keith Tan
 */
public class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";
    private Tasklist list;
    private int toUpdate;
    private String updateType;
    private String newField;

    /**
     * Constructor for UpdateCommand.
     * Takes in the task number to edit description and update arguments to create an update command
     */
    public UpdateCommand(Tasklist list, String[] updateDetails) {

        this.list = list;
        this.toUpdate = Integer.parseInt(updateDetails[0]);
        this.updateType = updateDetails[1];
        this.newField = updateDetails[2].trim();

    }

    /**
     * Executes the update command and updates the inputted task in the task list
     *
     * @throws DukeException throws an exception if any Duke Error occurs while running
     *                       the associated methods
     */
    @Override
    public String execute() throws DukeException {

        if (updateType.equals("desc")) {

            String successMessage = this.list.updateDescription(toUpdate, newField);
            return successMessage;

        } else {

            if (updateType.equals("sdt")) {
                String successMessage = this.list.updateStartDateTime(toUpdate , newField);
                return successMessage;

            } else {
                assert updateType.equals("edt") : "No date time of this type to update";
                String successMessage = this.list.updateEndDateTime(toUpdate, newField);
                return successMessage;
            }


        }


    }
}
