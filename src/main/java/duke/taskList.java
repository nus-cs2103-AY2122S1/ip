package duke;
/**
 * List refers to a task list that is used in the Duke program.
 * An List object contains the tasks and several methods that work on the task.
 *
 * @author Dominic Siew Zhen Yu
 */

import java.io.*;
import java.util.ArrayList;

public class taskList {
    private ArrayList<Task> taskList;
    private FileWriter taskFile;
    private boolean isFirstTimeOpening;
    private Storage memory;

    /**
     * The constructor for the List class that instantiates a List object.
     */

    public taskList() {
        this.taskList = new ArrayList<Task>();
        File file = new File("data/memory.txt");
        this.memory = new Storage("data/memory.txt");
    }


    public taskList(File memory)  {
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
                String[] seperate1 = currentLine.toString().split(" ", 2);
                String taskTypeText = seperate1[0];
                String taskType;

                String[] seperate2 = seperate1[1].toString().split(" ", 2);
                boolean isCompleted = (seperate2[0].equals("[✓]"))? true: false;
                String eventInfo = seperate2[1];

                switch(taskTypeText) {
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
                }

                if (isCompleted){
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
     * the addTask() places generic tasks into the list (which was used in level 1-3).
     *
     * @param userInput
     */

    public void addTask(String userInput) {
        taskList.add(new Task(userInput));
    }

    /**
     * the printList() method prints out the String representation of the tasks
     * in the List object.
     */

    public String printList() {
        int numb = 1;
        String output = "Here are the tasks in your list:";
        for(Task task: taskList) {
            output += "\n" + numb + ". " + task.printName();
            numb++;
        }
        System.out.println(output);
        return output;
    }

    /**
     * the updateTaskStatus() method marks a task to be as completed.
     *
     * @param userInput the rank of the task in a list
     */

    public String updateTaskStatus(int userInput, boolean isInput) {
        String output = "";

        if (userInput > taskList.size()) {
            output ="please enter a number that's between 1 and " + taskList.size();
            System.out.println(output);
            return output;
        }

        Task task = taskList.get(userInput - 1);
        String lineToRemove = task.printName();
        task.toggleComplete();

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
            String output = "Got it. I've added this task:" + "\n" + task.printName() +
                    "\nNow you have " + taskList.size() + " tasks in the list.";
            System.out.println(output);
            this.memory.addTaskToMemory(task.printName());
            return output;
        } else {
            return "";
        }

    }
    /**
     * the addEvent() method adds Events task into the list object.
     *
     * @param name the name of the event
     * @param timeline the period that the event is taking place
     */
    public String addEvent(String name, String timeline, boolean isInput) {
        Task task = new Event(name, timeline);
        taskList.add(task);
        if (isInput) {
            String output = "Got it. I've added this task:" + "\n" + task.printName() +
                    "\nNow you have " + taskList.size() + " tasks in the list.";
            this.memory.addTaskToMemory(task.printName());
            System.out.println(output);
            return output;
        } else {
            return "";
        }

    }
    /**
     * the addDeadline() method adds Events task into the list object.
     *
     * @param name the name of the deadline
     * @param deadline the deadline of the deadline task
     */
    public String addDeadline(String name, String deadline, boolean isInput) {
        Task task = new Deadlines(name, deadline, isInput);
        taskList.add(task);

        if (isInput) {
            String output = "Got it. I've added this task:" + "\n" + task.printName() +
                    "\nNow you have " + taskList.size() + " tasks in the list.";
            this.memory.addTaskToMemory(task.printName());
            System.out.println(output);
            return output;
        } else {
            return "";
        }
    }
    /**
     * the removeTask() method removes the task from the list object.
     *
     * @param userInput the rank of the task in a list
     */
    public String removeTask(int userInput) {
        Task task = taskList.get(userInput);
        String taskName = task.printName();

        taskList.remove(userInput);
        String output = "Noted. I've removed the task:\n" + taskName +
                "\nNow you have " + taskList.size() + " tasks in the list.";
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
     * findWord() method finds methods with the keyword inputted by users.
     *
     */

    public String findWord(String keyWord) {
        String output = "Here are the matching tasks in your list:";
        int numb = 1;
        for(Task task: taskList) {
            if (task.getName().contains(keyWord)) {
                output += "\n" + numb + ". " + task.printName();
                numb++;
            }
        }
        System.out.println(output);
        return output;
    }
}
