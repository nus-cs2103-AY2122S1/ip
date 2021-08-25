package duke;

public class FileParser {
    public FileParser() {

    }

    /**
     * Takes in a string as an input and returns a task to be added to the task list.
     *
     * @param input
     * @return
     * @throws DukeException
     */
    public Task parse(String input) throws DukeException {
        try {
            String[] args = input.split("\\|");
            String taskType = args[0];
            Boolean isCompleted = args[1].contains("X");
            String taskDescription = args[2];
            String datetime = args.length > 3 ? args[3] : "";
            Task task;
            switch(taskType) {
                case "[E]":
                    task = new Event(taskDescription, datetime);
                    break;
                case "[D]":
                    task = new Deadline(taskDescription, datetime);
                    break;
                case "[T]":
                    task = new Todo(taskDescription);
                    break;
                default:
                    throw new DukeException("a line could not be read from the savefile or was read incorrectly. " +
                            "An empty list will be used instead.");
            }

            if (isCompleted) {
                task.setDone();
            }

            return task;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("a line was saved incorrectly. An empty list will be used instead.");
        }

    }


}
