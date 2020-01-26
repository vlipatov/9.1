import java.io.File;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String path;
        float length;
        System.out.println("Введите путь к папке. \nФормат для ввода - C:\\Users\\username\\folderName");
        Scanner scanner = new Scanner(System.in);
        path = scanner.nextLine();
        File folder = new File(path);
        try {
            length = folderSizeCalc(folder);
            System.out.println("Размер всех файлов и папок в даннной директории - " + getLengthFormatted(length));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        long size  = sizeCalOneLine(path);

        System.out.println("Размер при расчете в одну строку - " + getLengthFormatted(size));

    }
//======================================================================================================================
    /** Расчет размера в одну строку*/
    private static long sizeCalOneLine(String path) throws IOException {
        long size = Files.walk(Paths.get(path))
                .filter(f -> f.toFile().isFile())
                .mapToLong(f -> f.toFile().length())
                .sum();

        return size;
    }
    
//======================================================================================================================
    /** Написал расчет размера в отдельном методе*/
    private static long folderSizeCalc(File file) {
        long size = 0;
        if (file.isFile()) {
            size = file.length();
        } else if (file.isDirectory()) {
            File[] subDir = file.listFiles();
            for (File f : subDir) {
                if (f.isFile()) {
                    size += f.length();
                } else {
                    size += folderSizeCalc(f);
                }
            }
        }
        return size;
    }
//======================================================================================================================
    /**Также в отдельном методе сделал вывод в читаемом формате*/
    private static String getLengthFormatted(float length) {
        DecimalFormat df = new DecimalFormat("0.00");
        float lengthKB = 1024.0f;
        float lengthMB = lengthKB * lengthKB;
        float lengthGB = lengthMB * lengthKB;
        float lengthTB = lengthGB * lengthKB;
        if (length < lengthMB)
            return df.format(length / lengthKB) + " Kb";
        else if (length < lengthGB)
            return df.format(length / lengthMB) + " Mb";
        else if (length < lengthTB)
            return df.format(length / lengthGB) + " Gb";
        return df.format(length / lengthTB) + " Tb";
    }
}
