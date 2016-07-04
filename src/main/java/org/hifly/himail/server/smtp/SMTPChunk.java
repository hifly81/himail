package org.hifly.himail.server.smtp;

import java.io.BufferedOutputStream;
import java.io.OutputStream;

public class SMTPChunk {

    private final SMTPConfiguration configuration;
    private final OutputStream out;
    private StringBuilder buffer;
    private String inputMessage;
    private boolean inputMode = false;

    public SMTPChunk(SMTPConfiguration configuration, OutputStream out)  {
        this.configuration = configuration;
        this.out = new BufferedOutputStream(out);
        this.buffer = new StringBuilder();
    }

    public boolean isInputMode() {
        return inputMode;
    }

    public void setInputMode(boolean inputMode) {
        this.inputMode = inputMode;
    }

    public SMTPConfiguration getConfiguration() {
        return configuration;
    }

    public OutputStream getOut() {
        return out;
    }

    public String getInputMessage() {
        return inputMessage;
    }

    public void setInputMessage(String inputMessage) {
        this.inputMessage = inputMessage;
    }

    public StringBuilder getBuffer() {
        return buffer;
    }

}
