package org.hifly.himail.server.smtp.reply;


import org.hifly.himail.server.smtp.SMTPMessage;
import org.hifly.himail.server.smtp.SMTPChunk;

import static org.hifly.himail.constants.Constants.END_COMMAND;
import static org.hifly.himail.constants.Constants.WHITE_SPACE;
import static org.hifly.himail.server.smtp.constants.SMTPCode.SERVICE_READY;

public class WelcomeReply extends SMTPReply {


    public WelcomeReply(SMTPChunk chunk, SMTPMessage message) throws Exception {
        super(chunk, message);
    }

    @Override
    public void send() throws Exception {
        //TODO configure strings
        //TODO define charset
        chunk.getBuffer().append(SERVICE_READY.getCode());
        chunk.getBuffer().append(WHITE_SPACE);
        chunk.getBuffer().append(chunk.getConfiguration().getConfigMap().get("domain"));
        chunk.getBuffer().append(WHITE_SPACE);
        chunk.getBuffer().append(SERVICE_READY.getDesc());
        chunk.getBuffer().append(END_COMMAND);
    }

}
