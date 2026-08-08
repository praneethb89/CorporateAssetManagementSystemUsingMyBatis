package com.crimsonlogic.corporateassetmanagementsystem.util;

import com.crimsonlogic.corporateassetmanagementsystem.menu.MainMenu;

public class SystemRunner {

    public static void startApplication() {
        System.out.println("Initializing system components...");
        System.out.println("Connecting to Database...");

        // Kick off the new Main Menu routing
        MainMenu mainMenu = new MainMenu();
        mainMenu.display();
    }
}