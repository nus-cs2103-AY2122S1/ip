package duke;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> arrayList;
    TaskList(ArrayList<Task> loaded) {
        arrayList = loaded;
    }

    TaskList() {
        this(new ArrayList<>(100));
    }

    public void listMatchingTasks(String word) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        int counter = 1;
        for (Task t : arrayList) {
            if (t.containWord(word)) {
                sb.append(counter + ". " + t + "\n");
                counter += 1;
            }
        }
        if (counter == 1) {
            sb = new StringBuilder();
            sb.append("No matching tasks found!");
        }
        Ui.printStatement(sb.toString());

    }

    public void listTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list: \n");
        int counter = 1;
        for (Task t : arrayList) {
            sb.append(counter + ". " + t + "\n");
            counter += 1;
        }

        Ui.printStatement(sb.toString());
    }

    public void addTask(Command command, String parameter, String date) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task: \n");
        String parsedDate = Parser.dateParser(date).equals("") ? date : Parser.dateParser(date);
        Task task;

        if (command.equals(Command.TODO)) {
            task = new Todo(parameter);
        } else if (command.equals(Command.DEADLINE)){
            task = new Deadline(parameter, parsedDate);
        } else if (command.equals(Command.EVENT)){
            task = new Event(parameter, parsedDate);
        } else {
            throw new DukeException("Invalid command");
        }
        arrayList.add(task);
        sb.append(task + "\n");
        sb.append("Now you have " + String.valueOf(arrayList.size()) + " tasks in the list.");
        Ui.printStatement(sb.toString());



    }

    public void addTask(Command command, String parameter) {
        addTask(command, parameter, "");
    }

    public void removeTask(int index) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append(arrayList.get(index).toString() + "\n");
        arrayList.remove(index);
        sb.append("Now you have " + arrayList.size() + " tasks in the list.");
        Ui.printStatement(sb.toString());
    }

    public int size() {
        return arrayList.size();
    }

    public void done(Integer i) {
        Task task = arrayList.get(i);
        task.markAsDone();
        Ui.printStatement("Nice! I've marked this task as done:\n" + task);
    }
}