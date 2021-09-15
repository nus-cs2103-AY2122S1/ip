package duke;

public class DoneInput extends InputHandler {

    public DoneInput(Ui ui, TaskList taskList) throws DukeException {
        super(ui, taskList);
    }

    /**
     * Handles the case where the user wants to add a deadline task.
     *
     * @param input
     * @return
     * @throws EmptyDescriptionException
     */
    @Override
    public String handle (String input) throws EmptyDescriptionException {
        if (input.length() == 4) {
            throw new EmptyDescriptionException("error");
        }

        char taskIndex = input.charAt(5);
        int index = Integer.parseInt(String.valueOf(taskIndex));

        Task taskAtIndex = taskList.getTask(index);
        taskAtIndex.markAsDone();

        return ui.doneTask(taskAtIndex);
    }

}
