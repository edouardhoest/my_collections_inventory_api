package com.collector.my_collector_inventory;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean verifyPassword(String storedHash, String providedPassword) {
        return BCrypt.checkpw(providedPassword, storedHash);
    }
}