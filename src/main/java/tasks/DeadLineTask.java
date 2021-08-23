package tasks;

import java.time.LocalDate;
import java.time.Month;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The DeadlineTask class is a child class for tasks.Task
 * to support different specificities of a task
 * as input by user
 */

public final class DeadLineTask extends Task {
  /**
   * The String to store the type of task information
   * that identifies a Deadline task
   */
  final String type = "[D]";

  /**
   * The String to store the do-by-date information
   * that identifies a Deadline task
   */
  final String dueDate;

  private LocalDate localDate;

  /**
   * Constructor for a Deadline task
   *
   * @param s the input string to describe the Deadline task
   * @param date the do-by-date
   */
  public DeadLineTask(String s, String date) {
    super(s);
    this.dueDate = date;
    String day = checkForDate(date);
    if (!day.equals("")) {
      LocalDate ld = convertDate(day);
      setLocalDate(ld);
    }
  }

  private String checkForDate(String s) {
    String temp = "^[0-9]{1,2}[\\\\/][0-9]{1,2}[\\\\/][0-9]{4}\\s[0-9]{4}$";
    Pattern p = Pattern.compile(temp);
    Matcher m = p.matcher(s);
    if (m.find()) {
      return m.group();
    }
    return "";
  }

  private LocalDate convertDate(String s) {
    String[] date = s.substring(0, s.length() - 4).split("/");
    int day = Integer.parseInt(date[0].replaceAll(" ", ""));
    int month = Integer.parseInt(date[1].replaceAll(" ", ""));
    int year = Integer.parseInt(date[2].replaceAll(" ", ""));
    return LocalDate.of(year, month, day);
  }

  /**
   * To retrieve the information on the type of tasks.Task
   *
   * @return the String description of the type of tasks.Task
   */
  public String getType() {
    return this.type;
  }

  private void setLocalDate(LocalDate localDate) {
    this.localDate = localDate;
  }

  @Override
  public LocalDate getLocalDate() {
    return this.localDate;
  }

  /**
   * To retrieve the description of the Deadline task
   *
   * @return the String that is the description of the Deadline task
   */
  @Override
  public String getTask() {
    if (this.localDate == null) {
      return super.getTask() + " " + "(by: " + this.dueDate + ")";
    } else {
      return super.getTask() + " " + "(by: " + Month.of(this.localDate.getMonthValue()) + " "
              + this.localDate.getDayOfMonth() + " " + this.localDate.getYear() + ")";
    }
  }

  public String getActualTask() {
    return super.getTask();
  }

  @Override
  public String getSaveFormat() {
    if (super.getStatus().equals("[ ]")) {
      return "D" + "|" + this.getActualTask().strip() + "|" + this.dueDate + "|" + 0;
    } else {
      return "D" + "|" + this.getActualTask().strip() + "|" + this.dueDate + "|" + 1;
    }
  }
}
