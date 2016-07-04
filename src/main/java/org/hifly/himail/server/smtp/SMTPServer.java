package org.hifly.himail.server.smtp;

import org.hifly.himail.core.BasicThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class SMTPServer {

    private static Logger log = LoggerFactory.getLogger(SMTPServer.class);

    private final SMTPConfiguration configuration;

    public SMTPServer(SMTPConfiguration configuration) {
        this.configuration = configuration;
    }

    public void start() throws Exception {
        //TODO pool must be configurable
        Map<String, String> configMap =  this.configuration.getConfigMap();
        BasicThreadPool pool = new BasicThreadPool(Integer.valueOf(configMap.get("max_threads")));
        //TODO Server Socket with InetAddress
        try (ServerSocket server = new ServerSocket(Integer.valueOf(configMap.get("port")))) {
            log.info("Server ready: accepting connections on port " + server.getLocalPort());
            while (true) {  //main loop
                try {
                    Socket connection = server.accept();
                    pool.submit(new SMTPHandler(connection, this.configuration)); //delegate to worker thread
                } catch (IOException ex) {
                    //TODO define exception
                } catch (RuntimeException ex) {
                    //TODO define exception
                }
            }
        } catch (Exception e) {
            //TODO define exception
            throw new Exception(e);
        }
    }

    public static void main(String [] args) throws Exception {
        ClassLoader classLoader = SMTPServer.class.getClassLoader();
        File file = new File(classLoader.getResource("smtp.properties").getFile());
        SMTPConfiguration conf = new SMTPConfiguration(file);
        conf.configure();
        SMTPServer server = new SMTPServer(conf);
        server.start();

    }
}

