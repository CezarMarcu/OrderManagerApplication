package com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.RunnableClasses;

import com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Services.DirectoryWatcher.DirectoryWatcher;

public class DirectoryWatchRunnable implements Runnable{
    @Override
    public void run() {
        String directory = "/Users/marcucezar/Desktop/Aplicatie_Interviu_Marcu_Cezar/Results/Orders";
        DirectoryWatcher directoryWatcher = new DirectoryWatcher(directory);
        directoryWatcher.myWatchBegins();
    }
}
