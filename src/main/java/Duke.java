import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class Duke {

//    protected boolean isTerminated;

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            ArrayList<Task> taskList = storage.load();
            if (taskList.isEmpty()) {
                throw new DukeException("task list is empty");
            }
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

//        this.isTerminated = false;
    }

    public static void main(String[] args) {
        Duke victor = new Duke(Paths.get(System.getProperty("user.dir"), "data", "duke.txt"));
        victor.run();
//        Command command;
//        Scanner scanner = new Scanner(System.in);
//        victor.run();
//
//        while(!victor.isTerminated) {
//            command = victor.listen(scanner);
//            victor.executeCommand(command);
//        }
//        scanner.close();
    }

    public void run() {
        storage.load();
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                if (c == null) {
                    System.out.println("found null");
                    throw new DukeException("Command is null");
                }
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

//    public void run() {
//        storage.load();
//        ui.showWelcome();
//    }

//    private void executeCommand(Command command) {
//        try {
//            switch (command.getInstruction()) {
//                case LIST:
//                    ui.printList(tasks);
//                    break;
//                case TODO:
//                    if (command.getParameter_1() == null) {
//                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
//                    }
//                    createTask(new Todo(command.getParameter_1()));
//                    break;
//                case DEADLINE:
//                    if (command.getParameter_1() == null) {
//                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
//                    }
//                    if (command.getParameter_2() == null) {
//                        throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty.");
//                    }
//                    createTask(new Deadline(command.getParameter_1(), command.getParameter_2()));
//                    break;
//                case EVENT:
//                    if (command.getParameter_1() == null) {
//                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
//                    }
//                    if (command.getParameter_2() == null) {
//                        throw new DukeException("☹ OOPS!!! The date of an event cannot be empty.");
//                    }
//                    createTask(new Event(command.getParameter_1(), command.getParameter_2()));
//                    break;
//                case DONE:
//                    if (command.getParameter_1() == null) {
//                        throw new DukeException("☹ OOPS!!! The task number cannot be empty.");
//                    }
//                    if (Integer.parseInt(command.getParameter_1()) < 1 ||
//                            Integer.parseInt(command.getParameter_1()) > tasks.size()) {
//                        throw new DukeException("☹ OOPS!!! The task number is invalid");
//                    }
//                    markAsDone(Integer.parseInt(command.getParameter_1()));
//                    break;
//                case DELETE:
//                    if (command.getParameter_1() == null) {
//                        throw new DukeException("☹ OOPS!!! The task number cannot be empty.");
//                    }
//                    if (Integer.parseInt(command.getParameter_1()) < 1 ||
//                            Integer.parseInt(command.getParameter_1()) > tasks.size()) {
//                        throw new DukeException("☹ OOPS!!! The task number is invalid");
//                    }
//                    deleteTask(Integer.parseInt(command.getParameter_1()));
//                    break;
//                case BYE:
//                    bye();
//                    break;
//                case ERROR:
//                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//                default:
//                    throw new DukeException("☹ OOPS!!! Something went wrong");
//            }
//        }
//        catch (DukeException e) {
//            ui.formatPrint(e.getMessage());
//        }
//    }

//    private Command listen(Scanner scanner) {
//        String instruction = scanner.nextLine();
//        return new Command(instruction);
//    }

//    private void markAsDone(int taskNumber) {
//        Task task = tasks.markAsDone(taskNumber - 1);
//        storage.update(taskNumber, task, "m");
//        ui.formatPrint("Nice! I've marked this task as done:", "  [X] " + task.toString());
//    }

//    private void createTask(Task task) {
//        storage.save(task);
//        tasks.add(task);
//        ui.formatPrint("Got it. I've added this task:", "  " + task.toString(), tasks.toString());
//    }

//    private void deleteTask(int taskNumber) {
//        Task task = tasks.remove(taskNumber - 1);
//        storage.update(taskNumber, task, "d");
//        ui.formatPrint("Noted. I've removed this task:", "  " + task.toString(), tasks.toString());
//    }

//    private void bye() {
//        ui.showFarewell();
//        terminate();
//    }

//    private void terminate() {
//        this.isTerminated = true;
//    }
}
