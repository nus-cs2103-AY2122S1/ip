import org.json.simple.JSONObject;

public class Task {

    private boolean isCompleted;
    private String description;

    public Task(String description) {
        this.isCompleted = false;
        this.description = description;
    }

//    public void handle(String input) throws InvalidInputException {
//        // parse input first
//        String firstWord = "";
//        switch (firstWord) {
//            case "todo":
//                // create todo and let the todo handle it
//            case "event":
//            case "deadline":
//            default:
//        }
//    }

    public boolean checkCompletion() {
        return this.isCompleted;
    }

    public void complete() {
        this.isCompleted = true;
    }

    public String toString() {
        String output = description;
        if (this.isCompleted) {
            output = output.replaceFirst(" ", "X");
        }
        return output;
    }

    public JSONObject toJsonObject() {
        return null;
    }

    public static Task fromJsonObject(JSONObject obj) throws InvalidInputException {
        Task task;
        String taskType = (String) obj.get("type");
        String description = (String) obj.get("description");
        boolean isCompleted = (boolean) obj.get("isCompleted");
        switch (taskType) {
            case "deadline":
                task = new Deadline(description, (String) obj.get("by"));
                break;
            case "event":
                task = new Event(description, (String) obj.get("by"));
                break;
            case "todo":
                task = new ToDo(description);
                break;
            default:
                throw new InvalidInputException("Wrong input!");
        }
        return task;
    }

}
