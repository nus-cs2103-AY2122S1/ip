package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static final String LOCAL_FILE = "data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.load();
        } catch (DukeException e) {
            ui.showError(e);
        }
    }

    public void run() {
        this.ui.welcome();

        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(sc.nextLine());


        while (!parser.isBye()) {
            try {
                if (parser.isList()) {
                    ui.list(this.tasks);

                    parser = new Parser(sc.nextLine());
                } else if (parser.isDone()) {
                    try {
                        this.tasks.done(parser.secondPartInInt());
                        this.storage.save(parser.getCommand());
                        ui.done(this.tasks.getMostRecent());

                        parser = new Parser(sc.nextLine());
                    } catch (DukeException e) {
                        ui.showError(e);

                        parser = new Parser(sc.nextLine());
                    }
                } else if (parser.isToDo()) {
                    ToDo task;
                    try {
                        task = new ToDo(parser.secondPart());
                        this.tasks.add(task);
                        this.storage.save(parser.getCommand());
                        ui.addTask(this.tasks.getMostRecent(), this.tasks);

                        parser = new Parser(sc.nextLine());
                    } catch (DukeException e) {
                        ui.showError(e);

                        parser = new Parser(sc.nextLine());
                    }
                } else if (parser.isDeadline()) {
                    Deadline task;
                    try {
                        task = new Deadline(parser.deadline()[0], parser.deadline()[1]);
                        this.tasks.add(task);
                        this.storage.save(parser.getCommand());
                        ui.addTask(this.tasks.getMostRecent(), this.tasks);

                        parser = new Parser(sc.nextLine());
                    } catch (DukeException e) {
                        ui.showError(e);

                        parser = new Parser(sc.nextLine());
                    }
                } else if (parser.isEvent()) {
                    Event task;
                    try {
                        task = new Event(parser.event()[0], parser.event()[1]);
                        this.tasks.add(task);
                        this.storage.save(parser.getCommand());
                        ui.addTask(this.tasks.getMostRecent(), this.tasks);

                        parser = new Parser(sc.nextLine());
                    } catch (DukeException e) {
                        ui.showError(e);

                        parser = new Parser(sc.nextLine());
                    }
                } else if (parser.isDelete()) {
                    try {
                        this.tasks.delete(parser.secondPartInInt());
                        ui.deleteTask(this.tasks.getMostRecent(), this.tasks);

                        parser = new Parser(sc.nextLine());
                    } catch (DukeException e) {
                        ui.showError(e);

                        parser = new Parser(sc.nextLine());
                    }
                } else {
                    throw new DukeException("I do not know what you want to do!");
                }
            } catch (DukeException e) {
                ui.showError(e);

                parser = new Parser(sc.nextLine());
            }
        }

        ui.bye();
        sc.close();
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        String[] b = a.split(" ", 2);

        ArrayList<Task> history = new ArrayList<>();

        File importedFile = new File(LOCAL_FILE);


            while (!a.equals("bye")) {
                try {
                    if (a.equals("list")) {
                        System.out.println("Here are the tasks in your list:");

                        int length = history.size();
                        for (int i = 0; i < length; i++) {
                            System.out.println(String.valueOf(i + 1) + ". " + history.get(i));
                        }
                        a = sc.nextLine();
                        b = a.split(" ", 2);

                    } else if (b[0].equals("done")) {
                        try {
                            int taskIndex = Integer.valueOf(b[1]);
                            Task completedTask = history.get(taskIndex - 1);
                            completedTask.Done();
                            System.out.println("Nice! I have marked this task as done!");
                            System.out.println(completedTask);

                            try {
                                appendToFile(LOCAL_FILE, a + System.lineSeparator());
                            } catch (IOException e) {
                                System.out.println("Something went wrong: " + e.getMessage());
                            }

                            a = sc.nextLine();
                            b = a.split(" ", 2);
                        } catch (ArrayIndexOutOfBoundsException error) {
                            System.out.println(":(( sorry bud but which specific task is done?");

                            a = sc.nextLine();
                            b = a.split(" ", 2);
                        }
                    } else if (b[0].equals("todo")) {
                        try {
                            ToDo task = new ToDo(b[1]);
                            history.add(task);
                            int length = history.size();

                            System.out.println("Added task:");
                            System.out.println(task);
                            System.out.println("You have " + length + " tasks in the list");

                            try {
                                appendToFile(LOCAL_FILE, a + System.lineSeparator());
                            } catch (IOException e) {
                                System.out.println("Something went wrong: " + e.getMessage());
                            }

                            a = sc.nextLine();
                            b = a.split(" ", 2);
                        } catch (ArrayIndexOutOfBoundsException error) {
                            System.out.println(":(( sorry bud but the description of your todo cannot be empty!");

                            a = sc.nextLine();
                            b = a.split(" ", 2);
                        }
                    } else if (b[0].equals("deadline")) {
                        String[] c = b[1].split(" /by ", 2);
                        Deadline task = new Deadline(c[0], c[1]);
                        history.add(task);
                        int length = history.size();

                        System.out.println("Added task:");
                        System.out.println(task);
                        System.out.println("You have " + String.valueOf(length) + " tasks in the list");

                        try {
                            appendToFile(LOCAL_FILE, a + System.lineSeparator());
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }

                        a = sc.nextLine();
                        b = a.split(" ", 2);

                    } else if (b[0].equals("event")) {
                        String[] c = b[1].split(" /at ", 2);
                        Event task = new Event(c[0], c[1]);
                        history.add(task);
                        int length = history.size();

                        System.out.println("Added task:");
                        System.out.println(task);
                        System.out.println("You have " + String.valueOf(length) + " tasks in the list");

                        try {
                            appendToFile(LOCAL_FILE, a + System.lineSeparator());
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }

                        a = sc.nextLine();
                        b = a.split(" ", 2);
                    } else if (b[0].equals("delete")) {
                        int taskIndex = Integer.valueOf(b[1]);
                        Task removed = history.get(taskIndex - 1);

                        history.remove(taskIndex - 1);
                        int length = history.size();

                        System.out.println("Ok! I have removed this task:");
                        System.out.println(removed);
                        System.out.println("Now you have " + String.valueOf(length) + " tasks in the list.");

                        try {
                            appendToFile(LOCAL_FILE, a + System.lineSeparator());
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }

                        a = sc.nextLine();
                        b = a.split(" ", 2);
                    } else {
                        throw new DukeException("I do not know what you want to do!");
                    }
                } catch (DukeException error) {
                    System.out.println(error);

                    a = sc.nextLine();
                    b = a.split(" ", 2);
                }
            }
            System.out.println("Bye! Hope to see you again soon!");
            sc.close();

    }
}
