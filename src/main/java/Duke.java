import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    private static String PATH;

    private static boolean run;
    private static ArrayList<Task> tasks;


    public static void main(String[] args) {
        PATH = System.getProperty("user.dir") + "\\data\\save.txt";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = "____________________________________________________________\n"
                + "Hello! I'm Python>Java\n"
                + "What must I do for you?\n"
                + "____________________________________________________________";
        System.out.println(greeting);

        run = true;
        fromFile(PATH);

        Scanner inputReader = new Scanner(System.in);

        while(run) {
            String input = inputReader.nextLine();
            run = eval(input);
        }

        inputReader.close();
    }

    public static boolean eval(String input) {
        String[] inputArray = input.split(" ");
        String[] params;
        int selectedTask;

        switch (inputArray[0]) {
            case "bye":
                saveFile(PATH);
                System.out.println("____________________________________________________________\n"
                        + "Bye. Don't come again!\n"
                        + "____________________________________________________________\n");
                return false;
            case "list":
                System.out.println("____________________________________________________________\n");
                for(int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, tasks.get(i).toString());
                }
                System.out.println("____________________________________________________________\n");
                return true;
            case "done":
                selectedTask = Integer.parseInt(inputArray[1]) - 1;
                tasks.get(selectedTask).setDone();

                System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done: \n"
                + tasks.get(selectedTask).toString() + "\n"
                + "____________________________________________________________\n");
                return true;
            case "delete":
                selectedTask = Integer.parseInt(inputArray[1]) - 1;
                System.out.println("Noted. I've removed this task:\n" +
                        tasks.get(selectedTask).toString() + "\n" +
                        "Now you have " + tasks.size() + " tasks in the list.\n");
                tasks.remove(selectedTask);
                return true;
            case "event":
                params = input.split("/at");
                params[0] = params[0].substring(6, params[0].length() - 1);
                params[1] = params[1].substring(1);
                tasks.add(new Event(params[0], LocalDate.parse(params[1])));

                System.out.println("____________________________________________________________\n"
                        + "Got it. I've added this task:\n"
                        + tasks.get(tasks.size() - 1).toString() + "\n"
                        + "____________________________________________________________\n");
                return true;
            case "deadline":
                params = input.split("/by");
                params[0] = params[0].substring(9, params[0].length() - 1);
                params[1] = params[1].substring(1);
                tasks.add(new Deadline(params[0], LocalDate.parse(params[1])));

                System.out.println("____________________________________________________________\n"
                        + "Got it. I've added this task:\n"
                        + tasks.get(tasks.size() - 1).toString() + "\n"
                        + "____________________________________________________________\n");
                return true;
            case "todo":
                try {
                    String name = input.substring(5);
                    if (name.equals("")) {
                        throw new StringIndexOutOfBoundsException("empty todo description");
                    }
                    tasks.add(new ToDo(name));
                    System.out.println("____________________________________________________________\n"
                            + "Got it. I've added this task:\n"
                            + tasks.get(tasks.size() - 1).toString() + "\n"
                            + "____________________________________________________________\n");
                }
                catch (StringIndexOutOfBoundsException e) {
                    System.out.println("____________________________________________________________\n" +
                            "OOPS!!! The description of a todo cannot be empty.\n" +
                            "____________________________________________________________\n");
                }

                return true;
            default:
                System.out.println("____________________________________________________________\n" +
                        "OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "____________________________________________________________\n");
                return true;
        }
    }

    public static void fromFile(String path) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            String[] arr;
            boolean done;
            tasks = new ArrayList<>();

            while((line = bufferedReader.readLine()) != null) {
                arr = line.split("-");
                done = arr[1].equals("1");

                switch (arr[0]) {
                    case "T":
                        tasks.add(new ToDo(done, arr[2]));
                        break;
                    case "E":
                        tasks.add(new Event(done, arr[2], LocalDate.parse(arr[3])));
                        break;
                    case "D":
                        tasks.add(new Deadline(done, arr[2], LocalDate.parse(arr[3])));
                        break;
                    default:
                        throw new IllegalArgumentException("Unrecognized task flag");
                }
            }
        } catch(IOException e) {
            System.out.println(e.toString());
        }
    }

    public static void saveFile(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append("\n");
            }
            stringBuilder.append(task.saveString());
        }
        try {
            FileWriter fileWriter = new FileWriter(path);
            String output = stringBuilder.toString();
            fileWriter.write(output);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
