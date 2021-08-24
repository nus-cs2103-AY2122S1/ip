package commands;

import bot.Bot;
import bot.Ui;

public class EmptyCommand extends Command {

  @Override
  public void run(Bot bot, String[] args) {
    Ui.print(new String[]{
      Ui.ERROR_SIGNATURE + "I'm sorry, but I don't know what that means :-("
    });
  }
  
}
