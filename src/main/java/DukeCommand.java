import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum DukeCommand implements DukeCommandAction {
    HELP("help",
            "Display all commands",
            DukeCommandConfig.NO_ARGUMENTS,
            (Duke duke, String arg, Map<String, String> namedArgs) -> {
                duke.commandFormatter.printOutputLine("ALL COMMANDS:");
                Arrays.stream(DukeCommand.values())
                        .sorted(Comparator.comparing(DukeCommand::getName))
                        .map(DukeCommand::toString)
                        .forEach(duke.commandFormatter::printOutputLine);
                return true;
            }),
    LIST_TASKS("list",
            "List all tasks",
            DukeCommandConfig.NO_ARGUMENTS,
            (Duke duke, String arg, Map<String, String> namedArgs) -> {
                duke.output(String.format("You have %d %s.", duke.taskList.size(), duke.taskList.size() == 1 ? "task" : "tasks"));
                duke.taskList.stream()
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
                                int index = duke.taskList.indexOf(task);
                                duke.commandFormatter.printOutputLine(String.format("%d. %s", index + 1, task));
                            }
                            duke.commandFormatter.printOutputLine("");
                        });
                return true;
            }),
    ADD_TASK("add",
            "Add a task (with optionally a deadline or a date)",
            new DukeCommandConfig(new DukeCommandArgument("task", DukeCommandArgumentType.REQUIRED),
                    Map.of("by", new DukeCommandArgument("deadline", DukeCommandArgumentType.OPTIONAL),
                            "at", new DukeCommandArgument("date", DukeCommandArgumentType.OPTIONAL))),
            (Duke duke, String arg, Map<String, String> namedArgs) -> {
                DukeTask task;
                if (namedArgs.containsKey("by")) {
                    task = new DukeDeadlineTask(arg, namedArgs.get("by"));
                } else if (namedArgs.containsKey("at")) {
                    task = new DukeEvent(arg, namedArgs.get("at"));
                } else {
                    task = new DukeTask(arg);
                }
                duke.taskList.add(task);
                duke.commandFormatter.printOutputLine(String.format("Task added with title: %s", arg));
                return true;
            }),
    EXIT("bye",
            "Exit Duke",
            DukeCommandConfig.NO_ARGUMENTS,
            (Duke duke, String arg, Map<String, String> namedArgs) -> false),
    MARK_DONE("done",
            "Mark the task at the given index as done",
            new DukeCommandConfig(new DukeCommandArgument("index", DukeCommandArgumentType.REQUIRED), Map.of()),
            (Duke duke, String arg, Map<String, String> namedArgs) -> {
                int index = Integer.parseInt(arg) - 1;
                assert (!duke.taskList.isEmpty() && duke.taskList.size() > index);
                DukeTask task = duke.taskList.get(index);
                task.markAsDone();
                duke.commandFormatter.printOutputLine("I've marked the following task as done!");
                duke.commandFormatter.printOutputLine(task.toString());
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
            return String.format(" [%s]", config.positionalArg.getName());
        }
    }

    private String formatNamedArguments() {
        if (config.acceptedNamedArgs.isEmpty()) {
            return "";
        }
        return " " + String.join(" ", config.acceptedNamedArgs.entrySet().stream().map(entry -> String.format("[/%s [%s]]", entry.getKey(), entry.getValue().getName())).toArray(String[]::new));
    }

    @Override
    public String toString() {
        return String.format("- %s\n    %s",
                formatCommand(),
                description);
    }

    @Override
    public boolean apply(Duke duke, String arg, Map<String, String> namedArgs) throws InvalidCommandException {
        config.assertCompatibilityWith(arg, namedArgs);
        return action.apply(duke, arg, namedArgs);
    }

}