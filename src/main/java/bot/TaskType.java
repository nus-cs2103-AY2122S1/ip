package bot;

/**
 * Types of Tasks
 */
public enum TaskType {

    Deadline("D"), Event("E"), Todo("T"), Blank("B");

    private final String symbol;

    TaskType(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Get the TaskType's single character symbol
     *
     * @return TaskType's symbol
     */
    public String getSymbol() {
        return this.symbol;
    }

}
