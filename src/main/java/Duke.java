import java.util.Scanner;

public class Duke {
    private static Task[] tasksList = new Task[100];

    public void addTask(Task task, int index) {
        task.addThisTask();
        tasksList[index] = task;
        
        String taskform;
        if (index == 0) {
            taskform = " task";
        } else {
            taskform = " tasks";
        }
        System.out.println("Now you have " + (index + 1) + taskform + " in the list.");
    }

    public void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksList.length; i++) {
            Task t = tasksList[i];
            if (t == null) break;
            t.showThisTask(i + 1);
            
        }
    }

    public void completeTask(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        task.markAsDone();
    }

    public boolean checkTaskExists(int num) {
        if (tasksList[num] != null) {
            return true;
        } else {
            return false;
        }
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
        
        int index = 0;
        
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
                    if (tasksList[taskNum - 1] == null) {
                        throw new DukeException("You cannot complete a task that does not exist!");
                    } else {
                        neko.completeTask(tasksList[taskNum - 1]);
                    }
                    
                    
                } else {
                    
                    if (userInput.length() >= 4 && userInput.substring(0, 4).equals("todo")){
                        if (userInput.length() == 4) {
                            throw new DukeException("OOPS!!! The task name of a todo cannot be empty.");
                        }
                        Todo curr = new Todo(userInput.substring(5));
                        neko.addTask(curr, index);
                    } else if (userInput.length() >= 8 && userInput.substring(0, 8).equals("deadline")) {
                        if (userInput.length() == 8) {
                            throw new DukeException("OOPS!!! The task name of a deadline cannot be empty.");
                        } else if (!userInput.contains("/by")) {
                            throw new DukeException("Please input date for your deadline!");
                        }
                        String name = userInput.substring(9).split("/by")[0];
                        String date = userInput.substring(9).split("/by")[1];
                        Deadline curr = new Deadline(name, date);
                        neko.addTask(curr, index);
                    } else if (userInput.length() >= 5 && userInput.substring(0, 5).equals("event")) {
                        if (userInput.length() == 5) {
                            throw new DukeException("OOPS!!! The task name of an event cannot be empty.");
                        } else if (!userInput.contains("/at")) {
                            throw new DukeException("Please input time for your event!");
                        }
                        String name = userInput.substring(6).split("/at")[0];
                        String time = userInput.substring(6).split("/at")[1];
                        Event curr = new Event(name, time);
                        neko.addTask(curr, index);
                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    
                    
                    index++;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            
            
            
        }
        
        System.out.println("Bye! Neko wishes to see you again soon!\n");
        
    }
}
