package duke;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Represents the Duke chatbot. The chatbot currently acts as a to-do list that can receives3 different types of tasks.
 * You can mark the tasks as done and delete them when you're done with the tasks.
 */
public class Duke extends Application {

    private static Parser parser = new Parser();
    private duke.Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Duke class.
     */
    public Duke() {
        ui = new Ui();
        storage = new duke.Storage("data/alexa.txt");
        try {
            Ui.print("Hello! My name is Alexa \nHow can I help you today?");
            tasks = new TaskList(duke.Storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the Duke chatbot.
     * It is actively waiting for inputs from the User in order to perform its functions.
     */
    public static void run() {
        Scanner newInput = new Scanner(System.in);
        while (newInput.hasNextLine()) {
            String input = newInput.nextLine();
            duke.Task currentTask = new duke.Task(input);
            try {
                if (input.equals("bye")) {
                    Ui.print("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    Ui.list();
                } else if (parser.parseDone(input)) {
                    Ui.done(input);
                } else if (parser.parseFind(input)) {
                    Ui.find(input);
                } else if (parser.parseToDo(input)) {
                    TaskList.todo(input);
                } else if (parser.parseDeadline(input)) {
                    TaskList.deadline(input);
                } else if (parser.parseEvent(input)) {
                    TaskList.event(input);
                } else if (parser.parseDelete(input)) {
                    TaskList.delete(input);
                } else {
                    Ui.invalidInput();
                }
                duke.Storage.writeTasks();
            } catch (DukeException err) {
                Ui.print(err.getMessage());
            }
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }
}
