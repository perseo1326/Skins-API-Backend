package com.perseo1326.testBackend.controllers;

import com.perseo1326.testBackend.DTOs.SkinUserDTO;
import com.perseo1326.testBackend.models.Skin;
import com.perseo1326.testBackend.models.SkinUser;
import com.perseo1326.testBackend.services.SkinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/v1/skins")
@Tag(name = "Skins", description = "Consultas y gestion de las diversas skins y los usuarios.")
public class SkinController {

    private final SkinService skinService;

    public SkinController(SkinService skinService) {
        this.skinService = skinService;
    }

    /** GET /skins/avaible - Devuelve una lista de todas las skins disponibles para comprar. **/
    @Operation (summary = "Devuelve una lista de todas las skins disponibles para comprar.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Skins disponibles",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Skin.class)) })
    })
    @GetMapping("/available")
    @ResponseStatus(HttpStatus.OK)
    public List<Skin> getAllAvailableSkins (){
        return skinService.getAllAvailableSkins();
    }


    /** POST /skins/buy - Permite a los usuarios adquirir una skin y guardarla en la base de datos. **/
    @Operation(summary = "Permite a los usuarios adquirir una skin y guardarla en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compra exitosa de la skin solicitada por el usuario.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SkinUserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Parámetros incompletos o no válidos.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "La skin solicitada no pudo ser encontrada.",
                    content = @Content) })
    @PostMapping("/buy")
    @ResponseStatus(HttpStatus.CREATED)
    public SkinUser buySkin (@RequestBody @Valid SkinUserDTO skinUserDTO){
        return skinService.buySkin(skinUserDTO);
    }


    @Operation( summary = "Devuelve una lista de las skins compradas por un usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de Skins que pertenecen a un usuario.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Skin.class)) }),
            @ApiResponse(responseCode = "400", description = "Parámetro \"userId\" no presente.",
                    content = @Content)
     })
    /** GET /skins/myskins - Devuelve una lista de las skins compradas por el usuario. **/
    @GetMapping("/myskins")
    @ResponseStatus(HttpStatus.OK)
    public List<SkinUser> getMySkins(@Parameter(description = "ID del usuario.") @RequestParam("userid") Long userId){
        return skinService.getUserSkins(userId);
    }


    /** PUT /skins/color - Permite a los usuarios cambiar el color de una skin comprada. **/
    @Operation(summary = "Modifica el color de una skin comprada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actualización correcta",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SkinUserDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Parámetros incompletos o no válidos.",
                    content = @Content)
     })
    @PutMapping("/color")
    @ResponseStatus(HttpStatus.OK)
    public Skin updateColorSkin(@RequestBody @Valid SkinUserDTO skinUserDTO){
        return skinService.updateColorSkin(skinUserDTO);
    }


    @Operation(summary = "Elimina una skin comprada por un usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro eliminado satisfactoriamente."),
            @ApiResponse(responseCode = "400", description = "El \"userId\" no esta presente o el usuario no posee la skin solicitada.",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "El \"skinId\" no pudo ser encontrado.",
                    content = @Content) })
    /** DELETE /skins/delete/{id} - Permite a los usuarios eliminar una skin comprada. **/
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSkinFromUser(@Parameter(description = "El Id de la skin.") @PathVariable("id") String skinId, @Parameter(description = "El Id del usuario que solicita la acción.") @RequestParam("userid") @Valid Long userId){
        skinService.deleteSkinFromUser(userId, skinId);
    }


    /** GET /skin/getskin/{id} – Devuelve una determinada skin. **/
    @Operation(summary = "Devuelve una determinada skin.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Skin solicitada.",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Skin.class)) }),
            @ApiResponse(responseCode = "404", description = "El skinId no pudo ser encontrado.",
                    content = @Content) })
    @GetMapping("/getskin/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Skin getSkinBySkinId(@Parameter(description = "ID de la Skin solicitada.")@PathVariable("id") @Valid String skinId){
        return skinService.getSkinBySkinId(skinId);
    }

}
