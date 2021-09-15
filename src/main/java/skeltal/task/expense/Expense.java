package skeltal.task.expense;

import skeltal.Parser;
import skeltal.SkeltalException;
import skeltal.task.Task;

public class Expense extends Task {
    private int amount;

    public Expense(String description) throws SkeltalException {
        super(Parser.parseMoney(description)[0]);
        this.amount = parseAmount(Parser.parseMoney(description));
    }

    private static int parseAmount(String[] description) throws SkeltalException {
        String amountString = description[1];
        if (description.length == 1 || !isInteger(amountString)) {
            throw new SkeltalException("Error! You must input a number!");
        }
        int amount = Integer.parseInt(amountString);
        return amount;
    }

    public static boolean isInteger(String string) {
        try {
            Integer.valueOf(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int getAmount() {
        return this.amount;
    }

    @Override
    public String toString() {
        int num = ExpenseList.getIndex(this) + 1;
        return "[" + num + "] $" + this.amount + " -> " + this.getTaskDescription();
    }
}
