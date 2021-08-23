package duke;

import java.util.Locale;

public abstract class Task {
    private String description;
    private boolean isDone;
    private String icon; 

    public Task(String description, String icon) {
        this.description = description;
        this.icon = icon; 
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    /**
     * A method that indicates whether the task description contains a keyword.
     * 
     * @param keyword The keyword to be searched for in the description.
     * @return Boolean indicating whether the keyword is found in the description.
     */
    public boolean contains(String keyword) {
        return description.toLowerCase().indexOf(keyword.toLowerCase()) != -1; 
    }
    
    public String toData() {
        String isDoneNum = isDone ? "1" : "0"; 
        return String.format("%s~S~%s~S~%s", icon, isDoneNum, description); 
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }
}
