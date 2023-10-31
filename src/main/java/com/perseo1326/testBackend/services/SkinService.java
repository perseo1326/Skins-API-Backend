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
import java.util.Optional;


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

    private Skin getSkinFromId(String skinId) {
        for( Skin skin : this.skinsList){
            if(skin.getSkinId().equals(skinId)){
                return skin;
            }
        }
//        TODO: crear exception para manejo de skins invalidas
        return null;
    }

    /** filter only "ACTIVE" skins **/
    public List<Skin> getAllAvailableSkins (){

        return this.skinsList;
    }

    public SkinUser buySkin(SkinUserDTO skinUserDTO) {

        Optional<Skin> skin = Optional.ofNullable(this.getSkinBySkinId(skinUserDTO.getSkinid()));

        if (skin.isEmpty()){
            // TODO: exception no valid skin ID
            throw new IllegalStateException("El Skin no ha sido encontrado.");
        }

        Optional<SkinUser> skinUser = this.skinUserRepository.findByUserIdAndSkinId(skinUserDTO.getUserid(), skinUserDTO.getSkinid());
        if(skinUser.isPresent()){
            // TODO: exception record already exist!
            throw new IllegalStateException("La skin ya pertenece a este usuario.");
        }

        SkinUser skinUserNew = new SkinUser(skinUserDTO.getUserid(), skin.get().getSkinId(), skin.get().getColor());
        return this.skinUserRepository.save(skinUserNew);
    }

    public List<SkinUser> getUserSkins(Long userId) {

        return skinUserRepository.findSkinsByUserId(userId);
    }

    public SkinUser updateColorSkin(SkinUserDTO skinUserDTO){

        if(skinUserDTO.getColor() == null){
            // TODO: exception empty color request
            throw new IllegalStateException("no se ha proporcionado un color valido");
        }

        Optional<SkinUser> skinUser = this.skinUserRepository.findByUserIdAndSkinId(skinUserDTO.getUserid(), skinUserDTO.getSkinid());
        if(skinUser.isEmpty()){
            // TODO: exception
            throw new IllegalStateException("No se encuentra la skin para actualizar!");
        }

        skinUser.get().setSkinColor( skinUserDTO.getColor());
        return this.skinUserRepository.save(skinUser.get());
    }


    @Transactional
    public void deleteSkinFromUser(Long userId, String skinId){

        this.skinUserRepository.deleteBySkinId(userId, skinId);
    }

    /** Return  only de "active" skins **/
    public Skin getSkinBySkinId (String skinId){

        return getSkinFromId(skinId);
    }

}
