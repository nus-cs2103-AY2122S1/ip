import duke.tasks.*;
import duke.exceptions.*;
import duke.utils.Storage;
import duke.utils.TaskList;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static TaskList taskList;


//    public Duke() throws FileNotFoundException {
//        this.storage = new duke.utils.Storage();
//        taskList = storage.loadTaskList();
//
//    }

    public static void main(String[] args) throws DukeException{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke \nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        ArrayList<Task> tasks = new ArrayList<Task>();
        int taskCounter = 0;

        String input = "";

        Storage storage = new Storage();
        try{
            taskList = storage.loadTaskList();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }


        try {while(true){
            input = sc.nextLine().strip();
            if(input.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!\n");
                storage.saveTaskListToDisk(taskList);
                break;
                //System.exit();
            }
            else if(input.equals("list")) {
                for(int i = 0; i < taskList.numberOfTasks(); i++){
                    System.out.println((i+1) + "." + taskList.getTask(i).toString());
                }
            } else if (input.length() > 4 && input.substring(0,4).equals("done")){
                String taskDone = input.substring(5);
                int taskDoneIndex = Integer.parseInt(taskDone)-1;
                // duke.tasks.get(taskDoneIndex).makeDone();
                taskList.getTask(taskDoneIndex).makeDone();
                System.out.println("Nice! I've marked this task as done: ");
                // System.out.println(duke.tasks.get(taskDoneIndex).toString());
                System.out.println(taskList.getTask(taskDoneIndex).toString());

            }
            else {
                if(input.length()<4 ){
                    throw new DukeException("Unacceptable input");
                }
                if(input.substring(0,4).equals("todo")){
                    TodoTask newTodo = new TodoTask(input.substring(5));
                    //duke.tasks.add(newTask);
                    taskList.addTask(newTodo);
                    //taskCounter++;
                    System.out.println("Got it. I've added this task: ");
                    System.out.print("  " + newTodo.toString());

                } else if(input.substring(0,5).equals("event")){
                    //String at = input.split("/")[1].substring(3);

                    String[] dateArr = input.split("/");
                    String date = "";
                    for(int i=1; i< dateArr.length; i++){
                        if (i==1){
                            date+=dateArr[1].substring(3);
                        } else {
                            date += "/" + dateArr[i];
                        }
                    }
       
                    String at = input.split("/")[1].substring(3);
                    EventTask newEvent = new EventTask(input.substring(6).split("/")[0], date);
                    //duke.tasks.add(newEvent);
                    taskList.addTask(newEvent);
                    //taskCounter++;
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("  " + newEvent.toString());

                } else if(input.length()>5 && input.length()<8){
                    throw new DukeException("Unacceptable input");
                }
                else if(input.substring(0,6).equals("delete")) {
                    // delete from arraylist
                    // reduce counter by 1

                    int indexToDel = Integer.parseInt(input.substring(7))-1;
                    //Task tasktoDel = duke.tasks.get(indexToDel);
                    Task tasktoDel = taskList.getTask(indexToDel);

                    tasks.remove(indexToDel);
                    taskCounter--;
                    System.out.println("   Noted. I've removed this task: ");
                    System.out.println("  " + tasktoDel.toString());
                }
                else if(input.substring(0,8).equals("deadline")){
                    // deadline

                    //String by = input.split("/")[1].substring(3);
                    String[] dateArr = input.split("/");
                    String date = "";
                    for(int i=1; i< dateArr.length; i++){
                        if (i==1){
                            date+=dateArr[1].substring(3);
                        } else {
                            date += "/" + dateArr[i];
                        }
                    }
                    DeadlineTask newDeadline = new DeadlineTask(input.substring(9).split("/")[0], date);
                    //duke.tasks.add(newDeadline);
                    taskList.addTask(newDeadline);

                    taskCounter++;
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("  " + newDeadline.toString());

                }

                else {
                    throw new DukeException("Unacceptable input");
                }

                System.out.println("\nNow you have " +  (taskList.numberOfTasks()) + " duke.tasks in the list.");
            }
        }} catch (DukeException e){
            System.out.println("OOPS!!! You have enteted an invalid category");
        }

    }
}

