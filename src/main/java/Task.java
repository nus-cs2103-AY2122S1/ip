public class Task {
    protected String description;
    protected boolean isDone;
    private static final String dividerWord = " \\| ";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    public String PopulateSaveData() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }

    public static Task LoadFromSaveData(String s) throws DukeException{
        String[] arr = s.split(dividerWord, 4);
        switch (arr[0]) {
            case "T":
                ToDo todo = new ToDo(arr[2]);
                todo.isDone = arr[1].equals("1");
                return todo;
            case "D":
                Deadline deadline = new Deadline(arr[2], arr[3]);
                deadline.isDone = arr[1].equals("1");
                return deadline;
            case "E":
                Event event = new Event(arr[2], arr[3]);
                event.isDone = arr[1].equals("1");
                return event;
            default:
                throw new DukeException("Unknown symbol in save file.");
        }
    }
}