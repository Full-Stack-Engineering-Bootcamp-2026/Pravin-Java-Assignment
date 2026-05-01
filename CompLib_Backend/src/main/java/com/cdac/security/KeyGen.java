
package com.cdac.security;

import java.security.SecureRandom;
import java.util.Base64;

public class KeyGen {
    public static void main(String[] args) {
        byte[] key = new byte[32]; // ✅ 32 bytes required
        new SecureRandom().nextBytes(key);

        String base64Key = Base64.getEncoder().encodeToString(key);
        System.out.println(base64Key);
    }
}