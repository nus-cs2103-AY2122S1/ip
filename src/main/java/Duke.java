import java.util.Scanner;

public class Duke {
    private static Task[] tasksList = new Task[100];

    public static void addTask(Task task, int index) {
        tasksList[index] = task;
        System.out.println("added: " + task.getName());
    }

    public static void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksList.length; i++) {
            if (tasksList[i] == null) break;
            if (tasksList[i].getStatus()) {
                System.out.println(i + 1 + ".[X] " + tasksList[i].getName());
            } else {
                System.out.println(i + 1 + ".[ ] " + tasksList[i].getName());
            }
        }
    }

    public static void completeTask(Task task) {
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[X] " + task.getName());
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
            } else if (userInput.length() >= 6 && userInput.substring(0, 5).equals("done ")) {
                
                
                int taskNum = Integer.parseInt(userInput.substring(5));
                if (tasksList[taskNum - 1] == null) {
                    System.out.println("You cannot complete a task that does not exist!");
                } else {
                    Duke.completeTask(tasksList[taskNum - 1]);
                }
                
                
            } else {
                Task curr = new Task(userInput);
                Duke.addTask(curr, index);
                index++;
            }
            
            
        }
        
        System.out.println("Bye! Neko wishes to see you again soon!");
        
    }
}
