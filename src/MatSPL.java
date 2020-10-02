//package com.Algeo;
import java.util.*;
import java.io.*;


public class MatSPL {
	void isiMatriks(double[][] Mat, int baris, int kolom) {
		int i,j;
		System.out.println("isi matriks: ");
		Scanner sc = new Scanner(System.in);
		for (i=0; i<baris; i++) {
			System.out.println(String.format("Masukkan persamaan ke-%d :",i+1));
			for (j=0; j<kolom; j++) {
				if (j!=kolom-1){
					System.out.print(String.format("x%d = ",j+1));
				}
				else{
					System.out.print("Hasil = ");
				}
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
		while (i<(baris-countBrsNol) && kolref<kolom) {
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
    
    double[] SPLGauss (double[][] Mat, int baris, int kolom) {
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
        return x;
    }

	void solusiSPLGauss(double[][] Mat, int baris, int kolom) {
		matriksEselonBaris(Mat, baris, kolom);
		int countBrsNol = 0;
		int i = 0;
		while (i<baris-countBrsNol) {
			if (barisNol(Mat, kolom, i)) {
				countBrsNol +=1;
			}
			i=i+1;
		}
		System.out.println("isi nama file: ");
		Scanner sc = new Scanner(System.in);
		String alamat = sc.next();
		try {
			PrintStream outfile = new PrintStream(new File (alamat));
			System.out.println("Hasil perhitungan SPL dengan menggunakan metode Gauss: ");
			outfile.println("Hasil perhitungan SPL dengan menggunakan metode Gauss: ");
			double[][] untukParam = new double[baris][kolom];
			String[] sol = new String[kolom-1];
			if(countBrsNol==0 && banyakBukanNolBaris(Mat,(baris-1),kolom)==0) {
				System.out.println("SPL tidak memiliki solusi");
				outfile.println("SPL tidak memiliki solusi");
			}
			else {
				for (i=0; i<baris; i++) {
					for (int j=0; j<kolom; j++) {
						untukParam[i][j]=Mat[i][j];
					}
				}
				for (int j=0; j<kolom-1; j++) {
					sol[j]="_";
				}
				//membuat variabel untuk parameter
				int banyakvar=0;
				int kodeParam = 97;
				i=0;
				while (i<baris-countBrsNol && (i+banyakvar)<kolom && (i+banyakvar)<kolom-1) {
					if (Mat[i][i+banyakvar]!=1) {
						sol[i+banyakvar]=String.valueOf((char)(kodeParam+banyakvar));
						banyakvar=banyakvar+1;
					}
					else {
						i=i+1;
					}
				}
				if (i+banyakvar<kolom-1) {
					int j=i+banyakvar;
					while (j<kolom-1) {
						sol[j]=String.valueOf((char)(kodeParam+banyakvar));
						banyakvar=banyakvar+1;
						j+=1;
					}
				}
				i=baris-countBrsNol-1;
				while (i>=0) {
					//mencari yang hasilnya tidak berparameter
					int idx = pertamaBrsBukanNol(Mat,i,kolom);
					for (int j=idx+1; j<kolom-1; j++) {
						if (sol[j]=="_") {
							int d = barisMana$(Mat, j, baris, kolom);
							for (int k=j+1; k<kolom; k++) {
								untukParam[i][k]=untukParam[i][k]-untukParam[i][j]*untukParam[d][k];
							}
							untukParam[i][j]=0;
						}
					}
					i=i-1;
				}
				String[] hasil = new String[kolom-1];
				for (int k=0; k<kolom-1; k++ ) {
					if (sol[k]=="_") {
						int d = barisMana$(Mat, k, baris, kolom);
						if (untukParam[d][kolom-1]==0) {
							hasil[k]="";
						}
						else {
							hasil[k]=String.valueOf(untukParam[d][kolom-1]);
						}
						for (int l=k+1; l<kolom-2; l++) {
							if (untukParam[d][l]!=0) {
								double sil = untukParam[d][l]*(-1);
								if (hasil[k]=="") {
									hasil[k]=String.valueOf(sil)+sol[l];
								}
								else {
									if (sil>0) {
										if (sil!=1) {
											hasil[k]=hasil[k]+"+"+String.valueOf(sil)+sol[l];
										}
										else {
											hasil[k]=hasil[k]+"+"+sol[l];
										}
									}
									else {
										if (sil!=-1) {
											hasil[k]=hasil[k]+"-"+String.valueOf(-1*sil)+sol[l];
										}
										else {
											hasil[k]=hasil[k]+"-"+sol[l];
										}
									}
								}
							}
						}
						for (i=0; i<kolom-1; i++) {
							if (hasil[i]=="") {
								hasil[i]="0";
							}
						}
					}
					else {
						hasil[k]=sol[k];
					}
				}
				System.out.println("Solusi SPL:");
				outfile.println("Solusi SPL:");
				for (i=0; i<kolom-1; i++) {
					System.out.println("x"+(i+1)+" = "+hasil[i]);
					outfile.println("x"+(i+1)+" = "+hasil[i]);
				}
			}
			outfile.close();
			sc.close();
		}
		catch (FileNotFoundException e) {
            System.out.println("Tidak dapat membuka file untuk ditulis");
		}

	}
	
	int barisMana$(double[][] Mat, int $, int baris, int kolom) {
		/* mencari di baris mana $ menjadi pertamaBrsBukanNol di matriks */
		boolean ketemu=false;
		int i=0;
		while (i<baris && !ketemu) {
			if (pertamaBrsBukanNol(Mat, i, kolom)==$) {
				ketemu=true;
			}
			else {
				i=i+1;
			}
		}
		return i;
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
		/* mengeluarkan berapa banyak elemen bernilai $ di array sebelah kiri elemen arr[kolel] - kecuali kolom terakhir*/
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
	
	void matriksEselonReduksi(double[][] Mat, int baris, int kolom) {
		matriksEselonBaris(Mat, baris, kolom);
		int countBrsNol = 0;
		int i = 0;
		while (i<baris-countBrsNol) {
			if (barisNol(Mat, kolom, i)) {
				countBrsNol +=1;
			}
			else {
				i=i+1;
			}
		}
		for (i=1; i<baris-countBrsNol; i++) {
			for (int j=0; j<i; j++) {
				double x = Mat[j][pertamaBrsBukanNol(Mat, i, kolom)];
				for (int k=0; k<kolom; k++) {
					Mat[j][k]=Mat[j][k]-(x*Mat[i][k]);
				}
			}
		}
	}
	
	double kofaktor(double[][] MatriksInput) {
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
	
	void solusiSPLCramer(double[][] Mat, int baris, int kolom) {
		//buat matriks pembagi
		double[][] MatX = new double [baris][baris]; // pasti baris = kolom-1
		for (int i=0; i<baris; i++) {
			for (int j=0; j<baris; j++) {
				MatX[i][j] = Mat[i][j];
			}
		}
		double denum = kofaktor(MatX);
		System.out.println("isi nama file: ");
		Scanner sc = new Scanner(System.in);
		String alamat = sc.next();
		try {
			PrintStream outfile = new PrintStream(new File (alamat));
			System.out.println("Hasil perhitungan SPL dengan menggunakan metode Crammer: ");
			outfile.println("Hasil perhitungan SPL dengan menggunakan metode Crammer: ");
			if (denum != 0) {
				double[] arrHasil = new double[baris];
				for (int k=0; k<baris; k++) {
					double[][] MatNum = new double[baris][baris];
					for (int i=0; i<baris; i++) {
						for (int j=0; j<baris; j++) {
							MatNum[i][j] = MatX[i][j];
						}
					}
					//tukar elemen matriks untuk determinan pembilang
					for (int j=0; j<baris; j++) {
						MatNum[j][k] = Mat[j][kolom-1];
					}
					double num = kofaktor(MatNum);
					arrHasil[k]=num/denum;
				}
				System.out.println("Solusi SPL:");
				outfile.println("Solusi SPL:");
				for (int a=0; a<baris; a++) {
					System.out.println(String.format("x%d = %.2f", a+1, arrHasil[a]));
					outfile.println(String.format("x%d = %.2f", a+1, arrHasil[a]));
				}
			}
			else {
				System.out.println("SPL tidak memiliki solusi tunggal (unik)");
				outfile.println("SPL tidak memiliki solusi tunggal (unik)");
			}
			outfile.close();
			sc.close();
		}
		catch (FileNotFoundException e) {
            System.out.println("Tidak dapat membuka file untuk ditulis");
		}
		
	}
	
	void solusiSPLGaussJordan(double[][] Mat, int baris, int kolom) {
		matriksEselonReduksi(Mat, baris, kolom);
		int countBrsNol = 0;
		int i = 0;
		while (i<baris-countBrsNol) {
			if (barisNol(Mat, kolom, i)) {
				countBrsNol +=1;
			}
			else {
				i=i+1;
			}
		}
		System.out.println("isi nama file: ");
		Scanner sc = new Scanner(System.in);
		String alamat = sc.next();
		try {
			PrintStream outfile = new PrintStream(new File (alamat));
			System.out.println("Hasil perhitungan SPL dengan menggunakan metode Gauss-Jordan: ");
			outfile.println("Hasil perhitungan SPL dengan menggunakan metode Gauss-Jordan: ");
			if(countBrsNol==0 && banyakBukanNolBaris(Mat,(baris-countBrsNol-1),kolom)==0) {
				System.out.println("SPL tidak memiliki solusi");
				outfile.println("SPL tidak memiliki solusi");
			}
			else if (countBrsNol==0) {
				System.out.println("Solusi SPL: ");
				for (i=0; i<baris; i++) {
					System.out.println(String.format("x%d = %.2f", i+1, Mat[i][kolom-1]));
					outfile.println(String.format("x%d = %.2f", i+1, Mat[i][kolom-1]));
				}
			}
			else {
				int kodeParam = 97;
				String[] solusi = new String[kolom-1];
				for (i=0; i<kolom-1; i++) {
					solusi[i]="KOSONG";
				}
				int banyakvar=0;
				i=0;
				while (i<baris-countBrsNol) {
					if (Mat[i][i+banyakvar]!=1) {
						solusi[i+banyakvar]=String.valueOf((char)(kodeParam+banyakvar));
						banyakvar=banyakvar+1;
					}
					else {
						i=i+1;
					}
				}
				if (i+banyakvar<kolom-2) {
					int j=i+banyakvar;
					while (j<kolom-1) {
						solusi[j]=String.valueOf((char)(kodeParam+banyakvar));
						banyakvar=banyakvar+1;
						j+=1;
					}
				}
				for (i=baris-countBrsNol-1; i>=0; i--) {
					int x = pertamaBrsBukanNol(Mat,i,kolom);
					if (solusi[x]=="KOSONG") {
						if (banyakTidakNolSesudahElmt(Mat,x,i,kolom)==0) {
							solusi[x]=String.valueOf(Mat[i][kolom-1]);
						}
						else {
							if (Mat[i][kolom-1]!=0) {
								solusi[x]=String.valueOf(Mat[i][kolom-1]);
							}
							else {
								solusi[x]= "";
							}
							for (int j=x+1; j<kolom-1; j++) {
								if (Mat[i][j]>0) {
									if (Mat[i][j]!=1) {
										solusi[x]=solusi[x]+"-"+String.valueOf(Mat[i][j])+solusi[j];
									}
									else {
										solusi[x]=solusi[x]+"-"+solusi[j];
									}
								}
								else if(Mat[i][j]<0) {
									if (solusi[x]!="") {
										if (Mat[i][j]!=-1) {
											solusi[x]=solusi[x]+"+"+String.valueOf((-1)*Mat[i][j])+solusi[j];
										}
										else {
											solusi[x]=solusi[x]+"+"+solusi[j];
										}
									}
									else {
										if (Mat[i][j]!=-1) {
											solusi[x]=String.valueOf((-1)*Mat[i][j])+solusi[j];
										}
										else {
											solusi[x]=solusi[j];
										}
									}
								}
							}
						}
					}
				}
				System.out.println("Solusi SPL");
				outfile.println("Solusi SPL");
				for (i=0; i<kolom-1; i++) {
					System.out.print(String.format("x%d = ", i+1));
					System.out.println(solusi[i]);
					outfile.print(String.format("x%d = ", i+1));
					outfile.println(solusi[i]);
				}
			}
			sc.close();
		}
		catch (FileNotFoundException e) {
            System.out.println("Tidak dapat membuka file untuk ditulis");
		}
		
	}
	
	void solusiSPLGaussJordan1(double[][] Mat, int baris, int kolom) {
		matriksEselonReduksi(Mat, baris, kolom);
		int countBrsNol = 0;
		int i = 0;
		while (i<baris-countBrsNol) {
			if (barisNol(Mat, kolom, i)) {
				countBrsNol +=1;
			}
			i=i+1;
		}
		System.out.println("isi nama file: ");
		Scanner sc = new Scanner(System.in);
		String alamat = sc.next();
		try {
			PrintStream outfile = new PrintStream(new File (alamat));
			System.out.println("Hasil perhitungan SPL dengan menggunakan metode Gauss-Jordan: ");
			outfile.println("Hasil perhitungan SPL dengan menggunakan metode Gauss-Jordan: ");
			double[][] untukParam = new double[baris][kolom];
			String[] sol = new String[kolom-1];
			if(countBrsNol==0 && banyakBukanNolBaris(Mat,(baris-1),kolom)==0) {
				System.out.println("SPL tidak memiliki solusi");
				outfile.println("SPL tidak memiliki solusi");
			}
			else {
				for (i=0; i<baris; i++) {
					for (int j=0; j<kolom; j++) {
						untukParam[i][j]=Mat[i][j];
					}
				}
				for (int j=0; j<kolom-1; j++) {
					sol[j]="_";
				}
				//membuat variabel untuk parameter
				int banyakvar=0;
				int kodeParam = 97;
				i=0;
				while (i<baris-countBrsNol && (i+banyakvar)<kolom && (i+banyakvar)<kolom-1) {
					if (Mat[i][i+banyakvar]!=1) {
						sol[i+banyakvar]=String.valueOf((char)(kodeParam+banyakvar));
						banyakvar=banyakvar+1;
					}
					else {
						i=i+1;
					}
				}
				if (i+banyakvar<kolom-1) {
					int j=i+banyakvar;
					while (j<kolom-1) {
						sol[j]=String.valueOf((char)(kodeParam+banyakvar));
						banyakvar=banyakvar+1;
						j+=1;
					}
				}
				i=baris-countBrsNol-1;
				while (i>=0) {
					//mencari yang hasilnya tidak berparameter
					int idx = pertamaBrsBukanNol(Mat,i,kolom);
					for (int j=idx+1; j<kolom-1; j++) {
						if (sol[j]=="_") {
							int d = barisMana$(Mat, j, baris, kolom);
							for (int k=j+1; k<kolom; k++) {
								untukParam[i][k]=untukParam[i][k]-untukParam[i][j]*untukParam[d][k];
							}
							untukParam[i][j]=0;
						}
					}
					i=i-1;
				}
				String[] hasil = new String[kolom-1];
				for (int k=0; k<kolom-1; k++ ) {
					if (sol[k]=="_") {
						int d = barisMana$(Mat, k, baris, kolom);
						if (untukParam[d][kolom-1]==0) {
							hasil[k]="";
						}
						else {
							hasil[k]=String.valueOf(untukParam[d][kolom-1]);
						}
						for (int l=k+1; l<kolom-2; l++) {
							if (untukParam[d][l]!=0) {
								double sil = untukParam[d][l]*(-1);
								if (hasil[k]=="") {
									hasil[k]=String.valueOf(sil)+sol[l];
								}
								else {
									if (sil>0) {
										if (sil!=1) {
											hasil[k]=hasil[k]+"+"+String.valueOf(sil)+sol[l];
										}
										else {
											hasil[k]=hasil[k]+"+"+sol[l];
										}
									}
									else {
										if (sil!=-1) {
											hasil[k]=hasil[k]+"-"+String.valueOf(-1*sil)+sol[l];
										}
										else {
											hasil[k]=hasil[k]+"-"+sol[l];
										}
									}
								}
							}
						}
						for (i=0; i<kolom-1; i++) {
							if (hasil[i]=="") {
								hasil[i]="0";
							}
						}
					}
					else {
						hasil[k]=sol[k];
					}
				}
				System.out.println("Solusi SPL:");
				outfile.println("Solusi SPL:");
				for (i=0; i<kolom-1; i++) {
					System.out.println("x"+(i+1)+" = "+hasil[i]);
					outfile.println("x"+(i+1)+" = "+hasil[i]);
				}
			}
			outfile.close();
		}
		catch (FileNotFoundException e) {
            System.out.println("Tidak dapat membuka file untuk ditulis");
        }
	}
	
	int banyakTidakNolSesudahElmt(double[][] Mat, int kolapa, int bar, int kolom) {
		/* mengembalikan banyaknya elemen tidak nol di Mat[bar][kolapa] sampai Mat[bar][kolom-2] */
		int count=0;
		for (int i=kolapa+1; i<kolom-1; i++) {
			if (Mat[bar][i]!=0) {
				count=count+1;
			}
		}
		return count;
	}
}
