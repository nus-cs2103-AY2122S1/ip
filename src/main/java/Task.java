class Task {
    private String Description;

    private Task(String description) {
        this.Description = description;
    }

    public static Task createTask(String description) {
        return new Task(description);
    }

    public String getDescription() {
        return this.Description;
    }
}
