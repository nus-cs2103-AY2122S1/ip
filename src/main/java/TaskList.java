import java.util.LinkedList;
import java.util.List;

public class TaskList {
    public List<Task> tasks;

    public TaskList() {
        this.tasks = new LinkedList<>();
    }

    protected Chatbot.ChatContinue addTask(Chatbot.TaskCommands command, String input, Ui ui, FileDB fileDB) throws DukeIOException, DukeDateParseException {
        switch (command) {
            case DONE:
                return this.markDone(input, ui);
            case DELETE:
                return this.delete(input, ui);
            case TODO:
                return this.addTodo(input, fileDB, ui);
            case DEADLINE:
                return this.addDeadline(input, fileDB, ui);
            case EVENT:
                return this.addEvent(input, fileDB, ui);
            default:
                ui.showNotSupported();
                return Chatbot.ChatContinue.CONTINUE;
        }
    }

    private Chatbot.ChatContinue addTodo(String input, FileDB fileDB, Ui ui) throws DukeIOException{
        ToDo todo = new ToDo(input);
        tasks.add(todo);
        fileDB.save(todo);
        ui.showAddTaskSuccessful(todo);
        return Chatbot.ChatContinue.CONTINUE;
    }

    private Chatbot.ChatContinue addDeadline(String input, FileDB fileDB, Ui ui) throws DukeIOException, DukeDateParseException {
        Deadline deadline = new Deadline(input);
        tasks.add(deadline);
        fileDB.save(deadline);
        ui.showAddTaskSuccessful(deadline);
        return Chatbot.ChatContinue.CONTINUE;
    }

    private Chatbot.ChatContinue addEvent(String input, FileDB fileDB, Ui ui) throws DukeIOException, DukeDateParseException {
        Event event = new Event(input);
        tasks.add(event);
        fileDB.save(event);
        ui.showAddTaskSuccessful(event);
        return Chatbot.ChatContinue.CONTINUE;
    }


    private Chatbot.ChatContinue markDone(String args, Ui ui) {
        try {
            int index = Integer.parseInt(args);
            Task task = tasks.get(index - 1);
            task.markDone();
            ui.showMarked(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeArgumentException("Sorry, that item does not exist!");
        } catch (NumberFormatException e) {
            throw new DukeArgumentException("The index is invalid");
        }
        return Chatbot.ChatContinue.CONTINUE;
    }

    private Chatbot.ChatContinue delete(String args, Ui ui) {
        try {
            int index = Integer.parseInt(args);
            if (index > 0 && index <= tasks.size()) {
                Task task = tasks.get(index - 1);
                tasks.remove(index - 1);
                ui.showDeleted(task);
            } else {
                throw new DukeTaskException("Task does not exist!");
            }
        } catch (NumberFormatException e) {
            throw new DukeTaskException("Task does not exist!");
        }
        return Chatbot.ChatContinue.CONTINUE;
    }

    protected Chatbot.ChatContinue list(Ui ui) {
        if (tasks.size() == 0) {
            ui.showNothingAdded();
        } else {
            ui.showAllTasks(this.tasks);
        }
        return Chatbot.ChatContinue.CONTINUE;
    }
}
