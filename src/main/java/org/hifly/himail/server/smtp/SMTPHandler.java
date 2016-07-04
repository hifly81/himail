package org.hifly.himail.server.smtp;

import org.hifly.himail.server.smtp.reply.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.Callable;

public class SMTPHandler implements Callable<SMTPMessage> {

    private static Logger log = LoggerFactory.getLogger(SMTPServer.class);

    private final Socket connection;
    private final SMTPChunk smtpChunk;
    private final SMTPMessage message;
    private final StringBuilder buffer = new StringBuilder(); //buffer for this handler
    private String uuid;

    public SMTPHandler(Socket connection, SMTPConfiguration configuration) throws Exception {
        this.connection = connection;
        this.uuid = UUID.randomUUID().toString(); //generate UUID
        this.smtpChunk = new SMTPChunk(configuration, connection.getOutputStream());
        this.message = new SMTPMessage(this.uuid); //smtp message
    }

    @Override
    public SMTPMessage call() throws Exception {
        try {
            new WelcomeReply(this.smtpChunk, this.message).send(); //welcome command
            dispatchIngoingMessage(); //main loop waiting for msg
        } catch (IOException ex) {
            //TODO define exception
        } finally {
            connection.close();
        }
        return null;
    }

    public void dispatchIngoingMessage() throws Exception {
        InputStream in = connection.getInputStream();
        byte[] b_buffer = new byte[2048]; //generic buffer to get the input data
        int charsRead;
        boolean ingoing = true;
        while (ingoing && !smtpChunk.isInputMode()) {
            while ((charsRead = in.read(b_buffer)) != -1) {
                this.smtpChunk.setInputMessage(new String(b_buffer).substring(0, charsRead));
                SMTPReply command = SMTPReplyFactory.exec(this.smtpChunk, this.message);   //delegate to command
                if (command instanceof QuitReply) {
                    ingoing = false;
                    break; //exit inner loop
                }
                if (command instanceof DataReply) {
                    smtpChunk.setInputMode(true);  //set input mode
                    break; //exit inner loop
                }
            }

            if (smtpChunk.isInputMode()) {
                BufferedReader bin = new BufferedReader(new InputStreamReader(in));
                String line;
                StringBuilder inputData = new StringBuilder();
                while (!(line = bin.readLine()).equals(".")) {
                    inputData.append(line);
                    inputData.append("\n"); //append new line
                }
                this.smtpChunk.setInputMessage(inputData.toString());
                SMTPReplyFactory.exec(this.smtpChunk, this.message);
                smtpChunk.setInputMode(false);      //unset input mode
            }

        }
    }
}

