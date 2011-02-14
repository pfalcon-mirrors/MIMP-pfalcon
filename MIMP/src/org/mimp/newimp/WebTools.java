package org.mimp.newimp;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLConnection;

public class WebTools {
    
    public static byte[] download(URLConnection cnx) {
        byte[] dat = null;
        try {
            InputStream is = cnx.getInputStream();
            int len = cnx.getContentLength();
            if (len < 0) {
                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                byte[] buf = new byte[4096];
                for (;;) {
                    int nb = is.read(buf);
                    if (nb <= 0)
                        break;
                    bao.write(buf, 0, nb);
                }
                dat = bao.toByteArray();
                bao.close();
            }
            else {
                dat = new byte[len];
                int i = 0;
                while (i < len) {
                    int n = is.read(dat, i, len - i);
                    if (n <= 0)
                        break;
                    i += n;
                }
            }
            is.close();
        }
        catch (Exception ex) {
        }
        return dat;
    }
}
