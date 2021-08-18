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
        System.out.println("Type in anything and I will keep track of it as a task!");
        System.out.println("Type \"list\" to show all tasks so far");
        System.out.println("Type \"done\" to mark a task as done");
        System.out.println("Type \"bye\" to exit");


        while (true){
            String input = reader.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. *Poof!*");
                break;
            }

            if (input.equals("list")){
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

                        Scanner intScanner = new Scanner(System.in);

                        if (intScanner.hasNextInt()) {
                            int taskNumber = intScanner.nextInt();
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
                        } else {
                            //prevent Type error
                            System.out.println("Oops! Please key in an integer number");
                        }
                    }
                }


            } else {
                if (currentIndex == 99) {
                    //prevent out of bounds error
                    System.out.println("Oops! My task list is full. No new items can be added");
                } else {
                    //add item to the end of the array.
                    System.out.println("Added in " + input);
                    tasks[currentIndex] = new Task(input);
                    currentIndex++;
                }
            }
        }
    }
}
