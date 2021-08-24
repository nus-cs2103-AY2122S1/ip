package bot;

public enum TaskType {

  Deadline("D"), Event("E"), Todo("T"), Blank("B");

  private String symbol;

  private TaskType(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return this.symbol;
  }

}
