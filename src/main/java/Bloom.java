import bloom.app.Storage;
import bloom.app.TaskList;
import bloom.app.Ui;

public class Bloom {
    
    private Storage storage = new Storage();
    
    private TaskList tasks = new TaskList();
    
    private Ui ui = new Ui();
    
    public static void main(String[] args) {
        new Bloom().ui.start();
    }
}
