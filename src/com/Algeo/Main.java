package com.Algeo;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
	    System.out.println("Initilizing main");
	    bacaMatriks();
    }

    public static void bacaMatriks() {
        Scanner myObj = new Scanner(System.in);
        System.out.print("Enter Column = ");
        int column = myObj.nextInt();  // Read user input
        System.out.print("Enter Row = ");
        int row = myObj.nextInt();  // Read user input
        int a[][] = new int[row][column];
        for(int i = 0; i< row;i++){
            for(int j = 0;j<column;j++){
                System.out.print(String.format("Enter Matriks[%d][%d] = ", i, j));
                a[i][j] = myObj.nextInt();
            }
        }
    }
}

