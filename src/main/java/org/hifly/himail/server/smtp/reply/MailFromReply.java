package org.hifly.himail.server.smtp.reply;


import org.hifly.himail.server.smtp.SMTPMessage;
import org.hifly.himail.server.smtp.SMTPChunk;

import static org.hifly.himail.constants.Constants.END_COMMAND;
import static org.hifly.himail.constants.Constants.WHITE_SPACE;
import static org.hifly.himail.constants.Constants.OK;
import static org.hifly.himail.server.smtp.constants.SMTPCode.REQ_MAIL_OK;

public class MailFromReply extends SMTPReply {

    public MailFromReply(SMTPChunk chunk, SMTPMessage message) throws Exception {
        super(chunk, message);
    }

    @Override
    public void send() throws Exception {
        //TODO configure strings
        //TODO define charset
        //TODO extract from
        message.setFrom("LOREM IPSUM");
        chunk.getBuffer().append(REQ_MAIL_OK.getCode());
        chunk.getBuffer().append(WHITE_SPACE);
        chunk.getBuffer().append(OK);
        //TODO prepare mailbox
        chunk.getBuffer().append(END_COMMAND);
    }
}
