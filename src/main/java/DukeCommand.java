import java.util.*;
import java.util.stream.Collectors;

public enum DukeCommand implements DukeCommandAction {
    HELP("help",
            "Display all commands, or detailed info of given command",
            new DukeCommandConfig(new DukeCommandArgument("command_name", "The name of the command to view help for", DukeCommandArgumentType.OPTIONAL), Map.of()),
            (Duke duke, String arg, Map<String, String> namedArgs) -> {
                if (arg.isEmpty()) {
                    duke.output("ALL COMMANDS:");
                    Arrays.stream(DukeCommand.values())
                            .sorted(Comparator.comparing(DukeCommand::getName))
                            .map(DukeCommand::toString)
                            .forEach(duke::output);
                } else {
                    Optional<DukeCommand> command = getClosestMatch(arg);
                    if (command.isEmpty()) {
                        throw new InvalidCommandException(String.format("No command named \"%s\".", arg));
                    } else {
                        duke.output(command.get().toDetailedString());
                    }
                }
                return true;
            }),
    LIST_TASKS("list",
            "List all tasks",
            DukeCommandConfig.NO_ARGUMENTS,
            (Duke duke, String arg, Map<String, String> namedArgs) -> {
                duke.output(String.format("You have %d %s.", duke.getTaskList().size(), duke.getTaskList().size() == 1 ? "task" : "tasks"));
                duke.getTaskList().stream()
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
                            duke.output(group);
                            for (DukeTask task : tasks) {
                                int index = duke.getTaskList().indexOf(task);
                                duke.output(String.format("%d. %s", index + 1, task));
                            }
                            duke.output("");
                        });
                return true;
            }),
    ADD_TASK("add",
            "Add a task (with optionally a deadline or a date)",
            new DukeCommandConfig(new DukeCommandArgument("task", "The name of the task", DukeCommandArgumentType.REQUIRED),
                    Map.of("by", new DukeCommandArgument("deadline", "The deadline of the task", DukeCommandArgumentType.OPTIONAL),
                            "at", new DukeCommandArgument("date", "The date of the event", DukeCommandArgumentType.OPTIONAL))),
            (Duke duke, String arg, Map<String, String> namedArgs) -> {
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
                duke.addTask(task);
                duke.output(String.format("Task added with title: %s", arg));
                return true;
            }),
    DELETE_TASK("delete",
            "Delete a task",
            new DukeCommandConfig(new DukeCommandArgument("index", "The position of the task in the list", DukeCommandArgumentType.REQUIRED), Map.of()),
            (Duke duke, String arg, Map<String, String> namedArgs) -> {
                DukeTask task = duke.removeTaskAt(parseTaskIndex(duke, arg));
                duke.output("I've removed the following task.");
                duke.output(task.toString());
                return true;
            }),
    EXIT("bye",
            "Exit Duke",
            DukeCommandConfig.NO_ARGUMENTS,
            (Duke duke, String arg, Map<String, String> namedArgs) -> false),
    MARK_DONE("done",
            "Mark the task as done",
            new DukeCommandConfig(new DukeCommandArgument("index", "The position of the task in the list", DukeCommandArgumentType.REQUIRED), Map.of()),
            (Duke duke, String arg, Map<String, String> namedArgs) -> {
                int index = parseTaskIndex(duke, arg);
                DukeTask task = duke.markTaskAsDoneAt(index);
                if (duke.isTaskDoneAt(index)) {
                    duke.output("The following task is already marked as done! Good job!");
                } else {
                    duke.output("I've marked the following task as done!");
                }
                duke.output(task.toString());
                return true;
            });

    final String command;
    final String description;
    final DukeCommandConfig config;
    final DukeCommandAction action;

    DukeCommand(String command, String description, DukeCommandConfig config, DukeCommandAction action) {
        this.command = command;
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

    public String getName() {
        return command;
    }

    private String formatCommand() {
        return String.format("%s%s%s", command, formatPositionalArgument(), formatNamedArguments());
    }

    private String formatPositionalArgument() {
        if (config.positionalArg == DukeCommandArgument.NONE) {
            return "";
        } else {
            return " " + config.positionalArg.toString();
        }
    }

    private String formatNamedArguments() {
        if (config.acceptedNamedArgs.isEmpty()) {
            return "";
        }
        return " " + String.join(" ", config.acceptedNamedArgs.entrySet().stream().map(entry -> String.format("[/%s %s]", entry.getKey(), entry.getValue())).toArray(String[]::new));
    }

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
        String positionalArgDesc = config.positionalArg == DukeCommandArgument.NONE ? "" : String.format("  %s", config.positionalArg.toDetailedString());
        String namedArgsDesc = String.join("\n", config.acceptedNamedArgs.entrySet().stream().map(entry -> String.format("  /%s %s", entry.getKey(), entry.getValue().toDetailedString())).toArray(String[]::new));
        return String.join("\n", Arrays.stream(new String[]{positionalArgDesc, namedArgsDesc}).filter(s -> !s.isEmpty()).toArray(String[]::new));
    }

    @Override
    public boolean apply(Duke duke, String arg, Map<String, String> namedArgs) throws InvalidCommandException {
        config.assertCompatibilityWith(arg, namedArgs);
        return action.apply(duke, arg, namedArgs);
    }

    @Override
    public String toString() {
        return String.format("%-11s %s", command, description);
    }

    /**
     * Returns the given argument parsed as an integer representing an index in the task list.
     * Used by {@link DukeCommand#DELETE_TASK} and {@link DukeCommand#MARK_DONE}.
     *
     * @param duke The duke object containing the task list.
     * @param arg  The argument to parse as an index.
     * @throws InvalidCommandException If the given index is invalid.
     */
    private static int parseTaskIndex(Duke duke, String arg) throws InvalidCommandException {
        int index;
        try {
            index = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Index must be a number.");
        }
        if (index < 1) {
            throw new InvalidCommandException("Index must be at least 1.");
        } else if (duke.getTaskList().size() < index) {
            int listSize = duke.getTaskList().size();
            String listSizeMessage = listSize == 0 ? "task list is empty" : String.format("list only has %d %s", listSize, listSize == 1 ? "task" : "tasks");
            throw new InvalidCommandException(String.format("Cannot access task %d; %s.", index, listSizeMessage));
        }
        return index - 1;
    }
}