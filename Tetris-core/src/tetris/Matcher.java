package tetris;

import java.util.Arrays;

public class Matcher {
	
	int [][] Fnew;
	int [][] Fold;
	int Depth, Width; 
	boolean NoChanges;
	
	void CheckNeighbours(int b, int a, int d, int c, int i, int j) {
        if ((Fnew[i][j]==Fnew[a][b]) && (Fnew[i][j]==Fnew[c][d])) {
            Fold[a][b] = 0;
            Fold[i][j] = 0;
            Fold[c][d] = 0;
            NoChanges = false;
        };
    }
	void TestField() {
        int i,j;
        for (i=0; i<Depth; i++) {
            for (j=0; j<Width; j++) {
                Fold[i][j] = Fnew [i][j];
            }
        }
        for (i=1; i<Depth-1; i++) {
            for (j=1; j<Width-1; j++) {
                if (Fnew[i][j]>0) {
                    CheckNeighbours(j,i-1,j,i+1,i,j);//row
                    CheckNeighbours(j-1,i,j+1,i,i,j);//column
                    CheckNeighbours(j-1,i-1,j+1,i+1,i,j);//diag1
                    CheckNeighbours(j+1,i-1,j-1,i+1,i,j);//diag2
                }
            }
        }
        for ( i = 1; i < Depth-1; i++) {
        	if (Fnew[i][0]>0) {
        		CheckNeighbours(0, i-1, 0 , i+1, i, 0);
        	}
        	if (Fnew[i][Width-1]>0){
        		CheckNeighbours(Width-1, i-1, Width-1, i+1, i, Width-1);
        	}
        		
        	
		}
        for (j = 1; j < Width-1; j++) {
        	if (Fnew[Depth-1][j]>0){
        		CheckNeighbours(j+1, Depth-1, j-1, Depth-1, Depth-1, j);
        	}
		}
        
    }
	
	void PackField() {
        int i,j,n;
        for (i=0; i<Width; i++) {
            n = Depth-1;
            for (j=Depth-1; j>=0; j--) {
                if (Fold[j][i]>0) {
                    Fnew[n][i] = Fold[j][i];
                    n--;
                }
            };
            for (j=n; j>=0; j--) Fnew[j][i] = 0;
        }
    }

	public void Match(){
		do {
	
			NoChanges = true;
			TestField();
			if (!NoChanges) {
				PackField();
            }
        
		} while (!NoChanges);
	}
	public Matcher(int[][] _Fnew, int _Depth , int _Width) {
		Fnew = _Fnew;
		Fold = copy(Fnew);
		
//		Depth = _Depth;
//		Width = _Width;
		Depth = Fnew.length;
		Width = Fnew[0].length;
	}
	
	public static int[][] copy(int[][] src) {
	    int[][] dst = new int[src.length][];
	    for (int i = 0; i < src.length; i++) {
	        dst[i] = Arrays.copyOf(src[i], src[i].length);
	    }
	    return dst;
	}
}
