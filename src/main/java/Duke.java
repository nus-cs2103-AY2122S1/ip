import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke \nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int taskCounter = 0;
        //boolean[] doneTracker = new boolean[100];

        String input = "";
        while(true){
            input = sc.nextLine();
            if(input.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
                //System.exit();
            }
            else if(input.equals("list")) {
                for(int i = 0; i < taskCounter; i++){
                    String done;
                    if (tasks[i].done==true){
                        done = "X";
                    } else {
                        done = " ";
                    }
                    System.out.println((i+1) + "." + "[" + done + "] " + tasks[i].name);
                }
            } else if (input.length() > 4 && input.substring(0,4).equals("done")){
                String taskDone = input.substring(5);
                int taskDoneIndex = Integer.parseInt(taskDone)-1;
                tasks[taskDoneIndex].makeDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("  [X] " + tasks[taskDoneIndex].name);
            }
            else {
                tasks[taskCounter] = new Task(input);
                taskCounter++;
                System.out.println("added: " + input);
            }
        }

    }
}

class Task{
    String name;
    //String taskNumber;
    boolean done = false;

    Task(String name){
        this.name = name;
    }
    void makeDone(){
        done = true;
    }
}