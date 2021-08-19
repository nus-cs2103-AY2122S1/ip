import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Scanner input = new Scanner(System.in);
    private ArrayList<Task> list = new ArrayList<>();

    private void printLogo() {
        String logo = " ____        _\n"
                    + "|  _ \\ _   _| | _____\n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }


    private void greet() {
        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?\n");
    }


    private void run() {
        while (true) {
            String rawInput = this.input.nextLine();
            String[] userInput = rawInput.split(" ", 2);
            String command = userInput[0];
            try {
                if (command.equals("bye")) {
                    this.exit();
                    break;
                } else if (command.equals("list")) {
                    this.showList();
                } else if (command.equals("todo")) {
                    this.addTodo(userInput);
                } else if (command.equals("event")) {
                    this.addEvent(userInput);
                } else if (command.equals("deadline")) {
                    this.addDeadline(userInput);
                } else if (command.equals("done")) {
                    this.markAsDone(userInput);
                } else if (command.equals("delete")) {
                    this.delete(userInput);
                } else {
                    throw new DukeUnknownCommandException(command);
                }
            } catch (DukeException e) {
                System.out.printf("\t%s\n\n", e);
            }
        }
        this.input.close();
    }


    private void exit() {
        System.out.println("\tBye, hope to see you again!");
    }


    private void showList() {
        if (this.list.size() == 0) {
            System.out.println("\tYou have no task in your list.\n");
            return;
        }
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 1; i <= this.list.size(); i++) {
            System.out.println("\t" + i + ". " + this.list.get(i - 1));
        }
        System.out.println();
    }


    private void addTodo(String[] userInput) throws DukeMissingArgumentException {
        try {
            String description = userInput[1];
            this.list.add(new Todo(description));
            System.out.printf("\tadded todo:\n\t\t%s\n", this.list.get(this.list.size() - 1));
            System.out.printf("\tYou have %d tasks in the list.\n\n", this.list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }


    private void addEvent(String[] userInput) throws DukeMissingArgumentException {
        try {
            String[] splits = userInput[1].split(" /at ", 2);
            this.list.add(new Event(splits[0], splits[1]));
            System.out.printf("\tadded event:\n\t\t%s\n", this.list.get(this.list.size() - 1));
            System.out.printf("\tYou have %d tasks in the list.\n\n", this.list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }


    private void addDeadline(String[] userInput) throws DukeMissingArgumentException {
        try {
            String[] splits = userInput[1].split(" /by ", 2);
            this.list.add(new Deadline(splits[0], splits[1]));
            System.out.printf("\tadded deadline:\n\t\t%s\n", this.list.get(this.list.size() - 1));
            System.out.printf("\tYou have %d tasks in the list.\n\n", this.list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }


    private void markAsDone(String[] userInput)
            throws DukeNoTaskFoundException, DukeMissingArgumentException, DukeInvalidArgumentException {
        try {
            int taskNum = Integer.parseInt(userInput[1]);
            if (taskNum > this.list.size()) {
                throw new DukeNoTaskFoundException(taskNum);
            }
            this.list.get(taskNum - 1).markAsDone();
            System.out.println("\tI've marked this task as done!");
            System.out.printf("\t\t%s\n\n", this.list.get(taskNum - 1));
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }


    private void delete(String[] userInput)
            throws DukeNoTaskFoundException, DukeMissingArgumentException, DukeInvalidArgumentException {
        try {
            int taskNum = Integer.parseInt(userInput[1]);
            if (taskNum > this.list.size()) {
                throw new DukeNoTaskFoundException(taskNum);
            }
            System.out.println("\tI've deleted this task from the list!");
            System.out.printf("\t\t%s\n", this.list.get(taskNum - 1));
            this.list.remove(taskNum - 1);
            System.out.printf("\tYou have %d tasks in the list.\n\n", this.list.size());
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }


    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.printLogo();
        duke.greet();
        duke.run();
    }
}