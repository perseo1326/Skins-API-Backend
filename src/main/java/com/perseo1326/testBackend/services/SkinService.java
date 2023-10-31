package com.perseo1326.testBackend.services;

import com.perseo1326.testBackend.DTOs.SkinUserDTO;
import com.perseo1326.testBackend.models.Skin;
import com.perseo1326.testBackend.models.SkinUser;
import com.perseo1326.testBackend.repositories.SkinUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class SkinService {

    private ArrayList<Skin> skinsList;

    @Autowired
    private final InitialConfiguration initialConfiguration;

    @Autowired
    private final SkinUserRepository skinUserRepository;

    public SkinService(InitialConfiguration initialConfiguration, SkinUserRepository skinUserRepository) {
        this.initialConfiguration = initialConfiguration;
        this.skinUserRepository = skinUserRepository;
        this.loadSkins();
    }
    
    private void loadSkins(){
        this.skinsList = initialConfiguration.getCompletSkinsList();

        for (int i = 0; i < this.skinsList.size(); i++){
            if(!this.skinsList.get(i).getActive()){
                this.skinsList.remove(i);
            }
        }
    }

    /** filter only "ACTIVE" skins **/
    public List<Skin> getAllAvailableSkins (){

        return this.skinsList;
    }

    public String buySkin(SkinUserDTO skinUserDTO) {


        return "Skin Comprada";
    }

    public List<SkinUser> getUserSkins(Long userId) {

        List<SkinUser> skinsUser = skinUserRepository.findSkinsByUserId(userId);
        return skinsUser;
    }

    public String updateColorSkin(SkinUserDTO skinUserDTO){

        return "Actualizacion de color de skin pendiente!";
    }


    @Transactional
    public void deleteSkinFromUser(Long userId, String skinId){

        this.skinUserRepository.deleteBySkinId(userId, skinId);
//        return this.skinUserRepository.deleteSkinFromUser(skinId);
    }

    /** Return  only de "active" skins **/
    public Skin getSkinBySkinId (String skinId){

        for( Skin skin : this.initialConfiguration.getCompletSkinsList()){
            if(skin.getSkinId().equals(skinId)){
                return skin;
            }
        }
//        TODO: crear exception para manejo de skins invalidas
        return null;
    }


}
