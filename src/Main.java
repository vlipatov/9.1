import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String path;
        long length = 0;
        StringBuilder insideFolders = new StringBuilder();
        System.out.println("Введите путь к папке. \nФормат для ввода - C:\\Users\\username\\folderName");
        Scanner scanner = new Scanner(System.in);
        path = scanner.nextLine();
        File folder = new File(path);
        File[] files = folder.listFiles();
        for(File file : files) {
            if (file.isFile()) {
                length += file.length();
            }
            if(file.isDirectory())
            {
                insideFolders.append(file.getAbsolutePath() + "\n");
            }
        }
        long lengthKB = length/(1024);
        long lengthMB = length/((1024) * (1024));
        long lengthGB= lengthMB/(1024);
        System.out.println(length + " bytes");
        System.out.println(lengthKB + " KB");
        System.out.println(lengthMB + " MB");
        System.out.println(lengthGB + " GB");
        System.out.println(insideFolders.toString());
    }

}
