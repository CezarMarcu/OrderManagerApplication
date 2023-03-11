package com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.RunnableClasses;

import com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic.Services.DirectoryWatcher.DirectoryWatcher;

public class DirectoryWatchRunnable implements Runnable{
    @Override
    public void run() {
        DirectoryWatcher directoryWatcher = new DirectoryWatcher(System.getProperty("user.dir")
                + "/Results/Orders");
        directoryWatcher.myWatchBegins();
    }
}
