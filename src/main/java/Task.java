//enum TaskType {
//    ToDo,
//    Deadline,
//    Event
//
//    private String time
//    public abstract
//}

public class Task {
    private String description;
    private boolean done = false;

    public static Task makeTask(String type, String description) {
        switch (type) {
            case "todo": {
                return (new ToDos(description));
            }
            case "deadline": {
                return (new Deadline(description));
            }
            case "event": {
                return (new Event(description));
            }
            default: {
                return null;
            }
        }
    }

    public Task(String description) {
        this.description = description;
    }

    public void markDone() {
        this.done = true;
        System.out.println("Nice! I've marked this task as done:\n" + this.toString());
    }

    @Override
    public String toString() {
        String doneIndicator = this.done
                ? "[X]"
                : "[ ]";
        return (
                doneIndicator + " " + this.description
                );
    }
}
