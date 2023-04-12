package com.google.GoogleTokenAuthenticationIntegrator.service.impl;

import com.google.GoogleTokenAuthenticationIntegrator.repository.Generator;
import com.google.GoogleTokenAuthenticationIntegrator.service.IGeneratorService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class GeneratorService implements IGeneratorService {

    private Generator generator;

    @Override
    public void createQRCode(String barCodeData, HttpServletResponse response, int height, int width) throws WriterException, IOException {
        generator.createQRCode(barCodeData, response, height, width);
    }

    @Override
    public String getGoogleAuthenticatorBarCode(String secretKey, String account, String issuer) {
        return generator.getGoogleAuthenticatorBarCode(secretKey, account, issuer);
    }

    @Override
    public String generateSecretKey() {
        return generator.generateSecretKey();
    }

    @Override
    public String getTOTPCode(String secretKey) {
        return generator.getTOTPCode(secretKey);
    }

    @Autowired
    public void setGenerator(Generator generator) {
        this.generator = generator;
    }
}
