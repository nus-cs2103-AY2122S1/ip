package duke;

import duke.Event;

public class EventInput extends InputHandler {


    public EventInput(Ui ui, TaskList taskList) throws DukeException {
        super(ui, taskList);
    }

    /**
     * Handles the case where a user wants to add an event task.
     *
     * @param input
     * @return
     * @throws EmptyDescriptionException
     */
    public String handle(String input) throws EmptyDescriptionException {
        if (input.length() == 5) {
            throw new EmptyDescriptionException("error" );
        }
        int charIndex = input.indexOf("/" );
        int atIndex = charIndex + 4;

        String at = input.substring(atIndex);
        String task = input.substring(6, charIndex - 1);

        Task eventTask = new Event(task, at);
        taskList.add(eventTask);

        return ui.addTask(eventTask);
    }
}
