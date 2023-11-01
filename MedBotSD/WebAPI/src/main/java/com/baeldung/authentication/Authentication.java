package com.baeldung.authentication;

public class Authentication {

    private Authentication() {}

    private static int privilegeLevel = -1;

    public static int getPrivilegeLevel() {
        return privilegeLevel;
    }

    public static void setPrivilegeLevel(int privilegeLevel) {
        Authentication.privilegeLevel = privilegeLevel;
    }
}
