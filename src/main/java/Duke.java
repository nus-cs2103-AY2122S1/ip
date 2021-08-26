import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    private static String DIR = "./data";
    private static String FILENAME = "duke.txt";
    private static String DATAPATH = String.valueOf(Paths.get(Duke.DIR, Duke.FILENAME));

    private TaskList taskList;
    private Ui ui;

    private enum CommandType {
        BYE, LIST,
        TODO, EVENT, DEADLINE,
        DONE, DELETE,
    }


    public Duke() {
        this.taskList = new TaskList();
        this.ui = new Ui();
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
            this.ui.showDatabaseError();
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
        this.taskList.add(taskToAdd);
    }


    private void createDatabase() {
        File db = new File(Duke.DATAPATH);
        File dir = new File(Duke.DIR);
        dir.mkdir();
        try {
            db.createNewFile();
        } catch (IOException e) {
            this.ui.showLoadingError();
        }
    }


    private void run() {
        this.ui.showGreet();
        this.loadData();
        while (true) {
            try {
                String rawInput = this.ui.readInput();
                String[] userInput = rawInput.split(" ", 2);
                CommandType commandType = this.getCommand(userInput[0]);

                switch (commandType) {
                case BYE:
                    this.exit();
                    this.ui.closeInput();
                    return;
                case LIST:
                    this.ui.showList(this.taskList);
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
                this.ui.showDukeException(e);
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
            this.ui.showExit();
        } catch (IOException e) {
            this.ui.showSavingError();
        }
    }


    private void saveData() throws IOException {
        FileWriter fw = new FileWriter(Duke.DATAPATH, false);
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 1; i <= this.taskList.getLength(); i++) {
            bw.write(this.taskList.get(i).stringifyTask());
            bw.newLine();
        }
        bw.close();
        fw.close();
    }


    private void addTodo(String[] userInput) throws DukeMissingArgumentException {
        try {
            String description = userInput[1];

            Task todo = new Todo(description);
            this.taskList.add(todo);
            this.ui.showAdd(todo, this.taskList.getLength());
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
                this.ui.showInvalidDateRange();
                return;
            }

            Task event = new Event(splits[0], startTime, endTime);
            this.taskList.add(event);
            this.ui.showAdd(event, this.taskList.getLength());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        } catch (DateTimeParseException e) {
            this.ui.showInvalidDateFormat();
        }
    }


    private void addDeadline(String[] userInput) throws DukeMissingArgumentException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
            String[] splits = userInput[1].split(" /by ", 2);
            LocalDateTime time = LocalDateTime.parse(splits[1], formatter);

            Task deadline = new Deadline(splits[0], time);
            this.taskList.add(deadline);
            this.ui.showAdd(deadline, this.taskList.getLength());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        } catch (DateTimeParseException e) {
            this.ui.showInvalidDateFormat();
        }
    }


    private void markAsDone(String[] userInput)
            throws DukeNoTaskFoundException, DukeMissingArgumentException, DukeInvalidArgumentException {
        try {
            int taskNum = Integer.parseInt(userInput[1]);
            if (taskNum > this.taskList.getLength()) {
                throw new DukeNoTaskFoundException(taskNum);
            }
            this.taskList.get(taskNum).markAsDone();
            this.ui.showDone(this.taskList.get(taskNum));
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
            if (taskNum > this.taskList.getLength()) {
                throw new DukeNoTaskFoundException(taskNum);
            }
            Task taskToDelete = this.taskList.get(taskNum);
            this.ui.showDelete(taskToDelete, this.taskList.getLength());
            this.taskList.delete(taskNum);
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