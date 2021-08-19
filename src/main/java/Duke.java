import java.util.Scanner;

import Duke.exception.DukeException;
import Duke.exception.InvalidCommandException;
import Duke.exception.InvalidCommandParameterException;
import Duke.task.TaskList;
import Duke.task.Todo;
import Duke.task.Deadline;
import Duke.task.Event;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList list = new TaskList();

        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

        String input = scanner.nextLine();
        Scanner inputScanner = new Scanner(input);
        String checkForKeyword = inputScanner.next();

        while(!input.equals("bye")){
            try{
                if(input.equals("list")){
                    list.listTasks();
                }else if(checkForKeyword.equals("done")){
                    if(inputScanner.hasNextInt()){
                        int taskNumber = inputScanner.nextInt() - 1;
                        list.markDone(taskNumber);
                    }else{
                        throw new InvalidCommandParameterException();
                    }
                }
                else if(checkForKeyword.equals("todo")){
                    if(inputScanner.hasNextLine()) {
                        String secondWord = inputScanner.nextLine();
                        list.addTask(new Todo(secondWord));
                    }else{
                        throw new InvalidCommandParameterException();
                    }
                }
                else if(checkForKeyword.equals("deadline")){
                    if(inputScanner.hasNextLine()) {
                        String[] contentAndDate = inputScanner.nextLine().split("/by", 2);
                        String content = contentAndDate[0];
                        String date = contentAndDate[1];
                        list.addTask(new Deadline(content, date));
                    }else{
                        throw new InvalidCommandParameterException();
                    }
                }
                else if(checkForKeyword.equals("event")){
                    if(inputScanner.hasNextLine()) {
                        String[] contentAndDate = inputScanner.nextLine().split("/at", 2);
                        String content = contentAndDate[0];
                        String date = contentAndDate[1];
                        list.addTask(new Event(content, date));
                    }else {
                        throw new InvalidCommandParameterException();
                    }
                }
                else{
                    throw new InvalidCommandException();
                }
            } catch(DukeException e) {
                System.out.println("\n" + e.getMessage() + "\n");
            }
            input = scanner.nextLine();
            inputScanner = new Scanner(input);
            checkForKeyword = inputScanner.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
