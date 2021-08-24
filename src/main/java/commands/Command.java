package commands;

import bot.Bot;

public abstract class Command {

  String[] args;

  public void setArgs(String[] args) {
    this.args = args;
  };

  public String[] getArgs() {
    return args;
  }

  /**
   * Run the command
   * 
   * @param bot the bot that invokes the command
   * @param args list of argument strings
   */
  public abstract void run(Bot bot, String[] args);

}
