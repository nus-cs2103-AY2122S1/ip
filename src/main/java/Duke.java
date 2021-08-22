import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasksList = new ArrayList<>();
    
   

    // public enum ActionType {
    //     ADDTASK, LIST, DONE, DELETE, BYE
    // }

    /**
     * Adds the task to the taskslist.
     * 
     * @param task the task to be added
     */
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

    /**
     * List all the tasks in the tasks list by printing them out in sequence.
     */
    public void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksList.size(); i++) {
            Task t = tasksList.get(i);
            t.showThisTask(i + 1);
        }
    }

    /**
     * Converts a line from text file to a task
     * @param s a line from text file
     * @return a task from the string
     */
    public Task stringToTask(String s) {
        if (s.startsWith("[T]")) {
            return new Todo(s.substring(8));
        } else if (s.startsWith("[D]")) {
            String name = s.split(":")[0].substring(8);
            String date = s.split(":")[1];
            return new Deadline(name, date);
        } else {
            String name = s.split(":")[0].substring(8);
            String time = s.split(":")[1];
            return new Event(name, time);
        }
    }

    /**
     * Mark a task's statis as done and print out the result.
     * 
     * @param task the task to be marked as done
     */
    public void completeTask(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        task.markAsDone();
    }

    /**
     * Check if a task that the user requests for exists or not.
     * 
     * @param num the task index
     * @return true when the task exists or false when the task does not exist
     */
    public boolean checkTaskExists(int num) {
        if (tasksList.size() > num) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Delete a task from the tasks list.
     * 
     * @param task the task to be deleted
     */
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

    /**
     * Writes a task to the text file
     * @param content content of the task
     * @throws IOException
     */
    public void writeToFile(String content) throws IOException {
        FileWriter writer = new FileWriter("duke.txt", true);
        writer.append(content);
        writer.flush();
        writer.close();
    }

    /**
     * Deletes a task from the text file
     * @param num the index of the task in the list
     * @param scanner scanner for the text file
     * @return the updated text file string
     * @throws IOException
     */
    public void deleteTaskFromFile(int num, Scanner scanner) throws IOException {
        Task task = tasksList.get(num - 1);
        String fileString = "";
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            fileString += input + "\n";
        }
        System.out.println("before: \n" + fileString +"\n");
        String newFile = fileString.replace(task.toString() + "\n", "");
        FileWriter writer = new FileWriter("duke.txt");
        writer.write(newFile);
        writer.flush();
        writer.close();
    }

    public void markAsDoneInFile(int num, Scanner scanner) throws IOException {
        Task task = tasksList.get(num - 1);
        String fileString = "";
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            fileString += input + "\n";
        }
        System.out.println("before: \n" + fileString +"\n");
        String newFile = fileString.replace(task.toString() + "\n", task.toString().replace("[ ]", "[X]"));
        FileWriter writer = new FileWriter("duke.txt");
        writer.write(newFile);
        writer.flush();
        writer.close();
    }

    public static void main(String[] args) throws DukeException, IOException{
        Duke neko = new Duke();
        File nekoData = new File("duke.txt");
                 
        if (!nekoData.exists()) {
            nekoData.createNewFile();
            System.out.println("File created: " + nekoData.getName());
        } else {
            System.out.println("File already exists.");
        }
        Scanner scanner = new Scanner(nekoData);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            Task task = neko.stringToTask(s);
            tasksList.add(task);
        }
        
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
                        Scanner newSc = new Scanner(nekoData);
                        neko.markAsDoneInFile(taskNum, newSc);
                        neko.completeTask(tasksList.get(taskNum - 1));
                    }
                    
                    
                } else if (userInput.length() >= 8 && userInput.substring(0, 7).equals("delete ")) {
                    int taskNum = Integer.parseInt(userInput.substring(7));
                    if (tasksList.size() < taskNum) {
                        throw new DukeException("You cannot delete a task that does not exist!");
                    } else {
                        Scanner newSc = new Scanner(nekoData);
                        neko.deleteTaskFromFile(taskNum, newSc);
                        neko.deleteTask(tasksList.get(taskNum - 1));
                        
                    }
                } else {
                    
                    if (userInput.length() >= 4 && userInput.substring(0, 4).equals("todo")){
                        if (userInput.length() == 4) {
                            throw new DukeException("OOPS!!! The task name of a todo cannot be empty.");
                        }
                        Todo curr = new Todo(userInput.substring(5));
                        neko.addTask(curr);
                        neko.writeToFile("[T] " + curr.showStatus() + curr.name + "\n");
                        
                        System.out.println("Successfully wrote to the file.");
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
                        neko.writeToFile("[D] " + curr.showStatus() + curr.name + ":" + curr.date + "\n");
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
                        neko.writeToFile("[E] " + curr.showStatus() + curr.name + ":" + curr.time + "\n");
                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } 

            
            
            
        }
        
        
        
        System.out.println("Bye! Neko wishes to see you again soon!\n");
        scanner.close();
        
    }
}
