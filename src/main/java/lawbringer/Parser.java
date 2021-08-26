package lawbringer;

import java.time.format.DateTimeParseException;

public class Parser {

    protected static void parse(String userInput, TaskList taskList, Ui ui) throws LawbringerException {
        String commandType = userInput.split(" ")[0];
        try {
            switch (commandType) {
            case "list":
                taskList.printTaskList();
                break;
            case "done":
                taskList.markTaskAsDone(userInput, ui);
                break;
            case "delete":
                taskList.deleteTask(userInput, ui);
                break;
            case "todo":
                taskList.addTodoTask(userInput, ui);
                break;
            case "deadline":
                taskList.addDeadlineTask(userInput, ui);
                break;
            case "event":
                taskList.addEventTask(userInput, ui);
                break;
            default:
                throw new LawbringerException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DateTimeParseException e) {
            throw new LawbringerException("☹ OOPS!!! You have to enter a valid date in the form "
                    + "yyyy-mm-dd");
        } catch (LawbringerException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
