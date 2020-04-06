import java.util.HashMap;
import java.util.Map;

public class Asg4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] A = {1,2,3,4},
		      B = {5,6,7},
			  C = {8,9,10,11};
		
		HashMap<Integer, Integer> f = new HashMap<>();
		HashMap<Integer, Integer> g = new HashMap<>();
		
		// f: A -> B
		f.put(A[0], B[0]);
		f.put(A[1], B[0]);
		f.put(A[2], B[1]);
		f.put(A[3], B[2]);
		
		// g: B -> C
		g.put(B[0], C[0]);
		g.put(B[1], C[1]);
		g.put(B[2], C[2]);
		
		
		// g o f
		String gof = "";
		int c1=0, c2=0;
		for (Map.Entry<Integer, Integer> G : g.entrySet()) {
			c2=0;
			for (Map.Entry<Integer, Integer> F : f.entrySet()) {
				if(c1==c2) {
					gof+="("+G.getKey()+","+F.getValue()+")"+", ";
				}
				c2++;
			}
			c1++;
		}
		
		System.out.println("{"+gof.trim().substring(0,gof.length()-2)+"}");
	}

}
