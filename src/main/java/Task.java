public class Task {
    protected String description;
    protected boolean isDone;

    public enum Category {
        TODO, DEADLINE, EVENT
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static void createTask(String description, String time, Category category) {
        switch (category) {
            case TODO:
                Duke.list.add(new ToDo(description));
                break;
            case DEADLINE:
                Duke.list.add(new Deadline(description, time));
                break;
            case EVENT:
                Duke.list.add(new Event(description, time));
                break;
        }
        System.out.println(Duke.friendGreeting + "added: " + Duke.list.get(Duke.list.size() - 1).toString() + " to your to-do list!");
        System.out.println("Now you have " + Duke.list.size() + " tasks in the list.");
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