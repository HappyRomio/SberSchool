package com.sbt.homework.les4Exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main {

    public static void main(String... args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String url;
        boolean flag = true;
        while(flag){
            System.out.println("Enter url: ");
            try{
                url=br.readLine();
                readContent(url);
                flag = false;
            }catch (IOException e){
                e.printStackTrace();
                flag = true;
            }
        }
    }

    public static void readContent(String url) throws IOException {
        URL address = new URL(url);
        URLConnection connection = address.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while((line = reader.readLine()) != null){
            System.out.println(line);
        }
        reader.close();
    }
}
