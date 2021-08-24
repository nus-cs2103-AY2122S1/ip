import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    protected static List<Task> todoList;
    protected static final String HOME = System.getProperty("user.home");
    protected static final Path PATH = java.nio.file.Paths.get(HOME, "Duke", "data", "duke.txt");

    public static void printLine() {
        System.out.println("--------------------------------------------------");
    }

    public static void printBigIcon() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
    }

    public static void printHello() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printLine();
    }

    public static void read() throws IOException {
        String fileContent = new String(Files.readAllBytes(Duke.PATH), StandardCharsets.UTF_8);
        System.out.println(fileContent);
    }

    public static void write(String s) throws IOException {
        Files.write(Duke.PATH, s.getBytes(), StandardOpenOption.WRITE);
    }

    public static void upload() throws IOException {
        String taskList = "";
        for (int i = 0; i < Duke.todoList.size(); i++) {
            taskList += Duke.todoList.get(i).encode();
        }
        write(taskList);
    }

    public static void download() throws IOException {
        List<String> lines = Files.readAllLines(Duke.PATH, StandardCharsets.UTF_8);
        todoList = new ArrayList<>();
        for (String hardCode: lines) {
            todoList.add(Task.decode(hardCode));
        }
    }

    public static void createFile() throws IOException {
        if (!java.nio.file.Files.exists(Duke.PATH)) {
            Files.createDirectories(Duke.PATH.getParent());
            Files.createFile(Duke.PATH);
        };
    }

    public static void main(String[] args) {
        todoList = new ArrayList<>();

        try {
            createFile();
            printLine();
            // printBigIcon();
            printHello();
            download();
            Command.process();
            upload();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
