package org.hifly.himail.server.smtp;


import org.apache.commons.lang3.StringUtils;
import org.hifly.himail.core.AbstractConfiguration;
import org.hifly.himail.validation.Validator;

import static org.hifly.himail.constants.ErrorCode.ERROR_CONFIGFILE_NOTCOMPLAINT;
import static org.hifly.himail.constants.ErrorCode.ERROR_CONFIGFILE_MISSINGPROP;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SMTPConfiguration extends AbstractConfiguration {

    public Map<String,String> configMap = new HashMap<String,String>() {
        {
            put("binding_address", "127.0.0.1");
            put("port", "25");
            put("max_threads", "50");
            put("domain", "himail.CHANGEME");
        }
    };


    public SMTPConfiguration(File file) {
        super(file);
    }

    @Override
    public boolean validate() throws Exception {
        Properties properties;
        try {
            properties = readConfigFile();    //read property file
        } catch (IOException io) {
            throw new Exception(io);
        }

        int smtpKeysSize = configMap.size();
        int propsKeysSize = properties.size();
        if (smtpKeysSize != propsKeysSize) //invalid number of keys
            throw new IllegalStateException("Config file not complaint [ " + ERROR_CONFIGFILE_NOTCOMPLAINT + " ]");

        Enumeration enuKeys = properties.keys();
        while (enuKeys.hasMoreElements()) {
            String key = (String) enuKeys.nextElement();
            String value = properties.getProperty(key);
            if(!configMap.containsKey(key))  //key not expected
                throw new IllegalStateException("Config file not complaint [ " + ERROR_CONFIGFILE_MISSINGPROP + " ]");
            if(!StringUtils.isBlank(value)) { //validate values
                validateValue(key, value);
            }
        }

        super.configMap = configMap;

        return true;
    }

    @Override
    public void configure() throws Exception {
        validate(); //validation
    }

    private void validateValue(String key, String value) throws Exception {
        switch(key) {
            case "binding_address":
                if(Validator.validateIpAddress(value))
                    configMap.put(key, value);
                break;
            case "port":
                if(Validator.validateIpPort(Integer.valueOf(value)))
                    configMap.put(key, value);
                break;
            case "max_threads":
                if(Validator.validateMaxThreads(Integer.valueOf(value)))
                    configMap.put(key, value);
                break;
            case "domain":  //TODO validate domain
                configMap.put(key, value);
                break;
            default: throw new IllegalStateException("Config file not complaint [ " + ERROR_CONFIGFILE_NOTCOMPLAINT + " ]");
        }

    }

    private Properties readConfigFile() throws Exception {
        Properties properties;
        try (FileInputStream fis = new FileInputStream(getConfigFile())) {
            properties = new Properties();
            properties.load(fis);
        } catch (IOException io) {
            throw new Exception(io);
        }
        return properties;
    }
}
