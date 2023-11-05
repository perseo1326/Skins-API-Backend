package com.perseo1326.testBackend.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perseo1326.testBackend.DTOs.SkinUserDTO;
import com.perseo1326.testBackend.exceptions.NotFoundDataException;
import com.perseo1326.testBackend.exceptions.NotValidDataException;
import com.perseo1326.testBackend.models.Skin;
import com.perseo1326.testBackend.services.InitialConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MimeTypeUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SkinControllerTest {

    private final InitialConfiguration initialConfiguration;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public SkinControllerTest(InitialConfiguration initialConfiguration, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.initialConfiguration = initialConfiguration;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }


    @Test
    @DisplayName("Obtiene todas las skins que estan activas")
    void getAllAvailableSkins_test() {

        ArrayList<Skin> originalSkinsList = initialConfiguration.getOnlyValidSkins();

        try {
            MvcResult allSkinsResponse = mockMvc.perform(
                    MockMvcRequestBuilders.get("/v1/skins/available")
                            .accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andReturn();

            var getSkinsList = objectMapper.readValue(allSkinsResponse.getResponse().getContentAsString(), new TypeReference<ArrayList<Skin>>() {
            });

            assertAll (
                    () -> assertEquals( getSkinsList.size(), originalSkinsList.size()),
                    () -> assertEquals(200, allSkinsResponse.getResponse().getStatus(), "El estado no es correcto"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    @DisplayName("Comprar un skin, brindando un objeto 'SkinUserDTO' valido")
    void buySkin_withValidRequest() {

        SkinUserDTO skinUserDTO = new SkinUserDTO(5L, "A1", "white");


        try {
            MvcResult mvcResult = mockMvc.perform(
                            MockMvcRequestBuilders.post("/v1/skins/buy")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(skinUserDTO))
                    )
                    .andExpect(status().isCreated()).andReturn();

            var getSkinBought = objectMapper.readValue (mvcResult.getResponse().getContentAsString(), Skin.class );

            assertAll(
                    () -> assertEquals(201, mvcResult.getResponse().getStatus()),
                    () -> assertEquals(MediaType.APPLICATION_JSON.toString(), mvcResult.getResponse().getContentType()),
                    () -> assertEquals(skinUserDTO.getSkinid(), getSkinBought.getSkinId())
            );

        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    @DisplayName("Duplicar la compra de una skin por el mismo usuario.")
    void buySkin_twice() {

        SkinUserDTO skinUserDTO = new SkinUserDTO(2L, "A1", "white");

        try {
            MvcResult mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.post("/v1/skins/buy")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(skinUserDTO))
                    )
                    .andExpect(status().isBadRequest()).andReturn();

            assertEquals(400, mvcResult.getResponse().getStatus(), "El status no es correcto!");
            assertEquals(NotValidDataException.class, mvcResult.getResolvedException().getClass());
            assertEquals("La skin a comprar ya pertenece a este usuario.", mvcResult.getResolvedException().getMessage());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Comprar un skin con 'skiniId' Invalido.")
    void buySkin_NotValidSkinId() {

        SkinUserDTO skinUserDTO = new SkinUserDTO(2L, "Z10", "white");

        try {
            MvcResult mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.post("/v1/skins/buy")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(skinUserDTO))
                    )
                    .andExpect(status().isNotFound()).andReturn();

            assertEquals(404, mvcResult.getResponse().getStatus(), "El status no es correcto!");
            assertEquals(NotFoundDataException.class, mvcResult.getResolvedException().getClass());
            assertEquals("No se encontro el skin solicitado", mvcResult.getResolvedException().getMessage());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @DisplayName("Obtener las skins de un determinado usuario")
    @Test
    void getMySkins() {

        try {
            MvcResult mvcResult = mockMvc.perform(
                            MockMvcRequestBuilders.get("/v1/skins/myskins")
                                    .param("userid", "2")
                                    .accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andReturn();

            var getMySkinsList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<ArrayList<Skin>>() {} );

            assertAll(
                    () -> assertEquals(200, mvcResult.getResponse().getStatus(), "El status no es correcto!"),
                    () -> assertEquals(4, getMySkinsList.size())
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("Obtener las skins de un determinado usuario, sin usuario")
    @Test
    void getMySkins_withNoUserException() {

        try {
            MvcResult mvcResult = mockMvc.perform(
                            MockMvcRequestBuilders.get("/v1/skins/myskins")
//                                    .param("userid", "") No Params for this test!!
                                    .accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            assertAll(
                    () -> assertEquals("application/problem+json", mvcResult.getResponse().getContentType()),
                    () -> assertEquals(400, mvcResult.getResponse().getStatus(), "El status no es correcto!"),
                    () -> assertTrue(mvcResult.getResolvedException().getLocalizedMessage().contains("not present"))
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @DisplayName("cambiar el color de una skin ya comprada")
    @Test
    void updateColorSkin() {

        SkinUserDTO skinUserDTO = new SkinUserDTO(3L, "F6", "orange");

        try {
            MvcResult mvcResult = mockMvc.perform(
                            MockMvcRequestBuilders.put("/v1/skins/color")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(skinUserDTO))
                    )
                    .andExpect(status().isOk())
                    .andReturn();

            var skin = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Skin.class );
            var x = mvcResult.getResponse();
            System.out.println("Valor de X: \n");

            assertEquals(200, mvcResult.getResponse().getStatus(), "El status no es correcto!");
            assertEquals(MimeTypeUtils.APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType());
            assertTrue(skin.getColor().equals(skinUserDTO.getColor()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("cambiar el color de una skin que NO se ha comprado")
    @Test
    void updateColorSkin_noOwnedSkin() {

        SkinUserDTO skinUserDTO = new SkinUserDTO(100L, "F6", "orange");

        try {
            MvcResult mvcResult = mockMvc.perform(
                            MockMvcRequestBuilders.put("/v1/skins/color")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(skinUserDTO))
                    )
                    .andExpect(status().isBadRequest())
                    .andReturn();

            assertEquals(400, mvcResult.getResponse().getStatus(), "El status no es correcto!");
            assertEquals(MimeTypeUtils.APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType());
            assertTrue(mvcResult.getResponse().getContentAsString().contains("debe ser comprada"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @DisplayName("cambiar el color de una skin que NO existe")
    @Test
    void updateColorSkin_noFoundedSkinId() {

        SkinUserDTO skinUserDTO = new SkinUserDTO(1L, "Y666", "orange");

        try {
            MvcResult mvcResult = mockMvc.perform(
                            MockMvcRequestBuilders.put("/v1/skins/color")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(skinUserDTO))
                    )
                    .andExpect(status().isNotFound())
                    .andReturn();

            assertEquals(404, mvcResult.getResponse().getStatus(), "El status no es correcto!");
            assertEquals(MimeTypeUtils.APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType());
            assertTrue(mvcResult.getResponse().getContentAsString().contains("No se encontro el skin"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void deleteSkinFromUser_wrongSkinId() {

        String skinId = "X64";
        Long userId = 3L;

        try {
            MvcResult mvcResult = mockMvc.perform(
                            MockMvcRequestBuilders.delete("/v1/skins/delete/{skinId}", skinId )
                                    .param("userid", userId.toString())
                                    .accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
                    .andExpect(status().isNotFound())
                    .andReturn();

            assertAll(
                    () -> assertEquals(404, mvcResult.getResponse().getStatus(), "El status no es correcto!"),
                    () -> assertEquals(MimeTypeUtils.APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType()),
                    () -> assertTrue(mvcResult.getResponse().getContentAsString().contains("No se encontro el skin solicitado"))
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void deleteSkinFromUser_skinNotBelongsToUser() {

        String skinId = "A1";
        Long userId = 3L;

        try {
            MvcResult mvcResult = mockMvc.perform(
                            MockMvcRequestBuilders.delete("/v1/skins/delete/{skinId}", skinId )
                                    .param("userid", userId.toString())
                                    .accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            assertAll(
                    () -> assertEquals(400, mvcResult.getResponse().getStatus(), "El status no es correcto!"),
                    () -> assertEquals(MimeTypeUtils.APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType()),
                    () -> assertTrue(mvcResult.getResponse().getContentAsString().contains("el usuario no posee"))
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @DisplayName("Obtener una skin por su 'skinId")
    @Test
    void getSkinBySkinId() {

        Skin skin = initialConfiguration.getCompletSkinsList().get(3);

        try {
            MvcResult mvcResult = mockMvc.perform(
                            MockMvcRequestBuilders.get("/v1/skins/getskin/{skinId}", skin.getSkinId())
                                    .accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk())
                    .andReturn();

            var getSkin = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Skin.class);

                    assertAll(
                    () -> assertEquals(200, mvcResult.getResponse().getStatus(), "El status no es correcto!"),
                    () -> assertEquals(MimeTypeUtils.APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType()),
                    () -> assertEquals(skin.getSkinId(), getSkin.getSkinId())
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @DisplayName("Obtener una skin por su 'skinId, sin 'sinId'")
    @Test
    void getSkin_withNoSkinIdException() {

        try {
            MvcResult mvcResult = mockMvc.perform(
                            MockMvcRequestBuilders.get("/v1/skins/getskin/")
                                    .accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
                    .andExpect(status().isNotFound())
                    .andReturn();

                    assertAll(
                    () -> assertEquals(404, mvcResult.getResponse().getStatus(), "El status no es correcto!"),
                    () -> assertEquals(0, mvcResult.getResponse().getContentLength())
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @DisplayName("Obtener una skin por su 'skinId, con 'skinId' inválida")
    @Test
    void getSkin_withNoValidSkinIdException() {

        try {
            MvcResult mvcResult = mockMvc.perform(
                            MockMvcRequestBuilders.get("/v1/skins/getskin/{skinid}", "Z56")
                                    .accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
                    .andExpect(status().isNotFound())
                    .andReturn();

                    assertAll(
                        () -> assertEquals(404, mvcResult.getResponse().getStatus(), "El status no es correcto!"),
                        () -> assertEquals(MimeTypeUtils.APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType()),
                        () -> assertTrue(mvcResult.getResponse().getContentAsString().contains("No se encontro el skin solicitado"))
                    );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}