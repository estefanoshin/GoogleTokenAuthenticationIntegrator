package com.google.GoogleTokenAuthenticationIntegrator.service;

import com.google.zxing.WriterException;
import de.taimos.totp.TOTP;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;

public interface IGeneratorService {

    public void createQRCode(String barCodeData, HttpServletResponse response, int height, int width)
            throws WriterException, IOException;

    public String getGoogleAuthenticatorBarCode(String secretKey, String account, String issuer);


    public String generateSecretKey();

    public String getTOTPCode(String secretKey);
}
