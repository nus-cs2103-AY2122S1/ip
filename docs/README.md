# BobbyBot

BobbyBot is a chatbot running on Java that helps to store tasks and contacts. <br>
Given below are instructions on how to use it.

## Starting up the bot

Prerequisites: JDK 11

1. Download the JAR file at `release`
2. Run jar file `java -jar bobbybot.jar`
3. A chatbot as shown below will appear where you can start keying in commands. Add some tasks to start making use of BobbyBot!  
<img width="397" alt="image" src="https://user-images.githubusercontent.com/13284470/133483407-1a21a245-21cf-4083-9d6a-2331e9a7e4d3.png">


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
Format: `deadline DESCRIPTION /by DATETIME`  
:bulb: Note that DATETIME for a deadline has to follow the following format: `dd-mm-yyyy hh:mm`  
Example:
* `deadline cs2103 assignment /by 23-01-2020 11:01` 

### Viewing all tasks: `list`  
Lists out all tasks
Format: `list`  
💡The first square brackets represents the task type [T] for Todo, [E] for Event, [D] for deadline  
💡The second square brackets represents if a task is not done [ ] or done [X]

### Finding tasks with keyword: `find`  
Lists out all tasks that match a keyword
Format: `find KEYWORD`  
<img width="392" alt="image" src="https://user-images.githubusercontent.com/13284470/133481478-f08c1f5e-82da-4fa4-9a64-2c10b958fd10.png">


### Marking a task as done: `done`  
Mark a task as done
Format: `done INDEX`  
* INDEX must be an integer and a valid task number (1,2,3..)

### Deleting a task: `delete`  
Format: `delete INDEX`
* INDEX must be an integer and a valid task number (1,2,3..)

### Shutting down the bot: `bye`  
Format: `bye`

## Contacts Commands

### Adding a contact: `contact`  
Adds a contact with phone number, email and address
Format: `contact NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS`

Examples:
* `contact Elon Musk p/123456789 e/elon@tsla.com a/Moon`
* `contact Doge Man p/98765432 e/doge@coin.com a/Sun`

### Viewing all contacts: `list_contact`  
Format: `list_contact`

### Deleting a contact: `delete_contact`  
Format: `delete_contact INDEX`


## Saving the data
The data that is collected by Bobby is automatically saved! No worries 😄 If you want to view the raw data being loaded, check out the /data directory

## Command Summary  
  
Command | Example
------------ | -------------
todo | `todo grab lunch`
event | `event church meeting /at Sunday 2-4pm`
deadline | format: `deadline DESCRIPTION /by [dd-mm-yyyy hh:mm]`  <br>`deadline DESCRIPTION /bt DATETIME`
list | `list`
find | `find cs2103`
done | `done 1`
delete | `delete 1`
contact | format: contact NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS: <br> `contact Elon Musk p/123456789 e/elon@tsla.com a/Moon`
list_contact | `list_contact`
delete_contact | `delete_contact 1`

