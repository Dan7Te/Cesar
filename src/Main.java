import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Шифруем/дешефруем? (1/0): ");
        int num = in.nextInt();
        boolean isEncrypt = (num == 1);

        System.out.println("Введите путь к файлу подгрузки: ");
        String inWay = in.next();
        System.out.println("Введите шаг");
        int step = in.nextInt();
        System.out.println("Введите путь к файлу загрузки");
        String outWay = in.next();

        if(isEncrypt) Encrypt(inWay, outWay, step);
        else Decrypt(inWay, outWay, step);

    }

    public static void Encrypt(String inWay, String outWay, int step){
        boolean isError = false;

        StringBuilder output = new StringBuilder();

        try(FileReader fr = new FileReader(inWay)){
            int i;
            while((i = fr.read()) != -1){
                if(i>64 && i<91) {
                    int a;
                    if(i+step > 90) a = 64 + (i+step-90);
                    else a = i + step;
                    output.append((char)a);
                } else if (i>96 && i<123) {
                    int a;
                    if(i+step > 122) a = 96 + (i+step-122);
                    else a = i + step;
                    output.append((char)a);
                }
                else output.append((char)i);
                //System.out.print(i + " ");
            }
        }
        catch(IOException exc){
            System.out.println("Произошла ошибка чтения");
            isError = true;
        }


        try(FileWriter writer = new FileWriter(outWay, false)){
            writer.write(output.toString());
        }
        catch(IOException exc){
            System.out.println("Произошла ошибка записи");
            isError = true;
        }

        if(isError) System.out.println("Программа завершила работу с ошибкой");
        else System.out.println("Программа корректно завершила работу");
    }

    public static void Decrypt(String inWay, String outWay, int step){
        boolean isError = false;

        StringBuilder output = new StringBuilder();

        try(FileReader fr = new FileReader(inWay)){
            int i;
            while((i = fr.read()) != -1){
                if(i>64 && i<91) {
                    int a;
                    if(i-step < 65) a = 90 - (i-step+65);
                    else a = i - step;
                    output.append((char)a);
                } else if (i>96 && i<123) {
                    int a;
                    if(i-step < 97) a = 123 - (i+step) + 97;
                    else a = i - step;
                    output.append((char)a);
                }
                else output.append((char)i);
            }
        }
        catch(IOException exc){
            System.out.println("Произошла ошибка чтения");
            isError = true;
        }


        try(FileWriter writer = new FileWriter(outWay, false)){
            writer.write(output.toString());
        }
        catch(IOException exc){
            System.out.println("Произошла ошибка записи");
            isError = true;
        }

        if(isError) System.out.println("Программа завершила работу с ошибкой");
        else System.out.println("Программа корректно завершила работу");
    }
}