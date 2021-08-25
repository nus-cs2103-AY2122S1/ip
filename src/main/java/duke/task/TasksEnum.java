package duke.task;

import duke.DukeException;

/**
 * An enum of tasks that each have a method to create a new instance of the task.
 */
public enum TasksEnum {
    TODO () {
        @Override
        public Task getTask(String input) {
            if (input == null || input.equals("")) {
                throw new DukeException("The description of this todo task cannot be empty.");
            }
            return new Todo(input);
        }
    },
    EVENT () {
        @Override
        public Task getTask(String input) {
            if (input == null || !input.contains(" /at ")) {
                throw new DukeException("There should be a description followed by \"/at\" and" +
                        " then the date and time of the event.");
            }
            String[] splitOtherInput = input.split(" /at ", 2);
            return new Event(splitOtherInput[0], splitOtherInput[1]);
        }
    },
    DEADLINE () {
        @Override
        public Task getTask(String input) {
            if (input == null || !input.contains(" /by ")) {
                throw new DukeException("There should be a description followed by \"/by\" and" +
                        " then the date and time of the deadline.");
            }
            String[] splitOtherInput = input.split(" /by ", 2);
            return new Deadline(splitOtherInput[0], splitOtherInput[1]);
        }
    };

    /**
     * Creates a new task based on the description and date/time.
     * @param input Contains the description and/or date/time of the task.
     * @return the new task
     */
    public abstract Task getTask(String input) throws DukeException;
}
