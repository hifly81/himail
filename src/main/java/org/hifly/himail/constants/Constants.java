package org.hifly.himail.constants;


public interface Constants {

    public static final String WHITE_SPACE = " ";
    public static final String MINUS = "-";
    public static final String OK = "OK";
    public static final String END_COMMAND = "\r\n";

    public static final String IPADDRESS_PATTERN =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                    "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    public static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final String INTEGER_PATTERN = "^[0-9]+$";

    public static final int MAX_THREADS = 5000;

    public static final int MAX_IP_PORT = 65536;
}
