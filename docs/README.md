# User Guide

Use Duke to help you manage your busy lifestyle! 

Add your ToDos☑️, Deadlines⏲️ and Events🎆 to Duke now!

## Features 

### Autosave

All the tasks that you add will be saved straight to your local database💽!

## Usage

### `todo` - Add a ToDo☑️ task to the storage

Adds a ToDo☑️ task with a _description_!

Example of usage: 

`todo CS2103T quiz`

Expected output: 

![image](https://user-images.githubusercontent.com/77244987/133315811-c83aedfc-27f5-458c-adf7-4a5056c0f1e6.png)

-----
### `deadline` - Add a Deadline⏲️ task to the storage

Creates a Deadline⏲️ task with a _description_ and 

1. _date_ with format: `yyyy-mm-dd`
   
   Example of usage:
   
   `deadline 2103T IP /by 2021-09-18`
   
   Expected output:
   
   ![image](https://user-images.githubusercontent.com/77244987/133316214-1d206e6c-08f5-48ff-844f-02582d0d17a9.png)

   ***OR***

2. _date and time_ with format: `yyyy-mm-dd HH:mm`

   Example of usage:
   
   `deadline 2103T IP /by 2021-09-18 23:59`

   Expected output: 
   
   ![image](https://user-images.githubusercontent.com/77244987/133316265-d79f27cc-d156-4321-9e12-c70886b6f471.png)

-----
### `event` - Add a Event🎆 task to the storage

Creates a Event🎆 task with a _description_ and 

1. _date_ with format: `yyyy-mm-dd`
   
   Example of usage:
   
   `event monkey eat banana /at 2021-09-20`

   Expected output:

   ![image](https://user-images.githubusercontent.com/77244987/133316361-9757f9bf-3f68-4cf8-9d06-4092bcdc69ca.png)

   
   ***OR***

2. _date and time_ with format: `yyyy-mm-dd HH:mm`

   Example of usage:
   
   `event monkey eat banana /at 2021-09-20 12:30`

   Expected output: 
   
   ![image](https://user-images.githubusercontent.com/77244987/133316418-3ed32d18-dc5c-4c5f-8073-a591479ff467.png)

-----
### `list` - List all tasks💽

Lists all task currently in the storage💽

Example of usage: 

`list`

Expected output:

![image](https://user-images.githubusercontent.com/77244987/133316494-22e1fe7b-1fea-47cc-a991-402e8e729744.png)

-----
### `done` - Mark a task as done✔️

Marks a task at a given _index_ as done✔️

Example of usage: 

`done 1`

Expected output:

![image](https://user-images.githubusercontent.com/77244987/133316890-b953ae40-0711-4607-9d80-b85f669b475e.png)

-----
### `delete` - Delete a task from storage⚔️

Deletes a tasks at a given _index_ from storage⚔️

Example of usage: 

`delete 1`

Expected output:

![image](https://user-images.githubusercontent.com/77244987/133317274-b0cb372e-e4a3-4fe3-aea9-c6457441886e.png)

-----
### `find` - Find a task from storage🔍

Finds a task from storage that matches the _search string_🔍

Example of usage: 

`find 2103`

Expected output:

![image](https://user-images.githubusercontent.com/77244987/133317663-6bb4d4e9-7c84-4040-a804-616cb686d3e2.png)

-----
### `reschedule` - Change the date and/or time of a task📆

Changes the date and/or time of a task at a given _index_ in storage with a new _date and/or time_📆

Example of usage:

`reschedule 1 /to 2022-09-19 15:40`

Expected output:

![image](https://user-images.githubusercontent.com/77244987/133318370-52a4f8af-90ee-453f-b02c-2d5660915ea2.png)

-----
### `update` - Change the description of a task✍️

Change the description of a task at a given _index_ in storage with a new _description_✍️

Example of usage:

`update 1 /to monke monke`

Expected output:

![image](https://user-images.githubusercontent.com/77244987/133319532-b312e6d1-c618-4d36-88a3-8e89f3247f33.png)

-----
