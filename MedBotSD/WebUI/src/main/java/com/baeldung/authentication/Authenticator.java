package com.baeldung.authentication;

public class Authenticator {

    private static int privilegeLevel = 0;

    private Authenticator() {}

    public static void setPrivilegeLevel(int level) {
        privilegeLevel = level;
    }

    public static int getPrivilegeLevel() {
        return privilegeLevel;
    }
}
