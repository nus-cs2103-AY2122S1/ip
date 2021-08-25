import exceptions.DukeException;
import task.Task;

import java.time.LocalDate;
import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void init() {
        System.out.println(Constants.logo + Constants.HELLO + Constants.LINE);
    }

    public String read() {
        try {
            if (sc.hasNext()) {
                return sc.nextLine();
            } else {
                return "bye";
            }
        } catch (DukeException ex) {
            throw new DukeException("Missing Input!");
        }
    }


    public void displayBye() {
        System.out.println(Constants.BYE);
        System.exit(0);
    }

    public void displayList(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        list.print();
    }

    public void displayDone(String taskDetails) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskDetails);
    }


    public void displayDelete(String taskDetails, TaskList list) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskDetails);
        list.printRemainingTasks();

    }

    public void displayTasksOn(LocalDate date, TaskList list) {
        if (!list.isEmpty()) {
            list.print();
        } else {
            System.out.println("No tasks are due on " + date + "!");
        }
    }

    public void displayLoadingError(Exception ex) {
        System.out.println(ex.toString());
    }

    public void displayError(String message) {
        System.out.println(message);
    }

    public void displayLine() {
        System.out.println(Constants.LINE);
    }

    public void displayAdd(Task task, TaskList taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        taskList.printRemainingTasks();
    }

}
