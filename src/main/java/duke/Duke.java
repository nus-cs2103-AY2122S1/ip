package duke;

import java.util.NoSuchElementException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class Duke extends Application {

    private boolean activated;
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;


    public Duke() {
        this("/data", "data.txt");
    }

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
                    nextTask = new Deadline(desc, var);
                    tasks.add(nextTask);
                    storage.saveEntry(nextTask);
                    ui.deadline(nextTask, tasks.size());
                } else {
                    nextTask = new Event(desc, var);
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
                // Task [] res = tasks.find(phrase);
                // ui.find(res);
                break;
            default:
                ui.echo();
            }
        }
    }

    public void main(String[] args) {
        new Duke().run();
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }
}
