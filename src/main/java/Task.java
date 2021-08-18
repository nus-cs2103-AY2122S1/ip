/**
 * Task contains information about a task.
 */
public class Task {
    String value = null;

    public Task(String value){
        this.value = value;
    }

    /**
     * Getting the information of the task.
     * @return Information of the task.
     */
    public String getValue() {
        return value;
    }
}
