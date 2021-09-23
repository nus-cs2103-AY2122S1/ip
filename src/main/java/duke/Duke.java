package duke;

import duke.task.*;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {

    private boolean activated;
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public Duke() {
        this("/data", "data.txt");
    }

    private Duke(String storagePath, String fileName) {
        activated = true;
        ui = new Ui();
        storage = new Storage(storagePath, fileName);
        tasks = new TaskList(storage.getFile());
    }

    public String getResponse(String input) {
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
                nextIn = scannerObj.nextLine();
                if (Parser.isNotValid(nextIn, TASK_TYPE.T)) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
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
                    System.out.println("☹ OOPS!!! Please supply a description and/or date in the format yyyy-mm-dd!");
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
                    System.out.println("☹ OOPS!!! Please supply a description and/or a date & time in the format yyyy-mm-dd HH:MM(24 hour clock)!");
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
        }
        return out;
    }


}
