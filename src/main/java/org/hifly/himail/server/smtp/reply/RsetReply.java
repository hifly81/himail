package org.hifly.himail.server.smtp.reply;


import org.hifly.himail.server.smtp.SMTPChunk;
import org.hifly.himail.server.smtp.SMTPMessage;

import static org.hifly.himail.constants.Constants.*;
import static org.hifly.himail.server.smtp.constants.SMTPCode.REQ_MAIL_OK;

public class RsetReply extends SMTPReply {

    public RsetReply(SMTPChunk chunk, SMTPMessage message) throws Exception {
        super(chunk, message);
    }

    @Override
    public void send() throws Exception {
        //TODO configure strings
        //TODO define charset
        //TODO reset mail conversation status
        chunk.getBuffer().append(REQ_MAIL_OK.getCode());
        chunk.getBuffer().append(WHITE_SPACE);
        chunk.getBuffer().append(OK);
        chunk.getBuffer().append(END_COMMAND);
    }
}
