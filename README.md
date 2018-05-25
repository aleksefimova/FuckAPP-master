# FuckAPP-master

2018.05.22 David: The new "Button" button should save the data in a database using room and change the activity to a activity with an overview of all the sessions in the database. At the moment the database is set up, but the MapsActivity.java does not yet connect with the ViewModel: MapsActivity.java Line:89 

2018.05.24 17:30 David: I changed the concept from a database to store the stuff in shared preferences. I added a class to handle the Mapping Session as a proper Object.

2018.05.24 20:30 David: A scetch for task 6 is implemented in the MappingSession Class as ExportSession Method. This method could need some optimaisation:
1. Check if the file already exists and handle that case properly, or add a timestamp to the Filename,
2. Ask for the permission to write on the external storeage, at the Moment it has to be set manualy in Settings of the phone,
3. Change to a better Dataformat, like .kml.

The Class has also a Method to save permently to shared preferences to save all the Sessions for the App, and a Constructor to generate an Instance from shared preferences. For better understanding, there are a lot of comments to better understand.
