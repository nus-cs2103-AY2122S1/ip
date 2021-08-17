import java.util.Scanner;

public class Duke {
    private static Task[] tasksList = new Task[100];

    public static void addTask(Task task, int index) {
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

    public static void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksList.length; i++) {
            Task t = tasksList[i];
            if (t == null) break;
            System.out.println(i + 1 +  "."+ t.showPrefix() + t.showStatus() + t.name);
            
        }
    }

    public static void completeTask(Task task) {
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task.showPrefix() + "[X] " + task.name);
    }

    public static boolean checkTaskExists(int num) {
        if (tasksList[num] != null) {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from Neko!\nWhat can I do for you?\n(Nothing actually cos I will just repeat what you say!)\n");
        Scanner sc = new Scanner(System.in);  
        
        int index = 0;
        
        while (sc.hasNext()) {
            String userInput = sc.nextLine();

            if (userInput.equals("bye")) {
                sc.close();
                break;
            }
            if (userInput.equals("list")) {
                Duke.listTask();
            } else if (userInput.equals("")) {
                System.out.println("Please input task before entering!");
            } else if (userInput.length() >= 6 && userInput.substring(0, 5).equals("done ")) {
                
                
                int taskNum = Integer.parseInt(userInput.substring(5));
                if (tasksList[taskNum - 1] == null) {
                    System.out.println("You cannot complete a task that does not exist!");
                } else {
                    Duke.completeTask(tasksList[taskNum - 1]);
                }
                
                
            } else {
                
                if (userInput.length() >= 6 && userInput.substring(0, 5).equals("todo ")){
                    Todo curr = new Todo(userInput.substring(5));
                    Duke.addTask(curr, index);
                } else if (userInput.length() >= 10 && userInput.substring(0, 9).equals("deadline ")) {
                    String name = userInput.substring(9).split("/by")[0];
                    String date = userInput.substring(9).split("/by")[1];
                    Deadline curr = new Deadline(name, date);
                    Duke.addTask(curr, index);
                } else if (userInput.length() >= 7 && userInput.substring(0, 6).equals("event ")) {
                    String name = userInput.substring(6).split("/at")[0];
                    String time = userInput.substring(6).split("/at")[1];
                    Event curr = new Event(name, time);
                    Duke.addTask(curr, index);
                } else {
                    Task curr = new Task(userInput);
                    Duke.addTask(curr, index);
                }
                
                
                index++;
            }
            
            
        }
        
        System.out.println("Bye! Neko wishes to see you again soon!");
        
    }
}
