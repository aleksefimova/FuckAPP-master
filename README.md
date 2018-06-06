# FuckAPP-master

2018.05.22 David: The new "Button" button should save the data in a database using room and change the activity to a activity with an overview of all the sessions in the database. At the moment the database is set up, but the MapsActivity.java does not yet connect with the ViewModel: MapsActivity.java Line:89 

2018.05.24 17:30 David: I changed the concept from a database to store the stuff in shared preferences. I added a class to handle the Mapping Session as a proper Object.

2018.05.24 20:30 David: A scetch for task 6 is implemented in the MappingSession Class as ExportSession Method. This method could need some optimaisation:
1. Check if the file already exists and handle that case properly, or add a timestamp to the Filename,
2. Ask for the permission to write on the external storeage, at the Moment it has to be set manualy in settings of the phone,
3. Change to a better Dataformat, like .kml.

The Class has also a Method to save permently to shared preferences to save all the Sessions for the App, and a Constructor to generate an Instance from shared preferences. For better understanding, there are a lot of comments to better understand.

2018.05.25 12:00 David: Task 6 is gernally done, you can export the points by pressing the Button button at the time (just to show how it works) and then saves the session, with the date as default name, at the Downloads folder of the device. (Do not forget to give manual permission for storage on the phone if you want to try it out.) I also fixed the problem that practically there existed no markers ArrayList. 

Doing that I also implemented a few changes to the DataTypes of all this Stuff: The single Points (/markers) are now of the Type MappingPoint and replace the MarkerOption. In the corresponding class you can see that a MappingPoint Object contains the coordinates and a title and can be extendet with attributes for your metadata as I symbolically indicated there. It also has a method to convert it to a MarkerOptions Object. The advantage of that is that it is way better structured, we don't have dozens of keys that are not used and the exportet file is way cleaner and it can also handle the metadata and follwos the idea of object oriented programming. The former ArrayList is now represented as an MappingSession Object that contains the ArrayList of the now MappinPoints(former MarkerOptions). Beside that it contains a Title for the Session and allows several Methods to work with the data, like for example an exportSession() Method to export it to a txt file, or addPoint(MappingPoint) to add a Point to the Session. The class is also prepeard for Task 5, with SaveSession() it saves the Session permanently in shared preferences and with a also implementet Constructor a MappingSession can be created from shared preferences. For further investigation check the code of the corresponding class.

Doing that I figured out that there is another small problem with the implementation: When creating the MappingPoint/MarkersOptions "userInput.getText()" is used to get the title. Since at this point in the code the user has made no input the userInput is empty, giving the MappingPoint/MarkersOption no title. So all this has to be ordered differently so that the point gets the title from the userInput after the input is made. I haven't changed that yet.

2018.05.25 23:00 David: The exported file has now the Sattelites and a LocatingMode (which is always "manual" =D). And it asks for permission to Storage now. I also fixed the bug with the markers that they appear no matter if you click "ok" or "cancel" in the dialog box. Now they appear when you click on the map, but dissapear again when you click the cancel button.

2018.05.26 01:04 David: When exporting the file it now asks for an optional new title for the Session if the title is the default title. The same is implementet for SaveSession(), what brings us closer to fullfilling Task 5b. See the major changes in MappingSession.java

2018.05.26 David: Sessions get saved autmatically now, no matter if you rotate, close the app or smash the phone on the desk!! (Okay, I am not sure about the last thing...) From this step it is not far to 5b. Generally it works like that: There is a Dictionary file "SessionDictionary" where the names of all Sessions are saved, in a list, so that we know what we have. Than one of this names can be selected and the corresponding session can be loaded, like it is done in the onStart() method of the MapsActivity and at the very end of the onMapReady() method where the markers are added. Than the wanted changes to the Session can be made and after that the Session with a corresponding entry in the Dictionary can be saved like it is done in the onStop() method of the Maps Activity. From this points it should be able to follow the code with the coments. For 5b only this already existing Dictionary list needs to be displayed somewhere with the possibility to tap on the entries of that list and than this session can be loaded like it is done in onStart and onMapReady. Then there only needs to be a back button which saves the file and navigates back to the Dictionary List window/dialog/or whatever. This is done like in onStop() but instead of MappingSession.save() for this case MappingSession.saveSession() is an already implementet method of the Session object which does the same like save() but asks for a name for the Session if it has the default name (date), the same like what happens if the "export button" is clicked.

2018.06.04 David: All should be done, in an at least "just for security"-way. But I guess there will come a better version of Sandro. This GitHub Version won't work anymore on every computer because it has that local properties file on it, I don't know how to delete stuff here on GitHub. But my last changes should all be just in the app folder anyways.

2018.06.06 10:00 Daivd: Fixed the three remaining bugs. 
1. That a new session/renamed session was not imediately shown after going back to the MainActivity from MapsActivity
2. That after renaming a session it kept the session with the old name also in the storage and list of sessions in the MainActivity
3. That the Markers dissapered when working on a new, never saved session after rotating the view.

Now the functionality should be on an acceptable level. Regardless, on my opinion we should optimize a few other things as well bevore testing, like the UI and for example where the Map focuses when starting. These are small changes with big effect.
