package de.cfp.modder;

import de.cfp.modder.gui.MainGUI;
import de.cfp.modder.util.Constants;

import java.io.File;

public class Modder {

    public static void main(String[] args) {
        System.out.println("Launching Modder!");
        System.out.println("Configuration:");
        System.out.println("Mod Directory (Loaded mods): " + Constants.MODS);
        System.out.println("Modder Directory (Unloaded mods): " + Constants.MODDER);
        // Modder directory structure:
        // modder
        //    MODPROFILENAME
        //       MODPROFILENAME.mp
        //       mods
        //          Mod1.jar
        //          Mod2.jar
        setup();
        new MainGUI();
    }

    private static void setup() {
        File mods = new File(Constants.MODS);
        File modder = new File(Constants.MODDER);

        if(!mods.exists()) {
            mods.mkdirs();
            System.out.println("Created mods directory");
        }
        if(!modder.exists()) {
            modder.mkdirs();
            System.out.println("Created modder directory");
        }
    }

}
