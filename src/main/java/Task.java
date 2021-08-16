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
        String[] splitTask = input.split(" ", 2);

        switch (splitTask[0]) {
            case "todo":
                new Todo(splitTask[1]);
                break;
            case "deadline":
                String[] splitTime = splitTask[1].split("/", 2);
                new Deadline(splitTime[0], splitTime[1]);
                break;
            case "event":
                String[] splitTimeEvent = splitTask[1].split("/", 2);
                new Event(splitTimeEvent[0], splitTimeEvent[1]);
                break;
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
