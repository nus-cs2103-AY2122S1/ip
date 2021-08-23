package duke;

import duke.exception.InvalidInputException;
import duke.exception.InvalidInstructionException;

import duke.parser.Parser;

import duke.storage.Storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

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
        storage = new Storage(filePath);
        try {
            ArrayList<String> savedTasks = storage.getStorageContents();
            taskList = new TaskList(savedTasks);
        } catch(FileNotFoundException e) {
            new File(filePath);
            taskList = new TaskList(new ArrayList<>());
        }
        ui = new Ui();
        parser = new Parser();
    }

    private void saveTasks() throws IOException {

        String contents = "";

        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
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
        storage.writeToStorage(contents, false);
    }

    public void run() {

        ui.greeting(taskList.getList());

        Scanner scanner = new Scanner(System.in);

        boolean leave = false;
        while (!leave) {
            try {
                String inputStr = scanner.nextLine();
                HashMap<String, Object> input = parser.parse(inputStr);

                switch ((String) input.get("cmd")) {
                case "bye":
                    ui.farewell();
                    leave = true;
                    saveTasks();
                    break;
                case "list":
                    ui.reply(taskList.getList());
                    break;
                case "done":
                    try {
                        taskList.completeTask((Integer) input.get("index") - 1);
                        ui.doneMsg(taskList.getTask((Integer) input.get("index") - 1));
                    } catch (IndexOutOfBoundsException e) {
                        throw new InvalidInputException("Task number does not exist. Complete failed.");
                    }

                    break;
                case "delete":
                    try {
                        ui.deleteMsg(taskList.getTask((Integer) input.get("index") - 1),
                                taskList.getSize());
                        taskList.deleteTask((Integer) input.get("index") - 1);
                    } catch (IndexOutOfBoundsException e) {
                        throw new InvalidInputException("Task number does not exist. Delete failed.");
                    }
                    break;
                case "todo":
                    ToDo todo = new ToDo((String) input.get("details"));
                    taskList.addTask(todo);
                    ui.addTaskMsg(todo, taskList.getSize());
                    break;
                case "deadline":
                    Deadline deadline = new Deadline((String) input.get("details"), (String) input.get("by"),
                            (String) input.get("date"), (Integer) input.get("time"));
                    taskList.addTask(deadline);
                    ui.addTaskMsg(deadline, taskList.getSize());
                    break;
                case "event":
                    Event event = new Event((String) input.get("details"), (String) input.get("at"),
                            (String) input.get("date"), (Integer) input.get("start"), (Integer) input.get("end"));
                    taskList.addTask(event);
                    ui.addTaskMsg(event, taskList.getSize());
                    break;
                case "date":
                    LocalDate date = (LocalDate) input.get("date");
                    int time = (Integer) input.get("time");
                    String list = "";
                    for (int i = 0; i < taskList.getSize(); i++) {
                        Task task = taskList.getTask(i);
                        if (date.equals(task.getDate()) && time == task.getTime()) {
                            list += "\t\t" + task.toString() + '\n';
                        }
                    }
                    if (list.equals("")) {
                        ui.reply("No tasks are due/happening.");
                    } else {
                        ui.reply("These tasks are due/happening:\n"
                                + list);
                    }
                    break;
                }
            } catch (IllegalStateException | NoSuchElementException e) {
                try {
                    saveTasks();
                } catch (IOException ie) {
                    ui.printException(ie);
                }
                ui.printException(e);
            } catch (InvalidInputException e) {
                ui.invalidInput(e);
            } catch (IOException e) {
                ui.printException(e);
            } catch (InvalidInstructionException e) {
                ui.invalidInstruction(e);
            }
        }

    }

    public static void main(String[] args) {
        new Duke("taskList.txt").run();
    }
}
