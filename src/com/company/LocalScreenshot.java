package com.company;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LocalScreenshot extends Thread{

    // Формат времени
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");

    @Override
    public void run() {
        // Бесконечный цикл
        for (; ; ) {
            try {
                // Время: сейчас
                Date CurrentTimestamp = new Date();
                // Текущая дирректория проета
                String thisDir = System.getProperty("user.dir");

                // Создание каталога для хранения кадров
                File dir = new File(thisDir + "\\Screen");
                dir.mkdir();

                String name = formatter.format(CurrentTimestamp) + ".png";

                // Для создания файла в текущем каталоге проекта,
                // в качестве родительского параметра необходимо написать "."
                File grabedScreen = new File(dir, name);

                ImageIO.write(grabScreen(), "png", grabedScreen);
                sleep(5000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // Возвращает дирректория рабочего стола
    private static File getHomeDir() {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        return fsv.getHomeDirectory();
    }

    //
    private static BufferedImage grabScreen() {
        try {
            // Создается объект типа Rectangle (прямоуголиник) характеризующийся:
            // началом координат (левый-верхний угол), шириной и высотой.
            // В качестве параметра в конструктор передается объект типа Dimension из пакета java.awt,
            // содержащий поля 	height и width. В данном случае отсчет ведется от начала координат.
            // Метод createScreenCapture - принимает в качестве параметра объект Rectangle,
            // определяющий формат захватываемого кадра.
            // В качестве возвращаемого значения объект типа BufferedImage (захваченное изображение, без захвата курсора).
            return new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())) ;
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return null;
    }
}
