package skeltal.task.expense;

import java.util.ArrayList;

public class ExpenseList {
    private static ArrayList<Expense> expenses = new ArrayList<>();

    public static String addExpense (Expense money) {
        expenses.add(money);
        String reply = "Got it. I've added this expense\n" +
                money + "\n" +
                "Now you have " + expenses.size() + " expenses in the list.";
        return reply;
    }
    public static String listExpenses() {
        String reply = "Here are the expenses in your list:\n";
        for (Expense e : expenses) {
            reply += e + "\n";
        }
        return reply.trim();
    }
    public static String deleteExpense(String index) {
        String reply = "";
        int i = Integer.parseInt(index) - 1;
        reply += expenses.get(i) + "\n";
        expenses.remove(i);
        reply += "Now you have " + expenses.size() + " expenses in the list.";
        return reply;
    }

    public static int getIndex(Expense expense) {
        return expenses.indexOf(expense);
    }

    public static String sum() {
        int total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        return "Your total spending is: $" + total;
    }
}
