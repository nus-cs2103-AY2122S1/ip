package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Command class will execute the different commands input from the user
 */
public class Command {
    /**
     * executes different commands from user and save tasks into a file
     * @param tasks takes in TaskList object containing the list of tasks
     * @param ui takes in Ui object containing the list of message to user
     * @param save Storage object to store the TaskList
     */
    public void execute(TaskList tasks, Ui ui, Storage save) {
        ui.Start();
        boolean hasExit = false;
        Scanner sc = new Scanner(System.in);
        String filePath = "data/duke.txt";
        int count = tasks.size();

        while (!hasExit) {
            String command = Parser.parseCommand(sc.next());
            switch(command){
            case "bye":
                ui.Bye();
                hasExit = true;
                break;
            case "list":
                String list = "";
                int listNum = 1;
                for (int i = 0; i < tasks.size(); i++){
                    list += listNum + "." + tasks.get(i) + "\n";
                    listNum++;
                }
                ui.list(list);
                break;
            case "done":
                int doneNum = sc.nextInt() - 1;
                try {
                    tasks.get(doneNum).markAsDone();
                    ui.Done(tasks,doneNum);
                    break;
                } catch (IndexOutOfBoundsException e) {
                    ui.showDoneError();
                    break;
                }
            case "todo":
                try {
                    String todoDescription = sc.nextLine().trim();
                    Task todo = new Todo(todoDescription, count);
                    if(todoDescription.isEmpty()){
                        throw new DukeException(ui.emptyDescriptionError());
                    }
                    tasks.add(todo);
                    count++;
                    ui.todo(todo,count);
                    break;
                } catch (DukeException e){
                    System.out.println(e.getMessage());
                    break;
                }
            case "delete":
                int delNum = sc.nextInt()-1;
                try {
                    Task delete = tasks.get(delNum);
                    count--;
                    ui.Delete(delete,count);
                    tasks.remove(delNum);
                    break;
                } catch (IndexOutOfBoundsException e){
                    ui.showDeleteError();
                    break;
                }
            case "deadline":
                try {
                    String[] deadlineArr = sc.nextLine().split("/by");
                    if(deadlineArr[0].strip().isEmpty()){
                        throw new DukeException(ui.emptyDescriptionError());
                    }
                    LocalDate d1 = LocalDate.parse(deadlineArr[1].trim());
                    Task deadline = new Deadline(deadlineArr[0].trim(),
                            d1.format(DateTimeFormatter.ofPattern("MMM dd YYYY")), count);
                    tasks.add(deadline);
                    count++;
                    ui.deadline(deadline,count);
                    break;
                } catch (DateTimeParseException e){
                    ui.showDeadlineError1();
                    break;
                } catch (DukeException e){
                    System.out.println(e.getMessage());
                    break;
                }
            case "event":
                try {
                    String[] eventArr = sc.nextLine().split("/at");
                    if(eventArr[0].strip().isEmpty()){
                        throw new DukeException(ui.emptyDescriptionError());
                    }
                    Task event = new Event(eventArr[0].trim(),eventArr[1].trim(), count);
                    tasks.add(event);
                    count++;
                    ui.event(event,count);
                    break;
                } catch (DukeException e){
                    System.out.println(e.getMessage());
                    break;
                }
            case "find":
                try {
                    String keyword = sc.nextLine().trim();
                    String findList = "\n";
                    if(keyword.strip().isEmpty()){
                        throw new DukeException(ui.emptyDescriptionError());
                    }
                    for (int i = 0; i < tasks.size(); i++){
                        if (tasks.get(i).getDescription().contains(keyword)){
                            findList += tasks.get(i) + "\n";
                        }
                    }
                    ui.find(findList);
                    break;
                } catch (DukeException e){
                    System.out.println(e.getMessage());
                    break;
                }
            default:
                ui.defaultError();
            }
            save.writeToFile(filePath,tasks);
        }
    }
}
