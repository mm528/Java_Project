##Procedures Documentation

* **insert_user** procedure will take eight parameters: isSuperUser, userName,<br> password, forename, surname, email, phoneNum, officeNum.<br>
Follow the syntax below to call the procedure:<br>
```sql
call insert_user(False,'Guyqw','fwefwef','Darren','VissionWard','email@gmail.com',734455345,NULL);
```
Note: The last paramater officeNum can be NULL.<br>
<br>

* **create_event** procedure will take eight parameters: userID, eventName,<br> eventDescription, eventDate, startTime, endTime, location, allDayEvent.<br>
Follow the syntax below to call the procedure:<br>
```sql
call create_event(1,'Fun Games','Some kind of activities','2015-04-28','08:00:00','16:00:00','WQ565',False);
```
<br>

* **delete_event** procedure will take two parameters: eventID, userID.<br>
Follow the syntax below to call the procedure:<br>
```sql
call delete_event(2,1);
```
Note: before deleting an event, procedure will have to delete
<br>records in Reminder, Attendance and Notification tables respectively,
<br>due to the relation constraints.

