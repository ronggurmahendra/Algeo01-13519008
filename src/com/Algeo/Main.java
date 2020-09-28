package com.Algeo;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Menu();
    }
    public static void Menu(){
        System.out.println("MENU");
        System.out.println("1. Sistem Persamaan Linier");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Regresi linier berganda");
        System.out.println("6. Keluar");

        Scanner menu = new Scanner(System.in);
        int input = menu.nextInt();
        switch (input){
            case 1:
                //program SPL
                System.out.println("1. Metode eliminasi Gauss");
                System.out.println("2. Metode eliminasi Gauss-Jordan");
                System.out.println("3. Metode matriks balikan");
                System.out.println("4. Kaidah Cramer");
                int inputSPL = menu.nextInt();
                switch (inputSPL){
                    case 1:
                        /* pilih masukan dari keyboard atau file text */
						System.out.println("Pilih metode masukan: ");
						System.out.println("1. Masukan dari keyboard");
						System.out.println("2. Masukan dari file txt");
						System.out.println("-------------------------");
						System.out.print("Metode masukan: ");
						int opsi = sc.nextInt();
						
						if (opsi == 1) {
							/* masukan jumlah baris dan kolom */
							System.out.print("Masukkan banyak persamaan: ");
							int baris = sc.nextInt();
							System.out.print("Masukkan banyak variabel: ");
							int kolom = sc.nextInt()+1;
							/* membuat matriks */
							MatSPL M = new MatSPL();
							double[][] Mat = new double [baris][kolom];
							/* membaca isi matriks */
							M.isiMatriks(Mat, baris, kolom);
							/* menampilkan solusi SPL */
							M.solusiSPLGauss(Mat, baris, kolom);
						}
						// else {} -- belum :)

                        break;
                    case 2:
                        //masukin program SPL Metode eliminasi Gauss-Jordan

                        break;
                    case 3:
                        //masukin program SPL Metode matriks balikan

                        break;
                    case 4:
                        //masukin program SPL Kaidah Cramer

                        break;
                }


                break;
            case 2:
                //program Determinan
                System.out.println("1.Metode kofaktor");
                System.out.println("2. Metode OBE");
                Scanner scannerDeterminan = new Scanner(System.in);
                int inputDeterminan = scannerDeterminan.nextInt();
                Determinan determinan = new Determinan();
                double[][] matriks;
                matriks = determinan.bacaMatriks();
                //determinan.tulisMatriks(matriks);
                if (inputDeterminan == 1){
                    System.out.print("Determinan Matriks dengan kofaktor = ");
                    System.out.println(determinan.kofaktor(matriks));
                }else if (inputDeterminan == 2){
                    System.out.print("Determinan Matriks dengan OBE = ");
                    System.out.println(determinan.OBE(matriks));
                }
                break;
            case 3:
                //program Matriks Balikan

                break;
            case 4:
                //program Interpolasi Polinom

                break;
            case 5:
                //program Regresi linier berganda

                break;
            case 6:
                //Keluar
                System.exit(0);
                break;

        }
    }
}

