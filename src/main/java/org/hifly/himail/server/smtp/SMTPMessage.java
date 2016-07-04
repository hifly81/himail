package org.hifly.himail.server.smtp;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SMTPMessage {

    public enum SMTPRecipient {
        TO, CC, BCC;
    }

    private final String uuid;
    private String from;
    private List<String> to;
    private List<String> cc;
    private List<String> bcc;
    private String subject;
    private Date date;
    private SMTPBody body;

    public SMTPMessage(String uuid) {
        this.uuid = uuid;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public SMTPBody getBody() {
        return body;
    }

    public void setBody(SMTPBody body) {
        this.body = body;
    }

    public String getUuid() {
        return uuid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public void addRecipient(String newRecipient, SMTPRecipient type) {
        //TODO refactor
        if(type == SMTPRecipient.TO) {
            if(to == null)
                to = new ArrayList();
            to.add(newRecipient);
        }
        else if(type == SMTPRecipient.CC) {
            if(cc == null)
                cc = new ArrayList();
            cc.add(newRecipient);
        }
        else if(type == SMTPRecipient.BCC) {
            if(bcc == null)
                bcc = new ArrayList();
            bcc.add(newRecipient);
        }
    }
}
