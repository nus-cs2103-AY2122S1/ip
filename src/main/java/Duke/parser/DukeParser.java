package Duke.parser;

import Duke.exception.InvalidCommandException;
import Duke.exception.InvalidCommandParameterException;
import Duke.exception.NoSuchTaskException;
import Duke.task.*;
import Duke.UI;

import java.util.Scanner;

public class DukeParser {
    TaskList list;

    public DukeParser(TaskList list){
        this.list = list;
    }

    public void parse(String rawInput) throws InvalidCommandException, InvalidCommandParameterException,NoSuchTaskException{
        Scanner inputScanner = new Scanner(rawInput);
        String checkForKeyword = inputScanner.next();
        //...process
        switch(checkForKeyword) {
            case "list":
                handleList(inputScanner);
                break;
            case "done":
                handleDone(inputScanner);
                break;
            case "delete":
                handleDelete(inputScanner);
                break;
            case "todo":
                handleTodo(inputScanner);
                break;
            case "deadline":
                handleDeadline(inputScanner);
                break;
            case "event":
                handleEvent(inputScanner);
                break;
            default:
                throw new InvalidCommandException();
        }
    }

    //execute the action OR return (actionType, content, date(for deadline,event));
    public void handleList(Scanner inputScanner) throws InvalidCommandParameterException{
        if(inputScanner.hasNext()){
            throw new InvalidCommandParameterException();
        }else{
            UI.printStorageList(list);
        }
    }

    public void handleDone(Scanner inputScanner) throws InvalidCommandParameterException, NoSuchTaskException {
        if(inputScanner.hasNextInt()){
            int taskPos = inputScanner.nextInt() - 1;
            list.markDone(taskPos);
            UI.printTaskDone(list.getTask(taskPos + 1));
        }else{
            throw new InvalidCommandParameterException();
        }
    }

    public void handleDelete(Scanner inputScanner) throws InvalidCommandParameterException,NoSuchTaskException {
        if(inputScanner.hasNextInt()){
            int taskPos = inputScanner.nextInt() - 1;
            Task temp = list.deleteTask(taskPos);
            UI.printTaskDeleted(temp, list);
        }else{
            throw new InvalidCommandParameterException();
        }
    }

    public void handleTodo(Scanner inputScanner) throws InvalidCommandParameterException {
        if(inputScanner.hasNextLine()) {
            String secondWord = inputScanner.nextLine();
            list.addTask(new Todo(secondWord));
            UI.printTaskAdded(list);
        }else{
            throw new InvalidCommandParameterException();
        }
    }

    public void handleDeadline(Scanner inputScanner) throws InvalidCommandParameterException {
        if(inputScanner.hasNextLine()) {
            String[] contentAndDate = inputScanner.nextLine().split("/by", 2);
            String content = contentAndDate[0];
            String date = contentAndDate[1];
            list.addTask(new Deadline(content, date));
            UI.printTaskAdded(list);
        }else{
            throw new InvalidCommandParameterException();
        }
    }

    public void handleEvent(Scanner inputScanner) throws InvalidCommandParameterException {
        if(inputScanner.hasNextLine()) {
            String[] contentAndDate = inputScanner.nextLine().split("/at", 2);
            String content = contentAndDate[0];
            String date = contentAndDate[1];
            list.addTask(new Event(content, date));
            UI.printTaskAdded(list);
        }else {
            throw new InvalidCommandParameterException();
        }
    }

}
