package com.wix.traitsoft.tpo_mnnit;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by saksham_ on 30-Dec-17.
 */

public class Httpurl {

        private Httpurl() {
        }

        private static HttpURLConnection httpURLConnection;

        public static HttpURLConnection getConnection(URL url) {
            if (httpURLConnection == null) {
                try {
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    return httpURLConnection;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else
                return httpURLConnection;
            return null;
        }
}
