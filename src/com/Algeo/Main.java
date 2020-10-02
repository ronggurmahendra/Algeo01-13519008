package com.Algeo;
import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        Menu();
    }
    public static void Menu(){
		System.out.println("\n");
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
                switch (inputSPL) {
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
							int baris1 = sc.nextInt();
							System.out.print("Masukkan banyak variabel: ");
							int kolom1 = sc.nextInt()+1;
							/* membuat matriks */
							MatSPL M1 = new MatSPL();
							double[][] Mat1;
							Mat1 = new double [baris1][kolom1];
						if (opsi == 1) {
							/* membaca isi matriks */
							M1.isiMatriks(Mat1, baris1, kolom1);
							/* menampilkan solusi SPL */
							M1.solusiSPLGauss(Mat1, baris1, kolom1);
						} 
						else {
							/*membaca dari file */
							System.out.print("Masukkan alamat file : ");
							String alamat1;
							alamat1 = sc.next();
							Scanner inFile1 = null;
							try {
								inFile1 = new Scanner(new File(alamat1));
								int i = 0;
								while (inFile1.hasNext()) {
									for (int j=0; j < kolom1; j++){
										Mat1[i][j] = inFile1.nextDouble();
									} 
									i++;
								}
								inFile1.close();
							} catch (Exception e) {
								System.out.println("File tidak ditemukan");
							}
							/* menampilkan solusi SPL */
							M1.solusiSPLGauss(Mat1, baris1, kolom1);
						}
					Menu();
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
						int baris2 = sc.nextInt();
						System.out.print("Masukkan banyak variabel: ");
						int kolom2 = sc.nextInt()+1;
						/* membuat matriks */
						MatSPL M2 = new MatSPL();
						double[][] Mat2 = new double [baris2][kolom2];
						if (opsi == 1) {
							/* membaca isi matriks */
							M2.isiMatriks(Mat2, baris2, kolom2);
							/* menampilkan solusi SPL */
							M2.solusiSPLGaussJordan(Mat2, baris2, kolom2);
						} 
						else {
							/* membaca dari file */
							System.out.print("Masukkan alamat file : ");
							String alamat2;
							alamat2 = sc.next();
							Scanner inFile2 = null;
							try {
								inFile2 = new Scanner(new File(alamat2));
								int i = 0;
								while (inFile2.hasNext()) {
									for (int j=0; j < kolom2; j++){
										Mat2[i][j] = inFile2.nextDouble();
									} 
									i++;
								}
								inFile2.close();
							} catch (Exception e) {
								System.out.println("File tidak ditemukan");
							}
							/* menampilkan solusi SPL */
							M2.solusiSPLGaussJordan(Mat2, baris2, kolom2);
						}
					Menu();
					 break;
                    case 3:
                        //masukin program SPL Metode matriks balikan
                        SPLMatrixBalikan SPLInverse = new SPLMatrixBalikan();
                        SPLInverse.SPLinverse();
						Menu();
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
						int baris3 = sc.nextInt();
						int kolom3 = baris3+1;
						/* membuat matriks */
						MatSPL M3 = new MatSPL();
						double[][] Mat3 = new double [baris3][kolom3];		
						if (opsi == 1) {
							/* membaca isi matriks */
							M3.isiMatriks(Mat3, baris3, kolom3);
							/* menampilkan solusi SPL */
							M3.solusiSPLCramer(Mat3, baris3, kolom3);
						} 
						else {
							/* membaca dari file */
							System.out.print("Masukkan alamat file : ");
							String alamat3;
							alamat3 = sc.next();
							Scanner inFile3 = null;
							try {
								inFile3 = new Scanner(new File(alamat3));
								int i = 0;
								while (inFile3.hasNext()) {
									for (int j=0; j < kolom3; j++){
										Mat3[i][j] = inFile3.nextDouble();
									} 
									i++;
								}
								inFile3.close();
							} catch (Exception e) {
								System.out.println("File tidak ditemukan");
							}
							/* menampilkan solusi SPL */
							M3.solusiSPLCramer(Mat3, baris3, kolom3);
						}
					Menu();
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
				Menu();
                break;
            case 3:
                //program Matriks Balikan
                MatrixBalikan Inverse = new MatrixBalikan();
                Inverse.MainInverse();
				Menu();
                break;
            case 4:
                //program Interpolasi Polinom
                interpolasi interpolasi = new interpolasi();
                interpolasi.inter();
				Menu();
                break;
            case 5:
                //program Regresi linier berganda
                RegresiLinear regresiLinear = new RegresiLinear();
                Scanner scannerRegresi = new Scanner(System.in);
                //Determinan wkwk = new Determinan();
                double[][] matriksRegresi;
                System.out.println("Pilih jenis input : ");
                System.out.println("1. Input Keyboard ");
                System.out.println("2. Input File ");
                System.out.print("Jenis input : ");
                int inputregresiLinear = scannerRegresi.nextInt();
                if (inputregresiLinear == 1){
                    matriksRegresi = regresiLinear.bacaSPL();
                }else {
                    matriksRegresi = regresiLinear.bacaFile();
                }
                double[] hasil;
                hasil = regresiLinear.regresiLinear(matriksRegresi);
                System.out.print("y = ");
                for(int i = 0;i < hasil.length;i++){
                    System.out.printf("%.2f ", hasil[i]);

                    if (i != 0) {
						System.out.print(" X");
						System.out.print(i);
					}
                    if(i != hasil.length-1){
                        System.out.print(" + ");
                    }

                }

				//System.out.print(hasil[hasil.length]);
				Menu();
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

