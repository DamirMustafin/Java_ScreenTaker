package com.company;

public class Main {

    public static void main(String[] args) {

        LocalScreenshot LSh = new LocalScreenshot();
        DropboxScrenshot DSh = new DropboxScrenshot();

        LSh.start();
        DSh.start();
    }
}
