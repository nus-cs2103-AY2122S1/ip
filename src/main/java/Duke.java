import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

import java.io.File;
import java.io.IOException;

public class Duke {
    final private static String strBreak = "    ____________________________________________________________\n";
    final private static String fileName = "taskList.txt";
    final private FileManager taskListManager = new FileManager(Duke.fileName);
    private ArrayList<Task> tasks;
    private int count;

    private Duke() {
        tasks = taskListManager.getTaskList();
        count = tasks.size();
    }

    private void addTask(String input) throws DukeException {
        String type = input.split(" ")[0].toLowerCase();
        String taskDescription = Stream.of(input.split(" "))
                .skip(1).reduce("", (x, y) -> x + " " + y);
        Task newTask = Task.makeTask(type, taskDescription);
        tasks.add(newTask);
        count++;
        String toPrint = String.format("     Got it. I've added this task:\n     %s\n     Now you have %x task%s in the list.",
                newTask.toString(), this.count, this.count > 1 ? "s" : "");
        System.out.println(strBreak + toPrint + "\n" + strBreak);
    }

    private String getTasks() {
        if (count == 0) {
            return (
                    strBreak + "    You have nothing on your list\n" + strBreak
                    );
        }
        String tasksStr = strBreak + "\n    Here are the tasks in your list:\n";
        for (int i = 0; i < count; i++) {
            tasksStr += "     " + (i + 1) + ". " + this.tasks.get(i).toString() + "\n";
        }
        return (tasksStr + strBreak);
    }

    public static void main(String[] args) {
        Duke currentDuke = new Duke();
        System.out.println("Urgh I hate having to wake up. Why did you do that");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.replaceAll("\\s+","").toLowerCase().equals("list")) {
                System.out.println(currentDuke.getTasks());
            } else if (input.split(" ")[0].toLowerCase().equals("done")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                currentDuke.tasks.get(index - 1).markDone(true);
            } else if (input.split(" ")[0].toLowerCase().equals("delete")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                currentDuke.removeTask(index - 1);
            } else {
                try {
                    currentDuke.addTask(input);
                } catch (DukeException e){
                    System.out.println(e);
                }
            }
            input = sc.nextLine();
        }
        currentDuke.sayBye();
    }

    private void sayBye() {
        taskListManager.updateTaskList(tasks);
        System.out.println("Don't wake me up again");
    }

    private void removeTask(int index) {
        Task removedTask = this.tasks.get(index);
        this.tasks.remove(index);
        this.count--;
        System.out.println(String.format("%s\n    Noted. I've removed this task:\n    %s\n    Now you have %x tasks in the list\n%s",
                strBreak, removedTask, this.count, strBreak));
    }
}
