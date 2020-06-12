package cn.wllnb.etms.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author WLL
 */
@Component
public class Md5Encrypt {

    private static String PASSWORD_SALT = "SHUETMS_WLL";

    @Value("${password.salt:#{null}")
    public static void setPasswordSalt(String passwordSalt) {
        PASSWORD_SALT = passwordSalt;
    }

    private static String md5Hex(String string){
        return DigestUtils.md5DigestAsHex(string.getBytes());
    }

    public static String computePasswordHash(String password) {
        String md5 = md5Hex(password);
        return md5Hex(md5 + PASSWORD_SALT);
    }

    public static void main(String[] args){
        String originString = "password";
        String result = computePasswordHash(originString);
        System.out.println("result:");
        System.out.println(result);

    }
}
