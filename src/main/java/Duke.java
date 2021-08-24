import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {

    private final static String SAVE_FILE = "data/tasks.txt";
    private final static String SAVE_DIR = "data/";
    private final static String BOOT_MESSAGE =
            "                 |`._         |\\\n" +
                    "                 `   `.  .    | `.    |`.\n" +
                    "                  .    `.|`-. |   `-..'  \\           _,.-'\n" +
                    "                  '      `-. `.           \\ /|   _,-'   /\n" +
                    "              .--..'        `._`           ` |.-'      /\n" +
                    "               \\   |                                  /\n" +
                    "            ,..'   '                                 /\n" +
                    "            `.                                      /\n" +
                    "            _`.---                                 /\n" +
                    "        _,-'               `.                 ,-  /\"-._\n" +
                    "      ,\"                   | `.             ,'|   `    `.\n" +
                    "    .'                     |   `.         .'  |    .     `.\n" +
                    "  ,'                       '   ()`.     ,'()  '    |       `.\n" +
                    "'-.                    |`.  `.....-'    -----' _   |         .\n" +
                    " / ,   ________..'     '  `-._              _.'/   |         :\n" +
                    " ` '-\"\" _,.--\"'         \\   | `\"+--......-+' //   j `\"--.. , '\n" +
                    "    `.'\"    .'           `. |   |     |   / //    .       ` '\n" +
                    "      `.   /               `'   |    j   /,.'     '\n" +
                    "        \\ /                  `-.|_   |_.-'       /\\\n" +
                    "         /                        `\"\"          .'  \\\n" +
                    "        j                                           .\n" +
                    "        |                                 _,        |\n" +
                    "        |             ,^._            _.-\"          '\n" +
                    "        |          _.'    `'\"\"`----`\"'   `._       '\n" +
                    "        j__     _,'                         `-.'-.\"`\n" +
                    "          ',-.,' mh\n\n" +
                    "Loading save file...";
    private final static String END_MESSAGE = "Cya";

    private final static DukeList list = new DukeList();

    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
    }

    private static void display(String content) {
        System.out.println(
                "69696969696969696969696969696969696969696969696969696969696966969696969696969696969696969696969\n"
                + content
                + "\n69696969696969696969696969696969696969696969696969696969696966969696969696969696969696969696969\n"
        );
    }

    private static void displayList() {
        display(list.toString());
    }

    private static String loadFile() {
        File file = new File(SAVE_FILE);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitted = line.split("%,", 4);
                String input;
                TaskType type;
                boolean isDone = Boolean.parseBoolean(splitted[1]);
                switch (splitted[0]) {
                case "T":
                    input = "todo " + splitted[2];
                    type = TaskType.TODO;
                    break;
                case "D":
                    input = "deadline " + splitted[2] + " /by " + splitted[3];
                    type = TaskType.DEADLINE;
                    break;
                case "E":
                    input = "event " + splitted[2] + " /at " + splitted[3];
                    type = TaskType.EVENT;
                    break;
                default:
                    throw new CorruptedFileException();
                }
                Task task = addTask(input, type);
                task.isDone = isDone;
            }
            scanner.close();
            return "Save file successfully loaded";
        } catch (DukeException e) {
            return "Error loading save file";
        } catch (FileNotFoundException e) {
            return "No save file found";
        }
    }

    private static void saveFile() throws CorruptedFileException {
        File dir = new File(SAVE_DIR);
        if (!Files.exists(Paths.get(SAVE_DIR))) {
            dir.mkdir();
        }
        File file = new File(SAVE_FILE);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(SAVE_FILE);
            for (Task t : list) {
                fw.write(t.formatSave() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new CorruptedFileException();
        }
    }

    private static Task addTask(String input, TaskType type) throws InvalidArgumentsException, InvalidTaskException {
        int descriptionEnd;
        String description;
        String dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        Task task;
        switch (type) {
            case TODO:
                try {
                    // filter out todoXXXX
                    // StringIndexOutOfBoundsException thrown here if input = "todo"
                    if (input.charAt(4) != ' ') {
                        throw new InvalidTaskException();
                    }
                    // StringIndexOutOfBoundsException thrown here if input = "todo "
                    description = input.substring(5);
                    // Checks if description is all whitespace
                    if (description.trim().isEmpty()) {
                        throw new InvalidArgumentsException();
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidArgumentsException();
                }
                task = new ToDo(description);
                break;
            case DEADLINE:
                try {
                    // filter out deadlineXXXX
                    // StringIndexOutOfBoundsException thrown here if input = "deadline"
                    if (input.charAt(8) != ' ') {
                        throw new InvalidTaskException();
                    }
                    descriptionEnd = input.indexOf(" /by ");
                    // StringIndexOutOfBoundsException thrown here if input = "deadline /by "
                    // or if " /by " is not present in input
                    description = input.substring(9, descriptionEnd);
                    // Checks if description is all whitespace
                    if (description.trim().isEmpty()) {
                        throw new InvalidArgumentsException();
                    }
                    // StringIndexOutOfBoundsException thrown here if input = "$String /by "
                    dateTime = input.substring(descriptionEnd + 5);
                    // Checks if dateTime is all whitespace
                    if (dateTime.trim().isEmpty()) {
                        throw new InvalidArgumentsException();
                    }
                    task = new Deadline(description, LocalDateTime.parse(dateTime, formatter));
                } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
                    throw new InvalidArgumentsException();
                }

                break;
            case EVENT:
                try {
                    // filter out eventXXXX
                    // StringIndexOutOfBoundsException thrown here if input = "event"
                    if (input.charAt(5) != ' ') {
                        throw new InvalidTaskException();
                    }
                    descriptionEnd = input.indexOf(" /at ");
                    // StringIndexOutOfBoundsException thrown here if input = "event /at"
                    // or if " /at " is not present in input
                    description = input.substring(6, descriptionEnd);
                    // Checks if description is all whitespace
                    if (description.trim().isEmpty()) {
                        throw new InvalidArgumentsException();
                    }
                    // StringIndexOutOfBoundsException thrown here if input = "$String /at "
                    dateTime = input.substring(descriptionEnd + 5);
                    // Checks if dateTime is all whitespace
                    if (dateTime.trim().isEmpty()) {
                        throw new InvalidArgumentsException();
                    }
                    task = new Event(description, LocalDateTime.parse(dateTime, formatter));
                } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
                    throw new InvalidArgumentsException();
                }
                break;
            default:
                throw new InvalidTaskException();
        }
        list.add(task);
        return task;
    }

    private static void markDone(String input) throws InvalidArgumentsException, InvalidTaskException {
        int taskNum;
        try {
            // filter out doneXXXX
            // StringIndexOutOfBoundsException thrown here if input = "done",
            // which is caught by IndexOutOfBoundsException
            if (input.charAt(4) != ' ') {
                throw new InvalidTaskException();
            }
            // NumberFormatException thrown here if substring is a invalid integer string
            taskNum = Integer.parseInt(input.substring(5)) - 1;
            // IndexOutOfBoundsException thrown here if taskNum > list size
            Task task = list.get(taskNum);
            task.markDone();
            display("Nice! I've marked this task as done:\n"
                    + "  "
                    + task.toString());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentsException();
        }
    }

    private static void deleteTask(String input) throws InvalidArgumentsException, InvalidTaskException {
        int taskNum;
        try {
            // filter out deleteXXXX
            // StringIndexOutOfBoundsException thrown here if input = "delete",
            // which is caught by IndexOutOfBoundsException
            if (input.charAt(6) != ' ') {
                throw new InvalidTaskException();
            }
            // NumberFormatException thrown here if substring is a invalid integer string
            taskNum = Integer.parseInt(input.substring(7)) - 1;
            // IndexOutOfBoundsException thrown here if taskNum > list size
            Task task = list.get(taskNum);
            list.delete(taskNum);
            display("Noted. I've removed this task:\n"
                    + "  "
                    + task.toString() + "\n"
                    + "Now you have " + list.size() + " tasks in the list");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentsException();
        }
    }

    private static void runDuke() {
        display(BOOT_MESSAGE);
        display(loadFile());
        display("Sup ma man. watcha want?");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                displayList();
            } else if (input.startsWith("done")) {
                try {
                    markDone(input);
                } catch (DukeException e) {
                    display(e.getMessage());
                }
            } else if (input.startsWith("delete")) {
                try {
                    deleteTask(input);
                } catch (DukeException e) {
                    display(e.getMessage());
                }
            } else if (input.startsWith("todo")) {
                try {
                    Task task = addTask(input, TaskType.TODO);
                    display("Got it. I've added this task:\n"
                            + "  "
                            + task.toString() + "\n"
                            + "Now you have " + list.size() + " tasks in the list"
                    );
                } catch (DukeException e) {
                    display(e.getMessage());
                }
            } else if (input.startsWith("deadline")) {
                try {
                    Task task =  addTask(input, TaskType.DEADLINE);
                    display("Got it. I've added this task:\n"
                            + "  "
                            + task.toString() + "\n"
                            + "Now you have " + list.size() + " tasks in the list"
                    );
                } catch (DukeException e) {
                    display(e.getMessage());
                }
            } else if (input.startsWith("event")) {
                try {
                    Task task =  addTask(input, TaskType.EVENT);
                    display("Got it. I've added this task:\n"
                            + "  "
                            + task.toString() + "\n"
                            + "Now you have " + list.size() + " tasks in the list"
                    );
                } catch (DukeException e) {
                    display(e.getMessage());
                }
            } else {
                try {
                    throw new InvalidTaskException();
                } catch (DukeException e) {
                    display(e.getMessage());
                }
            }
        }
        scanner.close();
        try {
            saveFile();
            display("File saved");
        } catch (CorruptedFileException e) {
            display("Error saving file");
        }
        display(END_MESSAGE);
    }

    public static void main(String[] args) {
        runDuke();
    }

}
