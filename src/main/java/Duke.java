import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private ArrayList<Task> taskList;
    private Scanner sc;
    private File localFile;

    public Duke() {
        taskList = new ArrayList<>();
    }

    public enum TaskType {TODO, DEADLINE, EVENT}

    public void chat() {
        getFile();
        String startText = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(startText);
        sc = new Scanner(System.in);
        label:
        while (sc.hasNext()) {
            try {
                String command = sc.next();
                switch (command) {
                    case "bye":
                        String closingMessage = "Goodbye! Hope to see you again soon!\n";
                        System.out.println(outputTemplate(closingMessage));
                        sc.close();
                        break label;
                    case "list":
                        String list = "These are the tasks in your list:\n";
                        for (int i = 0; i < taskList.size(); i++) {
                            list += (i + 1) + ". " + taskList.get(i).toString() + "\n";
                        }
                        System.out.println(outputTemplate(list));
                        break;
                    case "done": {
                        int taskNumber = getTaskNumber();
                        Task completedTask = taskList.get(taskNumber - 1);
                        completedTask.markAsDone();
                        String message = "Good work! Task is now marked as done:\n" + completedTask + "\n";
                        System.out.println(outputTemplate(message));
                        saveList();
                        break;
                    }
                    case "todo": {
                        addTask(TaskType.TODO);
                        break;
                    }
                    case "deadline": {
                        addTask(TaskType.DEADLINE);
                        break;
                    }
                    case "event": {
                        addTask(TaskType.EVENT);
                        break;
                    }
                    case "delete": {
                        int taskNumber = getTaskNumber();
                        Task deletedTask = taskList.remove(taskNumber - 1);
                        String message = "Alright! I've deleted this task:\n" + deletedTask + getTaskListStatus();
                        System.out.println(outputTemplate(message));
                        saveList();
                        break;
                    }
                    case "on": {
                        String dateString = sc.nextLine();
                        LocalDate date = LocalDate.parse(dateString.trim());
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                        String message = "The tasks that you have on " + date.format(formatter) + " are:\n";
                        for (int i = 0; i < taskList.size(); i++) {
                            Task task = taskList.get(i);
                            if (task instanceof Deadline) {
                                Deadline deadline = (Deadline) task;
                                if (deadline.getBy().isEqual(date)) {
                                    message += deadline + "\n";
                                }
                            } else if (task instanceof Event) {
                                Event event = (Event) task;
                                if (event.getTime().isEqual(date)) {
                                    message += event + "\n";
                                }
                            }
                        }
                        System.out.println(outputTemplate(message));
                        break;
                    }
                    default: {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException e) {
                System.out.println(outputTemplate(e + "\n"));
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.chat();
    }

    private String outputTemplate(String output) {
        String line = "~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~";
        return line + "\n" + output + line;
    }

    private void addTask(TaskType type) throws DukeException {
        String messageHeader = "Alright! New task added:\n";
        switch (type) {
            case DEADLINE: {
                String description = getDescription("deadline");
                String[] parameters = description.split(" /by ");
                Deadline task = new Deadline(parameters[0].trim(), parameters[1]);
                Deadline task = new Deadline(parameters[0].trim(), LocalDate.parse(parameters[1]));
                taskList.add(task);
                String message = messageHeader + task + getTaskListStatus();
                System.out.println(outputTemplate(message));
                break;
            }
            case EVENT: {
                String description = getDescription("event");
                String[] parameters = description.split(" /at ");
                Event task = new Event(parameters[0], parameters[1]);
                Event task = new Event(parameters[0], LocalDate.parse(parameters[1]));
                taskList.add(task);
                String message = messageHeader + task + getTaskListStatus();
                System.out.println(outputTemplate(message));
                break;
            }
            case TODO: {
                String description = getDescription("todo");
                ToDo task = new ToDo(description.trim());
                taskList.add(task);
                String message = messageHeader + task + getTaskListStatus();
                System.out.println(outputTemplate(message));
                break;
            }
        }
        saveList();
    }

    private String getDescription(String taskType) throws DukeException {
        String description = sc.nextLine().trim();
        if (description.isEmpty()) {
            throw new DukeException(String.format("The description of a %s cannot be empty!", taskType));
        } else {
            return description;
        }
    }

    private int getTaskNumber() throws DukeException {
        int taskNumber = sc.nextInt();
        if (taskNumber < 1 || taskNumber > taskList.size()) {
            throw new DukeException("I cannot find this task number!");
        } else {
            return taskNumber;
        }
    }

    private String getTaskListStatus() {
        if (taskList.size() == 0) {
            return "There are no tasks in the list\n";
        } else if (taskList.size() == 1) {
            return "\nThere is currently 1 task in your list\n";
        } else {
            return String.format("\nThere are currently %d tasks in your list\n", taskList.size());
        }
    }

    private void getFile() {
        try {
            File dataFolder = new File("data");
            dataFolder.mkdir();
            localFile = new File(dataFolder,"duke.txt");
            if (localFile.createNewFile()) {

            } else {
                Scanner fileScanner = new Scanner(localFile);
                while (fileScanner.hasNextLine()) {
                    String data = fileScanner.nextLine();
                    String[] parameters = data.split(" / ");
                    switch (parameters[0]) {
                    case "T": {
                        ToDo task = new ToDo(parameters[2]);
                        task.setDone(Integer.parseInt(parameters[1]));
                        taskList.add(task);
                        break;
                    }
                    case "D": {
                        Deadline task = new Deadline(parameters[2], LocalDate.parse(parameters[3]));
                        task.setDone(Integer.parseInt(parameters[1]));
                        taskList.add(task);
                        break;
                    }
                    case "E": {
                        Event task = new Event(parameters[2], LocalDate.parse(parameters[3]));
                        task.setDone(Integer.parseInt(parameters[1]));
                        taskList.add(task);
                        break;
                    }
                    }
                }
                fileScanner.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void saveList() {
        String content = "";
        for (int i = 0; i < taskList.size(); i++) {
            content += taskList.get(i).toFileFormat() + "\n";
        }
        try {
            FileWriter myWriter = new FileWriter("./data/duke.txt");
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
