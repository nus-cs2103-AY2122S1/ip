public class Task {
    protected String description;
    protected boolean isDone;

    public static Task taskFactory(TaskType taskType, String input) throws DukeException {
        Task newTask = null;

        switch (taskType) {
            case TODO: {
                String description = input.substring(4).trim();
                if (description.isBlank()) {
                    throw new DukeException("Please provide a description of the todo Sir/Mdm.");
                }
                newTask = new Todo(description);
                break;
            }
            case DEADLINE: {
                int index = input.indexOf('/');
                if (index == -1) {
                    if (input.equals("deadline")) {
                        throw new DukeException("Please provide a description and date of the deadline Sir/Mdm.");
                    } else {
                        throw new DukeException("Please provide a date of the deadline Sir/Mdm.");
                    }
                }
                String description = input.substring(9, index).trim();
                if (description.isBlank()) {
                    throw new DukeException("Please provide a description of the deadline Sir/Mdm.");
                }
                String date = input.substring(index + 1);
                newTask = new Deadline(description, date);
                break;
            }
            case EVENT: {
                int index = input.indexOf('/');
                if (index == -1) {
                    if (input.equals("event")) {
                        throw new DukeException("Please provide a description and timeline of the event Sir/Mdm.");
                    } else {
                        throw new DukeException("Please provide a timeline of the event Sir/Mdm.");
                    }
                }

                String description = input.substring(5, index).trim();
                if (description.isBlank()) {
                    throw new DukeException("Please provide a description of the event Sir/Mdm.");
                }
                String date = input.substring(index + 1);
                newTask = new Event(description, date);
                break;
            }
        }
        return newTask;
    }


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
