import java.util.Scanner;

public class Duke {

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
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String logo = "                                        _       \n"
                + "  _ __  _ _   _ __  ___ ___ ___ ___ ___| |__ ___\n"
                + " | '  \\| '_| | '  \\/ -_) -_|_-</ -_) -_) / /(_-<\n"
                + " |_|_|_|_|   |_|_|_\\___\\___/__/\\___\\___|_\\_\\/__/\n"
                + "\n";
        Task[] tasks = new Task[100];
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
                    System.out.println(i + ". " + tasks[i-1]);
                }
            } else if (input.equals("done")){
                if (currentIndex == 0){
                    System.out.println("Oops! There are no tasks yet!");
                } else{
                //mark an item of index i as done.
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
                            tasks[taskNumber-1].setDone();
                            break;
                        }

                    }
                }


            } else if (input.equals("todo") || input.equals("deadline") || input.equals("event")){

                if (currentIndex == 99) {
                    //prevent out of bounds error
                    System.out.println("Oops! My task list is full. No new items can be added");
                } else {
                    String type = input;
                    System.out.println("Ok! A " + type + " task");

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
                        if (type.equals("todo")){
                            newTask = new ToDo(taskName);
                        } else if (type.equals("deadline")){
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
                    tasks[currentIndex] = newTask;
                    System.out.println("Ok! I've added the task below");
                    System.out.println(newTask);
                    currentIndex++;
                    System.out.println("There are now " + currentIndex + " tasks");
                }


            } else { //if unrecognised command
                System.out.println("Oops! Sorry I don't recognise that command!");
                System.out.println("Type \"help\" for list of commands, and ensure that there are no illegal characters or trailing spaces");
            }
        }
    }
}
