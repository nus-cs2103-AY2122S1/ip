import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Parser {

    protected static void parse(String userInput, List<Task> tasks, Ui ui) throws LawbringerException {
        String commandType = userInput.split(" ")[0];
        Task task;
        try {
            switch (commandType) {
            case "list":
                int counter = 1;
                for (Task t : tasks) {
                    System.out.println(counter + "." + t.toString());
                    counter++;
                }
                break;
            case "done":
                int doneIndex = Integer.parseInt(userInput.split(" ")[1]);
                tasks.get(doneIndex - 1).markAsDone();
                task = tasks.get(doneIndex - 1);
                ui.showDoneMessage(task);
                break;
            case "delete":
                int deleteIndex = Integer.parseInt(userInput.split(" ")[1]);
                task = tasks.get(deleteIndex - 1);
                tasks.remove(deleteIndex - 1);
                ui.showDeleteMessage(task, tasks);
                break;
            case "todo":
                ToDo todo = new ToDo(userInput.substring(5));
                tasks.add(todo);
                ui.showTodoMessage(todo, tasks);
                break;
            case "deadline":
                int deadlineIndex = userInput.indexOf('/');
                String by = userInput.substring(deadlineIndex + 4);
                LocalDate localDate = LocalDate.parse(by);
                Deadline deadline = new Deadline(userInput.substring(9, deadlineIndex),
                        localDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
                tasks.add(deadline);
                ui.showDeadlineMessage(deadline, tasks);
                break;
            case "event":
                int eventIndex = userInput.indexOf('/');
                String at = userInput.substring(eventIndex + 4);
                Event event = new Event(userInput.substring(6, eventIndex), at);
                tasks.add(event);
                ui.showEventMessage(event, tasks);
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
