package duke;

import duke.command.DukeCommand;
import duke.exception.InvalidCommandException;
import duke.task.DukeDeadlineTask;
import duke.task.DukeEvent;
import duke.task.DukeTask;
import duke.task.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Ui {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final Scanner scanner;
    private final PrintStream outputStream;
    private boolean exit;

    public Ui(InputStream inputStream, PrintStream outputStream) {
        this.scanner = new Scanner(inputStream);
        this.outputStream = outputStream;
        this.exit = false;
    }

    public String nextCommand() {
        outputStream.print("Duke> ");
        return scanner.nextLine();
    }

    public void outputLine(String output) {
        outputStream.println(output);
    }

    public void printWelcomeMessage() {
        outputLine("Hello from\n" + logo);
    }

    public void printExitMessage() {
        outputLine("Goodbye from\n" + logo);
    }

    public void printHelp() {
        try {
            DukeCommand.HELP.apply(null, this, null, "", Map.of());
        } catch (InvalidCommandException e) {
            e.printStackTrace();
        }
    }

    public void markExit() {
        exit = true;
    }

    public boolean shouldContinue() {
        return !exit;
    }

    public void printTaskList(TaskList taskList) {
        taskList.getTasks().stream()
                .collect(Collectors.groupingBy(t -> {
                    if (t instanceof DukeEvent) {
                        return "Events";
                    } else if (t instanceof DukeDeadlineTask) {
                        return "Tasks with deadlines";
                    } else {
                        return "Tasks";
                    }
                }))
                .forEach((String group, List<DukeTask> tasks) -> {
                    outputLine(group);
                    for (DukeTask task: tasks) {
                        int index = taskList.indexOf(task);
                        outputLine(String.format("%d. %s", index + 1, task));
                    }
                    outputLine("");
                });
    }
}
