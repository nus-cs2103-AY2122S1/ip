import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Duke {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        // handle greetings
        String greetings = "Hello! I'm Grace" + "\n" + "How can I help you?";
        System.out.println(greetings);

        //creating the list for task
        ArrayList<Task> list = new ArrayList<Task>();
        ArrayList<String> done_check = new ArrayList<String>();
        int task_number;

        //reading the text file n inputting previous tasks into list
        String added = "";
        try {
            File file =
                    new File("./data/Duke.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                added = sc.nextLine();
                if (added.equals("")) {
                } else if (added.equals("Do these soon!!")) {
                } else if (added.substring(1, 2).equals("T")) {
                    Task todo = new Todo("todo " + added.substring(7), true);
                    list.add(todo);
                    done_check.add(added.substring(4, 5));
                } else if (added.substring(1, 2).equals("D")) {
                    Task deadline = new Deadline("deadline " + added.substring(7), true);
                    list.add(deadline);
                    done_check.add(added.substring(4, 5));
                } else {
                    Task event = new Event("event " + added.substring(7), true);
                    list.add(event);
                    done_check.add(added.substring(4, 5));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            try {
                Path path = Paths.get("./data");
                Files.createDirectories(path);
                System.out.println("Directory data created");
            } catch (IOException r) {
                System.err.println("Failed to create directory!" + r.getMessage());
            }
        }

        //exception handling
        String next_line = scan.nextLine();
        int caught = 0;

        while (true) {
            try {
                Duke.catch_error(next_line);
            } catch (DukeException e) {
                System.out.println(e.output_error());
                caught = 1;
            } finally {
                if (caught == 1) {
                    next_line = scan.nextLine();
                    caught = 0;
                } else {
                    break;
                }
            }
        }


        int first_time = 0;
        while (true) {
            if (first_time == 1) {
                next_line = scan.nextLine();
            }

            // exit if bye
            if (next_line.equals("bye")) {
                System.out.println("Bye bye! Hope to see you again soon!");
                break;
            }

            // outputting the list
            else if (next_line.equals("list")) {
                int count = 1;
                System.out.println("Do these soon:" + "\n");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(count + ". [" + list.get(i).get_type() + "][" + done_check.get(i) + "] " + list.get(i).get_task());
                    count = count + 1;
                }
                first_time = 1;
            }

            //marking task as done
            else if (next_line.length() > 4 && next_line.substring(0, 4).equals("done")) {
                System.out.println("Yay! you have finished this task!");
                task_number = Integer.valueOf(next_line.substring(5)) - 1;
                done_check.set(task_number, "X");
                System.out.println("[" + list.get(task_number).get_type() + "][" + done_check.get(task_number) + "] " + list.get(task_number).get_task());
                first_time = 1;
            }

            //deleting task from the list
            else if (next_line.length() > 6 && next_line.substring(0, 6).equals("delete")) {
                task_number = Integer.valueOf(next_line.substring(7));
                System.out.println("Congrats! You have completed this task!");
                System.out.println("[" + list.get(task_number - 1).get_type() + "][] " + list.get(task_number - 1).get_task());
                done_check.remove(task_number - 1);
                list.remove(task_number - 1);
                System.out.println(list.size() + " more to go!! Press on!!");
                first_time = 1;
            }

            // adding todo to the list
            else if (next_line.length() > 4 && next_line.substring(0, 4).equals("todo")) {
                Task todo = new Todo(next_line);
                list.add(todo);
                done_check.add(" ");
                System.out.println("Added the task! :)");
                System.out.println("[" + todo.get_type() + "][] " + todo.get_task());
                System.out.println("Jiayou! you have " + list.size() + " tasks in the list.");
                first_time = 1;
            }

            //adding deadline to the list
            else if (next_line.length() > 8 && next_line.substring(0, 8).equals("deadline")) {
                Task deadline = new Deadline(next_line);
                list.add(deadline);
                done_check.add(" ");
                System.out.println("Added the task! :)");
                System.out.println("[" + deadline.get_type() + "][] " + deadline.get_task());
                System.out.println("Jiayou! you have " + list.size() + " tasks in the list.");
                first_time = 1;
            }

            //adding event to the list
            else if (next_line.length() > 5 && next_line.substring(0, 5).equals("event")) {
                Task event = new Event(next_line);
                list.add(event);
                done_check.add(" ");
                System.out.println("Added the task! :)");
                System.out.println("[" + event.get_type() + "][] " + event.get_task());
                System.out.println("Jiayou! you have " + list.size() + " tasks in the list.");
                first_time = 1;
            }

            //catching random expression error
            else {

                while (true) {
                    try {
                        Duke.random_error(next_line);
                    } catch (DukeException e) {
                        System.out.println(e.output_error());
                        first_time = 1;
                    } finally {
                        first_time = 1;
                        break;
                    }
                }
            }
        }


        try {
            check_file();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            try {
                Path path = Paths.get("./data");
                Files.createDirectories(path);
                System.out.println("Directory data created");
            } catch (IOException r) {
                System.err.println("Failed to create directory!" + r.getMessage());
            }
        }


        try {
            String task = "Do these soon!!" + "\n";
            for (int i = 0; i < list.size(); i++) {
                task = task + "[" + list.get(i).get_type() + "][" + done_check.get(i) + "] " + list.get(i).get_task() + "\n";
            }
            Duke.writeToFile("./data/Duke.txt", task);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    public static void random_error(String next_line) throws DukeException {
        DukeException e = new RandomDescription(next_line);
        throw e;
    }

    public static void catch_error(String next_line) throws DukeException {
        if (next_line.equals("todo")) {
            DukeException e = new EmptyDescription(next_line);
            throw e;
        } else if (next_line.equals("deadline")) {
            DukeException e = new EmptyDescription(next_line);
            throw e;
        } else if (next_line.equals("event")) {
            DukeException e = new EmptyDescription(next_line);
            throw e;
        }
    }

    private static void writeToFile(String filePath, String textToWrite) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToWrite);
        fw.close();
    }

    private static void check_file() throws FileNotFoundException {
        Path path = Paths.get("./data");
        if (!Files.exists(path)) {
            FileNotFoundException e = new FileNotFoundException();
            throw e;
        }
    }


}
