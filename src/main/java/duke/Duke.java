package duke;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;

public class Duke {

    private final StorageList SL;
    private static Ui ui;
    private Parser parser;
    private Storage storage;

    private static final int TASK_TODO = 1;
    private static final int TASK_DEADLINE = 2;
    private static final int TASK_EVENT = 3;
    private static final int TASK_OUTOFBOUNDS = 4;
    private static final int TASK_UNKNOWN = 5;

    public Duke(String filePath) throws FileNotFoundException {
        ui = new Ui();
        ui.greeting();
        parser = new Parser();
        storage = new Storage(filePath);
        SL = new StorageList(storage.load());
    }

    public static void main(String[] args) {
        try {
            new Duke("data/duketest.txt").result();
        } catch (FileNotFoundException e){
            ui.fileNotFoundMsg();
        }
    }


    private void result() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();

            try {
                if (parser.isDoneCmd(input)) {
                    marking(input);

                } else if (parser.isValidTodo(input)) {
                    if(input.length() == 4) {
                        throw new DukeException(ui.taskErrorMsg(TASK_TODO));
                    }
                    ToDos todo = new ToDos(parser.getTodoDescription(input));
                    SL.add(todo);
                    ui.taskAddedMsg(todo.toString(), SL.size());

                } else if (parser.isValidDeadline(input)) {
                    if (input.length() == 8) {
                        throw new DukeException(ui.taskErrorMsg(TASK_DEADLINE));
                    }
                    String by = parser.getDeadlineTime(input);
                    Deadlines dl = new Deadlines(parser.getDeadlineDescription(input), by);
                    SL.add(dl);
                    ui.taskAddedMsg(dl.toString(), SL.size());

                } else if (parser.isValidEvent(input)) {
                    if (input.length() == 5) {
                        throw new DukeException(ui.taskErrorMsg(TASK_EVENT));
                    }
                    String at = parser.getEventTime(input);
                    Events event = new Events(parser.getEventDescription(input), at);
                    SL.add(event);
                    ui.taskAddedMsg(event.toString(), SL.size());

                } else if (parser.isDeleteCmd(input)) {
                    if (input.length() == 6) {
                        throw new DukeException(ui.taskErrorMsg(TASK_UNKNOWN));
                    }
                    int idx = parser.getDeleteIdx(input);
                    SL.delete(idx);
                } else {
                    Task task = new Task(input);
                    String desc = task.getDescription();
                    switch (desc) {
                    case "bye":
                        ui.bye();
                        return;
                    case "list":
                        ui.displayListContents(SL);
                        break;
                    default:
                        throw new DukeException(ui.taskErrorMsg(TASK_UNKNOWN));
                    }
                }
                storage.save(SL);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                ui.ioErrorMsg();
            }
        }

    }

    public void marking(String input) throws DukeException {
        if (input.length() >= 6) {
            if (parser.isInteger(input)) {
                int taskNum = Integer.parseInt(input.substring(5)) - 1;
                if (taskNum < SL.size() && taskNum >= 0) {
                    SL.get(taskNum).markAsDone();
                    ui.taskDoneConfirmation();
                    SL.get(taskNum).displayTask();
                } else {
                    throw new DukeException(ui.taskErrorMsg(TASK_OUTOFBOUNDS));
                }
            } else {
                throw new DukeException(ui.taskErrorMsg(TASK_UNKNOWN));
            }
        } else {
            throw new DukeException(ui.taskErrorMsg(TASK_UNKNOWN));
        }
    }
}
