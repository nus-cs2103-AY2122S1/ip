package logic;

import java.time.LocalDateTime;

public class CommandArgument {
    // 0-indexing assumed here
    private Integer index = null;
    private String description = null;
    private LocalDateTime timing = null;
    
    public Integer getIndex() {
        return index;
    }
    
    public void setIndex(Integer index) {
        this.index = index;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalDateTime getTiming() {
        return timing;
    }
    
    public void setTiming(LocalDateTime timing) {
        this.timing = timing;
    }
}
