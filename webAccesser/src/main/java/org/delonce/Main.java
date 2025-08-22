package org.delonce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class Main {
    public static String test = "test val";

    public static void main( String[] args ) throws IOException {
        test = "hello value";
        User user1 = new User("Alex", "passwd");
        fetch("http://www.google.com");
        fetch("http://www.vk.com");
        fetch("http://www.mail.ru");
    }

    private static void fetch(final String address)
            throws MalformedURLException, IOException {

        final URL url = new URL(address);
        final URLConnection connection = url.openConnection();

        try( final BufferedReader in = new BufferedReader(
                new InputStreamReader( connection.getInputStream() ) ) ) {

            String inputLine = null;
            final StringBuffer sb = new StringBuffer();
            while ( ( inputLine = in.readLine() ) != null) {
                sb.append(inputLine);
            }

            System.out.println("Content size: " + sb.length());
        }
    }
}

