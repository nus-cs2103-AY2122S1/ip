import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    private static String DIR = "./data";
    private static String FILENAME = "duke.txt";
    private static String DATAPATH = String.valueOf(Paths.get(Duke.DIR, Duke.FILENAME));

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
        File db = new File(Duke.DATAPATH);
        try {
            Scanner fileReader = new Scanner(db);
            while (fileReader.hasNextLine()) {
                String entry = fileReader.nextLine();
                readEntry(entry);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            this.createDatabase();
        } catch (DukeException e) {
            System.out.println("Error when reading entry!");
        }
    }


    private void readEntry(String entry) throws DukeDatabaseException {
        String[] fields = entry.split("\\|");
        Task taskToAdd;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        switch (fields[0]) {
        case "T":
            taskToAdd = new Todo(fields[2]);
            break;
        case "E":
            taskToAdd = new Event(fields[2],
                    LocalDateTime.parse(fields[3], formatter),
                    LocalDateTime.parse(fields[4], formatter));
            break;
        case "D":
            taskToAdd = new Deadline(fields[2], LocalDateTime.parse(fields[3], formatter));
            break;
        default:
            throw new DukeDatabaseException();
        }
        if (Integer.parseInt(fields[1]) == 1) {
            taskToAdd.markAsDone();
        }
        this.list.add(taskToAdd);
    }


    private void createDatabase() {
        File db = new File(Duke.DATAPATH);
        File dir = new File(Duke.DIR);
        dir.mkdir();
        try {
            db.createNewFile();
        } catch (IOException e) {
            System.out.println("Error when creating the database!");
        }
    }


    private void run() {
        this.greet();
        this.loadData();

        while (true) {
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
        }
    }


    private CommandType getCommand(String command) throws DukeUnknownCommandException {
        try {
            return Duke.CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new DukeUnknownCommandException(command);
        }
    }


    private void exit() {
        try {
            this.saveData();
            System.out.println("\tBye, hope to see you again!");
        } catch (IOException e) {
            System.out.println("Error when saving data!");
        }
    }


    private void saveData() throws IOException {
        FileWriter fw = new FileWriter(Duke.DATAPATH, false);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Task task : this.list) {
            bw.write(task.stringifyTask());
            bw.newLine();
        }
        bw.close();
        fw.close();
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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
            String[] splits = userInput[1].split(" /from ", 2);
            String[] timestamps = splits[1].split(" /to ", 2);
            String start = timestamps[0];
            String end = timestamps[1];
            LocalDateTime startTime = LocalDateTime.parse(start, formatter);
            LocalDateTime endTime = LocalDateTime.parse(end, formatter);
            if (startTime.isAfter(endTime)) {
                System.out.println("\tEnd time must be after the start time!\n");
                return;
            }

            this.list.add(new Event(splits[0], startTime, endTime));
            System.out.printf("\tadded event:\n\t\t%s\n", this.list.get(this.list.size() - 1));
            System.out.printf("\tYou have %d tasks in the list.\n\n", this.list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        } catch (DateTimeParseException e) {
            System.out.println("\tPlease enter the start/end time in the format of <DD/MM/YY HH:MM>!\n");
        }
    }


    private void addDeadline(String[] userInput) throws DukeMissingArgumentException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
            String[] splits = userInput[1].split(" /by ", 2);
            LocalDateTime time = LocalDateTime.parse(splits[1], formatter);
            this.list.add(new Deadline(splits[0], time));
            System.out.printf("\tadded deadline:\n\t\t%s\n", this.list.get(this.list.size() - 1));
            System.out.printf("\tYou have %d tasks in the list.\n\n", this.list.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        } catch (DateTimeParseException e) {
            System.out.println("\tPlease enter the time in the format of <DD/MM/YY HH:MM>!\n");
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
        new Duke().run();
    }
}