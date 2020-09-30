package com.Algeo;
import java.util.Scanner;
import java.io.*;

/**
 * SPLMatrixBalikan
 */
public class SPLMatrixBalikan {
    private int input;
    private int m;
    private Scanner in = new Scanner (System. in);
    private int i,j,k;
    private MatrixBalikan inverse = new MatrixBalikan();
    private double det;

    public void SPLinverse(){
        //pilihan input
        System.out.println("Pilih jenis input : ");
        System.out.println("1. Input Keyboard ");
        System.out.println("2. Input File ");
        System.out.print("Jenis input : ");
        this.input = in.nextInt();

        System.out.print("Masukkan jumlah persamaan : ");
        this.m = in.nextInt();
        double [][] matrixA = new double[this.m][this.m]; //matriks A
        double[][] matrixB = new double[this.m][1]; //matriks B

        if (this.input == 1){
            System.out.println("Masukkan Elemen Matriks A : ");
            for(i=0; i < this.m; i++){
                for(j=0; j < this.m; j++){
                    matrixA[i][j] = in.nextDouble();
                }   
            }
            
            System.out.println("Masukkan Elemen Matriks B : ");
            for(i=0; i < this.m; i++){
                for(j=0; j < 1; j++){
                    matrixB[i][j] = in.nextDouble();
                }   
            }
        } else {
            System.out.print("Masukkan nama file : ");
            String alamat;
            alamat = in.next();
            Scanner inFile = null;
            try {
                inFile = new Scanner(new File(alamat));
                i = 0;
                while (inFile.hasNext()) {
                    for (j=0; j < m; j++){
                        matrixA[i][j] = inFile.nextDouble();
                    } 
                    matrixB[i][0] = inFile.nextDouble();
                    i++;
                }
                inFile.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not Found");
            } 
        }

        //cari invers matrix A
        int n = matrixA.length;
        double[][] MInv = new double[n][n];
        MInv = inverse.inverse(matrixA);

        //kalikan A invers dengan b
        double X[][]= new double[n][1];
        for (i = 0; i < n; i++) { 
            for (j = 0; j < 1; j++) { 
                for (k = 0; k < n; k++){
                    X[i][j] += MInv[i][k]*matrixB[k][j]; 
                } 
            } 
        } //dapat nilai x

        // cari determinan
        this.det = inverse.determinan(matrixA);

        //tampilkan hasil
        if (det == 0){
            System.out.println("SPL tidak memiliki solusi tunggal (unik)");
        } else {
            System.out.println("Hasil penyelesaian SPL dengan matriks balikan : ");
            for(i=0; i<n; i++){
                for(j=0; j<1; j++){
                System.out.printf("X%d = %f\n", i+1, X[i][j]);
                }
            }
        }

        //salin hasil ke file
        try {
            PrintStream outfile = new PrintStream(new File ("output.txt"));
            if (det == 0){
                outfile.println("SPL tidak memiliki solusi tunggal (unik)");
            } else {
                System.out.println("Hasil penyelesaian SPL dengan matriks balikan : ");
                for(i=0; i<n; i++){
                    for(j=0; j<1; j++){
                    outfile.printf("X%d = %f\n", i+1, X[i][j]);
                    }
                }
            }
            outfile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Tidak dapat membuka file untuk ditulis");
        }
    }
}
