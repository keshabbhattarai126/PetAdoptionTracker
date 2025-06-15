/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petadoptiontracker.controller;

import petadoptiontracker.model.UserData;

/**
 *
 * @author LeathLOQ
 */
public class SessionManager {
    private static UserData currentUser;

    public static void login(UserData user) {
        currentUser = user;
    }

    public static void logout() {
        currentUser = null;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }
    public static UserData getCurrentUser() {
    return currentUser;
}
}