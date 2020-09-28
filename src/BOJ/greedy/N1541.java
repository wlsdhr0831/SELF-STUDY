package BOJ.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N1541 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		char[] exp = s.toCharArray();
		
		int idx = 0, sum = 0;
		while(idx < exp.length && exp[idx] >= '0' && exp[idx] <= '9')
			sum = sum*10 + (exp[idx++]-'0');
		
		boolean minus = false;
		if(idx < exp.length && exp[idx++] == '-') minus = true;
		
		int num = 0;
		for(; idx < exp.length; idx++) {
			if(exp[idx] >= '0' && exp[idx] <= '9') {
				num = num*10 + (exp[idx]-'0');
			}
			else {
				if(minus) sum -= num;
				else sum += num;
				num = 0;
				if(exp[idx] == '-') minus = true;
			}
		}
		if(minus) sum -= num;
		else sum += num;
		System.out.println(sum);
	}
}
