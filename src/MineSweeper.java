import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    private final Scanner scan = new Scanner(System.in);
    private final Random random = new Random();
    private int col;
    private int row;
    private int mine;
    private String[][] mayintarlasi;
    private String[][] frame;


    public void run() {


        System.out.println("====  Mayın Tarlasına Hoşgeldiniz!  ====");
        System.out.print("Sütun sayısı belirleyin:");
        row = scan.nextInt();
        System.out.print("Satır sayısı belirleyin:");
        col = scan.nextInt();
        mine = (col * row) / 4;
        mayintarlasi = new String[row][col];
        frame = new String[row][col];

        for (int i = 0; i < mayintarlasi.length; i++) {
            for (int j = 0; j < mayintarlasi[i].length; j++) {
                mayintarlasi[i][j] = "-";
                frame[i][j] = "-";
            }
        }


        while (mine > 0) {
            int i = random.nextInt(row);
            int j = random.nextInt(col);
            if (mayintarlasi[i][j].equals("-")) {
                mayintarlasi[i][j] = "*";
                mine--;
            }

        }

        printFrame();

        oyna();
    }

    private void oyna() {
        boolean finish = false;

        while (!finish) {
            System.out.print("Satır girin:");
            int srcRow = scan.nextInt();
            System.out.print("Sütun girin:");
            int srcCol = scan.nextInt();
            int sayi = 0;

            if (srcRow < row && srcCol < col) {

                if (mayintarlasi[srcRow][srcCol].equals("-") && frame[srcRow][srcCol].equals("-")) {

                    for (int i = srcRow - 1; i < srcRow + 2; i++) {

                        for (int j = srcCol - 1; j < srcCol + 2; j++) {

                            if (i >= 0 && j >= 0 && i < row && j < col && mayintarlasi[i][j].equals("*")) {

                                sayi++;
                                frame[srcRow][srcCol] = Integer.toString(sayi);

                            } else {
                                frame[srcRow][srcCol] = Integer.toString(sayi);
                            }



                    }


                }
                printFrame();

                if (!cheackWin()) {
                    System.out.println("Tebrikler oyunu başarıyla tamamladınız.");
                    printMine();
                    finish = true;
                }


            } else if (mayintarlasi[srcRow][srcCol].equals("*")) {
                System.out.println("Girdiğiniz kordinatlarda mayın bulunmaktadır!!!");
                System.out.println("Oyunu kaybettiniz!!!");
                printMine();
                finish = true;

            } else if (!frame[srcRow][srcCol].equals("-")) {
                System.out.println("Girdiğiniz kordinatları daha önce girdiniz.");
                System.out.println("Lütfen başka kordinat giriniz");


            }
        }
        else{
            System.out.println("Belirlediğiniz kordinatlar içerisinde bir kordinat girin ya da oyunu kapatıp"
                    + "\n" + " daha büyük bir harita belirleyin ");
        }


    }

}
    private void printFrame() {
        System.out.println("======================");
        for (int i = 0; i < frame.length; i++) {
            for (int j = 0; j < frame[i].length; j++) {

                System.out.print(frame[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("======================");
    }


    private void printMine() {
        System.out.println("======================");
        for (int i = 0; i < mayintarlasi.length; i++) {
            for (int j = 0; j < mayintarlasi[i].length; j++) {

                System.out.print(mayintarlasi[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("======================");
    }


    private boolean cheackWin() {
        int count = 0;
        int sayac = 0;
        for (int i = 0; i < frame.length; i++) {
            for (int j = 0; j < frame[i].length; j++) {
                if (frame[i][j] == "-") {
                    count++;
                }
                if (mayintarlasi[i][j] == "*") {
                    sayac++;
                }

            }

        }
        if (sayac == count) {
            return false;
        }


        return true;
    }


}



