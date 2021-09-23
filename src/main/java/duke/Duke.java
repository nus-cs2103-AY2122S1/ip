package duke;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;

public class Duke {

    private boolean activated;
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image bot = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke() {
        this("/data", "data.txt");
    }

    public Duke(String storagePath, String fileName) {
        activated = true;
        ui = new Ui();
        storage = new Storage(storagePath, fileName);
        tasks = new TaskList(storage.getFile());
    }

    /*public String getResponse(String input) {
        Scanner scannerObj = new Scanner(input);
        String out = "";
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
                out = ui.exit();
                break;
            case 1:
                out = ui.list(tasks);
                break;
            case 2:
                int index = scannerObj.nextInt() - 1;
                tasks.get(index).setDone();
                out = ui.done(tasks.get(index));
                break;
            case 3:
                try {
                    nextTask = new Todo(scannerObj.nextLine());
                    tasks.add(nextTask);
                    storage.saveEntry(nextTask);
                    out = ui.todo(nextTask, tasks.size());
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
                    out = ui.deadline(nextTask, tasks.size());
                } else {
                    nextTask = new Event(desc, var);
                    tasks.add(nextTask);
                    storage.saveEntry(nextTask);
                    out = ui.event(nextTask, tasks.size());
                }
                break;
            case 6:
                index = scannerObj.nextInt() - 1;
                Task t = tasks.get(index);
                tasks.remove(index);
                storage.deleteEntry(t);
                out = ui.delete(t, tasks.size());
                break;
            case 7:
                nextTask = new Task(scannerObj.nextLine());
                tasks.add(nextTask);
                storage.saveEntry(nextTask);
                out = ui.add(nextTask.toString());
                break;
            case 8:
                String phrase = scannerObj.nextLine();
                TaskList res = tasks.find(phrase);
                out = ui.list(res);
                break;
            default:
                out = ui.echo();
            }
        }
        return out;
    }*/


    public String getResponse(String input) {
        return "Duke heard: " + input;
    }


}
