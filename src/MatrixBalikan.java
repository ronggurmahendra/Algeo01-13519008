package com.Algeo;
import java.io.*;
import java.util.Scanner;


/**
 * MatrixBalikan
 */
public class MatrixBalikan {
    private int n;
    private int i,j,k,h;
    private Scanner in = new Scanner (System. in);
    private int input;

    public double determinan (double[][] MatriksInput){
        Determinan determin = new Determinan();
        double det;
        det = determin.OBE(MatriksInput);
        return(det);
    }

    public double[][] minor (double a[][]){
        n = a.length;
        double min[][]= new double[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                double[][] newone = new double[n - 1][n - 1];
                k=0;
                int brs =0;
                while (k<n){
                    h=0;
                    int kol = 0;
                    while(h<n){
                        if(k!=i && h!=j){
                            newone[brs][kol] = a[k][h];
                            kol++;
                        }
                        h++;
                    }
                    if(k!=i){
                        brs++;
                    }
                    k++;
                }
            min[i][j] = determinan(newone);
        }
    }   
        return(min);
    }

    public double[][] cofactor (double a[][]){
        n = a.length;
        double co[][]= new double[n][n];
        double M1[][]= new double[n][n];
        
        M1 = minor(a);

        for (i=0; i<n; i++){
            for (j=0; j<n; j++){
                if((i+j) % 2 == 0){
                    co[i][j] = M1[i][j];
                } else {
                    co[i][j] = (-1)*M1[i][j];
                }
                
            }
        }
        return (co);
    }

    public double[][] Transpose (double a[][]){
        n = a.length;
        double Trans[][]= new double[n][n];
        for (i = 0; i < n; i++){
            for (j=0; j < n; j++){
                Trans[i][j] = a[j][i];
            }
        }

        return (Trans);
    }

    public double[][] inverse (double a[][]){
        n = a.length;
        double[][] MInv = new double[n][n];

        //cari determinan a
        double det;
        det = determinan(a);
        if (det != 0.0){
            double[][] MKo = cofactor(a);
            //cari adj a (transpose cofactor a)
            double[][] AdjA  = Transpose(MKo); 
            //cari inverse
            for (i=0; i<n; i++){
                for (j=0; j<n; j++){
                    MInv[i][j] = (1/det)*AdjA[i][j];
                }
            }
        }
        return(MInv);    
    }

    public void MainInverse(){
        //pilih input
        System.out.println("Pilih jenis input : ");
        System.out.println("1. Input Keyboard ");
        System.out.println("2. Input File ");
        System.out.print("Jenis input : ");
        this.input = in.nextInt();

        System.out.print("Masukkan jumlah baris dan kolom : ");
        this.n = in.nextInt();
        double[][] matriks = new double[n][n];

        if(this.input == 1){
            System.out.println("Masukkan Elemen Matriks : ");
            for(i=0; i<n; i++){
                for(j=0; j<n; j++){
                    matriks[i][j] = in.nextDouble();
                }   
            }
        } else if (this.input == 2) {
            System.out.print("Masukkan nama file : ");
            String alamat;
            alamat = in.next();
            Scanner inFile = null;
            try {
                inFile = new Scanner(new File(alamat));
                i = 0;
                while (inFile.hasNext()) {
                    for (j=0; j < n; j++){
                        matriks[i][j] = inFile.nextDouble();
                    }
                    i++;
                }
                inFile.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not Found");
            } 
        }

        double[][] MInv = new double[n][n];
        //cari determinan a
        double det;
        det = determinan(matriks);

        //tampilkan hasil
        if (det == 0){
            System.out.println("Matriks Balikan Tidak Ada");
        } else {
            System.out.println("Matriks Balikan : ");
            MInv = inverse(matriks);
            //tulis matriks ke layar
            for(i=0; i<n; i++){
                for(j=0; j<n; j++){
                    System.out.print(MInv[i][j]);
                    if (j != n-1){
                        System.out.print(" ");
                    }
                }
                if (i != n-1){
                    System.out.println("");
                }   
            }
        }
        System.out.println(" ");
        //salin matriks ke file
        try {
            String alamat2;
            System.out.print("Masukkan nama file hasil eksekusi : ");
            alamat2 = in.next();
            PrintStream outfile = new PrintStream(new File (alamat2));
            if (det == 0){
                outfile.println("Matriks Balikan Tidak Ada");
            } else {
                for(i=0; i<n; i++){
                    for(j=0; j<n; j++){
                        outfile.print(MInv[i][j]);
                        if (j != n-1){
                            outfile.print(" ");
                        }
                    }
                    if (i != n-1){
                        outfile.println("");
                    }   
                }
            }
            outfile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Tidak dapat membuka file untuk ditulis");
        }
    }
}

