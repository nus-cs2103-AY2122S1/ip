import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Duke {
    final DukeCommandFormatter commandFormatter = new DukeCommandFormatter(System.in, System.out);
    final List<DukeTask> taskList = new ArrayList<>();
    final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

    public void start() {
        printWelcomeMessage();
        DukeCommand.HELP.apply(this, null, null);
        boolean shouldListen;
        do {
            String command = commandFormatter.nextCommand();
            shouldListen = processCommand(command);
        } while (shouldListen);
        printExitMessage();
    }

    public void printWelcomeMessage() {
        commandFormatter.printOutputLine("Hello from\n" + logo);
    }

    public void printExitMessage() {
        commandFormatter.printOutputLine("Goodbye from\n" + logo);
    }

    /**
     * Processes the given command (a line). Returns true if more commands are to be listened to.
     *
     * @param command The command to be processed.
     * @return If Duke should continue listening to commands.
     */
    public boolean processCommand(String command) {
        // Get the longest duke command that matches to command
        Optional<DukeCommand> dukeCommand = Arrays.stream(DukeCommand.values())
                .sorted(Comparator.comparingInt(c -> -c.getCommand().length()))
                .filter(c -> command.startsWith(c.getCommand()))
                .findFirst();
        if (dukeCommand.isPresent()) {
            DukeCommand actualCommand = dukeCommand.get();
            String arguments = command.substring(actualCommand.getCommand().length());
            String[] tokens = arguments.split("/");
            String positionalArg = tokens[0].trim();
            Map<String, String> namedArgs = new HashMap<>();
            for (int i = 1; i < tokens.length; i++) {
                String[] namedArg = tokens[i].trim().split(" ", 2);
                namedArgs.put(namedArg[0].trim(), namedArg[1].trim());
            }
            return dukeCommand.get().apply(this, positionalArg, namedArgs);
        } else {
            return true;
        }
    }

    private static class DukeCommandFormatter {
        private final Scanner scanner;
        private final PrintStream outputStream;

        DukeCommandFormatter(InputStream inputStream, PrintStream outputStream) {
            this.scanner = new Scanner(inputStream);
            this.outputStream = outputStream;
        }

        String nextCommand() {
            outputStream.print("Duke> ");
            return scanner.nextLine();
        }

        void printOutputLine(String output) {
            outputStream.println(output);
        }
    }

    @FunctionalInterface
    public interface DukeCommandAction {
        /**
         * Processes the given command (a line). Returns true if more commands are to be listened to.
         *
         * @param duke      The Duke object which the command uses to execute its commands.
         * @param arg       The positional argument to the command.
         * @param namedArgs The named arguments to the command.
         * @return If Duke should continue listening to commands.
         */
        boolean apply(Duke duke, String arg, Map<String, String> namedArgs);
    }

    private enum DukeCommand implements DukeCommandAction {
        HELP("help", "Display all commands", (Duke duke, String arg, Map<String, String> namedArgs) -> {
            duke.commandFormatter.printOutputLine("ALL COMMANDS:");
            Arrays.stream(DukeCommand.values())
                    .sorted(Comparator.comparing(DukeCommand::getCommand))
                    .map(DukeCommand::toString)
                    .forEach(duke.commandFormatter::printOutputLine);
            return true;
        }),
        LIST_TASKS("list", "List all tasks", (Duke duke, String arg, Map<String, String> namedArgs) -> {
            duke.commandFormatter.printOutputLine(String.format("You have %d %s.", duke.taskList.size(), duke.taskList.size() == 1 ? "task" : "tasks"));
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
                        duke.commandFormatter.printOutputLine(group);
                        for (int i = 0; i < tasks.size(); i++) {
                            DukeTask task = tasks.get(i);
                            int index = duke.taskList.indexOf(task);
                            duke.commandFormatter.printOutputLine(String.format("%d. %s", index + 1, task));
                        }
                        duke.commandFormatter.printOutputLine("");
                    });
            return true;
        }),
        ADD_TASK("add", "Add a task", (Duke duke, String arg, Map<String, String> namedArgs) -> {
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
        EXIT("bye", "Exit Duke", (Duke duke, String arg, Map<String, String> namedArgs) -> false),
        MARK_DONE("done", "Mark the task as done", (Duke duke, String arg, Map<String, String> namedArgs) -> {
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
        final DukeCommandAction action;

        DukeCommand(String command, String description, DukeCommandAction action) {
            this.command = command;
            this.description = description;
            this.action = action;
        }

        public String getCommand() {
            return command;
        }

        @Override
        public String toString() {
            return String.format("%s - %s", command, description);
        }

        @Override
        public boolean apply(Duke duke, String arg, Map<String, String> namedArgs) {
            return action.apply(duke, arg, namedArgs);
        }
    }
}
