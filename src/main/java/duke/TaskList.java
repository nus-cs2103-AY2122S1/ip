package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList; 
    }
    
    public void add(Task newTask) {
        taskList.add(newTask);
        System.out.println("Just added:\n" + newTask.toString());
        System.out.println("You currently have " + taskList.size() + " tasks in the list.");
    }

    public void editTask(String[] input) throws DukeException {
        String num = input[1];
        int index = Integer.parseInt(num) - 1;
        if (index >= taskList.size() || index == 0) {
            throw new DukeException("There is no such task in your list D:");
        }
        String command = input[0];
        switch (command) {
            case "done":
                Task toMark = taskList.get(index);
                toMark.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" 
                        + toMark.toString());
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
    
    public String convertToData() {
        StringBuilder data = new StringBuilder();
        for (Task task : taskList) {
            data.append(task.toData() + "\n"); 
        }
        return data.toString(); 
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
