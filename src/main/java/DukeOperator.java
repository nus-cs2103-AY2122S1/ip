public class DukeOperator {
    
    private final String userInput;
    private final TaskStorage storage;

    public DukeOperator(String userInput, TaskStorage storage) {
        this.userInput = userInput;
        this.storage = storage;
    }

    public final boolean operate() throws DukeException {
        DukeInputProcessor operationChecker = new DukeInputProcessor(this.userInput);
        Operation operation = operationChecker.checkOperation();
        switch(operation) {
            case bye:
                bye();
                return false;
            case list:
                list();
                break;
            case done:
                done();
                break;
            case delete:
                delete();
                break;
            case todo: 
                todo();
                break;
            case event:
                event();
                break;
            case deadline:
                deadline();
                break;
        }
        return true;
    }

    private void bye() {
        System.out.println(
            StringFormat.formatString("Bye. Hope to see you again soon!")
        );
    }

    private void list() {
        System.out.println(
            StringFormat.tabAndFormat(this.storage.toString())
        );
    }

    private void done() {
        String[] inputs = this.userInput.split(" ");
        int ind = Integer.valueOf(inputs[1]) - 1;
        System.out.println(
            StringFormat.tabAndFormat(storage.markDone(ind))
        );
    }

    private void delete() {
        String[] inputs = this.userInput.split(" ");
        int ind = Integer.valueOf(inputs[1]) - 1;
        System.out.println(
            StringFormat.tabAndFormat(storage.delete(ind))
        );
    }

    private void todo() {
        String[] inputs = this.userInput.split(" ", 2);
        System.out.println(
            StringFormat.tabAndFormat(
                storage.add(new Todo(inputs[1]))
            )
        );  
    }

    private void event() {
        String[] inputs = this.userInput.split(" ", 2);
        String[] args = inputs[1].split(" /at ", 2);
        System.out.println(
            StringFormat.tabAndFormat(
                storage.add(new Event(args[0], args[1]))
            )
        );   
    }

    private void deadline() {
        String[] inputs = this.userInput.split(" ", 2);
        String[] args = inputs[1].split(" /by ", 2);
        System.out.println(
            StringFormat.tabAndFormat(
                storage.add(new Deadline(args[0], args[1]))
            )
        ); 
    }

}
