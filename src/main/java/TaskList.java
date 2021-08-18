import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void add(Task newTask) {
        taskList.add(newTask);
        System.out.println("Just added:\n" + newTask.toString());
        System.out.println("You currently have " + taskList.size() + " tasks in the list.");
    }

    public void done(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("Pls specify the task index");
        }
        String num = input[1];
        int index = Integer.parseInt(num) - 1;
        if (index >= taskList.size()) {
            throw new DukeException("There is no such task in your list D:");
        } else {
            Task toMark = taskList.get(index);
            toMark.markAsDone();
        }
    }

    @Override
    public String toString() {
        if (taskList.isEmpty()) {
            return "Your list is empty :(";
        }
        String result = "Here's your list!";
        for (int i = 0; i < taskList.size(); i++) {
            String curr = taskList.get(i).toString();
            result += String.format("\n %s. %s", i + 1, curr);
        }
        return result;
    }
}
