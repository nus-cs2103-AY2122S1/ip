package duke;

import java.util.Scanner;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.Ui;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.ToDos;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.IOException;

public class Duke {
    private final TaskList taskList;
    private final Storage storage;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
    }

    private String dateTimeFormatter(String unformattedDate) {
        // 2/12/2019 1800
        String stringDate = unformattedDate.split(" ")[0];
        String time = unformattedDate.split(" ")[1];

        LocalDate date = LocalDate.of(Integer.parseInt(stringDate.split("/")[2]),
                Integer.parseInt(stringDate.split("/")[1]), Integer.parseInt(stringDate.split("/")[0]));

        LocalDateTime dateTime = date.atTime(Integer.parseInt(time.substring(0, 2)),
                Integer.parseInt(time.substring(2, 4)));

        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
    }

    public void start() throws FileNotFoundException, IOException {
        String userInput = "";
        String action = "";
        String descriptions = "";
        String[] actionDescription = new String[2];
        String dateTime = "";
        String onlyDescription = "";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> savedTasks = storage.loadData();
        Command command = new AddCommand();
        taskList.loadFromStorage(savedTasks);

        Ui.printIntro();
        while (true) {
            userInput = sc.nextLine();

            actionDescription = userInput.split(" ", 2);

            try {
                action = actionDescription[0];
                switch (action) {
                    case "bye":
                        Ui.printBye();
                        sc.close();
                        return;

                    case "list":
                        Ui.printList(taskList.getTaskList());
                        break;

                    case "done":
                        taskList.doneItem(userInput.split(" ")[1]);
                        storage.updateDone(userInput.split(" ")[1]);
                        break;

                    case "todo":
                        descriptions = actionDescription[1];
                        ToDos todos = new ToDos(descriptions);
                        command.execute(taskList, storage, todos);
                        break;

                    case "deadline":
                        descriptions = actionDescription[1];
                        onlyDescription = descriptions.split(" /")[0];
                        dateTime = descriptions.split(" /by ")[1];
                        dateTime = dateTimeFormatter(dateTime);
                        Deadline deadline = new Deadline(onlyDescription, dateTime);
                        command.execute(taskList, storage, deadline);
                        break;

                    case "event":
                        descriptions = actionDescription[1];
                        onlyDescription = descriptions.split(" /")[0];
                        dateTime = descriptions.split(" /at ")[1];
                        Events event = new Events(onlyDescription, dateTime);
                        command.execute(taskList, storage, event);
                        break;

                    case "delete":
                        Command deleteCommand = new DeleteCommand(userInput.split(" ")[1]);
                        deleteCommand.execute(taskList, storage);
                        break;

                    case "":
                        break;

                    default:
                        Ui.printMessage(Ui.ERROR_MSG_UNKOWN_MSG);
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e);
                Ui.printMessage(Ui.ERROR_MSG_EMPTY_DESCRIPTION);
                continue;
            }

        }

    }
}