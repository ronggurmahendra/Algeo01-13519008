package com.Algeo;

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
    public double OBE(double[][] MatriksInput){
        return this.kofaktor(MatriksInput);
    }
}
