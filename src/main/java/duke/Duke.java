package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    protected static Storage storage;
    protected static TaskList tasks;
    protected static Ui ui;

    public Duke(String filePath) throws FileNotFoundException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.readCommand();
                Scanner s = new Scanner(System.in);
                String command = s.nextLine();
                isExit = Parser.parse(command);
                //Command c = duke.Parser.parse(fullCommand);
                //c.execute(tasks, ui, storage);
            //} catch (duke.DukeException e) {
            //    ui.showError(e.getMessage());
            } catch (DukeException | IOException e) {
                e.printStackTrace();
            } finally {
                ui.showDivider();
            }
        }
        ui.showGoodbyeMessage();
    }

    public static void markDone(int i) throws DukeException {
        System.out.println("Nice! I've marked this task as done: ");
        tasks.getTask(i - 1).markAsDone();
        TaskList.updateMemory(storage.getPath(), tasks);
        String res = tasks.getTask(i-1).toString();
        System.out.println(res + "\n");
    }

    public static void getTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            String res = (i + 1) + ". " + tasks.getTask(i);
            System.out.println(res);
        }
    }

    public static void toDo(String input) throws DukeException {
        if (input.equals("todo")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.\n");
        }
        String t = input.split("todo ")[1];
        ToDo td = new ToDo(t);
        tasks.addNewTask(td);
        TaskList.updateMemory(storage.getPath(), tasks);
        System.out.println("Got it. I've added this task: \n " + td +
                           "\n" + "Now you have " + tasks.getSize() + " tasks in the list.");
    }

    public static void deadline(String input) throws DukeException {
        String t = input.split("deadline ")[1];
        Deadline dl = new Deadline(t);
        tasks.addNewTask(dl);
        TaskList.updateMemory(storage.getPath(), tasks);
        System.out.println("Got it. I've added this task: \n " + dl
               + "\n" + "Now you have " + tasks.getSize() + " tasks in the list.");
    }

    public static void event(String input) throws DukeException {
        String t = input.split("event ")[1];
        Event e = new Event(t);
        tasks.addNewTask(e);
        TaskList.updateMemory(storage.getPath(), tasks);
        System.out.println("Got it. I've added this task: \n " + e
                            + "/n"  + "Now you have " + tasks.getSize()  + " tasks in the list.");
    }

    public static void deleteTask(int i) throws DukeException {
        System.out.println("Noted. I've removed this task: ");
        String deleted = tasks.getTask(i-1).toString();
        tasks.deleteGivenTask(i - 1);
        TaskList.updateMemory(storage.getPath(), tasks);
        System.out.println(" " + deleted);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Duke("data/duke.txt").run();
    }
}