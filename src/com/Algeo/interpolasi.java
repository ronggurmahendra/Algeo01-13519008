package com.Algeo;
import java.util.Scanner;
import java.io.*;

/**
 * interpolasi
 */
public class interpolasi {
    Scanner in = new Scanner (System. in);
    private MatSPL SPLGauss = new MatSPL();
    private int input;
    private int n; //input banyak titik dan nilai X yang ingin dicari
    private int i,j,k;

    public void inter(){
        //pilihan input
        System.out.println("Pilih jenis input : ");
        System.out.println("1. Input Keyboard ");
        System.out.println("2. Input File ");
        System.out.print("Jenis input : ");
        this.input = in.nextInt();
        
        System.out.print("Masukkan banyak titik : ");
        this.n = in.nextInt();
        double[][] ListPoint = new double[n][2];

        if (this.input == 1){
            System.out.println("Masukkan titik : ");
            for (i=0; i < n; i++){
                for(j=0; j<2; j++){
                    ListPoint[i][j] = in.nextDouble();
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
                    for (j=0; j < 2; j++){
                        ListPoint[i][j] = inFile.nextDouble();
                    }
                    i++;
                }
                inFile.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not Found");
            } 
        }

        //deklarasi matriks augmented
        double[][] M = new double[n][n+1];
        for(i=0;i<n;i++){
            for(j=0;j<n+1;j++){
                M[i][j] = 1;
            }
        }

        //bentuk matriks augmented
        double pangkat;
        for(i=0;i<n;i++){
            for(j=0;j<n+1;j++){
                if(j==n){
                    M[i][j] = ListPoint[i][1];
                } else {
                    pangkat = 1;
                    for (k=0;k<j;k++){
                        pangkat = pangkat*ListPoint[i][0];
                    }
                    M[i][j] = pangkat;
                }
            }
        }

        //selesaikan SPL dengan metode gauss
        double[] SPL = new double[n];
        SPL = SPLGauss.SPLGauss(M, n, n+1);

        //tulis persamaan polinomnya
        System.out.println("Persamaan interpolasi polinomnya : ");
        System.out.print("p(x) = ");
        for (i = 0; i < n; i++){
            if (i == 0){
                System.out.printf("%f ", SPL[i]);
            } else {
                System.out.printf("+ (%f) * x^%d ", SPL[i], i);
            }
        }
        System.out.println(" ");
        //tentukan nilai x yang ingin dicari
        System.out.print("Nilai X yang ingin di cari : ");
        double X = in.nextDouble();

        //cari nilai X dengan rumus yang didapat
        double hasil = 0;
        double hasilpangkat;
        for (i=0; i < n; i++){
            hasilpangkat = 1;
            for (j = 0; j<i; j++){
                hasilpangkat = hasilpangkat*X;
            }
            hasil = hasil + SPL[i]*hasilpangkat;
        }

        //tulis hasil
        System.out.printf("Solusi atau nilai Y yang dihasilkan untuk X = %f adalah %f\n", X, hasil);

        //salin hasil pada file
        try {
            String alamat2;
            System.out.print("Masukkan nama file hasil eksekusi : ");
            alamat2 = in.next();
            PrintStream outfile = new PrintStream(new File (alamat2));
            //tulis persamaan polinomnya
            outfile.println("Persamaan interpolasi polinomnya : ");
            outfile.print("p(x) = ");
            for (i = 0; i < n; i++){
                if (i == 0){
                    outfile.printf("%f ", SPL[i]);
                } else {
                    outfile.printf("+ (%f) * x^%d ", SPL[i], i);
                }
            }
            outfile.println(" ");
            outfile.printf("Solusi atau nilai Y yang dihasilkan untuk X = %f adalah %f\n", X, hasil);
            outfile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Tidak dapat membuka file untuk ditulis");
        }
    }
}