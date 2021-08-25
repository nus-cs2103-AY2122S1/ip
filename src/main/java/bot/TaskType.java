package bot;

public enum TaskType {

    Deadline("D"), Event("E"), Todo("T"), Blank("B");

    private final String symbol;

    TaskType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return this.symbol;
    }

}
