package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import duke.exception.InvalidInputException;
import duke.exception.InvalidInstructionException;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        try {
            ArrayList<String> savedTasks = this.storage.getStorageContents();
            this.taskList = new TaskList(savedTasks);
        } catch(FileNotFoundException e) {
            new File(filePath);
            this.taskList = new TaskList(new ArrayList<>());
        }
        this.ui = new Ui();
        this.parser = new Parser();
    }

    private void saveTasks() throws IOException {

        String contents = "";

        for (int i = 0; i < this.taskList.getSize(); i++) {
            Task task = this.taskList.getTask(i);
            String details = task.getDetails();
            String done = task.isCompleted()
                    ? "done"
                    : "not-done";
            if (task.getClass() == ToDo.class) {
                String type = "T";
                contents += type + ' ' + done + ' ' + details;
            } else {

                LocalDate date = task.getDate();
                String dateStr = "";
                if (date == null) {
                    dateStr = "null";
                } else {
                    String year = "" + task.getDate().getYear();
                    String month = task.getDate().getMonthValue() < 10
                            ? "0" + task.getDate().getMonthValue()
                            : "" + task.getDate().getMonthValue();
                    String day = "" + task.getDate().getDayOfMonth();
                    dateStr = year + '-' + month + '-' + day;
                }

                if (task.getClass() == Deadline.class) {
                    String type = "D";
                    String by = ((Deadline) task).getBy() == null
                            ? "null"
                            : ((Deadline) task).getBy();
                    String time = "" + task.getTime();
                    contents += type + ' ' + done + ' ' + details + ' ' + by + ' '
                            + dateStr + ' ' + time;
                } else if (task.getClass() == Event.class) {
                    String type = "E";
                    String at = ((Event) task).getAt() == null
                            ? "null"
                            : ((Event) task).getAt();
                    String start = "" + task.getTime();
                    String end = "" + ((Event) task).getEndTime();
                    contents += type + ' ' + done + ' ' + details + ' ' + at + ' '
                            + dateStr + ' ' + start + ' ' + end;
                }
            }
            contents += System.lineSeparator();
        }
        this.storage.writeToStorage(contents, false);
    }

    public void run() {

        this.ui.greeting(this.taskList.getList());

        Scanner scanner = new Scanner(System.in);

        boolean leave = false;
        while (!leave) {
            try {
                String inputStr = scanner.nextLine();
                HashMap<String, Object> input = this.parser.parse(inputStr);

                switch ((String) input.get("cmd")) {
                case "bye":
                    this.ui.farewell();
                    leave = true;
                    saveTasks();
                    break;
                case "list":
                    this.ui.reply(this.taskList.getList());
                    break;
                case "done":
                    try {
                        this.taskList.completeTask((Integer) input.get("index") - 1);
                        this.ui.doneMsg(this.taskList.getTask((Integer) input.get("index") - 1));
                    } catch (IndexOutOfBoundsException e) {
                        throw new InvalidInputException("Task number does not exist. Complete failed.");
                    }

                    break;
                case "delete":
                    try {
                        this.ui.deleteMsg(this.taskList.getTask((Integer) input.get("index") - 1),
                                this.taskList.getSize());
                        this.taskList.deleteTask((Integer) input.get("index") - 1);
                    } catch (IndexOutOfBoundsException e) {
                        throw new InvalidInputException("Task number does not exist. Delete failed.");
                    }
                    break;
                case "todo":
                    ToDo todo = new ToDo((String) input.get("details"));
                    this.taskList.addTask(todo);
                    this.ui.addTaskMsg(todo, this.taskList.getSize());
                    break;
                case "deadline":
                    Deadline deadline = new Deadline((String) input.get("details"), (String) input.get("by"),
                            (String) input.get("date"), (Integer) input.get("time"));
                    this.taskList.addTask(deadline);
                    this.ui.addTaskMsg(deadline, this.taskList.getSize());
                    break;
                case "event":
                    Event event = new Event((String) input.get("details"), (String) input.get("at"),
                            (String) input.get("date"), (Integer) input.get("start"), (Integer) input.get("end"));
                    this.taskList.addTask(event);
                    this.ui.addTaskMsg(event, this.taskList.getSize());
                    break;
                case "date":
                    LocalDate date = (LocalDate) input.get("date");
                    int time = (Integer) input.get("time");
                    String list = "";
                    for (int i = 0; i < this.taskList.getSize(); i++) {
                        Task task = this.taskList.getTask(i);
                        if (date.equals(task.getDate()) && time == task.getTime()) {
                            list += "\t\t" + task.toString() + '\n';
                        }
                    }
                    if (list.equals("")) {
                        this.ui.reply("No tasks are due/happening.");
                    } else {
                        this.ui.reply("These tasks are due/happening:\n"
                                + list);
                    }
                    break;
                }
            } catch (IllegalStateException | NoSuchElementException e) {
                try {
                    saveTasks();
                } catch (IOException ie) {
                    this.ui.printException(ie);
                }
                this.ui.printException(e);
            } catch (InvalidInputException e) {
                this.ui.invalidInput(e);
            } catch (IOException e) {
                this.ui.printException(e);
            } catch (InvalidInstructionException e) {
                this.ui.invalidInstruction(e);
            }
        }

    }

    public static void main(String[] args) {
        new Duke("taskList.txt").run();
    }
}
