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
            result.append(String.format("%d. %s\n", i + 1, tasks[i].taskName));
        }
        return result.toString();
    }

    private class Task {
        private final String taskName;

        public Task(String taskName) {
            this.taskName = taskName;
        }
    }
}
