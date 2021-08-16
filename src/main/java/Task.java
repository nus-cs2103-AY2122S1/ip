public class Task {
    private static int maxId = 1;

    private final int id;
    private String name;
    private boolean completed;

    public Task(String name) {
        this.id = Task.maxId;
        this.name = name;
        this.completed = false;
        Task.increaseMax();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public void updateName(String input) {
        this.name = input;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    public String details() {
        String checkbox = "[" + (getCompleted() ? "X" : " ") + "]";
        return checkbox + " " + this.getName();
    }

    public static void increaseMax() {
        Task.maxId++;
    }

    public static void resetMaxId() {
        Task.maxId = 1;
    }

    public static int getMaxId() {
        return Task.maxId;
    }

    @Override
    public String toString() {
        String checkbox = "[" + (this.getCompleted() ? "X" : " ") + "]";
        return this.getId() + "." + checkbox + " " + this.getName();
    }

}
