package duke;

import java.util.Scanner;

import java.io.IOException;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Parser {
    private static Ui ui = new Ui();
    private static Storage storage = new Storage();
    private static TaskListInternal taskListInternal = new TaskListInternal();

    public Parser() {

    }

    public static void parse() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                sc.close();
                ui.sayBye();
                break;
            } else if (command.equals("list")) {

                ui.showList(TaskListInternal.lines);

            } else if (command.contains("done")) {
                String numbers = command.substring(5);
                try {
                    int taskNo = Integer.parseInt(numbers);
                } catch (NumberFormatException notANumber) {
                    System.err.println(notANumber);
                    ui.showNotANumberMsg();
                    continue;
                }
                int taskNo = Integer.parseInt(numbers);
                if (TaskListInternal.lines.size() < taskNo) {
                    ui.showOutOfBoundsMsg(TaskListInternal.lines.size());
                    continue;
                }
                if (taskNo <= 0) {
                    ui.showLessThanZeroMsg(taskNo);
                    continue;
                }
                taskNo--;
                taskListInternal.makeDone(storage, taskNo);
            } else if (command.contains("delete") || command.contains("remove")) {
                String numbers = command.substring(7);
                try {
                    int taskNo = Integer.parseInt(numbers);
                } catch (NumberFormatException notANumber) {
                    System.err.println(notANumber);
                    ui.showNotANumberMsg();
                    continue;
                }
                int taskNo = Integer.parseInt(numbers);
                if (TaskListInternal.lines.size() < taskNo) {
                    ui.showOutOfBoundsMsg(TaskListInternal.lines.size());
                    continue;
                }
                if (taskNo <= 0) {
                    ui.showLessThanZeroMsg(taskNo);
                    continue;
                }
                taskNo--;
                taskListInternal.delete(storage, taskNo);

            } else if (command.contains("todo")) {
                String task = command.substring(5);
                if (task.equals("")) {
                    ui.showNoNameError();
                    continue;
                }
                ToDo taskToAdd = new ToDo(task);

                String toBeAdded = taskToAdd.toString();
                taskListInternal.add(storage, toBeAdded);
                try {
                    storage.writeListToFile(Duke.file.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (command.contains("deadline")) {
                String taskNDate = command.substring(9);
                if (!(taskNDate.contains("/by"))) {
                    ui.showNoDateError();
                    continue;
                }
                int splitIndex = taskNDate.indexOf("/by");
                String task = taskNDate.substring(0, splitIndex - 1);
                String date = taskNDate.substring(splitIndex + 4);
                if (task.equals("")) {
                    ui.showNoNameError();
                    continue;
                }

                try {
                    LocalDate test = LocalDate.parse(date);
                } catch (DateTimeException error) {
                    ui.showBadDateError();
                    continue;
                }

                Deadline taskToAdd = new Deadline(task, date);
                String toBeAdded = taskToAdd.toString();
                taskListInternal.add(storage, toBeAdded);

                try {
                    storage.writeListToFile(Duke.file.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (command.contains("event")) {
                String taskNDate = command.substring(6);
                if (!(taskNDate.contains("/at"))) {
                    ui.showNoDateError();
                    continue;
                }
                int splitIndex = taskNDate.indexOf("/at");
                String task = taskNDate.substring(0, splitIndex - 1);
                String date = taskNDate.substring(splitIndex + 4);
                if (task.equals("")) {
                    ui.showNoNameError();
                    continue;
                }

                try {
                    LocalDate test = LocalDate.parse(date);
                } catch (DateTimeException error) {
                    ui.showBadDateError();
                    continue;
                }

                Deadline taskToAdd = new Deadline(task, date);
                String toBeAdded = taskToAdd.toString();
                taskListInternal.add(storage, toBeAdded);

                try {
                    storage.writeListToFile(Duke.file.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (command.contains("find")) {
                String searchQuery=command.substring(5);
                ui.showSearchResults(TaskListInternal.lines,searchQuery);

            } else if (command.equals("WIPE")) {
                System.out.println("ARE YOU SURE? SAY Y IF YOU ARE AND LITERALLY ANYTHING ELSE IF YOU AREN'T");
                if (sc.nextLine().equals("Y")) {
                    TaskListInternal.lines.clear();
                    try {
                        storage.writeListToFile(Duke.file.getPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("BAYUM! The list has been wiped. How tragic.");
                } else {
                    System.out.println("WOWZA! That was real close.");
                }
            } else {
                System.out.println("(WHAT IS THIS PERSON TRYING TO SAY WHY IS HE TYPING GIBBERISH I'M JUST TRYING TO SURVIVE)");
            }
        }
    }
}
