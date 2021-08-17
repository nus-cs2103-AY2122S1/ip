public class Task {
    protected String description;
    private boolean isDone;
    private static Task[] tasks = new Task[100];
    private static int counter = 0;

    private Task(String description) {
        this.description = description;
        this.isDone = false;
        Task.tasks[counter] = this;
        Task.counter++;
    }

    public Task() {}

    private class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + "[" + this.getStatusIcon() + "] " + this.description;
        }
    }

    private class Deadline extends Task {
        private String time;

        public Deadline(String description, String time) {
            super(description);
            this.time = time;
        }

        @Override
        public String toString() {
            return "[D]" + "[" + this.getStatusIcon() + "] " + this.description
                    + "(" + time.replace(" ", ": ") + ")";
        }
    }

    private class Event extends Task {
        private String time;

        public Event(String description, String time) {
            super(description);
            this.time = time;
        }

        @Override
        public String toString() {
            return "[E]" + "[" + this.getStatusIcon() + "] " + this.description
                    + "(" + time.replace(" ", ": ") + ")";
        }
    }

    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void taskDone() {
        this.isDone = true;
    }

    public void markDone(int index) {
        tasks[index].taskDone();
    }

    public void add(String input) {
        input = input.trim();
        String emptyDescriptionError = null;
        String emptyTimelineError = null;
        String[] splitTask = input.split(" ", 2);

        switch (splitTask[0]) {
            case "todo":
                if (splitTask.length == 1) {
                    emptyDescriptionError = "todo";
                    break;
                }
                new Todo(splitTask[1]);
                break;
            case "deadline":
                if (splitTask.length == 1) {
                    emptyDescriptionError = "deadline";
                    break;
                }

                String[] splitTime = splitTask[1].split("/", 2);
                if (splitTime.length == 1) {
                    emptyTimelineError = "deadline";
                    break;
                }

                new Deadline(splitTime[0], splitTime[1]);
                break;
            case "event":
                if (splitTask.length == 1) {
                    emptyDescriptionError = "event";
                    break;
                }

                String[] splitTimeEvent = splitTask[1].split("/", 2);
                if (splitTimeEvent.length == 1) {
                    emptyTimelineError = "event";
                    break;
                }

                new Event(splitTimeEvent[0], splitTimeEvent[1]);
                break;
            default:
                throw new DukeException.InvalidCommandException(input);
        }

        if (emptyDescriptionError != null) {
            throw new DukeException.EmptyDescriptionException(emptyDescriptionError);
        } else if (emptyTimelineError != null) {
            throw new DukeException.EmptyTimelineDescription(emptyTimelineError);
        }
    }

    public static Task retrieveTask(int index) {
        return tasks[index];
    }

    public static int listLength() {
        return Task.counter;
    }

    public static void displayList() {
        System.out.println("\nHere are the tasks in your list:\n--------------------");
        for (int i = 0; i < tasks.length; i++) {
            Task task = tasks[i];
            if (task == null) {
                if (i == 0) System.out.println("List is empty!");
                break;
            }
            int index = i + 1;
            System.out.println(index + ". " + tasks[i]);
        }
    }
}
