package duke.task;

import duke.exception.DukeException;

/**
 * An enum of tasks that each have a method to create a new instance of the task.
 */
public enum TasksEnum {
    TODO() {
        @Override
        public Task getTask(String input) {
            if (input == null || input.equals("")) {
                throw new DukeException("The description of this todo task cannot be empty.");
            }
            return new Todo(input);
        }
    },
    EVENT() {
        @Override
        public Task getTask(String input) {
            if (input == null || !input.contains(" /from ")) {
                throw new DukeException("There should be a description followed by \"/from\" and"
                    + " then the starting date and time of the event. To specify end time, use \"/to\".");
            }
            String[] splitOtherInput = input.split(" /from ", 2);
            String[] splitStartAndEnd = splitOtherInput[1].split(" /to ", 2);

            return splitStartAndEnd.length < 2 // No end time
                ? new Event(splitOtherInput[0], splitOtherInput[1], splitOtherInput[1])
                : new Event(splitOtherInput[0], splitStartAndEnd[0], splitStartAndEnd[1]);
        }
    },
    DEADLINE() {
        @Override
        public Task getTask(String input) {
            if (input == null || !input.contains(" /by ")) {
                throw new DukeException("There should be a description followed by \"/by\" and"
                    + " then the date and time of the deadline.");
            }
            String[] splitOtherInput = input.split(" /by ", 2);
            return new Deadline(splitOtherInput[0], splitOtherInput[1]);
        }
    };

    /**
     * Creates a new task based on the description and date/time.
     *
     * @param input Contains the description and/or date/time of the task.
     * @return the new task
     */
    public abstract Task getTask(String input) throws DukeException;
}
