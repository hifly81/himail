package org.hifly.himail.server.smtp.reply;


import org.hifly.himail.server.smtp.SMTPMessage;
import org.hifly.himail.server.smtp.SMTPChunk;

import static org.hifly.himail.constants.Constants.*;
import static org.hifly.himail.server.smtp.constants.SMTPCode.START_MAIL_INPUT;

public class DataReply extends SMTPReply {

    public DataReply(SMTPChunk chunk, SMTPMessage message) throws Exception {
        super(chunk, message);
    }

    @Override
    public void send() throws Exception {
        //TODO configure strings
        //TODO define charset
        chunk.getBuffer().append(START_MAIL_INPUT.getCode());
        chunk.getBuffer().append(WHITE_SPACE);
        chunk.getBuffer().append(START_MAIL_INPUT.getDesc());
        chunk.getBuffer().append(END_COMMAND);
    }
}
