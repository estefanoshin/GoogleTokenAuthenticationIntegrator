package com.google.GoogleTokenAuthenticationIntegrator.controller;

import com.google.GoogleTokenAuthenticationIntegrator.service.impl.GeneratorService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController("/api/token")
public class AuthenticatorController {

    /**
     * TODO:
     * Esta secret Key tiene que guardarse y ser asociada a un usuario para los token
     * This key needs to be saved when the key is generated
     */
    private static String secretKey;
    private static final String MY_APP_NAME = "MY_CUSTOM_APP_NAME";

    private GeneratorService generatorService;

    /**
     * Generacion de la semilla, la misma se deberia guardar para luego validar contra el token
     */
    @GetMapping("/generate/{username}")
    public void generate(@PathVariable("username") String username, HttpServletResponse response) throws IOException, WriterException {

        /**
         * Grabar esta llave en este momento o en una futura llamada en otro endpoint y asociarlo a un usuario
         * Save this key now or on another endpoint associated with the user
         */
        secretKey = generatorService.generateSecretKey();

        String code = generatorService.getGoogleAuthenticatorBarCode(secretKey, username, MY_APP_NAME);
        generatorService.createQRCode(code, response, 400, 400);
    }

    /**
     * TODO: Agregar validaciones para el username en las aplicaciones finales
     * Validate the username for a more sturdier security on the final application
     */
    @GetMapping("/validate/{username}/{token}")
    public String validate(@PathVariable("username") String username, @PathVariable("token") String token) {
        String serverToken = generatorService.getTOTPCode(secretKey);
        return String.valueOf(serverToken.equals(token));
    }

    @Autowired
    public void setGeneratorService(GeneratorService generatorService) {
        this.generatorService = generatorService;
    }
}