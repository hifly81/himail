package org.hifly.himail.server.smtp.reply;

import org.hifly.himail.server.smtp.SMTPMessage;
import org.hifly.himail.server.smtp.SMTPChunk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hifly.himail.constants.Constants.WHITE_SPACE;

public class SMTPReplyFactory {

    private static Logger log = LoggerFactory.getLogger(SMTPReplyFactory.class);

    public static SMTPReply exec(SMTPChunk chunk, SMTPMessage message) throws Exception {
        SMTPReply smtpReply = null;
        String uuid = message.getUuid();
        String inputMessage = chunk.getInputMessage();

        if (chunk.isInputMode()) {
            //TODO validate data input
            log.debug("[" + uuid + "] data input completed!");
            log.debug("[" + uuid + "] data input dump:" + inputMessage);
            smtpReply = new DataTransferredReply(chunk, message);
        } else {
            inputMessage = inputMessage.replaceAll("\\n", "");  //trim carriage return
            inputMessage = inputMessage.replaceAll("\\r", "");
            inputMessage = inputMessage.toLowerCase();

            log.debug("[" + uuid + "] Received message: " + inputMessage);

            if (inputMessage.equals("quit")) {
                log.info("[" + uuid + "] Ending conversation...");
                smtpReply = new QuitReply(chunk, message);
            } else if (inputMessage.startsWith("helo" + WHITE_SPACE)) {
                //TODO validate helo
                log.info("[" + uuid + "] Starting conversation...");
                smtpReply = new HeloReply(chunk, message);
            } else if (inputMessage.startsWith("ehlo" + WHITE_SPACE)) {
                //TODO validate ehlo
                log.info("[" + uuid + "] Starting conversation...");
                smtpReply = new EHloReply(chunk, message);
            } else if (inputMessage.startsWith("mail from:")) {
                //TODO validate mail from
                smtpReply = new MailFromReply(chunk, message);
            } else if (inputMessage.startsWith("rcpt to:")) {
                //TODO validate rcpt to
                smtpReply = new RcptToReply(chunk, message);
            } else if (inputMessage.equals("data")) {
                //TODO validate data
                smtpReply = new DataReply(chunk, message);
            } else if(inputMessage.startsWith("vrfy" + WHITE_SPACE)) {
                //TODO validate vrfy
                smtpReply = new VrfyReply(chunk, message);
            } else if(inputMessage.startsWith("rset")) {
                //TODO validate rset
                smtpReply = new RsetReply(chunk, message);
            }
            else {
                log.info("[" + uuid + "] Message not valid!");
            }

        }

        return smtpReply;
    }
}
