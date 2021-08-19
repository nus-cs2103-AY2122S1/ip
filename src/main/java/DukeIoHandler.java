import java.util.Scanner;
        
public class DukeHandler {
    private final Scanner sc;
    private final TaskList taskList;
    
    public DukeHandler() {
        this.sc = new Scanner(System.in);
        this.taskList = new TaskList();
    }
    
    private String[] parseInput(String input) {
        return input.trim().split("\\s+");
    }
    
    private void dukeCommandController(String[] parsedInput) {
        
    }
}
