import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * A class that creates your personal assistant Winston
 */
public class Winston {
    private ArrayList<Task> list;

    /**
     * Constructor for class Winston
     */
    public Winston() {
        list = new ArrayList<>();
    }

    /**
     *  Adds a task to the arraylist
     *  
     * @param task An Object of type Task to be added to the Arraylist
     */
    private void addTask(Task task) {
        list.add(task);
    }

    /**
     * Marks the Task of given (position - 1) as completed
     *
     * @param position An integer corresponding to the task you wish to complete
     */
    public void markTask(Integer position) {
        list.get(position - 1).setComplete();
    }

    /**
     * Removing a Task from the arraylist based on position
     * 
     * @param position the position of the Task to be removed from the arraylist
     * Note: position will be (index of item in array list + 1)
     */
    public void deleteTask(Integer position) {
        list.remove(position - 1);
    }

    /**
     * Number of tasks left to be completed
     * 
     * @return the number of tasks left in the list that are not completed
     */
    public int size() {
        int counter = 0;
        for (Task task : list) {
            if (task.taskCompletion().equals("[ ]")) {
                counter += 1;
            }
        }
        return counter;
    }

    /**
     * Transforms the arraylist of tasks into a string for visualisation
     *
     * @return A string of the arraylist of tasks
     */
    public String getList() {
        Integer counter = 1;
        StringBuilder result = new StringBuilder("List of things to do:\n");
        for (Task task : this.list) {
            result.append( "\t" + counter + ". " + task.taskCompletion() + task.toString() + "\n");
            counter += 1;
        }
        result.append("End");
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hi there! Winston reporting.\nWhat can I do for you?\n" +
                "Available Commands: done, list, todo, deadline, event, bye, delete");
        String cmd = "";
        Winston winston1 = new Winston();
        while (!cmd.equals("bye")) {
            switch (cmd) {
                case "list":
                    System.out.println(winston1.getList());
                    System.out.println(winston1.size() + " tasks remaining.");
                    break;
                case "done":
                    System.out.println(winston1.getList());
                    System.out.println("Which task number have you completed?");
                    try{
                        winston1.markTask(scan.nextInt());
                    } catch(InputMismatchException | IndexOutOfBoundsException e) {
                        System.out.println("Please give a valid number. Resetting to home menu.");
                        break;
                    }
                    System.out.println("Don't worry, I've got you. Task Marked!"+ "\n" +
                            winston1.size() + " tasks remaining.");
                    break;
                case "todo":
                    System.out.println("What task would you like to add?");
                    winston1.addTask(new ToDoTask(scan.nextLine()));
                    System.out.println("Task Added!");
                    break;
                case "deadline": {
                    System.out.println("What task would you like to add?");
                    String task = scan.nextLine();
                    System.out.println("What is the due date of this task?");
                    String dueDate = scan.nextLine();
                    winston1.addTask(new DeadLine(task, dueDate));
                    System.out.println("Task Added!");
                    break;
                }
                case "event": {
                    System.out.println("What task would you like to add?");
                    String task = scan.nextLine();
                    System.out.println("When is this event?");
                    String on = scan.nextLine();
                    winston1.addTask(new Event(task, on));
                    System.out.println("Task Added!");
                    break;
                }
                case "delete": {
                    System.out.println(winston1.getList());
                    System.out.println("What task would you like to delete?");
                    try{
                        winston1.deleteTask(scan.nextInt());
                    } catch(InputMismatchException | IndexOutOfBoundsException e) {
                        System.out.println("Please give a valid number. Resetting to home menu.");
                        break;
                    }
                    
                    System.out.println("Don't worry, I've got you. Task Deleted!" + "\n" +
                            winston1.size() + " tasks remaining.");
                }
                case "": {
                    break;
                }
                default: {
                    System.out.print("Invalid command. Please input a valid command." + "\n");
                }
            }
            cmd = scan.nextLine();
        }

        scan.close();
        System.out.println("See ya later!");
    }
}
