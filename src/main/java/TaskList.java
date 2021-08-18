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

    public void editTask(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("Pls specify the task index");
        } else {
            String num = input[1];
            int index = Integer.parseInt(num) - 1;
            if (index >= taskList.size()) {
                throw new DukeException("There is no such task in your list D:");
            }
            String command = input[0];
            switch (command) {
                case "done":
                    Task toMark = taskList.get(index);
                    toMark.markAsDone();
                    break;
                case "delete":
                    System.out.println("Poof!\n" + taskList.get(index).toString() + "\nis gone");
                    taskList.remove(index);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list");
                    break;
                default:
                    System.out.println("Didn't understand that :(");
                    break;
            }
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
