import java.util.ArrayList;

public class DukeList {
    private ArrayList<Task> list = new ArrayList<>();

    public String addTask(Task task) {
        this.list.add(task);
        return "\tGot it. I've added this task:\n\t\t " + task.toString()
                + "\n\tNow you have " + list.size() + " tasks in the list.";
    }

    public String displayTask() {
        StringBuilder output = new StringBuilder("\tHere are the tasks in your list:\n");
        int i = 1;
        for (Task task: list) {
            String temp = "\t" + i + "." + task.toString() + "\n";
            output.append(temp);
            i++;
        }
        return output.toString();
    }

    public String markTask(int index) {
        return "\tNice! I've marked this task as done:\n\t\t" + list.get(index - 1).markDone();
    }

    public String handleInput(String input) {
        String[] inputArray = input.split(" ",2);
        String reply = "";
        String[] tempArray;

        switch (inputArray[0]) {
            case "list":
                reply = this.displayTask();
                break;
            case "done":
                reply = this.markTask(Integer.parseInt(inputArray[1]));
                break;
            case "todo":
                reply = this.addTask(new Todo(inputArray[1]));
                break;
            case "deadline":
                tempArray = inputArray[1].split(" /by ");
                reply = this.addTask(new Deadline(tempArray[0], tempArray[1]));
                break;
            case "event":
                tempArray = inputArray[1].split(" /at ");
                reply = this.addTask(new Event(tempArray[0], tempArray[1]));
                break;
            default:
                break;
        }
        return reply;
    }
}
