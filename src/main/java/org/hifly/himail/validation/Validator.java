package org.hifly.himail.validation;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.hifly.himail.constants.Constants.IPADDRESS_PATTERN;
import static org.hifly.himail.constants.Constants.INTEGER_PATTERN;
import static org.hifly.himail.constants.Constants.MAX_THREADS;
import static org.hifly.himail.constants.Constants.MAX_IP_PORT;

public class Validator {

    private static Pattern pattern;

    public static boolean validateIpAddress(String ipAddress) throws Exception {
        pattern = Pattern.compile(IPADDRESS_PATTERN);
        return doValidation(ipAddress);
    }

    public static boolean validateIpPort(int ipPort) throws Exception {
        pattern = Pattern.compile(INTEGER_PATTERN);
        if (doValidation(String.valueOf(ipPort)) && ipPort < MAX_IP_PORT)    //TODO verify if this is a limit
            return true;
        return false;
    }

    public static boolean validateMaxThreads(int maxThreads) throws Exception {
        pattern = Pattern.compile(INTEGER_PATTERN);
        if (doValidation(String.valueOf(maxThreads)) && maxThreads < MAX_THREADS)
            return true;
        return false;
    }

    private static boolean doValidation(String value) throws Exception {
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }


}
