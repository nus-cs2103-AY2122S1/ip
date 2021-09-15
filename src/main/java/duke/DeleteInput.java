package duke;

public class DeleteInput extends InputHandler {

    public DeleteInput(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    /**
     * Handles the case where the user wants to delete a task.
     *
     * @param input
     * @return
     * @throws EmptyDescriptionException
     */
    @Override
    public String handle(String input) throws EmptyDescriptionException {
        if (input.length() == 6) {
            throw new EmptyDescriptionException("error");
        }

        char taskIndex = input.charAt(7);
        int index = Integer.parseInt(String.valueOf(taskIndex));

        Task taskAtIndex = taskList.getTask(index);

        taskList.removeTask(index);
        return ui.deleteTask(taskAtIndex);
    }
}
