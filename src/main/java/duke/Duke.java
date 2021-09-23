package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TASK_TYPE;
import duke.task.Task;
import duke.task.Todo;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Duke {

    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image bot = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke() {
        this("/data", "data.txt");
    }

    public Duke(String storagePath, String fileName) {
        ui = new Ui();
        storage = new Storage(storagePath, fileName);
        tasks = new TaskList(storage.getFile());
    }

    public String getResponse(String input) {
        InputStream stream = new ByteArrayInputStream(input.getBytes());

        Scanner scannerObj = new Scanner(stream);
        String out = "";
        String nextIn;

        try {
            nextIn = scannerObj.next();
        } catch (NoSuchElementException e) {
            return "Buffer is Fucked";
        }

        int selector = Parser.decoder(nextIn);
        Task nextTask;
        switch (selector) {
        case 0:
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
            nextIn = scannerObj.nextLine();
            if (Parser.isNotValid(nextIn, TASK_TYPE.T)) {
                out = "☹ OOPS!!! The description of a todo cannot be empty.";
                break;
            }
            nextTask = new Todo(Parser.removeSpace(nextIn));
            tasks.add(nextTask);
            storage.saveEntry(nextTask);
            out = ui.todo(nextTask, tasks.size());
            break;
        case 4:
            String[] res;
            nextIn = scannerObj.nextLine();
            if (Parser.isNotValid(nextIn, TASK_TYPE.D)) {
                out = "☹ OOPS!!! Please supply a description and/or date in the format yyyy-mm-dd!";
                break;
            }
            res = Parser.dSplitter(nextIn);
            nextTask = new Deadline(res[0], res[1]);
            tasks.add(nextTask);
            storage.saveEntry(nextTask);
            out = ui.deadline(nextTask, tasks.size());
            break;
        case 5:
            String[] ins;
            nextIn = scannerObj.nextLine();
            if (Parser.isNotValid(nextIn, TASK_TYPE.E)) {
                out = "☹ OOPS!!! Please supply a description and/or a date & time in the format yyyy-mm-dd HH:MM(24 hour clock)!";
                break;
            }
            ins = Parser.eSplitter(nextIn);
            nextTask = new Event(ins[0], ins[1], ins[2]);
            tasks.add(nextTask);
            storage.saveEntry(nextTask);
            out = ui.deadline(nextTask, tasks.size());
            break;
        case 6:
            index = scannerObj.nextInt() - 1;
            Task t = tasks.get(index);
            tasks.remove(index);
            storage.deleteEntry(t);
            out = ui.delete(t, tasks.size());
            break;
        case 7:
            String phrase = scannerObj.nextLine();
            TaskList results = tasks.find(phrase);
            out = ui.list(results);
            break;
        default:
            out = ui.echo();
        }
        return out;
    }

}
