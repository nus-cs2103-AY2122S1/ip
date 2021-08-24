import java.util.ArrayList;

public class TaskList {
    Ui ui = new Ui();
    
    void list(ArrayList<Task> commands){
        try {
            if (commands.size() == 0) {
                throw new EmptyTaskListException();
            } else {
                ui.listTasksOutput(commands);
            }
        } catch (DukeException e){
            System.out.println(e.getMessage());
        }

    }

    void remove(int listNumber, ArrayList<Task> commands){
        try {
            if (listNumber < commands.size()) {
                ui.removeOutput(commands.get(listNumber), commands.size()-1);
                commands.remove(listNumber);
            } else {
                throw new InvalidTaskException();
            }
        } catch (DukeException e){
            System.out.println(e.getMessage());
        }
    }


}
