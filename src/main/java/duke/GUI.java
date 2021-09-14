package duke;

import java.util.Scanner;


public class GUI{

    private boolean isListening;
    private TaskList lst;

    public GUI(TaskList lst){
        this.isListening = true;
        this.lst = lst;
    }

    /**
     * Greet function for the chatbot to greet users
     *
     */
    public String greet(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you? :)");

        return "Hello from\n" + "DUKE!" + "\nWhat can I do for you? :)\n" +
                "Please enter :\n"+ "'list' to see your list of tasks \n" + "'todo {your todo}' to add a todo\n" +
                "'event {event description} /at YYYY-MM-DD hhmm' to add an event\n" +
                "'deadline {deadline description} /by YYYY-MM-DD hhmm' to add a deadline\n" +
                "'delete {INDEX}' to delete a task of specified INDEX\n" +
                "'find {KEYWORD}' to display all the tasks which have specified KEYWORD\n" +
                "'done INDEX' to mark a task of specified INDEX as done\n" +
                "'bye' to save your current task list\n";
    }

    /**
     * function to respond to a user input
     * @params String s
     */
    public String respond(String s){
        if(s.contains("done")){
            int order = Integer.parseInt(s.substring(5)); //getting the order of the task
            assert order > 0: "order should be more than 0";
            return lst.markDone(order);
        } else if(s.contains("list")){
            return lst.displayList();
        } else if(s.contains("deadline")){
            return lst.addDeadline(s);
        } else if(s.contains("event")){
            return lst.addEvent(s);
        } else if (s.contains("todo")){
            return lst.addTodo(s);
        } else if(s.contains("delete")){
            int order = Integer.parseInt(s.substring(7)); //getting the order of the task
            assert order > 0: "order should be more than 0";
            return lst.deleteTask(order);
        } else if (s.contains("find")){
            return lst.find(s.substring(5));
        } else if(s.contains("bye")){
            return "I have updated your task list file! thankyou and see you soon!";
        } else if(s.contains("sort by type")){
            return lst.sortByType();
        } else if(s.contains("sort by date reverse")){
            return lst.sortByDateReversed();
        } else if(s.contains("sort by date")){
            return lst.sortByDate();
        } else {
            return "I don't know what you're saying!";
        }
    }

    /**
     * Checks for the validity of user's commands and responds to it
     * @param input User's input
     * @return
     */
    public String getResponse(String input) {
        try{
            InputChecker ic = new InputChecker(input);
        } catch (DukeException e){
            return(e.getMessage());
        }
        return respond(input);

    }

}
