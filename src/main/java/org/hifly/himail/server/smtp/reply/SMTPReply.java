package org.hifly.himail.server.smtp.reply;

import org.hifly.himail.server.smtp.SMTPMessage;
import org.hifly.himail.server.smtp.SMTPChunk;

public abstract class SMTPReply {

    protected final SMTPChunk chunk;
    protected final SMTPMessage message;

    public SMTPReply(SMTPChunk chunk, SMTPMessage message) throws Exception {
        chunk.getBuffer().setLength(0); //buffer init
        this.chunk = chunk;
        this.message = message;

        send(); //send reply

        byte [] array = chunk.getBuffer().toString().getBytes();     //write to out stream
        chunk.getOut().write(array);
        chunk.getOut().flush();
    }

    public abstract void send() throws Exception;


}
