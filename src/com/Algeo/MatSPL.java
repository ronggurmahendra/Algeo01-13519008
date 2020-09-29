//package tubes1algeo;
package com.Algeo;
import java.util.Scanner;

//import buram.Matriks;

public class MatSPL {
	void isiMatriks(double[][] Mat, int baris, int kolom) {
		int i,j;
		System.out.println("isi matriks: ");
		Scanner sc = new Scanner(System.in);
		for (i=0; i<baris; i++) {
			for (j=0; j<kolom; j++) {
				Mat[i][j] = sc.nextDouble();
			}
		}
	}
	
	void tulisMatriks (double[][] Mat, int baris, int kolom) {
		int i,j;
		for (i=0; i<baris; i++) {
			for (j=0; j<kolom; j++) {
				if (j!=kolom-1) {
					System.out.print(Mat[i][j]+" ");
				}
				else {
					System.out.println(Mat[i][j]);
				}
			}
		}
	}
	
	void matriksEselonBaris(double[][] Mat, int baris, int kolom) {
		int i,j,k,kolref;
		int countBrsNol = 0;
		i = 0;
		while (i<baris-countBrsNol) {
			if (barisNol(Mat, kolom, i)) {
				barisNolKebawah(Mat, i, baris, kolom);
				countBrsNol +=1;
			}
			else {
				i=i+1;
			}
		}
		i = 0;
		kolref=i;
		while (i<(baris-countBrsNol)) {
			if (barisPatokan(Mat,i,kolref, baris)!=baris) {
				if (barisPatokan(Mat,i,kolref, baris)!=i) {
					tukarBaris(Mat,i,barisPatokan(Mat,i,kolref, baris),kolom);
				}
				double x = Mat[i][kolref];
				for (j=kolref; j<kolom; j++) { 
					Mat[i][j]= Mat[i][j] / x;
				}
				k=i+1;
				while (k<baris-countBrsNol) {
					if (Mat[k][kolref]!=0) {
						double y = Mat[k][kolref];
						for (j=kolref; j<kolom; j++){
							Mat[k][j]=Mat[k][j]-(y*Mat[i][j]);
						}
					}
					k=k+1; 
				}
				i=i+1;
				kolref=kolref+1;
			}
			else {
				kolref=kolref+1;
			}
		}
	}
	
	boolean barisNol(double[][] Mat, int kolom, int brs) {
		boolean nol = true;
		int j = 0;
		while (j<kolom && nol) {
			if (Mat[brs][j]!= 0) {
				nol = false;
			}
			else {
				j=j+1;
			}
		}
		return nol;
	}
	
	void barisNolKebawah(double[][] Mat, int brs, int baris, int kolom) {
		for (int i=brs; i<baris; i++) {
			for (int j=0; j<kolom; j++) {
				Mat[i][j]=Mat[i+1][j];
			}
		}
		for (int j=0; j<kolom; j++) {
			Mat[baris-1][j]=0;
		}
	}
	
	int barisPatokan(double[][] Mat, int brs, int kol, int baris) {
		while (brs<baris && Mat[brs][kol]==0 ) {
			brs=brs+1;
		}
		return brs;
	}
	
	void tukarBaris(double[][] Mat, int brs1, int brs2, int kolom) {
		double[] arrtemp = new double[kolom];
		for (int i=0; i<kolom; i++) {
			arrtemp[i]=Mat[brs1][i];
			Mat[brs1][i]=Mat[brs2][i];
			Mat[brs2][i]=arrtemp[i];
		}
	}
	
	void solusiSPLGauss(double[][] Mat, int baris, int kolom) {
		matriksEselonBaris(Mat, baris, kolom);
		double[] x = new double[kolom-1];
		String[] sol = new String[kolom-1];
		for (int j=0; j<kolom-1; j++) {
			sol[j]="_";
		}
		int kodeParam = 122;
		int i=baris-1;
		while (i>=0) {
			if (barisNol(Mat,kolom,i)) {
				i=i-1;
			}
			else{
				int idx = pertamaBrsBukanNol(Mat,i,kolom);
				if (idx==kolom-1) {
					i=-1;
				}
				else if (banyakBukanNolBaris(Mat,i,kolom)==1){
					x[idx]=Mat[i][kolom-1];
					sol[idx] ="$";
				}
				else {
					if (banyakBukanNolBaris(Mat,i,kolom)==banyak$SesudahElArr(sol,idx,kolom)+1) {
						double jum = Mat[i][kolom-1];
						for (int j=idx+1; j<kolom-1; j++) {
							jum =  jum - (Mat[i][j]*x[j]);
						}
						x[idx]=jum;
						sol[idx]="$";
					}
					else {
						double jum = Mat[i][kolom-1];
						for (int j=idx+1; j<kolom-1; j++) {
							if (sol[j]=="$") {
								jum =  jum - (Mat[i][j]*x[j]);
							}
						}
						sol[idx]=String.valueOf(jum);
						int kolel = kolom-1;
						for (int j=kolom-2; j>idx;j--) {
							if (j == sblVarTakNol_(Mat, sol, i, kolel)) {
								sol[idx] = sol[idx]+"+("+String.valueOf(-1*Mat[i][j])+")"+String.valueOf((char)kodeParam);
								sol[j] = String.valueOf((char)kodeParam);
								kodeParam=kodeParam-1;
								kolel=j;
							}
							if (Mat[i][j]==0 && sol[j]=="_") {
								sol[j] = String.valueOf((char)kodeParam);
								kodeParam=kodeParam-1;
							}
						}
					}
				}
			}
			i=i-1;
		}
		if (banyak$SesudahElArr(sol,-1, kolom)==0) {
			System.out.println("SPL tidak memiliki solusi");
		}
		else {
			System.out.println("Solusi SPL: ");
			for (i=0; i<kolom-1; i++) {
				if (sol[i]=="$") {
					System.out.println(String.format("x%d = %.2f", i+1, x[i]));
				}
				else {
					System.out.println("x"+String.valueOf(i+1)+" = "+sol[i]);
				}
			}
		}
	}
	
	int pertamaBrsBukanNol(double[][] Mat, int brs, int kolom) {
		/* mengeluarkan pada kolom keberapa dalam suatu baris pertama kali dijumpai elemen tidak nol */
		int i=0;
		while (i<kolom && Mat[brs][i]==0) {
			i=i+1;
		}
		return i;
	}
	
	int banyakBukanNolBaris(double[][] Mat, int brs, int kolom) {
		/* mengoutput berapa banyak elemen pada satu baris selain di bagian kolom terakhirnya yang nilainya bukan nol */
		int count = 0;
		for (int i=0; i<kolom-1; i++) {
			if (Mat[brs][i]!=0) {
				count=count+1;
			}
		}
		return count;
	}
	
	int banyak$SesudahElArr(String[] arr, int kolel, int kolom) {
		/* mengeluarkan berapa banyak elemen bernilai true di array sebelah kiri elemen arr[kolel] - kecuali kolom terakhir*/
		int count = 0;
		for (int i=kolel+1; i<kolom-1; i++) {
			if (arr[i]=="$") {
				count=count+1;
			}
		}
		return count;
	}
	
	int sblVarTakNol_(double[][] Mat, String[] arr, int brs, int kolel) {
		int i=kolel-1;
		while (i>=0 && (arr[i]=="$" || Mat[brs][i]==0)) {
			i=i-1;
		}
		return i;
	}
	
}

	
	
