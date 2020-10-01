package com.Algeo;
import java.util.Scanner;
import java.io.*;

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
        Scanner sc = new Scanner(System.in);
        System.out.print("Pilih menu : ");
        int input = menu.nextInt();
        switch (input){
            case 1:
                //program SPL
                System.out.println("1. Metode eliminasi Gauss");
                System.out.println("2. Metode eliminasi Gauss-Jordan");
                System.out.println("3. Metode matriks balikan");
                System.out.println("4. Kaidah Cramer");
                System.out.print("Pilih metode : ");
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
						/* masukan jumlah baris dan kolom */
							System.out.print("Masukkan banyak persamaan: ");
							int baris = sc.nextInt();
							System.out.print("Masukkan banyak variabel: ");
							int kolom = sc.nextInt()+1;
							/* membuat matriks */
							MatSPL M = new MatSPL();
							double[][] Mat = new double [baris][kolom];
						if (opsi == 1) {
							/* membaca isi matriks */
							M.isiMatriks(Mat, baris, kolom);
							/* menampilkan solusi SPL */
							M.solusiSPLGauss(Mat, baris, kolom);
						} 
						else {
							/*membaca dari file */
							System.out.print("Masukkan alamat file : ");
							String alamat;
							alamat = sc.next();
							Scanner inFile = null;
							try {
								inFile = new Scanner(new File(alamat));
								int i = 0;
								while (inFile.hasNext()) {
									for (int j=0; j < kolom; j++){
										Mat[i][j] = inFile.nextDouble();
									} 
									i++;
								}
								inFile.close();
							} catch (FileNotFoundException e) {
								System.out.println("File tidak ditemukan");
							}
							/* menampilkan solusi SPL */
							M.solusiSPLGauss(Mat, baris, kolom);
						}
					 break;
                    case 2:
                        /* pilih masukan dari keyboard atau file text */
						System.out.println("Pilih metode masukan: ");
						System.out.println("1. Masukan dari keyboard");
						System.out.println("2. Masukan dari file txt");
						System.out.println("-------------------------");
						System.out.print("Metode masukan: ");
						opsi = sc.nextInt();
						/* masukan jumlah baris dan kolom */
						System.out.print("Masukkan banyak persamaan: ");
						int baris = sc.nextInt();
						System.out.print("Masukkan banyak variabel: ");
						int kolom = sc.nextInt()+1;
						/* membuat matriks */
						MatSPL M = new MatSPL();
						double[][] Mat = new double [baris][kolom];
						if (opsi == 1) {
							/* membaca isi matriks */
							M.isiMatriks(Mat, baris, kolom);
							/* menampilkan solusi SPL */
							M.solusiSPLGaussJordan(Mat, baris, kolom);
						} 
						else {
							/* membaca dari file */
							System.out.print("Masukkan alamat file : ");
							String alamat;
							alamat = sc.next();
							Scanner inFile = null;
							try {
								inFile = new Scanner(new File(alamat));
								int i = 0;
								while (inFile.hasNext()) {
									for (int j=0; j < kolom; j++){
										Mat[i][j] = inFile.nextDouble();
									} 
									i++;
								}
								inFile.close();
							} catch (FileNotFoundException e) {
								System.out.println("File tidak ditemukan");
							}
							/* menampilkan solusi SPL */
							M.solusiSPLGaussJordan(Mat, baris, kolom);
						}
					 break;
                    case 3:
                        //masukin program SPL Metode matriks balikan
                        SPLMatrixBalikan SPLInverse = new SPLMatrixBalikan();
                        SPLInverse.SPLinverse();

                        break;
                    case 4:
                        /* pilih masukan dari keyboard atau file text */
						System.out.println("Pilih metode masukan: ");
						System.out.println("1. Masukan dari keyboard");
						System.out.println("2. Masukan dari file txt");
						System.out.println("-------------------------");
						System.out.print("Metode masukan: ");
						opsi = sc.nextInt();
						/* masukan jumlah baris dan kolom */
						System.out.print("Masukkan banyak persamaan dan variabel: ");
						int baris = sc.nextInt();
						int kolom = baris+1;
						/* membuat matriks */
						MatSPL M = new MatSPL();
						double[][] Mat = new double [baris][kolom];		
						if (opsi == 1) {
							/* membaca isi matriks */
							M.isiMatriks(Mat, baris, kolom);
							/* menampilkan solusi SPL */
							M.solusiSPLCramer(Mat, baris, kolom);
						} 
						else {
							/* membaca dari file */
							System.out.print("Masukkan alamat file : ");
							String alamat;
							alamat = sc.next();
							Scanner inFile = null;
							try {
								inFile = new Scanner(new File(alamat));
								int i = 0;
								while (inFile.hasNext()) {
									for (int j=0; j < kolom; j++){
										Mat[i][j] = inFile.nextDouble();
									} 
									i++;
								}
								inFile.close();
							} catch (FileNotFoundException e) {
								System.out.println("File tidak ditemukan");
							}
							/* menampilkan solusi SPL */
							M.solusiSPLCramer(Mat, baris, kolom);
						}
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
                Scanner in = new Scanner (System. in);
                System.out.println("Pilih jenis input : ");
                System.out.println("1. Input Keyboard ");
                System.out.println("2. Input File ");
                System.out.print("Jenis input : ");
                input = in.nextInt();
                if (input == 1){
                    matriks = determinan.bacaMatriks();
                }else {
                    matriks= determinan.bacaFile();
                }

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
                MatrixBalikan Inverse = new MatrixBalikan();
                Inverse.MainInverse();

                break;
            case 4:
                //program Interpolasi Polinom
                interpolasi interpolasi = new interpolasi();
                interpolasi.inter();

                break;
            case 5:
                //program Regresi linier berganda
                RegresiLinear regresiLinear = new RegresiLinear();
                Scanner scannerDeterminan = new Scanner(System.in);
                //Determinan wkwk = new Determinan();
                double[][] matriksRegresi;
                System.out.println("Pilih jenis input : ");
                System.out.println("1. Input Keyboard ");
                System.out.println("2. Input File ");
                System.out.print("Jenis input : ");
                input = in.nextInt();
                if (input == 1){
                    matriksRegresi = regresiLinear.bacaSPL();
                }else {
                    matriksRegresi = regresiLinear.bacaFile();
                }
                //matriksRegresi = {[1,1,1,1],[1,1,10,12],[1,2,1,18],[1,3,2,24],[1,4,3,30]};
                //matriksRegresi = regresiLinear.bacaSPL();
                //wkwk.tulisMatriks(matriksRegresi);
                double[] hasil;
                hasil = regresiLinear.regresiLinear(matriksRegresi);
                System.out.print("y = ");
                for(int i = 0;i < hasil.length;i++){
                    System.out.printf("%.2f ", hasil[i]);
                    System.out.print(" X");
                    System.out.print(i+1);
                    if(i != hasil.length-1){
                        System.out.print(" + ");
                    }

                }

                //System.out.print(hasil[hasil.length]);
                break;
            case 6:
                //Keluar
                System.exit(0);
                break;

        }
        menu.close();
        sc.close();
    }
}

