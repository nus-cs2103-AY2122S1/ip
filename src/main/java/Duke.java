import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ToDoList toDoList = ToDoList.of(new ArrayList<Task>());

        displayWithStyle(beginScript()); //display welcome msg

        while (sc.hasNext()) {
            int numOfTasks = toDoList.length();
            String userInput = sc.nextLine();
            String msgOutput = "";

            try {
                if (userInput.equals("list")) {
                    msgOutput = String.format(
                            "Here are the tasks in your list:\n%s", toDoList.toString()
                    );
                } else if (userInput.equals("bye")) {
                    msgOutput = endScript();
                } else if (userInput.matches("done\s[0-9]{1,2}")) {
                    //eg. done 12
                    //limiting tasks from 0-99
                    String inputBody = userInput.split(" ", 2)[1];
                    int idxFrom0 = Integer.parseInt(inputBody) - 1;
                    if (!(idxFrom0 < 0 || idxFrom0 >= numOfTasks)) { //valid argument indexes
                        toDoList.toggleDone(idxFrom0);
                        msgOutput = String.format(
                                "Nice! I've marked this task as done:\n    %s",
                                toDoList.get(idxFrom0).toString()
                        );
                    }
                } else if (userInput.matches("delete\s[0-9]{1,2}")) {
                    //eg. delete 3
                    String inputBody = userInput.split(" ", 2)[1];
                    int idxFrom0 = Integer.parseInt(inputBody) - 1;
                    if (!(idxFrom0 < 0 || idxFrom0 >= numOfTasks)) { //valid argument indexes
                        msgOutput = String.format(
                                "Noted. I've removed this task:\n    %s\nNow you have %d tasks in the list.",
                                toDoList.get(idxFrom0).toString(),
                                numOfTasks - 1
                        );
                        toDoList.removeTask(idxFrom0);
                    }
                }else if (userInput.matches("todo\\s\\w+.*")) {
                    //eg. todo read book
                    String inputBody = userInput.split(" ", 2)[1];
                    Task newTask = ToDo.of(inputBody);
                    msgOutput = String.format(
                            "Got it! I've added this task:\n %s\nNow you have %d tasks in the list.",
                            newTask.toString(), numOfTasks + 1
                    );
                    toDoList.addTask(newTask);
                } else if (userInput.matches("deadline\s.+\s\\/by\s.+")) {
                    //eg. deadline xxx /by xxx
                    String inputBody = userInput.split(" ", 2)[1];
                    String[] deadlineDetails = inputBody.split("\s/by\s", 2);
                    String deadlineTask = deadlineDetails[0];
                    String deadlineByDate = deadlineDetails[1];

                    Task newTask = Deadline.of(deadlineTask, deadlineByDate);
                    msgOutput = String.format(
                            "Got it! I've added this task:\n %s\nNow you have %d tasks in the list.",
                            newTask.toString(), numOfTasks + 1
                    );
                    toDoList.addTask(newTask);
                } else if (userInput.matches("event\s.+\s\\/at\s.+")) {
                    //eg. deadline xxx /by xxx
                    String inputBody = userInput.split(" ", 2)[1];
                    String[] eventDetails = inputBody.split("\s/at\s", 2);
                    String eventTask = eventDetails[0];
                    String eventTime = eventDetails[1];

                    Task newTask = Event.of(eventTask, eventTime);
                    msgOutput = String.format(
                            "Got it! I've added this task:\n %s\nNow you have %d tasks in the list.",
                            newTask.toString(), numOfTasks + 1
                    );
                    toDoList.addTask(newTask);
                }  else {
                            throw new DukeException(userInput);
//                        msgOutput = defaultReplyToInvalidInput();
                }
//                else {
//                    //user input not proper
//                    msgOutput = defaultReplyToInvalidInput();
//                }
            } catch (Exception e) {
                msgOutput = e.toString();
            } finally {
                displayWithStyle(msgOutput); //output msg to user
            }
        }

    }

    public static String beginScript() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return ("Hello from\n" + logo);
    }

    public static String endScript() {
        String exitStatment = "Bye, hope to see you again! :)";
        return exitStatment;
//        System.out.println(exitStatment);
    }
    public static void displayWithStyle(String text) {
        String indent = "    ";
        String topBorder    = "____________________________________\n";
        String bottomBorder = "------------------------------------\n";
        String textWithBorders = topBorder +  text + "\n" + bottomBorder;
        String[] lines = textWithBorders.split("\n");
//        System.out.println(Arrays.toString(lines));
        for (String line : lines) {
            System.out.println(indent + line);
        }
    }

    //checks if input = "keyword" || input = "keyword <body>"
    public static boolean hasKeywordAndBody(String input, String keyword) {
        return input.matches(String.format("%s\\s\\S+.*",keyword));
    }

    public static String defaultReplyToInvalidInput() {
        return "Invalid input o(>~<)O!\n" +
                "Checkout the available commands by typing them in the cmdline!\n"+
                "Available commands: [todo, deadline, event]";
    }
}

