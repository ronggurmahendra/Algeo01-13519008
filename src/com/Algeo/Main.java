package com.Algeo;
import java.util.Scanner;
import com.Algeo.Determinan   ;
//import package Determinan;
public class Main {

    public static void main(String[] args) {
	    System.out.println("Initilizing main");
        Determinan determinan = new Determinan();
	    double[][] matriks;
	    matriks = bacaMatriks();
        tulisMatriks(matriks);
        /*
        System.out.print("Determinan Matriks dengan kofaktor = ");
        System.out.println(determinan.kofaktor(matriks));
        System.out.print("Determinan Matriks dengan OBE = ");
        System.out.println(determinan.OBE(matriks));
        
         */
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
                System.out.print(String.format("Masukan Matriks[%d][%d] = ", i, j));
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
}

