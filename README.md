# BobbyBot

BobbyBot is a chatbot running on Java that helps to store tasks and contacts. <br>
Given below are instructions on how to use it.

## Starting up the bot

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Download the JAR file at `release`
2. Run jar file `java -jar bobbybot.jar`

## Task Commands


### Adding a todo: `todo`  
Adds a todo to the todo list  
Format: `todo DESCRIPTION`   
Example:
* `todo grab lunch`

### Adding an event: `event`  
Adds a event with a string description of time  
Format: `event DESCRIPTION /at TIME`   
Example:
* `event church meeting /at Sunday 2-4pm`
* `event football match /at 23 Sep`

### Adding a deadline: `deadline`  
Adds a deadline with a datetime argument
Format: `deadline DESCRIPTION /bt DATETIME`  
:bulb: Note that DATETIME for a deadline has to follow the following format: `dd-mm-yyyy hh:mm`  
Example:
* `deadline cs2103 assignment /by 23-01-2020 11:01` 

### Viewing all tasks: `list`  
Lists out all tasks
Format: `list`  

### Finding tasks with keyword: `find`  
Lists out all tasks that match a keyword
Format: `find KEYWORD`

### Finding tasks with keyword: `done`  
Mark a list as done
Format: `done INDEX`

### Deleting a task: `delete`  
Format: `delete INDEX`

### Shutting down the bot: `bye`  
Format: `delete INDEX`

## Contacts Commands

### Adding a contact: `contact`  
Adds a contact with phone number, email and address
Format: `contact NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS`

Examples:
* `contact Elon Musk p/123456789 e/elon@tsla.com a/Moon`
* `contact Doge Man p/98765432 e/doge@coin.com a/Sun`

### Viewing all contacts: `delete`  
Format: `list_contact`

### Deleting a contact: `delete`  
Format: `delete_contact INDEX`

