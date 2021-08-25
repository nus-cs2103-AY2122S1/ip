import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String DATAPATH = "./data";
    private static String DATAFILE = "duke.txt";

    private Scanner input = new Scanner(System.in);
    private ArrayList<Task> list = new ArrayList<>();

    private enum CommandType {
        BYE, LIST,
        TODO, EVENT, DEADLINE,
        DONE, DELETE,
    }

    private void greet() {
        String logo = " ____        _\n"
                    + "|  _ \\ _   _| | _____\n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?\n");
    }


    private void loadData() {
        File f = new File(String.valueOf(Paths.get(Duke.DATAPATH, Duke.DATAFILE)));
        try {
            Scanner fileReader = new Scanner(f);
            System.out.println("data has been loaded");
            while (fileReader.hasNextLine()) {
                String entry = fileReader.nextLine();
                // TODO: read from database
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            this.createDatabase();
        }
    }


    private void createDatabase() {
        File f = new File(String.valueOf(Paths.get(Duke.DATAPATH, Duke.DATAFILE)));
        File dir = new File(Duke.DATAPATH);
        dir.mkdir();
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Error loading database!");
        }
    }


    private void run() {
        try {
            String rawInput = this.input.nextLine();
            String[] userInput = rawInput.split(" ", 2);
            CommandType command = this.getCommand(userInput[0]);
            switch (command) {
            case BYE:
                this.exit();
                this.input.close();
                return;
            case LIST:
                this.showList();
                break;
            case TODO:
                this.addTodo(userInput);
                break;
            case EVENT:
                this.addEvent(userInput);
                break;
            case DEADLINE:
                this.addDeadline(userInput);
                break;
            case DONE:
                this.markAsDone(userInput);
                break;
            case DELETE:
                this.delete(userInput);
                break;
            }
        } catch (DukeException e) {
            System.out.printf("\t%s\n\n", e);
        }
        this.run();
    }


    private CommandType getCommand(String command) throws DukeUnknownCommandException {
        try {
            return Duke.CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new DukeUnknownCommandException(command);
        }
    }


    private void exit() {
        // TODO: write to the database
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
        duke.greet();
        duke.loadData();
        duke.run();
    }
}