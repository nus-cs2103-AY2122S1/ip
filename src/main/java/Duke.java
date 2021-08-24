import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static class DukeException extends Exception {
        public DukeException(String errorMessage) {
            super(errorMessage);
        }
    }

    public void init() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void run() throws IOException, DukeException {
        Pattern todoPattern = Pattern.compile("todo (.*)");
        Pattern deadlinePattern = Pattern.compile("deadline (.*) /by (.*)");
        Pattern eventPattern = Pattern.compile("event (.*) /at (.*)");

        TaskList storage = new TaskList();
        Files.createDirectories(Paths.get("data/"));
        File file = new File("data/duke.txt");

        // Load data if exists, else create new TaskList
//        if (Files.exists(java.nio.file.Paths.get(".", "data", "duke.txt"))) {
        if (!file.createNewFile()) {
            try {
                FileInputStream fis = new FileInputStream("data/duke.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);
                storage = (TaskList) ois.readObject();
                ois.close();
            } catch (IOException e) {
                System.out.println("IOException");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.out.println("classnotfound");
                e.printStackTrace();
            }
        } else {
            System.out.println("CAnt find the file");
            storage = new TaskList();
        }


        init();




        while (true) {
            // Enter data using BufferReader
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            // Reading data using readLine
            String input = reader.readLine();



            // print out list
            if (input.equals("list")) {
                storage.list();
                continue;
            }

            // complete a task
            if (Pattern.matches("done \\d", input)) {
                String[] items = input.split(" ");
                storage.done(Integer.parseInt(items[1]));
                continue;
            }

            if (Pattern.matches("delete \\d", input)) {
                String[] items = input.split(" ");
                storage.delete(Integer.parseInt(items[1]));
                continue;
            }

            Matcher todoMatcher = todoPattern.matcher(input);
            if (todoMatcher.find()) {
                storage.addCustom(new Todo(todoMatcher.group(1)));
                continue;
            }

            Matcher deadlineMatcher = deadlinePattern.matcher(input);
            if (deadlineMatcher.find()) {
                storage.addCustom(new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2)));
                continue;
            }

            Matcher eventMatcher = eventPattern.matcher(input);
            if (eventMatcher.find()) {
                storage.addCustom(new Event(eventMatcher.group(1), eventMatcher.group(2)));
                continue;
            }

            // exit application
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                FileOutputStream fos = new FileOutputStream("./data/duke.txt");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(storage);
                oos.close();
                break;
            }

            // identify reason for misinput
            if (input.length() >= 4 && input.substring(0, 4).equals("todo")) {
                System.out.println("OOPS!!! The description of a todo cannot be empty.");
                continue;
            }
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");


        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        try {
            duke.run();
        } catch (IOException e) {

            System.out.println("Error found while parsing input, shutting down");
            e.printStackTrace();
        } catch (DukeException e) {

        }
    }
}
