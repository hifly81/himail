package org.hifly.himail.server.smtp.reply;


import org.hifly.himail.server.smtp.SMTPMessage;
import org.hifly.himail.server.smtp.SMTPChunk;
import org.hifly.himail.server.smtp.constants.SMTPExtension;

import static org.hifly.himail.constants.Constants.END_COMMAND;
import static org.hifly.himail.constants.Constants.MINUS;
import static org.hifly.himail.server.smtp.constants.SMTPCode.REQ_MAIL_OK;

public class EHloReply extends SMTPReply {

    public EHloReply(SMTPChunk chunk, SMTPMessage message) throws Exception {
        super(chunk, message);
    }

    @Override
    public void send() throws Exception {
        //TODO configure strings
        //TODO define charset
        chunk.getBuffer().append(REQ_MAIL_OK.getCode());
        chunk.getBuffer().append(MINUS);
        chunk.getBuffer().append(chunk.getConfiguration().getConfigMap().get("domain"));
        chunk.getBuffer().append(END_COMMAND);
        //TODO define the extension supported
        for (SMTPExtension ext : SMTPExtension.values()) {
            chunk.getBuffer().append(REQ_MAIL_OK.getCode());
            chunk.getBuffer().append(MINUS);
            chunk.getBuffer().append(ext.name());
            chunk.getBuffer().append(END_COMMAND);
        }
    }
}
