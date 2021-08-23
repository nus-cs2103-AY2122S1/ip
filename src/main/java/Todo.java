import java.util.Arrays;
import java.util.List;

public class Todo extends Task {
    private static final String TASK_TYPE = "T";
    
    /**
     * Constructor of the Todo class
     *
     * @param description description of this todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of this todo
     *
     * @return string representation of this todo
     */
    @Override
    public String toString() {
        return super.toString();
    }
    
    @Override
    protected String toSavableFormat() {
        String isDone = Parser.parseIsDoneToString(this.isDone());
        List<String> stringList = Arrays.asList(TASK_TYPE, isDone, this.getDescription());
        return String.join(Duke.DELIMITER, stringList);
    }
    
    @Override
    protected String getTaskType() {
        return TASK_TYPE;
    }
}