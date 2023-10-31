package com.perseo1326.testBackend.controllers;

import com.perseo1326.testBackend.DTOs.SkinUserDTO;
import com.perseo1326.testBackend.models.Skin;
import com.perseo1326.testBackend.models.SkinUser;
import com.perseo1326.testBackend.services.SkinService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/v1/skins")

public class SkinController {

    private final SkinService skinService;

    public SkinController(SkinService skinService) {
        this.skinService = skinService;
    }

    /** GET /skins/avaible - Devuelve una lista de todas las skins disponibles para comprar. **/
    @GetMapping("/available")
    @ResponseStatus(HttpStatus.OK)
    public List<Skin> getAllAvailableSkins (){
        return skinService.getAllAvailableSkins();
    }

    /** POST /skins/buy - Permite a los usuarios adquirir una skin y guardarla en la base de datos. **/
    @PostMapping("/buy")
    @ResponseStatus(HttpStatus.OK)
    public SkinUser buySkin (@RequestBody @Valid SkinUserDTO skinUserDTO){
        return skinService.buySkin(skinUserDTO);
    }

    /** GET /skins/myskins - Devuelve una lista de las skins compradas por el usuario. **/
    @GetMapping("/myskins")
    @ResponseStatus(HttpStatus.OK)
    public List<SkinUser> getMySkins(@RequestParam("userid") Long userId){
        return skinService.getUserSkins(userId);
    }

    /** PUT /skins/color - Permite a los usuarios cambiar el color de una skin comprada. **/
    @PutMapping("/color")
    @ResponseStatus(HttpStatus.OK)
    public SkinUser updateColorSkin(@RequestBody @Valid SkinUserDTO skinUserDTO){
        return skinService.updateColorSkin(skinUserDTO);
    }

    /** DELETE /skins/delete/{id} - Permite a los usuarios eliminar una skin comprada. **/
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSkinFromUser(@PathVariable("id") String skinId, @RequestParam("userid") Long userId){
        skinService.deleteSkinFromUser(userId, skinId);
    }

    /** GET /skin/getskin/{id} – Devuelve una determinada skin. **/
    @GetMapping("/getskin/{id}")
    public Skin getSkinBySkinId(@PathVariable("id") String skinId){
        return skinService.getSkinBySkinId(skinId);
    }

}
