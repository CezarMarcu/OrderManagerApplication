package com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Services.DirectoryWatcher;

import com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Services.OrderProcessor.OrderProcessor;
import com.Aplicatie_Interviu_Marcu_Cezar.Config.Config;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/*
------------------------------------------------------------------------------------------------------------------------
                                                DOCUMENTATION
------------------------------------------------------------------------------------------------------------------------
    This service is used to watch the folder where orders from client are received.
    To implement this service the Watch API, provided by java.nio package, was used.

    STEPS:
        1 Create a watch service
        2 Get the path to the folder that will be watched
        3 Register the folder into the monitor services
            The folder will be registered for 2 events:CREATE and DELETE
            After the folder is registered, a watch key will be returned
            With this watch key I can poll any changes that will be made on the directory
        4 Poll the events into an infinite loop
            4.1 Get file name from even context
            4.2 Check for the event kind: create event or delete event
            4.3 Do an action depending on the event kind
            4.4 Reset the watch key

        The usage of this service would block the main thread that means that you can’t do anything else
    while polling for file changes in your directory.
        In order to solve this problem an asynchronous running was necessary.
        The "watchDirectory()" method doesn't return any value, so I'll create a class which implements the "Runnable"
    interface. After the implementation of the Runnable Class, this one will execute on a separated thread.
* */

public class DirectoryWatcher {

    //ATTRIBUTE
    private final String directoryPath;
    public static boolean running = false;


    //CONSTRUCTOR
    public DirectoryWatcher(Config directoryPath){
        this.directoryPath = directoryPath.toString();
    }


    //METHODS
    private void watchDirectory(){
        running = true;
        try{
            //STEP 1
            WatchService watchService = FileSystems.getDefault().newWatchService();
            //STEP 2
            Path directory = Path.of(this.directoryPath);
            //STEP 3
            WatchKey watchKey = directory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            //STEP 4
            while (running) {
                //STEP 4.1
                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    //STEP 4.2
                    WatchEvent<Path> pathEvent = (WatchEvent<Path>) event;
                    Path fileName = pathEvent.context();
                    //STEP 4.3
                    WatchEvent.Kind<?> kind = event.kind();
                    //STEP 4.4
                    if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                       OrderProcessor.ProcessOrders(new File(directoryPath + "/" + fileName));
                    }
                    //STEP 4.5
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
