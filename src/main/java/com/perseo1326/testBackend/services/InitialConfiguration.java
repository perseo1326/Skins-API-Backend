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

//@Component
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

//        ArrayList<Skin> skinsFromFileList = new ArrayList<>();

//        String configFile = projectProperties.skinConfigFile();
//        String configFile = "src/main/resources/skinConfig.json";
//        String skinsCollection;

//        ArrayList<Skin> skinsFromFileList = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.skinsFromFileList = objectMapper.readValue(new File(this.skinConfigFile), new TypeReference<ArrayList<Skin>>() {});

            var x = skinsFromFileList.getClass();
            System.out.println("Caragado archivo JSON!!");
        } catch (IOException exception) {
            System.out.println("No se puede acceder al archivo de configuracion de skins: \"" + this.skinConfigFile + "\"");
            exit(1);
        }
    }

    public ArrayList<Skin> getSkinsFromFileList() {
        return skinsFromFileList;
    }
}
