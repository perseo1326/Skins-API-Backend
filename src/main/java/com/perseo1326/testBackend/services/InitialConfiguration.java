package com.perseo1326.testBackend.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perseo1326.testBackend.models.Skin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.lang.System.exit;

@Configuration
public class InitialConfiguration {

    private ArrayList<Skin> skinsFromFileList;
    private String skinConfigFile;

    @Autowired
    private final Environment environment;

    public InitialConfiguration(Environment environment) {
        this.environment = environment;
        this.skinConfigFile = environment.getProperty("skinCollectionFile");
        this.loadSkins();
        System.out.println("inicializando clase 'InitialConfiguration'");
    }

    private void loadSkins() {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.skinsFromFileList = objectMapper.readValue(new File(this.skinConfigFile), new TypeReference<ArrayList<Skin>>() {});
            System.out.println("Caragado archivo JSON!!");
        } catch (IOException exception) {
            System.out.println("No se puede acceder al archivo de configuracion de skins: \"" + this.skinConfigFile + "\"");
            System.exit(1);
        }
    }

    public ArrayList<Skin> getCompletSkinsList() {
        return skinsFromFileList;
    }

    public ArrayList<Skin> getOnlyValidSkins(){

        ArrayList<Skin> onlyValidSkinsList = new ArrayList<>();

        for ( Skin skin : this.skinsFromFileList) {
            if(skin.getActive()){
                onlyValidSkinsList.add(skin);
            }
        }
        return onlyValidSkinsList;
    }

}
