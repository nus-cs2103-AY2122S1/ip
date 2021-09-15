package duke;

import duke.Deadline;

public class DeadlineInput extends InputHandler {

    public DeadlineInput(Ui ui, TaskList taskList) throws DukeException {
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
    public String handle(String input) throws EmptyDescriptionException {
        if (input.length() == 8) {
            throw new EmptyDescriptionException("error" );
        }
        int charIndex = input.indexOf("/" );
        int byIndex = charIndex + 4;
        String by = input.substring(byIndex);
        String task = input.substring(9, charIndex - 1);
        Task deadlineTask = new Deadline(task, by);
        taskList.add(deadlineTask);
        return ui.addTask(deadlineTask);
    }
}
