import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Duke {
    private static final String WELCOME_MSG = "Hello! I'm Mr House";
    private static final String EXIT_MSG = "Goodbye Courier!";
    private static final String TASK_MSG = "Here are your tasks:";

    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println(formatString(WELCOME_MSG));
        
        File file = new File("./data/duke.txt");

        try {
            Scanner dataReader = new Scanner(file);

            while (dataReader.hasNextLine()) {
                String[] data = dataReader.nextLine().split("\\|");
                String type = data[0];
                boolean isDone = data[1] == "1" ? true : false;
                String description = data[2];
                
                if (type.equals("T")) {
                    tasks.add(new ToDo(description, isDone));
                } else if (type.equals("D")) {
                    LocalDate time = LocalDate.parse(data[3].trim());
                    tasks.add(new Deadline(description, time, isDone));
                } else if (type.equals("E")) {
                    LocalDate startTime = LocalDate.parse(data[3].trim());
                    LocalDate endTime = LocalDate.parse(data[3].trim());
                    tasks.add(new Event(description, startTime, endTime, isDone));
                }
            }

            dataReader.close();
        } catch (FileNotFoundException e) {
            try {
                file.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
                return;
            }
        }  

        Scanner sc  = new Scanner(System.in);
        String input = sc.next();
        String description = sc.nextLine().trim();

        while (!input.equals("bye")) {
            try {
                action(input, description);
                writeTaskList();
            } catch (InvalidInputException e) {
                System.out.println(formatString(e.getMessage()));
            } catch (IOException e2) {
                sc.close();
                e2.printStackTrace();
                return;
            }

            input = sc.next();
            description = sc.nextLine().trim();
        }
        sc.close();
        System.out.println(formatString(EXIT_MSG));
    }

    private static void action(String action, String description) throws InvalidInputException {
        if (action.equals("list")) {
            System.out.print(formatString(getTaskString()));
        } else if (action.equals("done")) {
            int index = Integer.valueOf(description) - 1;
            System.out.println(formatString(tasks.get(index).markAsDone() + "\n" + getTaskCountString()));
        } else if (action.equals("delete")) {
            int index = Integer.valueOf(description) - 1;
            System.out.println(formatString(tasks.remove(index).delete() + "\n" + getTaskCountString()));
        } else if (action.equals("todo")){
            if (description.isBlank()) {
                throw new InvalidInputException("todo's description cannot be empty!");
            } else {
                Task newTask = new ToDo(description);
                tasks.add(newTask);
                System.out.println(formatString(newTask.actionString() + "\n" + getTaskCountString()));
            }
        } else if (action.equals("deadline")) {
            if (description.isBlank()) {
                throw new InvalidInputException("deadline's description cannot be empty!");
            } else {
                String[] split = description.split("/by", 2);
                description = split[0].trim();
                LocalDate time = LocalDate.parse(split[1].trim());

                Task newTask = new Deadline(description, time);
                tasks.add(newTask);
                System.out.println(formatString(newTask.actionString() + "\n" + getTaskCountString()));
            }
        } else if (action.equals("event")){
            if (description.isBlank()) {
                throw new InvalidInputException("event's description cannot be empty!");
            } else {
                String[] split = description.split("/from", 2);
                description = split[0].trim();
                String[] time = split[1].split("/to", 2);
                LocalDate startTime = LocalDate.parse(time[0].trim());
                LocalDate endTime = LocalDate.parse(time[1].trim());


                Task newTask = new Event(description, startTime, endTime);
                tasks.add(newTask);
                System.out.println(formatString(newTask.actionString() + "\n" + getTaskCountString()));
            }
        } else {
            throw new InvalidInputException("Invalid command");
        }
    }

    private static String getTaskString() {
        StringBuilder taskString = new StringBuilder(TASK_MSG + "\n");
        int len = tasks.size();

        for (int i = 0; i < len - 1; i ++) {
            taskString.append(i + 1 + ". " + tasks.get(i).toString() + "\n");
        }

        taskString.append(len + ". " + tasks.get(len - 1).toString());

        return taskString.toString();
    }

    private static void writeTaskList() throws IOException {
        try {
            FileWriter writer = new FileWriter("./data/duke.txt");
            StringBuilder data = new StringBuilder();

            int len = tasks.size();

            for (int i = 0; i < len - 1; i ++) {
                data.append(tasks.get(i).getformmatedData() + "\n");
            }

            data.append(tasks.get(len - 1).getformmatedData());

            writer.write(data.toString());
            writer.close();
        } catch (IOException e) {
            throw e;
        }
    }

    private static String getTaskCountString() {
        return String.format("You have %d tasks", tasks.size());
    }

    private static String formatString(String message) {
        return "\t____________________________\n" +
                "\t " + message.replace("\n", "\n\t ") + "\n" +
                "\t____________________________\n";
    }
}
