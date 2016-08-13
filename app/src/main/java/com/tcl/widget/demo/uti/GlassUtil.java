package com.tcl.widget.demo.uti;

/**
 * @author xiangxiangliu
 * @Description:
 * @date 2016/6/4 14:12
 * @copyright TCL-MIG
 */
public class GlassUtil {
    public static String[] getLevelColors(int level) {
        switch (level) {
            case 1:
                return new String[]{"#72e04c", "#38b91a"};
            case 2:
                return new String[]{"#cef135", "#adcb01"};
            case 3:
                return new String[]{"#f89f2b", "#ef5f05"};
            case 4:
                return new String[]{"#de4d35", "#c1170b"};
            case 5:
                return new String[]{"#8d0c4c", "#740837"};
            case 6:
                return new String[]{"#5c0019", "#440010"};
        }

        return new String[]{"#ffffff", "#ffffff"};
    }

    public static int getSmellLevel(float value) {
        if(value < 25.0f)
            return 1;
        else if(value < 40.0f)
            return 2;
        else if(value < 60.0f)
            return 3;
        else if(value < 80.0f)
            return 4;
        else if(value < 95.0f)
            return 5;
        else
            return 6;
    }

}
