package duke;

import duke.tasks.Task;

import java.util.Scanner;

public class UI {
    
    public static class Response {
        private String content;
        
        private Response(String s) {
            this.content = s;
        }
    
        private Response(Tasklist list) {
            this.content = list.toString();
        }
    
        public static Response added(Tasklist tasklist, Task task) {
            String msg = "Got it. I've added this task:\n\t" 
                + task.toString()
                + "\nNow you have "
                + tasklist.getTotalTasks()
                + (tasklist.getTotalTasks() == 1 ? " task " : " tasks ")
                + "in the list.";
    
            Response response = new Response(msg);
            System.out.println(response);
            return response;
        }
    
        public static Response completed(Task task) {
            String msg = "Nice! I've marked this task as done:\n" 
                + task.toString();
            Response response = new Response(msg);
            System.out.println(response);
            return response;
        }
    
        public static Response removed(Tasklist tasklist, Task task) {
            String msg = "Noted. I've removed this task:\n\t" 
                + task.toString() 
                + "\nNow you have " 
                + tasklist.getTotalTasks()
                + (tasklist.getTotalTasks() == 1 ? " task" : " tasks")
                + " in the list.";
    
            Response response = new Response(msg);
            System.out.println(response);
            return response;
        }
    
        public static Response listAllItems(Tasklist tasklist) {
            Response response = new Response("Here are the tasks in your list:\n" 
                + tasklist.toString());
            System.out.println(response);
            return response;
        }
        
        public static Response start() {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n\n";
    
            Response response = new Response(logo 
                + "Hello there, I'm Duke!\nWhat can I do for you?");
            System.out.println(response);
            return response;
        }
    
        public static Response exit() {
            Response response = new Response("Bye! Come back again!");
            System.out.println(response);
            return response;
        }
    
        public static Response error(String msg) {
            Response response = new Response(msg);
            System.out.println(response);
            return response;
        }
        
        @Override
        public String toString() {
            String linebreak = "\t_______________________________________________\n";
            
            String[] splitByLines = this.content.split("\n");
            String rawContent = "";
    
            for (int i = 0; i < splitByLines.length; i++) {
                rawContent += ("\t" + splitByLines[i] + "\n"); 
            }
            
            String result = (linebreak + rawContent + linebreak);
    
            return result;
        }
    }
    
    private Scanner inputScanner;

    public UI(Scanner inputScanner) {
        this.inputScanner = inputScanner;
    }

    public void showLoadError() {
        Response.error("Could not read your file :(");
    }

    public void showStartMsg() {
        Response.start();
    }

    public void showExitMsg() {
        Response.exit();
    }

    public void showRemovedTask(Tasklist taskList, Task task) {
        Response.removed(taskList, task);
    }

    public void listAllTasks(Tasklist taskList) {
        Response.listAllItems(taskList);
    }

    public void showCompletedTask(Task task) {
        Response.completed(task);
    }

    public void showAddedTask(Tasklist taskList, Task task) {
        Response.added(taskList, task);
    }

    public void showErrorMsg(DukeException e) {
        Response.error(e.getMessage());
    }

    public String readCommand() {
        String fullCommand = this.inputScanner.nextLine();
        return fullCommand;
    }
}
