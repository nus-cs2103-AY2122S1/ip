package logic;

import model.Command;

import java.time.LocalDateTime;

/**
 * Data class for the Command Arguments.
 * By default, if the arguments are null and not optional, because from the documentation,
 * Optional is used when the results from libraries are really optional.
 */
public class CommandArgument {
    // 0-indexing assumed here
    private Integer index = null;
    private String description = null;
    private LocalDateTime timing = null;
    private Command command = null;
    
    /**
     * Gets Index and by convention 0-indexed.
     *
     * @return Integer.
     */
    public Integer getIndex() {
        return index;
    }
    
    /**
     * Sets Index / Integer data.
     *
     * @param index Integer.
     */
    public void setIndex(Integer index) {
        this.index = index;
    }
    
    /**
     * Gets the command
     *
     * @return Command
     */
    public Command getCommand() {
        return command;
    }
    
    /**
     * Sets the command
     */
    public void setCommand(Command command) {
        this.command = command;
    }
    
    /**
     * Gets String Description.
     *
     * @return String.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Sets Description.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Gets LocalDateTime, which in the current implementation is in the dd/MM/yyyy format.
     */
    public LocalDateTime getTiming() {
        return timing;
    }
    
    /**
     * Sets the LocalDateTime.
     *
     * @param timing LocalDateTime.
     */
    public void setTiming(LocalDateTime timing) {
        this.timing = timing;
    }
}
