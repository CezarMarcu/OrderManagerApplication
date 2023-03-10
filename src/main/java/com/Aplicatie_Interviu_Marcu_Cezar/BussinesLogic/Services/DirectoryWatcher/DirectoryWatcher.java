package com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Services.DirectoryWatcher;

//IMPORTS
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/*
    DOCUMENTATION:
    ---------------------------------------------------------------------------------
    This service is used to watch the folder where orders from client are received.
    To implement this service the Watch API, provided by java.nio package, was used.
    The usage of this service would block the main thread that means that you can’t do anything else in your code
    while polling for file changes in your directory.

    In order to solve this problem an asynchronous running was necessary.
    The "watchDirectory()" method doesn't return any value, so I'll create a class which implements the "Runnable"
    interface. After the implementation of the Runnable Class, this one will execute on a separated thread.
* */
@SuppressWarnings("InfiniteLoopStatement")
public class DirectoryWatcher {

    //FIELDS
    private final String directoryPath;

    //CONSTRUCTOR
    public DirectoryWatcher(String directoryPath){
        this.directoryPath = directoryPath;
    }

    //METHODS

    //The method that watches the CREATE and DELETE events
    private void watchDirectory(){
        try{
            //Create a watch service
            WatchService watchService = FileSystems.getDefault().newWatchService();

            //Get the path to the folder that will be watched
            Path directory = Path.of(this.directoryPath);

            //Register the folder into the monitor services
            //The folder will be registered for 2 events:CREATE and DELETE
            //After the folder is registered, a watch key will be returned
            //With this watch key I can poll any changes that will be made on the directory
            WatchKey watchKey = directory.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE);

            //Poll the events into an infinite loop
            while (true) {
                //Pooling logic
                for (WatchEvent<?> event : watchKey.pollEvents()) {

                    //Get file name from even context
                    WatchEvent<Path> pathEvent = (WatchEvent<Path>) event;
                    Path fileName = pathEvent.context();

                    //Check for the event kind: create event or delete event
                    WatchEvent.Kind<?> kind = event.kind();

                    //Do an action depending on the event kind
                    if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                        System.out.println("A new file is created : " + fileName);
                    } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                        System.out.println("A file has been deleted: " + fileName);
                    }
                    //Reset the watch key
                    boolean valid = watchKey.reset();
                    if (!valid) {
                        break;
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void myWatchBegins(){
        this.watchDirectory();
    }
}
