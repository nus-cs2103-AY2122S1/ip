import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static class Task {
        protected String description;
        protected String taskType;
        protected boolean isDone;

        // constructor for task object
        public Task(String description, String taskType) {
            this.description = description;
            this.taskType = taskType;
            this.isDone = false;
        }

        public String getStatus() {
            return (isDone ? "X" : " ");
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public String hardDiskSave() {
            return "";
        }

        @Override
        public String toString() {
            return ("[" + getStatus() + "] " + this.description);
        }
    }

    public static class Deadlines extends Task {

        protected String by;
        protected LocalDate date;


        public Deadlines(String description, String finishBy) {
           super(description, "D");
           this.by = finishBy;
        }


        public String understandDate() {
            date = LocalDate.parse(this.by);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }


        @Override
        public String hardDiskSave() {
            return "D/" + this.isDone + "/" + this.description + "/" + this.by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + understandDate() + ")";
        }
    }

    public static class ToDos extends Task {

        public ToDos(String description) {
            super(description, "T");
        }

        @Override
        public String hardDiskSave() {
            return "T/" + this.isDone + "/" + this.description;
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Events extends Task {

        protected String date;
        protected LocalDate at;


        public Events(String description, String eventDate) {
            super(description, "E");
            this.date = eventDate;
        }


        public String understandDate() {
            at = LocalDate.parse(this.date);
            return at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }


        @Override
        public String hardDiskSave() {
            return "E/" + this.isDone + "/" + this.description + "/" + this.date;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + understandDate() + ")";

        }
    }

    public static void showIntro() {
        String dory = "      _\n"
                + "     | |\n"
                + "   __| |   ___    _ __   _   _\n"
                + "  /    |  /   \\  | /__| | | | |\n"
                + " |   O | |  O  | | |    | |_| |\n"
                + "  \\__,_|  \\___/  |_|     \\__, |\n"
                + "   ________________________/  |\n"
                + "  (__________________________/\n"
                + "\n"
                + " how to use:\n"
                + "     * type down something and i'll remember\n"
                + "         'todo' followed by your 'task'\n"
                + "             eg. todo go to sleep\n"
                + "         'deadline' followed by the '/by yyyy-mm-dd'\n"
                + "             eg. deadline finish test /by 2020-12-31\n"
                + "         'event' followed by the '/at yyyy-mm-dd'\n"
                + "             eg. event christmas /at 2020-10-10\n"
                + "     * type 'list' to show everything\n"
                + "     * type 'bye' to leave\n"
                + "     * type 'done' followed by the task number\n"
                + "       to mark it as done\n"
                + "     * type 'delete' followed by the task number\n"
                + "       to delete it from your list\n"
                + " >";

        String fish = "                              ....\n"
                + "                             /.... \\\n"
                + "   hi my name is dory    .-`\\ \\   `...')\n"
                + "   and i can help you   ( o   | |      (\n"
                + "    remember things      `-_ / /_./``-._)\n"
                + "                             `\\___\\";

        // introduction to chat bot
        System.out.println(fish);
        System.out.println(dory);
    }

    public static void updateHardDisk(ArrayList<Task> taskList) {
        Path dataFilePath = Paths.get("data/dory.txt");
        try {
            BufferedWriter bw = Files.newBufferedWriter(dataFilePath);
            for (int i = 0; i < taskList.size(); i++) {
                String fullLine = taskList.get(i).hardDiskSave();
                bw.write(fullLine);
                bw.newLine() ;
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

    public static void startChatBot(ArrayList<Task> taskList) throws DukeException {
        // creates a new Scanner instance
        // System.in is the keyboard input
        Scanner input = new Scanner(System.in);

        // arraylist to save the user's tasks
        ArrayList<Task> tasks = taskList;

        while (input.hasNextLine()) {
            // reads user input and modifies it to lower case
            String beforeEdit = input.nextLine();
            String nextInput = beforeEdit.toLowerCase();
            System.out.println("------------------------------------");

            if (nextInput.equals("bye")) {
                System.out.println(" > see you! hope to see you again :-)");
                System.out.println("==================================Oo");
                input.close();
                break;

            } else if (nextInput.equals("list")) {

                if (tasks.size() == 0) {
                    System.out.println("add anything using 'todo', 'deadline' or 'event'");
                } else {
                    System.out.println(" > here you go!");
                    // loop through the arraylist to show everything
                    for (int count = 0; count < tasks.size(); count++) {
                        Task eachTask = tasks.get(count);
                        int countFromOne = count + 1;
                        System.out.println(countFromOne + ". " + eachTask.toString());
                    }
                }
                System.out.println("==================================Oo");

            } else if (nextInput.contains("done")) {
                // to extract number from the input
                String taskToBeMarked = nextInput.replaceAll("[^0-9]", "");
                int numToBeMarked = Integer.parseInt(taskToBeMarked) - 1;
                tasks.get(numToBeMarked).markAsDone();
                System.out.println(" > i've marked this as done:");
                System.out.println("  " + tasks.get(numToBeMarked).toString());
                System.out.println("==================================Oo");

            } else if (nextInput.contains("delete")) {
                // to extract number from the input
                String taskToBeRemoved = nextInput.replaceAll("[^0-9]", "");
                int numToBeRemoved = Integer.parseInt(taskToBeRemoved) - 1;
                Task removed = tasks.get(numToBeRemoved);
                tasks.remove(numToBeRemoved);
                System.out.println(" > i've removed this task:");
                System.out.println("  " + removed.toString());
                System.out.println("you have " + tasks.size() + " things in your list");
                System.out.println("==================================Oo");
            } else {
                // add the task to the arraylist of tasks
                if (nextInput.contains("todo")) {
                    String updatedTask = nextInput.replace("todo", "").trim();
                    if (updatedTask.isBlank()) {
                        throw new ToDoException("please add a description to your todo task");
                    }
                    ToDos toDoTask = new ToDos(updatedTask);
                    tasks.add(toDoTask);
                    System.out.println(" > added:");
                    System.out.println("    " + toDoTask.toString());

                } else if (nextInput.contains("deadline")) {
                    String updatedTask = nextInput.replace("deadline", "").trim();
                    if (updatedTask.isBlank()) {
                        throw new DeadLineException("please add a description to your deadline task");
                    }

                    String deadlineToAdd = updatedTask.split("/by ")[0].trim();
                    String finishBy = updatedTask.split("/by ")[1].trim();

                    Deadlines deadlineTask = new Deadlines(deadlineToAdd, finishBy);
                    tasks.add(deadlineTask);
                    System.out.println(" > added:");
                    System.out.println("    " + deadlineTask.toString());

                } else if (nextInput.contains("event")) {
                    String updatedTask = nextInput.replace("event ", "").trim();
                    if (updatedTask.isBlank()) {
                        throw new EventException("please add a description to your event");
                    }

                    String eventToAdd = updatedTask.split("/at ")[0].trim();
                    String dateOfEvent = updatedTask.split("/at ")[1].trim();

                    Events eventTask = new Events(eventToAdd, dateOfEvent);
                    tasks.add(eventTask);
                    System.out.println(" > added:");
                    System.out.println("    " + eventTask.toString());

                } else {
                    throw new DukeException("i'm not sure i know what you mean :-( try typing something " +
                            "using 'todo', 'deadline' or 'event'");
                }

                if (tasks.size() == 1) {
                    System.out.println("you have " + tasks.size() + " thing in your list");
                } else if (tasks.size() > 1) {
                    System.out.println("you have " + tasks.size() + " things in your list");
                } else {
                    System.out.println("add anything using 'todo', 'deadline' or 'event'");
                }

                System.out.println("==================================Oo");
            }
            updateHardDisk(taskList);
        }
    }


    public static void main(String[] args) throws DukeException {
        // displays logo and instructions
        showIntro();
        // load data from hard disk when Dory starts up and starts bot
        Path dataFolderPath = Paths.get("data");
        Path dataFilePath = Paths.get("data/dory.txt");
        boolean doesFolderExist = Files.exists(dataFolderPath);
        boolean doesFileExist = Files.exists(dataFilePath);
        if (doesFolderExist) {
            if (doesFileExist) {
                try {
                    List<String> data = Files.readAllLines(dataFilePath);
                    ArrayList<Task> dataForDory = new ArrayList<>();
                    for (int i = 0; i < data.size(); i++) {
                        String[] lineTask = data.get(i).split("/");
                        String taskType = lineTask[0];
                        String isDone = lineTask[1];
                        String taskDesc = lineTask[2];

                        boolean doneOrNot = (isDone.equals("true"));
                        if (taskType.equals("T")) {
                            ToDos toDo = new ToDos(taskDesc);
                            toDo.isDone = doneOrNot;
                            dataForDory.add(toDo);
                        } else if (taskType.equals("D")) {
                            String finishBy = lineTask[3];
                            Deadlines deadline = new Deadlines(taskDesc, finishBy);
                            deadline.isDone = doneOrNot;
                            dataForDory.add(deadline);
                        } else if (taskType.equals("E")) {
                            String dateOfEvent = lineTask[3];
                            Events event = new Events(taskDesc, dateOfEvent);
                            event.isDone = doneOrNot;
                            dataForDory.add(event);
                        }
                    }
                    startChatBot(dataForDory);
                } catch (IOException e) {
                    ArrayList<Task> dataForDory = new ArrayList<>();
                    startChatBot(dataForDory);
                }
            } else {
                try {
                    Files.createFile(dataFilePath);
                } catch (IOException e) {
                    System.out.println("failed to create a data file");
                }
            }
        } else {
            try {
                Files.createDirectories(dataFolderPath);
                Files.createFile(dataFilePath);
            } catch (IOException e) {
                System.out.println("failed to create a data folder");
            }
        }
    }

}

