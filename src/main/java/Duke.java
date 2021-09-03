import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Duke {
    //Task and Subclasses
    public static class Task{

        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (this.isDone ? "X" : " "); // mark done task with X
        }

        public void setDone(){
            this.isDone = true;
            System.out.println(this.toString());
        }

        public String toString(){
            return "[" + this.getStatusIcon() + "] " + this.description;
        }

    }

    public static class ToDo extends Task{
        public ToDo(String description){
            super(description);
        }

        public String toString(){
            return "[T] " + "[" + this.getStatusIcon() + "] " + this.description;
        }

    }

    public static class Deadline extends Task{
        protected String endTime;

        public Deadline(String description, String endTime){
            super(description);
            this.endTime = endTime;
        }

        public String toString(){
            return "[D] " + "[" + this.getStatusIcon() + "] " + this.description
                    + " (by: " + this.endTime + ")";
        }
    }

    public static class Event extends Task{
        protected String timeRange;

        public Event(String description, String timeRange){
            super(description);
            this.timeRange = timeRange;
        }

        public String toString(){
            return "[E] " + "[" + this.getStatusIcon() + "] " + this.description
                    + " (at: " + this.timeRange + ")";
        }
    }
    public static void writeToFile(ArrayList<Task> tasks){
        File myObj;

        //Make a new duke.txt file if needed.
        try {
            myObj = new File("./duke.txt"); //Will be in the root directory.
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //write all tasks to the file. Separated by new lines
        try {
            FileWriter myWriter = new FileWriter("./duke.txt");
            for (int i = 1; i < tasks.size()+1; i++) {
                myWriter.write(i + ". " + tasks.get(i-1).toString());
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("File Updated!");
        } catch (IOException e){
            System.out.println("Oops! An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //START OF PROGRAM

        Scanner reader = new Scanner(System.in);
        String logo = "                                        _       \n"
                + "  _ __  _ _   _ __  ___ ___ ___ ___ ___| |__ ___\n"
                + " | '  \\| '_| | '  \\/ -_) -_|_-</ -_) -_) / /(_-<\n"
                + " |_|_|_|_|   |_|_|_\\___\\___/__/\\___\\___|_\\_\\/__/\n"
                + "\n";
        ArrayList<Task> tasks = new ArrayList<>();
        int currentIndex = 0;

        System.out.println(logo);
        System.out.println("I'm Mr Meeseeks look at me!");
        System.out.println("Type in \"todo\", \"deadline\" or \"event\" and I will keep track of a task!");
        System.out.println("Type \"list\" to show all tasks so far");
        System.out.println("Type \"done\" to mark a task as done");
        System.out.println("Type \"bye\" to exit");


        while (true){
            String input = reader.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. *Poof!*");
                break;
            }

            if (input.equals("help")){
                System.out.println("I'm Mr Meeseeks look at me!");
                System.out.println("Type in \"todo\", \"deadline\" or \"event\" and I will keep track of a task!");
                System.out.println("Type \"list\" to show all tasks so far");
                System.out.println("Type \"done\" to mark a task as done");
                System.out.println("Type \"bye\" to exit");
            } else if (input.equals("list")){
                //list out all tracked items
                System.out.println("Tasks I tracked so far:");
                for (int i = 1; i<currentIndex+1; i++){
                    System.out.println(i + ". " + tasks.get(i-1));
                }
            } else if (input.equals("done")){
                //mark an item of index i as done.
                if (currentIndex == 0){
                    System.out.println("Oops! There are no tasks yet!");
                } else{
                    while (true) {
                        System.out.println("Which task number should I mark as done?");
                        System.out.println("From 1 to " + currentIndex);
                        System.out.println("Type 0 to cancel");

                        String temp = reader.nextLine();
                        int taskNumber;

                        try{
                            taskNumber = Integer.parseInt(temp);
                        } catch (Exception e){ //if entered an invalid integer
                            System.out.println("Oops! Please enter a valid integer");
                            continue;
                        }


                        //a way to exit if not marking a task
                        if (taskNumber == 0){
                            System.out.println("Ok! Back to main");
                            break;
                        }

                        if (taskNumber > currentIndex || taskNumber < 0){
                            //prevent out of bounds error
                            System.out.println("Oops! Please enter a valid task number.");
                            System.out.println("From 1 to " + currentIndex);
                        } else{
                            System.out.println("Ok! I've marked the task below as done!");
                            tasks.get(taskNumber-1).setDone();
                            writeToFile(tasks);
                            break;
                        }

                    }
                }


            } else if (input.equals("todo") || input.equals("deadline") || input.equals("event")){
                //Create a new task
                if (currentIndex == 99) {
                    //limit of 100 items
                    System.out.println("Oops! My task list is full. No new items can be added");
                } else {
                    System.out.println("Ok! A " + input + " task");
                    String taskName;

                    while (true) {
                        System.out.println("What is the description of the task?");
                        taskName = reader.nextLine();
                        if (taskName.equals("")) { //name cannot be null
                            System.out.println("Oops! You have to give a name/description for that task");
                        } else {
                            break;
                        }
                    }

                    Task newTask;
                    String time;
                    if (input.equals("todo")){
                        newTask = new ToDo(taskName);
                    } else if (input.equals("deadline")){
                        while(true){
                            System.out.println("Enter the deadline for the task");
                            time = reader.nextLine();
                            if (time.equals("")){ //time cannot be null
                                System.out.println("Oops! Please enter a time");
                            } else{
                                break;
                            }
                        }

                        newTask = new Deadline(taskName, time);
                    } else{ //Event
                        while(true){
                            System.out.println("Enter the time range for the task");
                            time = reader.nextLine();
                            if (time.equals("")){ //time cannot be null
                                System.out.println("Oops! Please enter a time");
                            } else{
                                break;
                            }
                        }
                        newTask = new Event(taskName, time);
                    }
                    //add item to the end of the array.
                    tasks.add(newTask);
                    System.out.println("Ok! I've added the task below");
                    System.out.println(newTask);
                    currentIndex++;
                    System.out.println("There are now " + currentIndex + " tasks");
                    writeToFile(tasks);
                }


            } else if (input.equals("delete")){
                //delete item of index i, removing it from the list
                if (currentIndex == 0){
                    System.out.println("Oops! There are no tasks to delete right now");
                } else{
                    System.out.println("Which task number should I delete?");
                    System.out.println("From 1 to " + currentIndex);
                    System.out.println("Type 0 to cancel");

                    while (true) {

                        String temp = reader.nextLine();
                        int taskNumber;

                        try{
                            taskNumber = Integer.parseInt(temp);
                        } catch (Exception e){ //if entered an invalid integer
                            System.out.println("Oops! Please enter a valid integer");
                            System.out.println("From 1 to " + currentIndex);
                            continue;
                        }


                        //a way to exit if not deleting a task
                        if (taskNumber == 0){
                            System.out.println("Ok! Back to main");
                            break;
                        }

                        if (taskNumber > currentIndex || taskNumber < 0){
                            //prevent out of bounds error
                            System.out.println("Oops! Please enter a valid task number.");
                            System.out.println("From 1 to " + currentIndex);
                        } else{
                            System.out.println("Ok! Boom! The task is now gone!");
                            System.out.println(tasks.get(taskNumber-1));
                            tasks.remove(taskNumber-1);
                            currentIndex--;
                            System.out.println("There are now " + currentIndex + " tasks.");
                            writeToFile(tasks);
                            break;
                        }

                    }


                }
            } else { //if unrecognised command
                System.out.println("Oops! Sorry I don't recognise that command!");
                System.out.println("Type \"help\" for list of commands, and ensure that there are no illegal characters or trailing spaces");
            }
        }
    }
}
