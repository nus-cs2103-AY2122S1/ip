public class Task {
    private String title;
    private boolean completed;

    public Task(String input) {
        this.title = input;
        this.completed = false;
    }

    public String completeItem() {
        this.completed = true;
        return "Nice I've marked this task as done!\n" + this.toString();
    }


    @Override
    public String toString() {
        String complete = this.completed ? "[X]" : "[ ]";
        return complete + " " + this.title;
    }
}
