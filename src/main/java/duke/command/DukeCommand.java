package duke.command;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import duke.Cli;
import duke.Storage;
import duke.Ui;
import duke.exception.InvalidCommandException;
import duke.task.DukeDeadlineTask;
import duke.task.DukeEvent;
import duke.task.DukeSimpleTask;
import duke.task.DukeTask;
import duke.task.TaskList;

/**
 * Represents commands available in the Duke CLI as enums.
 *
 * Every value in the enum evokes the object constructor with
 * its name, description, command configuration describing
 * the kind of parameters it accepts, and a function that
 * defines the behavior of the command given certain arguments.
 */
public enum DukeCommand implements DukeCommandAction {
    ADD_TASK(
        "add",
        "Add a task (with optionally a deadline or a date)",
        new DukeCommandConfig(
            new DukeCommandArgument(
                "task",
                "The name of the task",
                DukeCommandArgumentType.REQUIRED
            ),
            Map.of(
                "by", new DukeCommandArgument("deadline", "The deadline of the task",
                            DukeCommandArgumentType.OPTIONAL),
                "at", new DukeCommandArgument("date", "The date of the event",
                            DukeCommandArgumentType.OPTIONAL)
            )
        ),
        (TaskList taskList, Ui ui, Storage storage, String arg, Map<String, String> namedArgs) -> {
            DukeTask task;
            if (namedArgs.containsKey("by") && namedArgs.containsKey("at")) {
                throw new InvalidCommandException("Task cannot be added with both a deadline and a date.");
            } else if (namedArgs.containsKey("by")) {
                task = new DukeDeadlineTask(arg, namedArgs.get("by"));
            } else if (namedArgs.containsKey("at")) {
                task = new DukeEvent(arg, namedArgs.get("at"));
            } else {
                task = new DukeSimpleTask(arg);
            }
            taskList.addTask(task);
            storage.saveTaskList(taskList);
            if (ui instanceof Cli) {
                Cli cli = (Cli) ui;
                cli.outputLine(String.format("Task added with title: %s", arg));
            }
        }
    ),
    DELETE_TASK(
        "delete",
        "Delete a task",
        new DukeCommandConfig(
            new DukeCommandArgument(
                "index",
                "The position of the task in the list",
                DukeCommandArgumentType.REQUIRED
            ),
            Map.of()
        ),
        (TaskList taskList, Ui ui, Storage storage, String arg, Map<String, String> namedArgs) -> {
            DukeTask task = taskList.removeTaskAt(parseTaskIndex(taskList, arg));
            storage.saveTaskList(taskList);
            if (ui instanceof Cli) {
                Cli cli = (Cli) ui;
                cli.outputLine("I've removed the following task.");
                cli.outputLine(task.toString());
            }
        }
    ),
    EXIT(
        "bye",
        "Exit Duke",
        DukeCommandConfig.NO_ARGUMENTS,
        (TaskList taskList, Ui ui, Storage storage, String arg, Map<String, String> namedArgs) -> {
            if (ui instanceof Cli) {
                Cli cli = (Cli) ui;
                cli.markExit();
            }
        }
    ),
    FIND_TASK(
        "find",
        "Find a task",
        new DukeCommandConfig(
            new DukeCommandArgument(
                "keyword",
                "The keyword to filter tasks by",
                DukeCommandArgumentType.REQUIRED
            ),
            Map.of()
        ),
        (TaskList taskList, Ui ui, Storage storage, String arg, Map<String, String> namedArgs) -> {
            List<DukeTask> dukeTasks = taskList.getTasks().stream()
                    .filter(dukeTask -> dukeTask.getName().toLowerCase().contains(arg.toLowerCase()))
                    .collect(Collectors.toList());
            TaskList filteredTaskList = new TaskList(dukeTasks);
            if (ui instanceof Cli) {
                Cli cli = (Cli) ui;
                cli.outputLine(String.format("Here are the tasks with titles containing \"%s\"", arg));
                cli.printTaskList(filteredTaskList);
            }
        }
    ),
    HELP(
        "help",
        "Display all commands, or detailed info of given command",
        new DukeCommandConfig(
            new DukeCommandArgument(
                "command_name",
                "The name of the command to view help for",
                DukeCommandArgumentType.OPTIONAL
            ),
            Map.of()
        ),
        (TaskList taskList, Ui ui, Storage storage, String arg, Map<String, String> namedArgs) -> {
            if (ui instanceof Cli) {
                Cli cli = (Cli) ui;
                if (arg.isEmpty()) {
                    cli.outputLine("ALL COMMANDS:");
                    Arrays.stream(DukeCommand.values())
                            .sorted(Comparator.comparing(DukeCommand::getName))
                            .map(DukeCommand::toString)
                            .forEach(cli::outputLine);
                } else {
                    Optional<DukeCommand> command = getClosestMatch(arg);
                    if (command.isEmpty()) {
                        throw new InvalidCommandException(String.format("No command named \"%s\".", arg));
                    } else {
                        cli.outputLine(command.get().toDetailedString());
                    }
                }
            }
        }
    ),
    LIST_TASKS(
        "list",
        "List all tasks",
        DukeCommandConfig.NO_ARGUMENTS,
        (TaskList taskList, Ui ui, Storage storage, String arg, Map<String, String> namedArgs) -> {
            if (ui instanceof Cli) {
                Cli cli = (Cli) ui;
                cli.outputLine(
                        String.format("You have %d %s.", taskList.size(), taskList.size() == 1 ? "task" : "tasks"));
                cli.printTaskList(taskList);
            }
        }
    ),
    MARK_DONE(
        "done",
        "Mark the task as done",
        new DukeCommandConfig(
            new DukeCommandArgument(
                "index",
                "The position of the task in the list",
                DukeCommandArgumentType.REQUIRED
            ),
            Map.of()
        ),
        (TaskList taskList, Ui ui, Storage storage, String arg, Map<String, String> namedArgs) -> {
            DukeTask task = taskList.getTaskAt(parseTaskIndex(taskList, arg));
            if (ui instanceof Cli) {
                Cli cli = (Cli) ui;
                if (task.isDone()) {
                    cli.outputLine("The following task is already marked as done! Good job!");
                } else {
                    task.markAsDone();
                    storage.saveTaskList(taskList);
                    cli.outputLine("I've marked the following task as done!");
                }
                cli.outputLine(task.toString());
            }
        }
    );

    private final String name;
    private final String description;
    private final DukeCommandConfig config;
    private final DukeCommandAction action;

    /**
     * Creates a DukeCommand instance with the given name, description, configuration and action.
     * @param name The name of the command
     * @param description The description of what the command does
     * @param config The configuration of the command (what kind of parameters it accepts)
     * @param action A function that defines what the command should do, given a set of parameters
     */
    DukeCommand(String name, String description, DukeCommandConfig config, DukeCommandAction action) {
        this.name = name;
        this.description = description;
        this.config = config;
        this.action = action;
    }

    public static Optional<DukeCommand> getClosestMatch(String command) {
        return Arrays.stream(DukeCommand.values())
                .sorted(Comparator.comparingInt(c -> -c.getName().length()))
                .filter(c -> command.startsWith(c.getName()))
                .findFirst();
    }

    /**
     * Returns the name of the command.
     *
     * @return the name of the command
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the command formatted to print in the help function.
     *
     * @return the command formatted to print in the help function.
     */
    private String formatCommand() {
        return String.format("%s%s%s", name, formatPositionalArgument(), formatNamedArguments());
    }

    /**
     * Returns the name of the positional argument, if any. Otherwise, returns an empty string.
     *
     * @return the name of the positional argument
     */
    private String formatPositionalArgument() {
        if (config.getPositionalArg() == DukeCommandArgument.NONE) {
            return "";
        } else {
            return " " + config.getPositionalArg().toString();
        }
    }

    /**
     * Returns a formatted string containing the named arguments.
     *
     * @return a formatted string containing the named arguments
     */
    private String formatNamedArguments() {
        if (config.getAcceptedNamedArgs().isEmpty()) {
            return "";
        }
        return " " + String.join(" ", config.getAcceptedNamedArgs().entrySet().stream().map(entry -> String.format(
                "[/%s %s]", entry.getKey(), entry.getValue())).toArray(String[]::new));
    }

    /**
     * Returns the help message of the command containing usage information. Used in {@link DukeCommand#HELP}.
     *
     * @return the help message of the command
     */
    public String toDetailedString() {
        String argsString = getDetailedArgumentsString();
        return String.format("usage: %s\n  %s.%s",
                formatCommand(),
                description,
                argsString.isEmpty() ? "" : String.format("\n\nArguments:\n%s", argsString));
    }

    /**
     * Returns a formatted list of arguments accepted by the command. Used by {@link #toDetailedString}.
     *
     * @return A formatted list of arguments.
     */
    private String getDetailedArgumentsString() {
        String positionalArgDesc = config.getPositionalArg() == DukeCommandArgument.NONE ? "" : String.format("  %s",
                config.getPositionalArg().toDetailedString());
        String namedArgsDesc = String.join("\n",
                config.getAcceptedNamedArgs().entrySet().stream().map(entry -> String.format("  /%s %s",
                        entry.getKey(), entry.getValue().toDetailedString())).toArray(String[]::new));
        return String.join("\n",
                Arrays.stream(new String[]{positionalArgDesc, namedArgsDesc}).filter(s -> !s.isEmpty())
                        .toArray(String[]::new));
    }

    /**
     * Runs the command with the given input arguments and objects. Throws an {@link InvalidCommandException} if
     * arguments provided are incompatible or malformed.
     *
     * @param taskList  The list of tasks.
     * @param ui        The {@link duke.Duke} object which the command uses to execute its commands.
     * @param storage   The storage object representing the file in which the tasks are stored.
     * @param arg       The positional argument to the command.
     * @param namedArgs The named arguments to the command.
     * @throws InvalidCommandException if the arguments provided are incompatible or malformed
     */
    @Override
    public void apply(TaskList taskList, Ui ui, Storage storage, String arg, Map<String, String> namedArgs)
            throws InvalidCommandException {
        config.assertCompatibilityWith(arg, namedArgs);
        action.apply(taskList, ui, storage, arg, namedArgs);
    }

    @Override
    public String toString() {
        return String.format("%-11s %s", name, description);
    }

    /**
     * Returns the given argument parsed as an integer representing an index in the task list.
     * Used by {@link DukeCommand#DELETE_TASK} and {@link DukeCommand#MARK_DONE}.
     *
     * @param taskList The list of tasks.
     * @param arg      The argument to parse as an index.
     * @throws InvalidCommandException If the given index is invalid.
     */
    private static int parseTaskIndex(TaskList taskList, String arg) throws InvalidCommandException {
        int index;
        try {
            index = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Index must be a number.");
        }
        if (index < 1) {
            throw new InvalidCommandException("Index must be at least 1.");
        } else if (taskList.size() < index) {
            int listSize = taskList.size();
            String listSizeMessage = listSize == 0 ? "task list is empty" : String.format("list only has %d %s",
                    listSize, listSize == 1 ? "task" : "tasks");
            throw new InvalidCommandException(String.format("Cannot access task %d; %s.", index, listSizeMessage));
        }
        return index - 1;
    }
}
