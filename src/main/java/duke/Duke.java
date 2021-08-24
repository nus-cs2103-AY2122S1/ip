package duke;

import duke.commands.Command;
import java.time.LocalDateTime;

public class Duke {
    private final UI ui;
    private final Storage storage;
    private final DateTimeHandler dth;
    private final TaskList taskList;
    private final Parser parser;

    public Duke() {
        ui = new UI();
        taskList = new TaskList();
        storage = new Storage();
        dth = new DateTimeHandler();
        parser = new Parser();
    }

    public void run() {
        try {
            storage.loadFile();
            storage.readFromFile(taskList);
        } catch (Exception e) {
            ui.print("The file could not be created");
        }
        ui.welcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();
            Command command = parser.parse(input);
            if (command == null) {
                ui.unrecognisedCommand();
                continue;
            }
            command.execute(taskList, storage, ui);
            isExit = command.isExit();

            String[] params = input.split(" ", 2);
            String[] parts;
            String arg;
//            if(params[0].equals("bye")) {
//                break;
//            }
            switch (params[0]) {
//            case "list":
//                System.out.println(ui.formatMessage(taskList.printList()));
//                break;
            case "done":
                if (params.length == 1) {
                    System.out.println(ui.formatMessage("Please enter a number after done"));
                    break;
                }
                arg = params[1];
                try {
                    int index = Integer.parseInt(arg);
                    if (index > taskList.size()) {
                        ui.print("There are only " + taskList.size() + " tasks");
                        break;
                    } else if (index == 0) {
                        ui.print(ui.formatMessage("There is no task 0"));
                        break;
                    }
                    Task t = taskList.getTask(index - 1);
                    t.completeTask();
                    System.out.println(ui.formatMessage(
                            "Nice! I've marked this task as done:\n       " + t + "\n     " +
                                    taskList.numOfTasks()
                    ));

                } catch (NumberFormatException e) {
                    System.out.println(ui.formatMessage("Please enter a number after done"));
                }
                break;
            case "delete":
                if (params.length == 1) {
                    System.out.println(ui.formatMessage("Please enter a number after delete"));
                    break;
                }
                arg = params[1];
                try {
                    int index = Integer.parseInt(arg);
                    if (index > taskList.size()) {
                        System.out.println(ui.formatMessage("There are only " + taskList.size() + " tasks"));
                        break;
                    } else if (index == 0) {
                        System.out.println(ui.formatMessage("There is no task 0"));
                        break;
                    }
                    Task t = taskList.getTask(index - 1);
                    taskList.removeTask(index-1);
                    System.out.println(ui.formatMessage(
                            "Noted. I've removed this task:\n       " + t + "\n     " +
                                    taskList.numOfTasks()
                    ));

                } catch (NumberFormatException e) {
                    System.out.println(ui.formatMessage("Please enter a number after done"));
                }
                break;
            case "todo":
                if (params.length == 1) {
                    System.out.println(ui.formatMessage("Please enter the name of the task after todo"));
                    break;
                }
                Todo t = new Todo(params[1], false);
                taskList.addToList(t);
                ui.print(taskList.taskAddedMessage(t));
                break;
            case "deadline":
                if (params.length == 1) {
                    System.out.println(ui.formatMessage("Please enter the name of the task after deadline"));
                    break;
                }
                if (!params[1].contains("/by")) {
                    System.out.println(ui.formatMessage("Please enter the deadline of the task after /by"));
                    break;
                }
                parts = params[1].split(" /by ");
                LocalDateTime deadlineDate = dth.parseDate(parts[1]);
                if (deadlineDate == null) {
                    ui.print(dth.invalidFormat());
                    break;
                }
                Deadline d = new Deadline(parts[0], false, deadlineDate);
                taskList.addToList(d);
                ui.print(taskList.taskAddedMessage(d));
                break;
            case "event":
                if (params.length == 1) {
                    System.out.println(ui.formatMessage("Please enter the name of the task after event"));
                    break;
                }
                if (!params[1].contains("/at")) {
                    System.out.println(ui.formatMessage("Please enter the start date of the task after /at"));
                    break;
                }
                parts = params[1].split(" /at ");
                LocalDateTime startDate = dth.parseDate(parts[1]);
                if (startDate == null) {
                    ui.print(dth.invalidFormat());
                    break;
                }
                Event e = new Event(parts[0], false, startDate);
                taskList.addToList(e);
                ui.print(taskList.taskAddedMessage(e));
                break;
            case "formats":
                System.out.println(ui.formatMessage(dth.getFormatList()));
                break;
//            default:
//                System.out.println(ui.formatMessage("That is not a recognised command"));
            }
            try {
                storage.writeToFile(taskList);
            } catch (Exception e) {
                ui.print("An Error Occurred");
            }

        }
        ui.goodByeMessage();

    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
