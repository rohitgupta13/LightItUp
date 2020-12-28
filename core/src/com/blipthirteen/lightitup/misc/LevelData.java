package com.blipthirteen.lightitup.misc;

import java.util.HashMap;

/**
 * Created by HP on 10/17/2017.
 */

public class LevelData {

    public static HashMap<Integer, HashMap<String, String>> levels = new HashMap<Integer, HashMap<String, String>>();

    static {
        HashMap<String, String> levelMap = new HashMap<String, String>();
        // Level 1
        levelMap.put("3,1", "3,0");
        levelMap.put("3,2", "1,0");
        levelMap.put("3,3", "1,0");
        levelMap.put("3,4", "1,0");
        levelMap.put("3,5", "3,180");
        levels.put(0, levelMap);

        // Level 2
        levelMap = new HashMap<String, String>();
        levelMap.put("3,2", "4,0");
        levelMap.put("4,2", "4,270");
        levelMap.put("3,3", "4,90");
        levelMap.put("4,3", "4,180");
        levels.put(1, levelMap);

        // Level 3
        levelMap = new HashMap<String, String>();
        levelMap.put("3,2", "3,0");
        levelMap.put("2,3", "3,90");
        levelMap.put("3,3", "2,180");
        levelMap.put("4,3", "1,90");
        levelMap.put("5,3", "3,270");
        levels.put(2, levelMap);

        // Level 4
        levelMap = new HashMap<String, String>();
        levelMap.put("1,1", "3,90");
        levelMap.put("2,1", "2,0");
        levelMap.put("3,1", "1,90");
        levelMap.put("4,1", "3,270");
        levelMap.put("2,2", "1,0");
        levelMap.put("2,3", "2,90");
        levelMap.put("3,3", "3,270");
        levelMap.put("2,4", "3,180");
        levels.put(3, levelMap);


        // Level 5
        levelMap = new HashMap<String, String>();
        levelMap.put("2,1", "3,0");
        levelMap.put("4,1", "3,0");
        levelMap.put("2,2", "1,0");
        levelMap.put("4,2", "1,0");
        levelMap.put("2,3", "2,90");
        levelMap.put("3,3", "1,90");
        levelMap.put("4,3", "2,270");
        levelMap.put("2,4", "1,0");
        levelMap.put("4,4", "1,0");
        levelMap.put("2,5", "3,180");
        levelMap.put("4,5", "3,180");
        levels.put(4, levelMap);


        // Level 6
        levelMap = new HashMap<String, String>();
        levelMap.put("2,1", "3,0");
        levelMap.put("4,1", "3,0");
        levelMap.put("2,2", "1,0");
        levelMap.put("4,2", "1,0");
        levelMap.put("0,3", "3,90");
        levelMap.put("1,3", "1,90");
        levelMap.put("2,3", "2,270");
        levelMap.put("4,3", "1,0");
        levelMap.put("4,4", "0,90");
        levelMap.put("4,5", "3,270");
        levelMap.put("2,4", "1,0");
        levelMap.put("4,4", "1,0");
        levelMap.put("2,5", "3,180");
        levelMap.put("4,5", "3,180");
        levels.put(5, levelMap);

        // Level 7
        levelMap = new HashMap<String, String>();
        levelMap.put("5,0", "4,0");
        levelMap.put("6,0", "3,270");
        levelMap.put("0,1", "3,90");
        levelMap.put("1,1", "2,0");
        levelMap.put("2,1", "3,270");
        levelMap.put("5,1", "1,0");
        levelMap.put("1,2", "1,0");
        levelMap.put("3,2", "4,0");
        levelMap.put("4,2", "1,90");
        levelMap.put("5,2", "4,180");
        levelMap.put("1,3", "2,90");
        levelMap.put("2,3", "1,90");
        levelMap.put("3,3", "2,180");
        levelMap.put("4,3", "2,0");
        levelMap.put("5,3", "1,90");
        levelMap.put("6,3", "3,270");
        levelMap.put("1,4", "1,0");
        levelMap.put("4,4", "1,0");
        levelMap.put("0,5", "3,90");
        levelMap.put("1,5", "2,180");
        levelMap.put("2,5", "3,270");
        levelMap.put("4,5", "4,90");
        levelMap.put("5,5", "4,270");
        levelMap.put("5,6", "4,90");
        levelMap.put("6,6", "3,270");
        levels.put(6, levelMap);

        // Level 8
        levelMap = new HashMap<String, String>();
        levelMap.put("3,2", "4,0");
        levelMap.put("4,2", "4,270");
        levelMap.put("3,3", "4,90");
        levelMap.put("4,3", "4,180");
        levelMap.put("2,1", "4,0");
        levelMap.put("3,1", "1,90");
        levelMap.put("4,1", "1,90");
        levelMap.put("5,1", "4,270");
        levelMap.put("2,2", "1,0");
        levelMap.put("5,2", "1,0");
        levelMap.put("2,3", "1,0");
        levelMap.put("5,3", "1,0");
        levelMap.put("2,4", "4,90");
        levelMap.put("3,4", "1,90");
        levelMap.put("4,4", "1,90");
        levelMap.put("5,4", "4,180");
        levels.put(7, levelMap);


        // Level 9
        levelMap = new HashMap<String, String>();
        levelMap.put("1,1", "3,0");
        levelMap.put("0,2", "3,90");
        levelMap.put("1,2", "2,180");
        levelMap.put("2,2", "4,270");
        levelMap.put("2,3", "4,90");
        levelMap.put("3,3", "1,90");
        levelMap.put("4,3", "1,90");
        levelMap.put("5,3", "4,270");
        levelMap.put("5,4", "1,0");
        levelMap.put("5,5", "4,90");
        levelMap.put("6,5", "3,270");
        levels.put(8, levelMap);

        // Level 10
        levelMap = new HashMap<String, String>();
        levelMap.put("1,0", "4,0");
        levelMap.put("2,0", "4,270");
        levelMap.put("1,1", "4,90");
        levelMap.put("2,1", "2,270");
        levelMap.put("2,2", "1,0");
        levelMap.put("2,3", "4,90");
        levelMap.put("3,3", "1,90");
        levelMap.put("4,3", "4,270");
        levelMap.put("4,4", "1,0");
        levelMap.put("4,5", "2,90");
        levelMap.put("5,5", "4,270");
        levelMap.put("4,6", "4,90");
        levelMap.put("5,6", "4,180");
        levels.put(9, levelMap);

        // Level 11
        levelMap = new HashMap<String, String>();
        levelMap.put("1,3", "3,90");
        levelMap.put("2,0", "4,0");
        levelMap.put("2,1", "1,0");
        levelMap.put("2,2", "1,0");
        levelMap.put("2,3", "2,270");
        levelMap.put("2,4", "1,0");
        levelMap.put("2,5", "1,0");
        levelMap.put("2,6", "4,90");
        levelMap.put("3,0", "4,270");
        levelMap.put("3,1", "1,0");
        levelMap.put("3,2", "2,90");
        levelMap.put("3,3", "1,0");
        levelMap.put("3,4", "2,90");
        levelMap.put("3,5", "1,0");
        levelMap.put("3,6", "4,180");
        levelMap.put("4,2", "3,270");
        levelMap.put("4,4", "3,270");
        levels.put(10, levelMap);

        // Level 12
        levelMap = new HashMap<String, String>();
        levelMap.put("0,0", "4,0");
        levelMap.put("1,0", "1,90");
        levelMap.put("2,0", "1,90");
        levelMap.put("3,0", "1,90");
        levelMap.put("4,0", "1,90");
        levelMap.put("5,0", "1,90");
        levelMap.put("6,0", "4,270");
        levelMap.put("0,6", "4,90");
        levelMap.put("1,6", "1,90");
        levelMap.put("2,6", "1,90");
        levelMap.put("3,6", "1,90");
        levelMap.put("4,6", "1,90");
        levelMap.put("5,6", "1,90");
        levelMap.put("6,6", "4,180");
        levelMap.put("0,1", "1,0");
        levelMap.put("0,2", "1,0");
        levelMap.put("0,3", "1,0");
        levelMap.put("0,4", "1,0");
        levelMap.put("0,5", "1,0");
        levelMap.put("6,1", "1,0");
        levelMap.put("6,2", "1,0");
        levelMap.put("6,3", "1,0");
        levelMap.put("6,4", "1,0");
        levelMap.put("6,5", "1,0");
        levelMap.put("2,1", "4,0");
        levelMap.put("3,1", "1,90");
        levelMap.put("4,1", "1,90");
        levelMap.put("5,1", "4,270");
        levelMap.put("2,2", "1,0");
        levelMap.put("5,2", "1,0");
        levelMap.put("2,3", "1,0");
        levelMap.put("5,3", "1,0");
        levelMap.put("2,4", "4,90");
        levelMap.put("3,4", "1,90");
        levelMap.put("4,4", "1,90");
        levelMap.put("5,4", "4,180");
        levels.put(11, levelMap);

        // Level 13
        levelMap = new HashMap<String, String>();
        levelMap.put("1,0", "4,0");
        levelMap.put("2,0", "4,270");
        levelMap.put("1,1", "4,90");
        levelMap.put("2,1", "2,270");
        levelMap.put("2,2", "1,0");
        levelMap.put("2,3", "2,90");
        levelMap.put("3,3", "1,90");
        levelMap.put("4,3", "2,270");
        levelMap.put("4,4", "1,0");
        levelMap.put("4,5", "2,90");
        levelMap.put("5,5", "4,270");
        levelMap.put("4,6", "4,90");
        levelMap.put("5,6", "4,180");
        levelMap.put("2,4", "1,0");
        levelMap.put("1,5", "4,0");
        levelMap.put("2,5", "2,270");
        levelMap.put("1,6", "4,90");
        levelMap.put("2,6", "4,180");
        levelMap.put("4,0", "4,0");
        levelMap.put("5,0", "4,270");
        levelMap.put("4,1", "2,90");
        levelMap.put("5,1", "4,180");
        levelMap.put("4,2", "1,0");
        levels.put(12, levelMap);

        // Level 14
        levelMap = new HashMap<String, String>();
        levelMap.put("1,3", "3,90");
        levelMap.put("2,0", "4,0");
        levelMap.put("2,1", "1,0");
        levelMap.put("2,2", "1,0");
        levelMap.put("2,3", "2,270");
        levelMap.put("2,4", "1,0");
        levelMap.put("2,5", "1,0");
        levelMap.put("2,6", "4,90");
        levelMap.put("3,0", "4,270");
        levelMap.put("3,1", "1,0");
        levelMap.put("3,2", "2,90");
        levelMap.put("3,3", "1,0");
        levelMap.put("3,4", "2,90");
        levelMap.put("3,5", "1,0");
        levelMap.put("3,6", "4,180");
        levelMap.put("4,2", "1,90");
        levelMap.put("4,4", "1,90");
        levelMap.put("5,2", "4,270");
        levelMap.put("5,3", "2,90");
        levelMap.put("5,4", "4,180");
        levelMap.put("6,3", "3,270");
        levels.put(13, levelMap);

        // Level 15
        levelMap = new HashMap<String, String>();
        levelMap.put("1,3", "3,90");
        levelMap.put("2,0", "2,0");
        levelMap.put("2,1", "1,0");
        levelMap.put("2,2", "1,0");
        levelMap.put("2,3", "2,270");
        levelMap.put("2,4", "1,0");
        levelMap.put("2,5", "1,0");
        levelMap.put("2,6", "2,180");
        levelMap.put("3,0", "2,0");
        levelMap.put("3,1", "1,0");
        levelMap.put("3,2", "2,90");
        levelMap.put("3,3", "1,0");
        levelMap.put("3,4", "2,90");
        levelMap.put("3,5", "1,0");
        levelMap.put("3,6", "2,180");
        levelMap.put("4,2", "1,90");
        levelMap.put("4,4", "1,90");
        levelMap.put("5,2", "4,270");
        levelMap.put("5,3", "2,90");
        levelMap.put("5,4", "4,180");
        levelMap.put("6,3", "3,270");
        levelMap.put("0,0", "4,0");
        levelMap.put("0,1", "1,0");
        levelMap.put("0,2", "1,0");
        levelMap.put("0,3", "1,0");
        levelMap.put("0,4", "1,0");
        levelMap.put("0,5", "1,0");
        levelMap.put("0,6", "4,90");
        levelMap.put("1,0", "1,90");
        levelMap.put("1,6", "1,90");
        levelMap.put("4,0", "1,90");
        levelMap.put("4,6", "1,90");
        levelMap.put("5,0", "4,270");
        levelMap.put("5,1", "1,0");
        levelMap.put("5,2", "2,270");
        levelMap.put("5,3", "2,90");
        levelMap.put("5,4", "2,270");
        levelMap.put("5,5", "1,0");
        levelMap.put("5,6", "4,180");
        levelMap.put("6,3", "3,270");
        levels.put(14, levelMap);
    }
}
