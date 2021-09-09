package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import duke.command.DukeCommand;
import duke.exception.InvalidCommandException;
import duke.task.DukeDeadlineTask;
import duke.task.DukeEvent;
import duke.task.DukeTask;
import duke.task.TaskList;

/**
 * Represents the UI interactions in the program.
 */
public class Cli implements Ui {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final Scanner scanner;
    private final PrintStream outputStream;
    private boolean shouldExit;

    /**
     * Creates a new user interface with the given input and output streams.
     * @param inputStream The stream containing user command input
     * @param outputStream The stream to output Duke's response to
     */
    public Cli(InputStream inputStream, PrintStream outputStream) {
        this.scanner = new Scanner(inputStream);
        this.outputStream = outputStream;
        this.shouldExit = false;
    }

    /**
     * Prints a prompt for the next command, and returns the submitted user input.
     *
     * @return the raw command input by the user
     */
    public String nextCommand() {
        outputStream.print("Duke> ");
        return scanner.nextLine();
    }

    /**
     * Outputs the given string with a newline.
     *
     * @param output the string to output
     */
    public void outputLine(String output) {
        outputStream.println(output);
    }

    /**
     * Prints a welcome message greeting the user.
     */
    public void printWelcomeMessage() {
        outputLine("Hello from\n" + logo);
    }

    /**
     * Prints an exit message greeting the user.
     */
    public void printExitMessage() {
        outputLine("Goodbye from\n" + logo);
    }

    /**
     * Prints the list of commands available using the {@link DukeCommand#HELP} command.
     */
    public void printHelp() {
        try {
            DukeCommand.HELP.apply(null, this, null, "", Map.of());
        } catch (InvalidCommandException e) {
            e.printStackTrace();
        }
    }

    /**
     * Marks Duke as to be exited after the latest command is done processing.
     */
    public void markExit() {
        shouldExit = true;
    }

    /**
     * Returns if Duke should continue accepting commands.
     *
     * @return if Duke should continue accepting commands.
     */
    public boolean shouldContinue() {
        return !shouldExit;
    }

    /**
     * Prints the given task list to the output stream in a neat format.
     * @param taskList The task list to print
     */
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
                    for (DukeTask task : tasks) {
                        int index = taskList.indexOf(task);
                        outputLine(String.format("%d. %s", index + 1, task.toCliString()));
                    }
                    outputLine("");
                });
    }
}
