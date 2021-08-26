import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File dir = new File("./data");
            File f = new File("./data/duke.txt");
            if (!dir.exists()) {
                dir.mkdirs();
                f.createNewFile();
            } else if (!f.exists()) {
                f.createNewFile();
            } else { // read line by line
                BufferedReader reader = new BufferedReader(new FileReader(f));
                String row;
                while ((row = reader.readLine()) != null) {
                    try {
                        tasks.add(Task.parseRepr(row));
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String input;

        while (true) {
            input = scanner.nextLine();
            switch (input) {
            case "bye": // exit
                System.out.println("Bye. Hope to see you again soon!");
                return;
            case "list": // read
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d.%s\n", i + 1, tasks.get(i));
                }
                break;
            default: // update & delete
                if (input.matches("done \\d+")) {
                    int i = Integer.parseInt(input.substring("done ".length()));
                    System.out.println("Nice! I've marked this task as done:");
                    Task t = tasks.get(i - 1);
                    t.markAsComplete();
                    System.out.println(t);
                } else if (input.matches("delete \\d+")) {
                    int i = Integer.parseInt(input.substring("delete ".length()));
                    System.out.println("Noted. I've removed this task:");
                    Task t = tasks.remove(i - 1);
                    System.out.println(t);
                } else { // add task
                    try {
                        Task t;
                        if (input.matches("event.*")) {
                            int k = input.indexOf("/at");
                            if (k < 0) {
                                throw new DukeException.MissingArgumentException("/at");
                            }
                            t = new Event(input.substring("event".length(), k).trim(), input.substring(k + 3).trim());
                        } else if (input.matches("deadline.*")) {
                            int k = input.indexOf("/by");
                            if (k < 0) {
                                throw new DukeException.MissingArgumentException("/by");
                            }
                            t = new Deadline(input.substring("deadline".length(), k).trim(), input.substring(k + 3).trim());
                        } else if (input.matches("todo.*")) {
                            t = new Todo(input.substring("todo".length()));
                        } else {
                            throw new DukeException.UnknownInputException();
                        }
                        tasks.add(t);
                        System.out.printf("Got it. I've added this task:\n%s\nNow you have %d tasks in the list\n", t, tasks.size());
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                try {
                    FileWriter fw = new FileWriter("./data/duke.txt", false);
                    tasks.forEach((task) -> {
                        try {
                            fw.write(task.getRepr()); // write each task
                            fw.write("\n");
                        } catch (java.io.IOException e) {
                            System.out.println(e.getMessage());
                        }
                    });
                    fw.close();
                } catch (java.io.IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}


