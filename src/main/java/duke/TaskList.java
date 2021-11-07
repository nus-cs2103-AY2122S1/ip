package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * List refers to a task list that is used in the Duke program.
 * An List object contains the tasks and several methods that work on the task.
 *
 * @author Dominic Siew Zhen Yu
 */

public class TaskList {
    private ArrayList<Task> taskList;
    private FileWriter taskFile;
    private boolean isFirstTimeOpening;
    private Storage memory;

    private String toDoIndicator = "[T]";
    private String deadlineIndicator = "[D]";
    private String eventIndicator = "[E]";

    /**
     * The constructor for the List class that instantiates a List object.
     */

    public TaskList() {
        this.taskList = new ArrayList<Task>();
        File file = new File("data/memory.txt");
        this.memory = new Storage("data/memory.txt");
    }

    /**
     * the initialisation of the TaskList class
     * @param memory the file where the tasks are kept in memory
     */

    public TaskList(File memory) {
        this.taskList = new ArrayList<Task>();
        this.memory = new Storage(memory.getPath());

        try {
            memory.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            File updated = new File("data/updated.txt");
            BufferedReader reader = null;
            reader = new BufferedReader(new FileReader(memory));
            BufferedWriter writer = new BufferedWriter(new FileWriter(updated));
            String currentLine = "";
            int numberAdded = 1;

            while ((currentLine = reader.readLine()) != null) {
                assert currentLine.length() > 4;
                String[] seperate1 = currentLine.toString().split(" ", 2);
                String taskTypeText = seperate1[0];
                String taskType;

                String[] seperate2 = seperate1[1].toString().split(" ", 2);
                boolean isCompleted = (seperate2[0].equals("[✓]")) ? true : false;
                String eventInfo = seperate2[1];
                switch (taskTypeText) {
                case "[T]":
                    String eventname = eventInfo;
                    this.addTodo(eventname, false);
                    break;
                case "[D]":
                    String[] seperateAgain = seperate2[1].split(" \\(by: ", 2);
                    String eventName = seperateAgain[0];
                    String deadline = seperateAgain[1].split("\\)", 2)[0];
                    this.addDeadline(eventName, deadline, false);
                    break;
                case "[E]":
                    String[] seperateAgaining = seperate2[1].split(" \\(at: ", 2);
                    String event = seperateAgaining[0];
                    String timeline = seperateAgaining[1].split("\\)", 2)[0];
                    this.addEvent(event, timeline, false);
                    break;
                default:
                    throw new DukeException("Generic");
                }
                if (isCompleted) {
                    this.updateTaskStatus(numberAdded, false);
                }
                numberAdded++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * prints out the String representation of the tasks in the List object.
     */

    public String printList() {
        int numb = 1;
        String output = "Here are the tasks in your list:";
        for (Task task: taskList) {
            output += "\n" + numb + ". " + task.printName();
            numb++;
        }
        System.out.println(output);
        return output;
    }

    /**
     * marks a task to be as completed.
     * @param userInput the rank of the task in a list
     */

    public String updateTaskStatus(int userInput, boolean isInput) {
        String output = "";

        if (userInput > taskList.size()) {
            output = "please enter a number that's between 1 and " + taskList.size();
            System.out.println(output);
            return output;
        }

        Task task = taskList.get(userInput - 1);
        String lineToRemove = task.printName();
        task.toggleComplete();
        if (!task.isCompleted()) {
            output += "I've marked this task as not done: \n" + task.printName();
            return output;
        }
        if (isInput) {

            output += "Nice! I've marked this task as done:\n" + task.printName();
            System.out.println(output);

            try {
                File updated = new File("data/updated.txt");

                BufferedReader reader = new BufferedReader(new FileReader(this.memory.load()));
                BufferedWriter writer = new BufferedWriter(new FileWriter(updated));

                String currentLine = "";

                while ((currentLine = reader.readLine()) != null) {
                    if (currentLine.equals(lineToRemove)) {
                        writer.write(task.printName() + "\n");
                        continue;
                    }
                    writer.write(currentLine + "\n");
                }
                writer.close();
                reader.close();

                boolean successful = updated.renameTo(this.memory.load());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output;
    }

    /**
     * the addTodo() method adds todos task into the list object
     *
     * @param userInput the name of the todo task
     */

    public String addTodo(String userInput, boolean isInput) {
        Task task = new Todos(userInput);
        taskList.add(task);

        if (isInput) {
            String output = this.addTaskResponse(task);
            System.out.println(output);
            this.memory.addTaskToMemory(task.printName());
            return output;
        } else {
            return "";
        }

    }

    /**
     * adds Events task into the list object.
     * @param name the name of the event
     * @param timeline the period that the event is taking place
     */
    public String addEvent(String name, String timeline, boolean isInput) {
        Task task = new Event(name, timeline, isInput);
        taskList.add(task);
        if (isInput) {
            String output = this.addTaskResponse(task);
            this.memory.addTaskToMemory(task.printName());
            System.out.println(output);
            return output;
        } else {
            return "";
        }
    }

    /**
     * prints out the response from DukeMan when a task is added.
     * @param input
     * @return
     */
    public String addTaskResponse(Task input) {
        String output = "Got it. I've added this task:" + "\n" + input.printName()
                + "\nNow you have " + taskList.size() + " tasks in the list.";

        return output;
    }

    /**
     * the addDeadline() method adds Events task into the list object.
     *
     * @param name the name of the deadline
     * @param deadline the deadline of the deadline task
     */
    public String addDeadline(String name, String deadline, boolean isInput) {
        Task task = new Deadline(name, deadline, isInput);
        taskList.add(task);

        if (isInput) {
            String output = this.addTaskResponse(task);
            this.memory.addTaskToMemory(task.printName());
            System.out.println(output);
            return output;
        } else {
            return "";
        }
    }

    /**
     * removes the task from the list object.
     * @param userInput the rank of the task in a list
     */
    public String removeTask(int userInput) {
        Task task = taskList.get(userInput);
        String taskName = task.printName();

        taskList.remove(userInput);
        String output = "Noted. I've removed the task:\n" + taskName
                + "\nNow you have " + taskList.size() + " tasks in the list.";
        System.out.println(output);

        String lineToRemove = task.printName();

        try {
            File updated = new File("data/updated.txt");

            BufferedReader reader = new BufferedReader(new FileReader(this.memory.load()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(updated));

            String currentLine = "";

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.equals(lineToRemove)) {

                    continue;
                }
                writer.write(currentLine + "\n");
            }
            writer.close();
            reader.close();

            boolean successful = updated.renameTo(memory.load());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return output;
    }

    /**
     * finds tasks with the keyword inputted by the user.
     * @param keyWord String input from the user
     * @return string that represents the list of tasks with the same keyword
     */
    public String findWord(String keyWord) {
        String output = "Here are the matching tasks in your list:";
        int numb = 1;
        for (Task task: taskList) {
            if (task.getName().contains(keyWord)) {
                output += "\n" + numb + ". " + task.printName();
                numb++;
            }
        }
        System.out.println(output);
        return output;
    }

    /**
     * prints tasks that happens on a given date.
     * @param date date of the task
     * @return a String of events that are sorted based on their timeslot
     */

    public String printSchedule(String date) {
        Schedule dateEvents = new Schedule();
        ArrayList<Task> schedule = dateEvents.sortTask(taskList, date);
        String output = "";
        int numb = 1;

        LocalDate currentDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String outputDate = currentDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        output += "Here's your schedule for " + outputDate + ":";

        for (Task task : schedule) {
            output += "\n" + numb + ". " + task.printName();
            numb++;
        }
        return output;
    }
}
