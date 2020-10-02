//package com.Algeo;

import java.io.*;
import java.util.*;

public class Determinan {
    public double kofaktor(double[][] MatriksInput) {
        if (MatriksInput[0].length != MatriksInput.length){
            return -999 ;//error karena bukan matriks persegi
        } else if(MatriksInput.length == 1){
            return MatriksInput[0][0];//Basis dari rekursi
        } else{//menghitung matriks [baris][kolom]
            double det = 0;
            double[][]  minor = new double[MatriksInput.length-1][MatriksInput.length-1];
            //k adalah kolom dari MatriksInput, selalu ambil baris 0
            //i adalah  baris MatriksInput
            //j adalah kolom MatriksInput
            //a adalah baris minor
            //b adalah kolom minor
            int k;
            for (k = 0;k < MatriksInput.length;k++){
                int i = 1;
                int a = 0;
                while(i < MatriksInput.length){
                    int j = 0;
                    int b = 0;
                    while(j < MatriksInput.length){
                        if (k != j){
                            minor[a][b] = MatriksInput[i][j];
                            b++;
                        }
                        j++;
                    }
                    a++;
                    i++;
                }
                if(k%2 == 1){
                    det -= MatriksInput[0][k]*this.kofaktor(minor);
                }else {
                    det += MatriksInput[0][k]*this.kofaktor(minor);
                }
            }
            return det;
        }

    }
    public double OBE(double[][] MatriksInput) {
        if (MatriksInput[0].length != MatriksInput.length) {
            return -999;//error karena bukan matriks persegi
        } else {
            int i, j, k;
            double[][] a = new double[MatriksInput.length + 1][MatriksInput.length + 1];
            int cntswap;
            double tmp;
            float ret;
            boolean found;
            //salin Matriks
            for (i = 0; i < MatriksInput.length; i++) {
                for (j = 0; j < MatriksInput.length; j++) {
                    a[i][j] = MatriksInput[i][j];
                }
            }
            //buat matriks segitiga atas
            cntswap = 0;
            for (i = 0; i < MatriksInput.length; i++) {
                if (Math.abs(a[i][i]) > 1e-14) {
                    found = false;
                    j = i + 1;
                    while (j <= MatriksInput.length && !found) {
                        if (Math.abs(a[j][i]) > 1e-14) {
                            found = true;
                            cntswap++;
                            for (k = i; k < MatriksInput.length; k++) {
                                tmp = a[i][k];
                                a[i][k] = a[j][k];
                                a[j][k] = tmp;
                            }
                        }
                        j++;
                    }
                }
                if (Math.abs(a[i][i]) > 1e-7) {
                    for (j = i + 1; j < MatriksInput.length; j++) {
                        tmp = a[j][i] / a[i][i];
                        for (k = i; k < MatriksInput.length; k++) {
                            a[j][k] -= a[i][k] * tmp;
                        }
                    }
                }

            }
            //kalikan diagonal
            ret = 1;
            for (i = 0; i < MatriksInput.length; i++) {
                if (Math.abs(a[i][i]) > 1e-14) ret *= a[i][i];
                else return 0;
            }
            if (cntswap % 2 == 1) ret *= -1;
            return ret;
        }
    }
    public  static  double[][] bacaFile(){

        System.out.print("Masukkan nama file : ");
        String alamat;
        Scanner in = new Scanner (System. in);
        alamat = in.next();
        Scanner inFile = null;

        try {
            inFile = new Scanner(new File(alamat));
            int i = 0;
            int baris = inFile.nextInt();
            int kolom = inFile.nextInt();
            double [][] matrix = new double[baris][kolom];
            while (inFile.hasNext() && i< baris) {
                for (int j = 0; j < kolom; j++) {
                    matrix[i][j] = inFile.nextDouble();
                }
                //matrixB[i][0] = inFile.nextDouble();
                i++;
            }
            inFile.close();
            return matrix;
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
            double [][] pass = new double [0][0];
            return pass;
        }

    }


    public static double[][] bacaMatriks() {
        Scanner myObj = new Scanner(System.in);
        System.out.print("Masukan Jumlah Kolom : ");
        int kolom = myObj.nextInt();  // Read user input
        System.out.print("Masukan Jumlah Baris : ");
        int baris = myObj.nextInt();  // Read user input
        double matriks[][] = new double[baris][kolom];
        for(int i = 0; i< baris;i++){
            for(int j = 0;j<kolom;j++){
                matriks[i][j] = myObj.nextDouble();
            }
        }
        myObj.close();
        return matriks;
    }

    public static void tulisMatriks(double[][] matriks){
        for (int i = 0; i < matriks.length;i++){
            for (int j = 0; j < matriks[i].length;j++){
                System.out.printf("%.2f ",matriks[i][j]);
            }
            System.out.println();
        }
    }
    public static void tulisFile(double hasil,String directory){
        try {
            PrintStream outfile = new PrintStream(new File (directory));
            //tulis persamaan polinomnya

            outfile.print("hasil Determinan Matriks tersebut adalah : ");
            outfile.print(hasil);
            outfile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Tidak dapat membuka file untuk ditulis");
        }
    }

}
