package duke;

import java.util.NoSuchElementException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.task.TASK_TYPE;

public class Duke extends Application {

    private boolean activated;
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public Duke(String storagePath, String fileName) {
        activated = true;
        ui = new Ui();
        storage = new Storage(storagePath, fileName);
        tasks = new TaskList(storage.getFile());
    }

    private void run() {
        Scanner scannerObj = new Scanner(System.in);
        ui.greet();
        while (activated) {
            String nextIn;

            try {
                nextIn = scannerObj.next();
            } catch (NoSuchElementException e) {
                continue;
            }

            int selector = Parser.decoder(nextIn);
            Task nextTask;
            switch (selector) {
            case 0:
                activated = false;
                ui.exit();
                break;
            case 1:
                ui.list(tasks);
                break;
            case 2:
                int index = scannerObj.nextInt() - 1;
                tasks.get(index).setDone();
                ui.done(tasks.get(index));
                break;
            case 3:
                try {
                    nextTask = new Todo(scannerObj.nextLine());
                    tasks.add(nextTask);
                    storage.saveEntry(nextTask);
                    ui.todo(nextTask, tasks.size());
                    break;
                } catch (NoSuchElementException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    break;
                }
            case 4:
            case 5:
                String desc = "";
                String var;
                int x = 0;
                while (true) {
                    nextIn = scannerObj.next();
                    if (nextIn.equals("/by") || nextIn.equals("/at")) {
                        x = 1;
                        continue;
                    }
                    if (x == 1) {
                        var = nextIn;
                        var = var.concat(scannerObj.nextLine());
                        break;
                    } else {
                        desc = (desc.equals("") ? nextIn : desc.concat(" ").concat(nextIn));
                    }
                }
                if (selector == 4) {
                    nextTask = new Deadline(desc,var);
                    tasks.add(nextTask);
                    storage.saveEntry(nextTask);
                    ui.deadline(nextTask,tasks.size());
                } else {
                    nextTask = new Event(desc,var);
                    tasks.add(nextTask);
                    storage.saveEntry(nextTask);
                    ui.event(nextTask, tasks.size());
                }
                break;
            case 6:
                index = scannerObj.nextInt() - 1;
                Task t = tasks.get(index);
                tasks.remove(index);
                storage.deleteEntry(t);
                ui.delete(t, tasks.size());
                break;
            case 7:
                nextTask = new Task(scannerObj.nextLine());
                tasks.add(nextTask);
                storage.saveEntry(nextTask);
                ui.add(nextTask.toString());
                break;
            case 8:
                String phrase = scannerObj.nextLine();
                Task [] res = tasks.find(phrase);
                ui.find(res);
                break;
            default:
                ui.echo();
            }
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
