package com.skilrock.dge.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


    public class ConfigManager {
        static Properties properties;

        public static void loadConfig() {
            try {
                File file = new File("Config.properties");
                FileInputStream fileInput = new FileInputStream(file);
                Properties properties = new Properties();
                properties.load(fileInput);
                ConfigManager.properties = properties;
            } catch (Exception e) {
                System.out.println("Exception occurred while loading config.properties " + e.getMessage());
            }
        }

        public static String getProperty(String key) {
            try {
                loadConfig();
                return properties.getProperty(key);
            } catch (Exception e) {
                return null;
            }

        }

        public static String setProperty(String key, String value) {
            try {
                String previousValue = (String) properties.setProperty(key, value);
                System.out.println(previousValue);
                return previousValue;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

    }



