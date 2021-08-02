package de.cfp.modder;

import de.cfp.modder.gui.MainGUI;
import de.cfp.modder.util.Constants;
import de.cfp.modder.util.FileUtils;

import java.io.File;

public class Modder {

    public static void main(String[] args) {
        if(args.length > 0) {
            if(args[0].equalsIgnoreCase("help")) {
                System.out.println("apply <profile> - Apply the mods for a profile");
                System.out.println("list - List profiles");
                System.out.println("create <profile> - Create a new profile");
                System.out.println("delete <profile> - Delete a profile");
            } else if(args[0].equalsIgnoreCase("apply")) {
                if(args.length == 2) {
                    if(FileUtils.profileExists(args[1])) {
                        FileUtils.applyMods(args[1]);
                        System.out.println("Applied mods.");
                    } else {
                        System.err.println("Profile does not exist.");
                    }
                } else {
                    System.err.println("Incorrect Syntax. Use apply <profile>");
                }
            } else if(args[0].equalsIgnoreCase("list")) {
                File f = new File(Constants.MODDER);

                String[] pathnames = f.list();
                for (int i = 0; i < pathnames.length; i++) {
                    System.out.println(pathnames[i]);
                }
            } else if(args[0].equalsIgnoreCase("create")) {
                if (args.length == 2) {
                    if (FileUtils.profileExists(args[1])) {
                        System.err.println("Profile already exists.");
                    } else {
                        FileUtils.createProfile(args[1]);
                        System.out.println("Profile created.");
                    }
                } else {
                    System.err.println("Incorrect Syntax. Use create <profile>");
                }
            } else if(args[0].equalsIgnoreCase("delete")) {
                if (args.length == 2) {
                    if (FileUtils.profileExists(args[1])) {
                        FileUtils.deleteProfile(args[1]);
                        System.out.println("Profile deleted.");
                    } else {
                        System.err.println("Profile does not exist.");
                    }
                } else {
                    System.err.println("Incorrect Syntax. Use delete <profile>");
                }
            }
            return;
        }
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
