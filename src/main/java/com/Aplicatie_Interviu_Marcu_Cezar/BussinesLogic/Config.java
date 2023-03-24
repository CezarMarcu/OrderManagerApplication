package com.Aplicatie_Interviu_Marcu_Cezar.BussinesLogic;

import java.io.File;

public enum Config {
    ORDERS("orders"),
    SUPPLIERS("suppliers");

    public final String resultsFolder = "/Results";
    public final String folderName;

    Config(String folderName){
        this.folderName = System.getProperty("user.dir") + resultsFolder + "/" + folderName + "/";
        System.out.println(this.folderName);
        boolean isFile = new File(this.folderName).exists();
        if (!(isFile)){
            boolean isCreated = new File(this.folderName).mkdirs();
            if(isCreated){
                System.out.println("Done");
            }
        }
    }

    @Override
    public String toString() {
        return folderName;
    }
}
