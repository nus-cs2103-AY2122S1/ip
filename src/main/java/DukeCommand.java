import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

public enum DukeCommand implements DukeCommandAction {
    HELP("help",
            "Display all commands",
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
                            for (int i = 0; i < tasks.size(); i++) {
                                DukeTask task = tasks.get(i);
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
                    Map.of("by", new DukeCommandArgument("deadline", "The deadline of the task",  DukeCommandArgumentType.OPTIONAL),
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
                    task = new DukeTask(arg);
                }
                duke.getTaskList().add(task);
                duke.output(String.format("Task added with title: %s", arg));
                return true;
            }),
    EXIT("bye",
            "Exit Duke",
            DukeCommandConfig.NO_ARGUMENTS,
            (Duke duke, String arg, Map<String, String> namedArgs) -> false),
    MARK_DONE("done",
            "Mark the task at the given index as done",
            new DukeCommandConfig(new DukeCommandArgument("index", "The position of the task in the list", DukeCommandArgumentType.REQUIRED), Map.of()),
            (Duke duke, String arg, Map<String, String> namedArgs) -> {
                int index;
                try {
                    index = Integer.parseInt(arg) - 1;
                } catch (NumberFormatException e) {
                    throw new InvalidCommandException("Index must be a number.");
                }
                if (duke.getTaskList().isEmpty()) {
                    throw new InvalidCommandException("Task list is empty.");
                } else if (duke.getTaskList().size() <= index) {
                    throw new InvalidCommandException("Index cannot be greater than list size.");
                }
                DukeTask task = duke.getTaskList().get(index);
                task.markAsDone();
                duke.output("I've marked the following task as done!");
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

    private String getDetailedArgumentsString() {
        return String.format("%s%s", config.positionalArg == DukeCommandArgument.NONE ? "" : String.format("  %s\n", config.positionalArg.toDetailedString()),
                String.join("\n", config.acceptedNamedArgs.entrySet().stream().map(entry -> String.format("  /%s %s", entry.getKey(), entry.getValue().toDetailedString())).toArray(String[]::new)));
    }

    @Override
    public String toString() {
        return String.format("%-10s %s", command, description);
    }

    @Override
    public boolean apply(Duke duke, String arg, Map<String, String> namedArgs) throws InvalidCommandException {
        config.assertCompatibilityWith(arg, namedArgs);
        return action.apply(duke, arg, namedArgs);
    }

}