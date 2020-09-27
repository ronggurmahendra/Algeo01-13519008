package com.Algeo;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
	    System.out.println("Initilizing main");
	    double[][] matriks;
	    matriks = bacaMatriks();
        tulisMatriks(matriks);
    }

    public static double[][] bacaMatriks() {
        Scanner myObj = new Scanner(System.in);
        System.out.print("Enter Column = ");
        int column = myObj.nextInt();  // Read user input
        System.out.print("Enter Row = ");
        int row = myObj.nextInt();  // Read user input
        double matriks[][] = new double[row][column];
        for(int i = 0; i< row;i++){
            for(int j = 0;j<column;j++){
                System.out.print(String.format("Enter Matriks[%d][%d] = ", i, j));
                matriks[i][j] = myObj.nextDouble();
            }
        }
        myObj.close();
        return matriks;
    }
    public static void tulisMatriks(double[][] matriks){
        for (int i = 0; i < matriks.length;i++){
            for (int j = 0; j < matriks[i].length;j++){
                System.out.printf("%f ",matriks[i][j]);
            }
            System.out.println();
        }
    }
}

