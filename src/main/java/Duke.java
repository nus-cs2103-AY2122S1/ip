import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasksList = new ArrayList<>();

    public void addTask(Task task) {
        System.out.println("Got it. I've added this task: ");
        task.showThisTask();
        tasksList.add(task);
        
        String taskform;
        if (tasksList.size() == 1) {
            taskform = " task";
        } else {
            taskform = " tasks";
        }
        System.out.println("Now you have " + (tasksList.size()) + taskform + " in the list.");
    }

    public void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksList.size(); i++) {
            Task t = tasksList.get(i);
            t.showThisTask(i + 1);
            
        }
    }

    public void completeTask(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        task.markAsDone();
    }

    public boolean checkTaskExists(int num) {
        if (tasksList.size() > num) {
            return true;
        } else {
            return false;
        }
    }

    public void deleteTask(Task task) {
        tasksList.remove(task);
        System.out.println("Noted. I've removed this task: ");
        task.showThisTask();
        String taskform;
        if (tasksList.size() == 1 || tasksList.size() == 0) {
            taskform = " task";
        } else {
            taskform = " tasks";
        }
        System.out.println("Now you have " + tasksList.size() + taskform + " in the list.");
    }

    public static void main(String[] args) throws DukeException{
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        Duke neko = new Duke();
        System.out.println("Hello from Neko!\nWhat can I do for you?\n");
        Scanner sc = new Scanner(System.in);  
        
        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            try {
                if (userInput.equals("bye")) {
                    sc.close();
                    break;
                }
                if (userInput.equals("list")) {
                    neko.listTask();
                } else if (userInput.length() >= 6 && userInput.substring(0, 5).equals("done ")) {
                    
                    
                    int taskNum = Integer.parseInt(userInput.substring(5));
                    if (tasksList.size() > taskNum) {
                        throw new DukeException("You cannot complete a task that does not exist!");
                    } else {
                        neko.completeTask(tasksList.get(taskNum - 1));
                    }
                    
                    
                } else if (userInput.length() >= 8 && userInput.substring(0, 7).equals("delete ")) {
                    int taskNum = Integer.parseInt(userInput.substring(7));
                    if (tasksList.size() > taskNum) {
                        throw new DukeException("You cannot delete a task that does not exist!");
                    } else {
                        neko.deleteTask(tasksList.get(taskNum - 1));
                    }
                } else {
                    
                    if (userInput.length() >= 4 && userInput.substring(0, 4).equals("todo")){
                        if (userInput.length() == 4) {
                            throw new DukeException("OOPS!!! The task name of a todo cannot be empty.");
                        }
                        Todo curr = new Todo(userInput.substring(5));
                        neko.addTask(curr);
                    } else if (userInput.length() >= 8 && userInput.substring(0, 8).equals("deadline")) {
                        if (userInput.length() == 8) {
                            throw new DukeException("OOPS!!! The task name of a deadline cannot be empty.");
                        } else if (!userInput.contains("/by")) {
                            throw new DukeException("Please input date for your deadline!");
                        }
                        String name = userInput.substring(9).split("/by")[0];
                        String date = userInput.substring(9).split("/by")[1];
                        Deadline curr = new Deadline(name, date);
                        neko.addTask(curr);
                    } else if (userInput.length() >= 5 && userInput.substring(0, 5).equals("event")) {
                        if (userInput.length() == 5) {
                            throw new DukeException("OOPS!!! The task name of an event cannot be empty.");
                        } else if (!userInput.contains("/at")) {
                            throw new DukeException("Please input time for your event!");
                        }
                        String name = userInput.substring(6).split("/at")[0];
                        String time = userInput.substring(6).split("/at")[1];
                        Event curr = new Event(name, time);
                        neko.addTask(curr);
                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            
            
            
        }
        
        System.out.println("Bye! Neko wishes to see you again soon!\n");
        
    }
}
