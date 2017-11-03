package com.android.luogui.baselibrary.constant;


import com.android.luogui.baselibrary.MyApplication;
import com.android.luogui.baselibrary.R;

/**
 *
 * Created by LuoGui on 2017/3/4.
 */

public class Constant {

    // 手机正则
    public static final String TELEPHONE_REGEX = "^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|7[06-8])\\d{8}$";

    public static String MONEY_UNIT = "RMB";

    public static final String FORMAT_DATE = "yyyy-MM-dd";

    public static final String THEME_PATTERN = "#[^#]+#";

    public static final String LOGINFO = "*========Log========*";

    public static int PROGRESS_BAR_COLOR = R.color.colorPrimary;

    public static boolean RSA_ENCRYPT = false;  //是否使用RSA加密

    public static boolean AES_ENCRYPT = false;  //是否使用AES加密

    /**
     * RSA公钥key
     */
    public static final String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzX8uuBUThewyMDIxt5J9eimq5" +
            "7T87P4Y9wdo172+6S550Gkq2+XX5oWYDAbB9MkXvoaQ4LyePJFY3xcI1deASYvv2" +
            "zlc1AIVfCEuH9xuAzVw0W7AUxXNcq4nzduNDpqjNZRYBfqb2xyQIitq3C/dNJ8PV" +
            "b+W6UypjeZmqYS0aHwIDAQAB";

    /**
     * RSA私钥key
     */
    public static final String RSA_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALNfy64FROF7DIwM" +
            "jG3kn16KarntPzs/hj3B2jXvb7pLnnQaSrb5dfmhZgMBsH0yRe+hpDgvJ48kVjfF" +
            "wjV14BJi+/bOVzUAhV8IS4f3G4DNXDRbsBTFc1yrifN240OmqM1lFgF+pvbHJAiK" +
            "2rcL900nw9Vv5bpTKmN5maphLRofAgMBAAECgYEAg0WGMDLW+BUvV4cPdpUsNOoj" +
            "tTH1ZozU13l5EqPKJpKUwe9D4qiqOChwGZ8w5E7Yc8jd/+gykUVMe8CUor9DXbe9" +
            "uMdiG1YPUq++DJLCprOpEaU84IERUX/qYHMuykLkf4oEt70ixqiB1J1ZNTiAsD6A" +
            "d5P2oL8skhxvcLVok3ECQQDYCE+hJh9JEbEdLDx/oTnewc4V1OYzSBHcOM1mhlO5" +
            "JhYzHKH8ZMUPJM9rPI5yQDl/BwUxEzTBh82kPniFVaxrAkEA1I9IZZ2davYM9A2r" +
            "If7VOBIMyB8xyNOgCIwFH3N29YNM6TYNP0T7RM9RYbrReMA4rJDteLkcdnW7dTU0" +
            "4s42HQJAHMU+lZ+WGw0YIWJ6zCSUqG33HEE2Ry4Z8tI9yKGrFMVVv4jYqdxhwhqM" +
            "5SMzdVPIVHqC3n5IUF3drToEov098QJAVuzVjYC8bI580513W6qsJYb0JS7knB03" +
            "N+eS/L6qalKstD5/gl1CaGxj+pR8r2Xkh2WpR5Jj+mnBJHiCzTHJXQJARweRUcVI" +
            "bsRemPjYCh6oWa1Db0vcIJ2ib3gwHSGzrQLF7c70QoXBsLiPL/IQapBx9UCwzUdI" +
            "0viyCXa+O9Cw9A==";

    /**
     * AES加解密key
     */
    public static final String AES_KEY = "AES_" + MyApplication.getInstance().getPackageName()+"_AES";
}
