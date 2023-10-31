package com.perseo1326.testBackend.services;

import com.perseo1326.testBackend.DTOs.SkinUserDTO;
import com.perseo1326.testBackend.models.Skin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class SkinService {

//    private HashMap<String, Skin> skinsMap;
    private List<Skin> skinsList;

    @Autowired
    private final InitialConfiguration initialConfiguration;

    public SkinService(InitialConfiguration initialConfiguration) {
        this.initialConfiguration = initialConfiguration;
        this.skinsList = initialConfiguration.getSkinsFromFileList();

//        List<Skin> skinsList = initialConfiguration.getSkinsFromFileList();
//        for (Skin skin : skinsList) {
//            this.skinsMap.put(skin.getSkinId(), skin);
//        }
    }



    public List<Skin> getAllAvailableSkins (){


//        for (HashMap.Entry<String, Skin> entry : skinsMap.entrySet()){
//            System.out.println("Clave: " + entry.getKey());
//            System.out.println("Objeto: " + entry.getValue());
//        }

        return skinsList;
    }

    public String buySkin(SkinUserDTO skinUserDTO) {

//        this.readSkinsFromFile();

//        Resource x = loadEmployeesWithClassPathResource();
//        x.toString();
//        Resource resource = new ClassPathResource("classpath:/skinConfig.txt");
//        File file = resource.getFile();

//        System.out.println("FILE: " + file.toString());
        return "Skin Comprada";


    }

    public String getUserSkins(int userId) {
        // neceista identiicar el usuario
        return "Skins del usuario " + userId + ", son: " + "A1, B2, C3";
    }

    public String updateColorSkin(SkinUserDTO skinUserDTO){

        return "Actualizacion de color de skin pendiente!";
    }

    public String deleteSkinFromUser(int userId, String skinId){
        return "La skin No. " + skinId + ", del usuario " + userId + " ha sido eliminada de su cuenta";
    }

    public String getSkinBySkinId (int userId, String skinId){
        return "Skin No. " + skinId + ", User Id: " + userId;
    }


}
