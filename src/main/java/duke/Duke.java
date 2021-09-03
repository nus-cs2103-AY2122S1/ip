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
        new Duke(LOCAL_FILE).run();

    }
}
