public class TodoList {
    private final Task[] tasks = new Task[100];
    private int emptyIndex = 0;
    public TodoList() { }

    public String add(String taskName) {
        Task task = new Task(taskName);
        tasks[emptyIndex] = task;
        emptyIndex++;
        return "added: " + taskName;
    }

    public String list() {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < emptyIndex; i++) {
            result.append(String.format("%d. %s\n", i + 1, tasks[i].toString()));
        }
        return result.toString();
    }

    public String markAsDone(int taskNumber) {
        try {
            Task task = tasks[taskNumber - 1];
            task.markAsDone();
            return String.format("Task %d marked as done:\n\t%s",
                    taskNumber,
                    task);
        } catch (IndexOutOfBoundsException e) {
            return String.format("Task number %d invalid", taskNumber);
        }
    }

    private static class Task {
        private final String taskName;
        private boolean done = false;

        public Task(String taskName) {
            this.taskName = taskName;
        }

        public void markAsDone() {
            this.done = true;
        }

        @Override
        public String toString() {
            if(done) {
                return String.format("[X] %s", this.taskName);
            }
            return String.format("[ ] %s", this.taskName);
        }
    }
}
