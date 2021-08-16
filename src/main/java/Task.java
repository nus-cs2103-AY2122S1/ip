public class Task {
    private String description;
    private Boolean done;

    public Task(String input) {
        description = input;
        done = false;
    }

    @Override
    public String toString() {
        String checkBox = done
                ? "[ ] "
                : "[X] ";
        return checkBox + description;
    }
}
