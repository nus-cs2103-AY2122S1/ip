package duke;


import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;
import javafx.application.Platform;

public class Ui {

    private Scanner scanner = null;
    private boolean isGui = false;
    private GraphicalUserInterface gui = null;


    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void addGui(GraphicalUserInterface gui) {
        scanner.close();
        this.gui = gui;
        this.isGui = true;
    }


    protected void showException(DukeException e) {
        if (isGui) {
            gui.displayDukeReply(e.getMessage() + "\n");
            return;
        }
        System.out.println(e.getMessage());
        System.out.println();
    }

    protected void greet() {
        if (isGui) {
            gui.displayDukeReply("Good Day Sir/Mdm, I am Duke.\nWhat can I do for you?\n");
        }
        System.out.println("Good Day Sir/Mdm, I am Duke.\nWhat can I do for you?\n");
    }

    public void end() {
        if (isGui) {
            gui.displayDukeReply("Goodbye Sir/Mdm. Hope to serve you again soon!\n");
            Platform.exit();
            return;
        }
        scanner.close();
        System.out.println("Goodbye Sir/Mdm. Hope to serve you again soon!\n");
    }

    public void add(Task task, TaskList tasks) {
        if (isGui) {
            gui.displayDukeReply("Understood Sir/Mdm, I have added the indicated task: " + "\n   " + task + "\n"
                + "Now you have " + tasks.size() + (tasks.size() == 1 ? " task." : " tasks.") + "\n");
            return;
        }


        System.out.println("Understood Sir/Mdm, I have added the indicated task: " + "\n   " + task);
        System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task." : " tasks.") + "\n");
    }

    public void showList(TaskList tasks) {
        if (isGui) {
            gui.displayDukeReply("Here are your tasks Sir/Mdm:\n" + list(tasks) + "\n");
            return;
        }


        System.out.println("Here are your tasks Sir/Mdm:");
        System.out.println(list(tasks));
        System.out.println();
    }

    public void markDone(Task task) {
        if (isGui) {
            gui.displayDukeReply("Good job Sir/Mdm! I shall mark this task as complete:\n   "
                + task + "\n");
            return;
        }

        System.out.println("Good job Sir/Mdm! I shall mark this task as complete:\n   "
            + task + "\n");
    }

    public void delete(Task task, TaskList tasks) {
        if (isGui) {
            gui.displayDukeReply("Much obliged Sir/Mdm! I shall delete this task:\n   "
                + task + "\n" + "Now you have " + tasks.size()
                + (tasks.size() == 1 ? " task." : " tasks.") + "\n");
            return;
        }
        System.out.println("Much obliged Sir/Mdm! I shall delete this task:\n   "
            + task + "\n" + "Now you have " + tasks.size()
            + (tasks.size() == 1 ? " task." : " tasks.") + "\n");

    }

    public String list(TaskList tasks) {
        String list = "";
        for (int i = 0; i < tasks.size(); i++) {
            list = list + (i == 0 ? "" : "\n") + (i + 1) + ". " + tasks.get(i);
        }
        return list;
    }

    public void findByDate(TaskList foundTasks) {
        String message = "Here are the deadlines and events that match the date Sir/Mdm:\n"
            + list(foundTasks);

        if (isGui) {
            gui.displayDukeReply(message + "\n");
            return;
        }

        System.out.println(message);
        System.out.println();
    }

    public void findByDescription(TaskList foundTasks) {
        String message = "Here are the results of the search Sir/Mdm:\n"
            + list(foundTasks);

        if (isGui) {
            gui.displayDukeReply(message + "\n");
            return;
        }

        System.out.println(message);
        System.out.println();
    }

    protected String readCommand() throws DukeException {
        if (isGui) {
            throw new DukeException("Something wrong happened: Cannot read command from GUI");
        }

        return scanner.nextLine();
    }

}
