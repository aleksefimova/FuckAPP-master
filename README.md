# FuckAPP-master

2018.05.22 David: The new "Button" button should save the data in a database using room and change the activity to a activity with an overview of all the sessions in the database. At the moment the database is set up, but the MapsActivity.java does not yet connect with the ViewModel: MapsActivity.java Line:89 

2018.05.24 17:30 David: I changed the concept from a database to store the stuff in shared preferences. I added a class to handle the Mapping Session as a proper Object.

2018.05.24 20:30 David: A scetch for task 6 is implemented in the MappingSession Class as ExportSession Method. This method could need some optimaisation:
1. Check if the file already exists and handle that case properly, or add a timestamp to the Filename,
2. Ask for the permission to write on the external storeage, at the Moment it has to be set manualy in settings of the phone,
3. Change to a better Dataformat, like .kml.

The Class has also a Method to save permently to shared preferences to save all the Sessions for the App, and a Constructor to generate an Instance from shared preferences. For better understanding, there are a lot of comments to better understand.

2018.05.25 12:00 David: Task 6 is gernally done, you can export the points by pressing the Button button at the time (just to show how it works) and then saves the session, with the date as default name, at the Downloads folder of the device. I also fixed the problem that practically there existed no markers ArrayList. 

Doing that I also implemented a few changes to the DataTypes of all this Stuff: The single Points (/markers) are now of the Type MappingPoint and replace the MarkerOption. In the corresponding class you can see that a MappingPoint Object contains the coordinates and a title and can be extendet with attributes for your metadata as I symbolically indicated there. It also has a method to convert it to a MarkerOptions Object. The advantage of that is that it is way better structured, we don't have dozens of keys that are not used and the exportet file is way cleaner and it can also handle the metadata and follwos the idea of object oriented programming. The former ArrayList is now represented as an MappingSession Object that contains the ArrayList of the now MappinPoints(former MarkerOptions). Beside that it contains a Title for the Session and allows several Methods to work with the data, like for example an exportSession() Method to export it to a txt file. For further investigation check the code of the corresponding class.

Doing that I figured out that there is another small problem with the implementation: When creating the MappingPoint/MarkersOptions "userInput.getText()" is used to get the title. Since at this point in the code the user has made no input the userInput is empty, giving the MappingPoint/MarkersOption no title. So all this has to be ordered differently so that the point gets the title from the userInput after the input is made. I haven't changed that yet.
