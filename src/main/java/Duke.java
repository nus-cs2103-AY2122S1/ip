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
//                    String done;
//                    if (tasks[i].done==true){
//                        done = "X";
//                    } else {
//                        done = " ";
//                    }
                    //System.out.println((i+1) + "." + "[" + tasks[i].type + "] " + "[" + done + "] " + tasks[i].name);
                    System.out.println((i+1) + "." + tasks[i].toString());
                }
            } else if (input.length() > 4 && input.substring(0,4).equals("done")){
                String taskDone = input.substring(5);
                int taskDoneIndex = Integer.parseInt(taskDone)-1;
                tasks[taskDoneIndex].makeDone();
                System.out.println("Nice! I've marked this task as done: ");
                //System.out.println("[" + tasks[taskDoneIndex].type + "]" + "  [X] " + tasks[taskDoneIndex].name);
                System.out.println(tasks[taskDoneIndex].toString());
            }
            else {
                System.out.println("Got it. I've added this task: ");

                if(input.substring(0,4).equals("todo")){
                    Task newTask = new Task(input.substring(5), "T");
                    tasks[taskCounter] = newTask;
                    taskCounter++;
                    //System.out.println("  [T]" + "[ ] " + input.substring(5));
                    System.out.print("  " + newTask.toString());

                } else if(input.substring(0,5).equals("event")){
                    String at = input.split("/")[1].substring(3);
                    Event newEvent = new Event(input.substring(6).split("/")[0], at);
                    tasks[taskCounter] = newEvent;
                    taskCounter++;
                    //System.out.println("  [E]" + "[ ] " + input.substring(6));
                    System.out.println("  " + newEvent.toString());
                } else {
                    // deadline
                    String by = input.split("/")[1].substring(3);
                    Deadline newDeadline = new Deadline(input.substring(9).split("/")[0], by);
                    tasks[taskCounter] = newDeadline;
                    taskCounter++;
                    //System.out.println("  [D]" + "[ ] " + input.substring(9));
                    System.out.println("  " + newDeadline.toString());

                }

                System.out.println("Now you have " +  (taskCounter) + " tasks in the list.");
            }
        }

    }
}

class Task{
    String name;
    //String taskNumber;
    boolean done = false;
    String type;

    Task(String name, String type){
        this.name = name;
        this.type = type;
    }
    void makeDone(){
        done = true;
    }

    @Override
    public String toString(){
        String doneSymbol = done? "X" : " ";
        String result = "[" + type + "] " + "[" + doneSymbol + "] " + name;
        return result;
    }
}

class Deadline extends Task{
    String by;
    Deadline(String description, String by) {
        super(description, "D");
        this.by = by;
    }

    @Override
    public String toString(){
        String doneSymbol = done? "X" : " ";
        String result = "[" + type + "] " + "[" + doneSymbol + "] " + name + "(by: " + by + ")";
        return result;
    }
}

class Event extends Task{
    String at;
    Event(String description, String at) {
        super(description, "E");
        this.at = at;
    }

    @Override
    public String toString(){
        String doneSymbol = done? "X" : " ";
        String result = "[" + type + "] " + "[" + doneSymbol + "] " + name + "(at: " + at + ")";
        return result;
    }
}