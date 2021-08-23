import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class Duke {
    protected boolean isTerminated;
    protected ArrayList<Task> list;

    public Duke() {
        this.isTerminated = false;
        list = new ArrayList<>();
    }

    public static void main(String[] args) {
        String logo =
                          "██    ██ ██  ██████ ████████  ██████  ██████  \n"
                        + "██    ██ ██ ██         ██    ██    ██ ██   ██ \n"
                        + "██    ██ ██ ██         ██    ██    ██ ██████  \n"
                        + " ██  ██  ██ ██         ██    ██    ██ ██   ██ \n"
                        + "  ████   ██  ██████    ██     ██████  ██   ██ \n";
        Duke victor = new Duke();
        victor.loadTask();
        victor.greet();
        Command command;
        Scanner scanner = new Scanner(System.in);

        while(!victor.isTerminated) {
            command = victor.listen(scanner);
            victor.executeCommand(command);
        }
        scanner.close();
    }

    private void executeCommand(Command command) {
        try {
            switch (command.getInstruction()) {
                case LIST:
                    printList();
                    break;
                case TODO:
                    if (command.getParameter_1() == null) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    createTask(new Todo(command.getParameter_1()));
                    break;
                case DEADLINE:
                    if (command.getParameter_1() == null) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    if (command.getParameter_2() == null) {
                        throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty.");
                    }
                    createTask(new Deadline(command.getParameter_1(), command.getParameter_2()));
                    break;
                case EVENT:
                    if (command.getParameter_1() == null) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    if (command.getParameter_2() == null) {
                        throw new DukeException("☹ OOPS!!! The date of an event cannot be empty.");
                    }
                    createTask(new Event(command.getParameter_1(), command.getParameter_2()));
                    break;
                case DONE:
                    if (command.getParameter_1() == null) {
                        throw new DukeException("☹ OOPS!!! The task number cannot be empty.");
                    }
                    if (Integer.parseInt(command.getParameter_1()) < 1 ||
                            Integer.parseInt(command.getParameter_1()) > list.size()) {
                        throw new DukeException("☹ OOPS!!! The task number is invalid");
                    }
                    markAsDone(Integer.parseInt(command.getParameter_1()));
                    break;
                case DELETE:
                    if (command.getParameter_1() == null) {
                        throw new DukeException("☹ OOPS!!! The task number cannot be empty.");
                    }
                    if (Integer.parseInt(command.getParameter_1()) < 1 ||
                            Integer.parseInt(command.getParameter_1()) > list.size()) {
                        throw new DukeException("☹ OOPS!!! The task number is invalid");
                    }
                    deleteTask(Integer.parseInt(command.getParameter_1()));
                    break;
                case BYE:
                    bye();
                    break;
                case ERROR:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                default:
                    throw new DukeException("☹ OOPS!!! Something went wrong");
            }
        }
        catch (DukeException e) {
            formatPrint(e.getMessage());
        }
    }

    private void greet() {
        String greeting = "Howdy, pardner!";
        String question = "Is there anything old Vic can do you for?";
        formatPrint(greeting, question);
    }

    private Command listen(Scanner scanner) {
        String instruction = scanner.nextLine();
        return new Command(instruction);
    }

    private void markAsDone(int taskNumber) {
        Task task = this.list.get(taskNumber - 1);
        task.markAsDone();
        updateTask(taskNumber, task, "m");
        formatPrint("Nice! I've marked this task as done:", "  [X] " + task.toString());
    }

    private void printList() {
        int count = 1;
        System.out.print("\n    ____________________________________________________________" +
                "\n    Here are the tasks in your list:\n");
        for (Task task : this.list) {
            System.out.printf("     %d.%s\n", count, task.toString());
            count++;
        }
        System.out.println("    ____________________________________________________________\n");
    }

    private void createTask(Task task) {
        saveTask(task);
        list.add(task);
        formatPrint("Got it. I've added this task:", "  " + task.toString(),
                "Now you have " + list.size() + " tasks in the list.");
    }

    private Path getPath() {
        // establish the directory
        String home = System.getProperty("user.dir");
        return Paths.get(home, "data", "duke.txt");
    }

    private Task convertDataToTask(String data) {
        try {
            String[] dataValues = data.split(" \\| ");
            String type = dataValues[0];
            boolean isCompleted = dataValues[1].equals("1");
            String description = dataValues[2];
            String date;
            if (dataValues.length > 3) {
                date = dataValues[3];
                switch (type) {
                    case "D":
                        return new Deadline(description, isCompleted, date);
                    case "E":
                        return new Event(description, isCompleted, date);
                    default :
                        throw new DukeException("something went wrong");
                }
            }
            return new Todo(description, isCompleted);
        }
        catch (DukeException e) {
            formatPrint(e.getMessage());
        }
        return null;
    }

    private void loadTask() {
        Path path = getPath();
        boolean directoryExists = Files.exists(path);

        try {
            if (directoryExists) {
                // for everything in the data file, add to the list
                List<String> data = Files.readAllLines(path);
                for (String str : data) {
                    Task task = convertDataToTask(str);
                    this.list.add(task);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveTask(Task task) {
        Path path = getPath();
        boolean directoryExists = Files.exists(path);
        // access the data file and make changes to it
        try {
            if (directoryExists) {
                String newData = task.toData() + '\n';
                Files.write(path, newData.getBytes(), StandardOpenOption.APPEND);
            } else {
                throw new DukeException("Data file doesn't exist");
            }
        }
        catch (IOException | DukeException e) {
            formatPrint(e.getMessage());
        }
    }

    private void updateTask(int taskNumber, Task task, String action) {
        Path path = getPath();
        boolean directoryExists = Files.exists(path);
        try {
            if (directoryExists) {
                List<String> lines = Files.readAllLines(path);
                if (action.equals("m")) {
                    lines.set(taskNumber - 1, task.toData());
                } else {
                    lines.remove(taskNumber - 1);
                }
                Files.write(path, lines);
            } else {
                throw new DukeException("Data file doesn't exist");
            }
        }
        catch (IOException | DukeException e) {
            formatPrint(e.getMessage());
        }
    }

    private void deleteTask(int taskNumber) {
        Task task = this.list.get(taskNumber - 1);
        updateTask(taskNumber, task, "d");
        this.list.remove(task);
        formatPrint("Noted. I've removed this task:", "  " + task.toString(),
                "Now you have " + list.size() + " tasks in the list.");
    }

    private void bye() {
        String bye = "See ya round, buckaroo.";
        formatPrint(bye);
        terminate();
    }

    private void terminate() {
        this.isTerminated = true;
    }

    private void formatPrint(String... lines) {
        System.out.println("\n    ____________________________________________________________");
        for (String line : lines) {
            System.out.printf("     %s\n", line);
        }
        System.out.println("    ____________________________________________________________\n");
    }
}
