package com.Aplicatie_Interviu_Marcu_Cezar.Config;

import java.io.File;

public enum Config {

    ORDERS("orders"),
    SUPPLIERS("suppliers");

    public final String TargetFolder;
    private final String resultsFolder = "/Results";


    Config(String targetFolder) {
        String root = System.getProperty("user.dir") + resultsFolder;
        TargetFolder = root +"/"+ targetFolder +"/";
        File theDirs = new File(String.valueOf(TargetFolder));
        if(!(theDirs.exists())){
            boolean isSuccessful = theDirs.mkdirs();
            if(isSuccessful){
                System.out.println("Done");
            }
        }
    }
    @Override
    public String toString() {
        return  TargetFolder;
    }
}
