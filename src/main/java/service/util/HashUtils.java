package service.util;

import service.CryptService;

import java.util.function.Function;

public class HashUtils {
    private static final CryptService bcrypt = new CryptService(11);

    public static String hash(String password) {
        return bcrypt.hash(password);
    }

    public static boolean verifyAndUpdateHash(String password, String hash, Function<String, Boolean> updateFunc) {
        return bcrypt.verifyAndUpdateHash(password, hash, updateFunc);
    }
}
