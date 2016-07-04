package org.hifly.himail.server.smtp.constants;


public enum SMTPCode {

    SERVICE_READY(220, "Wow you're using himail as SMTP server!"),
    SERVICE_CLOSING(221, "Service closing transmission channel"),
    REQ_MAIL_OK(250, ""),
    START_MAIL_INPUT(354, "Start mail input; end with <CRLF>.<CRLF>"),
    ACTION_ABORTED(451, "Requested action aborted: local error in processing"),
    STORAGE_INSUFF(452, "Requested action not taken: insufficient system storage"),
    PAR_ERROR(455, "Server unable to accommodate parameters"),
    CMD_NOTIMPL(502, "Command not implemented"),
    CMD_ERROR(503, "Bad sequence of commands"),
    CMD_PAR_NOTIMPL(504, "Command parameter not implemented"),
    ACTION_NOTTAKEN(550, "Requested action not taken"),
    STORAGE_EXCEED(552, "Requested mail action aborted: exceeded storage allocation"),
    MAILBOX_NAME_ERROR(553, "Requested action not taken: mailbox name not allowed"),
    TRANS_FAILED(554, "SMTP service not available"),
    MAIL_CMD_NOTIMPL(555, "MAIL FROM/RCPT TO parameters not recognized or not implemented");

    private final int code;
    private final String desc;

    private SMTPCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
