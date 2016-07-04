package org.hifly.himail.server.smtp.mx;


import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MailServerLookup {

    public List<MXRecord> getServer(String domain) throws Exception {
        InitialDirContext context = new InitialDirContext();
        Attributes attributes = context.getAttributes("dns:/" + domain, new String[] {"MX"});
        Attribute mx = attributes.get("MX");
        MXRecord mxRecord;
        if (mx == null) {
            mxRecord = new MXRecord(1, domain);
            return Arrays.asList(mxRecord);
        }

        List<MXRecord> mxRecords = new ArrayList(mx.size());
        for (int i = 0; i < mx.size(); i++) {
            String [] parts = ((String)mx.get(i)).split("\\s+");
            mxRecord = new MXRecord(Integer.valueOf(parts[0]),
                    parts[1].endsWith(".") ? parts[1].substring(0, parts[1].length() - 1) : parts[1]);
            mxRecords.add(mxRecord);
        }

        return mxRecords;
    }

}
