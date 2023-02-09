//Напишите программу, которая принимает с консоли число в формате byte и записывает его в файл ”result.txt”.
//Требуется перехватить все возможные ошибки и вывести их в логгер. (от -128 до 127)
//После написания, попробуйте подать на вход числа 100 и 200 и проследите разницу в результате



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.IOException;
import java.util.logging.*;




public class Main{


    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void log(Exception e, String msg) {
        FileHandler fh = null;
        SimpleFormatter sf = new SimpleFormatter();
        try {
            fh = new FileHandler("log.txt", true);
            logger.addHandler(fh);
            fh.setFormatter(sf);
            logger.log(Level.SEVERE, e.toString());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            if (fh != null) fh.close();
        }
    }

    public static byte[] get_byte_num() {
        byte[] res = new byte[1];
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите число: ");
        try {
            byte num = scn.nextByte();
            res[0] = num;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception: ", e);
        }
        return res;
    }

    public static void write_to_file(byte[] num) throws FileNotFoundException {
        try {
            FileOutputStream file = new FileOutputStream ("result.txt",  true);
            file.write(num);
        } catch (Exception e) {
            logger.log(Level.SEVERE,  "Exception: ", e);
        }
    }
    public static void main (String[] args) throws FileNotFoundException {
        byte[] input_byte = get_byte_num();
        try {
            write_to_file(input_byte);
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "Exception: ", e);
        }
    }
}