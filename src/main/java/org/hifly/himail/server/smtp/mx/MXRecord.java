package org.hifly.himail.server.smtp.mx;


public class MXRecord {

    private int priority;
    private String server;

    public MXRecord(int priority, String server) {
        this.priority = priority;
        this.server = server;
    }

    public int getPriority() {
        return priority;
    }

    public String getServer() {
        return server;
    }
}
