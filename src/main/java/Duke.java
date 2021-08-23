import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Duke {
    static String SEPARATOR = "____________________________________________________________";
    static String PREFIX = "\t";
    static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static String WELCOME_MSG = "Hello from\n" + LOGO + "What can I do for you?";
    static String BYE_MSG = "Bye. Hope to see you again soon!";

    static Path DATA_PATH = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");

    static List<Task> tasks = new ArrayList<Task>();

    static enum TaskType {
        DEADLINE("D"),
        EVENT("E"),
        TODO("T");

        private final String taskIcon;
        private TaskType(String taskIcon) {
            this.taskIcon = taskIcon;
        }

        public String getTaskIcon() {
            return this.taskIcon;
        }

        public static TaskType identifyTask(Task task) {
            return task instanceof Todo
                ? TaskType.TODO
                : task instanceof Deadline
                ? TaskType.DEADLINE
                : task instanceof Event
                ? TaskType.EVENT
                : null;
        }

        public static TaskType convertTaskIcon(String taskIcon) {
            switch (taskIcon) {
                case "D":
                    return TaskType.DEADLINE;
                case "E":
                    return TaskType.EVENT;
                case "T":
                    return TaskType.TODO;
                default:
                    throw new UnsupportedOperationException("unknown task icon");
            }
        }
    };

    public static void main(String[] args) {
        printBanner(WELCOME_MSG.split("\n"));
        try {
            tasks = readTaskList();
        } catch (Exception e) {
            printBanner(new String[]{ renderException(e) });
        }

        var sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            var command = sc.nextLine();
            try {
                boolean isEnd = menu(command);
                if (isEnd) {
                    break;
                }
            } catch (Exception e) {
                printBanner(new String[]{ renderException(e) });
            }
        }
        sc.close();
    }

    public static boolean menu(String input) throws Exception {
        var parameters = input.split(" ");
        var command = parameters[0];
        if (command.equals("bye") && parameters.length == 1) {
            printBanner(BYE_MSG.split("\n"));
            return true;
        } else if (command.equals("list") && parameters.length == 1) {
            printBanner(renderTaskList());
        } else if (command.equals("done") && parameters.length == 2) {
            int i = Integer.parseInt(parameters[1]) - 1;
            var task = tasks.get(i);
            task.toggle(true);
            writeTaskList(tasks);
            printBanner(new String[] {
                "Nice! I've marked this task as done:",
                "  " + renderTask(task)
            });
        } else if (command.equals("delete") && parameters.length == 2) {
            int i = Integer.parseInt(parameters[1]) - 1;
            var task = tasks.get(i);
            tasks.remove(i);
            writeTaskList(tasks);
            printBanner(new String[] {
                "Noted. I've removed this task:",
                "  " + renderTask(task),
                String.format("Now you have %d tasks in the list.", tasks.size()),
            });
        } else if (command.equals("todo")) {
            if (parameters.length == 1) {
                throw new Exception("The description of a todo cannot be empty.");
            }
            String taskLine = input.replace("todo", "").strip();
            var task = parseTaskLine(taskLine, TaskType.TODO);
            tasks.add(task);
            writeTaskList(tasks);
            printBanner(renderNewTask(task));
        } else if (command.equals("deadline") && input.contains("/by")) {
            String taskLine = input.replace("deadline", "").strip();
            var task = parseTaskLine(taskLine, TaskType.DEADLINE);
            tasks.add(task);
            writeTaskList(tasks);
            printBanner(renderNewTask(task));
        } else if (command.equals("event") && input.contains("/at")) {
            String taskLine = input.replace("event", "").strip();
            var task = parseTaskLine(taskLine, TaskType.EVENT);
            tasks.add(task);
            writeTaskList(tasks);
            printBanner(renderNewTask(task));
        } else {
            throw new Exception("I'm sorry, but I don't know what that means :-(");
        }
        return false;
    }

    public static List<Task> readTaskList() throws IOException {
        Files.createDirectories(DATA_PATH.getParent());
        if (!Files.exists(DATA_PATH)) {
            Files.createFile(DATA_PATH);
        }
        return Files.lines(DATA_PATH)
            .map((taskRow) -> parseTaskRow(taskRow))
            .collect(Collectors.toList());
    }

    public static void writeTaskList(List<Task> tasks) throws IOException {
        Stream<String> linesStream = tasks.stream().map((task) -> genTaskRow(task));
        Files.write(DATA_PATH, (Iterable<String>) linesStream::iterator);
    }

    public static Task parseTaskRow(String taskRow) {
        var taskParts = taskRow.split(" \\| ", 3);
        var taskType = TaskType.convertTaskIcon(taskParts[0]);
        var taskIsDone = Boolean.parseBoolean(taskParts[1]);
        var taskLine = taskParts[2];

        var task = parseTaskLine(taskLine, taskType);
        task.toggle(taskIsDone);
        return task;
    }

    public static Task parseTaskLine(String taskLine, TaskType taskType) {
        switch (taskType) {
            case TODO:
                return new Todo(taskLine);
            case DEADLINE:
                String[] deadlineParts = taskLine.split("\\s+/by\\s+", 2);
                return new Deadline(deadlineParts[0], deadlineParts[1]);
            case EVENT:
                String[] eventParts = taskLine.split("\\s+/at\\s+", 2);
                return new Event(eventParts[0], eventParts[1]);
            default:
                throw new UnsupportedOperationException("task type is not a valid enum value");
        }
    }

    public static String genTaskRow(Task task) {
        return String.format(
            "%s | %b | %s",
            TaskType.identifyTask(task).getTaskIcon(),
            task.getIsDone(),
            genTaskLine(task)
        );
    }

    public static String genTaskLine(Task task) {
        var taskType = TaskType.identifyTask(task);
        switch (taskType) {
            case TODO:
                return task.getDescription();
            case DEADLINE:
                var deadline = (Deadline) task;
                return String.format("%s /by %s", deadline.getDescription(), deadline.getDeadline());
            case EVENT:
                var event = (Event) task;
                return String.format("%s /at %s", event.getDescription(), event.getTime());
            default:
                throw new UnsupportedOperationException("task type is not a valid enum value");
        }
    }

    public static String renderTask(Task task) {
        var taskType = TaskType.identifyTask(task);
        String taskIcon = taskType.getTaskIcon();
        String statusIcon = task.getStatusIcon();
        switch (taskType) {
            case TODO:
                return String.format("[%s][%s] %s", taskIcon, statusIcon, task.getDescription());
            case DEADLINE:
                var deadline = (Deadline) task;
                return String.format("[%s][%s] %s (by: %s)", taskIcon, statusIcon, deadline.getDescription(), deadline.getDeadline());
            case EVENT:
                var event = (Event) task;
                return String.format("[%s][%s] %s (at: %s)", taskIcon, statusIcon, event.getDescription(), event.getTime());
            default:
                throw new UnsupportedOperationException("task type is not a valid enum value");
        }
    }

    public static String[] renderNewTask(Task task) {
        return new String[] {
            "Got it. I've added this task:",
            "  " + renderTask(task),
            String.format("Now you have %d tasks in the list.", tasks.size())
        };
    }

    public static String[] renderTaskList() {
        return Stream.concat(
            Stream.of("Here are the tasks in your list:"),
            IntStream.range(0, tasks.size())
                .mapToObj(i ->
                    String.format("%d. %s", i + 1, renderTask(tasks.get(i)))
                )
        ).toArray(String[]::new);
    }

    public static String renderException(Exception e) {
        return "☹ OOPS!!! " + e.getMessage();
    }

    public static void printBanner(String[] lines) {
        System.out.println(PREFIX + SEPARATOR);
        for (String line : lines) {
            System.out.println(PREFIX + line);
        }
        System.out.println(PREFIX + SEPARATOR);
    }
}
