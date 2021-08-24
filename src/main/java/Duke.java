import java.util.ArrayList;


public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static String DATA_FILE_PATH = "src/main/java/data.txt";
    private static Storage storage = new Storage(DATA_FILE_PATH);


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        taskList = storage.loadData();
        Parser parser = new Parser(storage, taskList);
        parser.parseCommands();
        }

}


