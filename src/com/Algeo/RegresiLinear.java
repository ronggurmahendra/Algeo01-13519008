package com.Algeo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RegresiLinear {

    public  double[] regresiLinear(double[][] matriksInput){ //[baris,Jumlah persamaan][kolom,x+1]
        double persBeta[][] = new double[matriksInput[0].length-1][matriksInput[0].length];
        int n = matriksInput[0].length-1;
        int k = matriksInput[0].length;
        for (int i = 0;i< n;i++){
            for(int j = 0;j<k;j++){
                ///persBeta[i][j] = i+j;
                double temp = 0;
                for(int a = 1;a<matriksInput.length;a++) {
                    temp += matriksInput[a][j] * matriksInput[a][i];
                }
                persBeta[i][j] = temp;
            }
        }

        //System.out.println("----------persBeta---------------");
        tulisMatriks(persBeta);
        double[] hasil = new double[matriksInput.length];
        //tulisMatriks(persBeta);
        //System.out.println("----------persBeta---------------");
        MatSPL Gauss = new MatSPL();
        hasil = Gauss.SPLGauss(persBeta, persBeta.length, persBeta[0].length);
        return hasil;

    }

    public  static  double[][]bacaFile(){

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
        }

    }

    public static double[][] bacaSPL() {
        Scanner myObj = new Scanner(System.in);
        System.out.print("Masukan Jumlah persamaan (baris) : ");
        int n = myObj.nextInt();  // Read user inputn
        System.out.print("Masukan Jumlah x (Kolom+1) : ");
        int k = myObj.nextInt();  // Read user input
        double matriks[][] = new double[n+1][k+2];
        for(int i = 1; i< n+1;i++){
            for(int j = 1; j < k+2 ;j++){
                if (j == k+1){
                    System.out.print(String.format("Masukan Y%d : ", i));
                }else{
                    System.out.print(String.format("Masukan X%d%d : ", i, j));
                }

                matriks[i][j] = myObj.nextDouble();
            }
        }
        for (int i = 0;i<n+1;i++){
            matriks[i][0] = 1;
        }
        for (int j = 0;j<k+2;j++){
            matriks[0][j] = 1;
        }
        myObj.close();
        return matriks;
    }

    public static void tulisMatriks(double[][] matriks) {
        for (int i = 0; i < matriks.length; i++) {
            for (int j = 0; j < matriks[i].length; j++) {
                System.out.printf("%.2f ", matriks[i][j]);
            }
            System.out.println();
        }
    }

}
