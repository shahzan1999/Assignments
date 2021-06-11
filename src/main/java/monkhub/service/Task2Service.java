package monkhub.service;

import org.springframework.stereotype.Service;

@Service
public class Task2Service {

	// replace h with star
	public String replaceH(String str) {
		char[] ch = str.toCharArray();
		for(int i=0;i<str.length();i++) {
			if(ch[i]=='H')
				ch[i]='*';
		}
		return String.valueOf(ch);
	}
	
	//replace i with star
	public String replaceI(String str) {
		char[] ch = str.toCharArray();
		for(int i=0;i<str.length();i++) {
			if(ch[i]=='I' || ch[i]=='i')
				ch[i]='*';
		}
		return String.valueOf(ch);
	}
	
	//print pattern
	public void Pattern1() {
		for(int i=1;i<=5;i++) {
			for(int j=1;j<=5;j++) {
				if(j<i)
					System.out.print(" ");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	//print sandglasses star pattern
	public void sandGlass( int n) {
		for(int i=1;i<=n;i++) {
			
			for(int j=1;j<i;j++) 
				System.out.print(" ");
			
			for(int k=i;k<=n;k++)	
			    System.out.print(k+" ");
			
			System.out.println();
		}
		
        for(int i=n-1;i>=1;i--) {
			
			for(int j=1;j<i;j++) 
				System.out.print(" ");
			
			for(int k=i;k<=n;k++)	
			    System.out.print(k+" ");
			
			System.out.println();
		}
	}
	
	//to print pattern
	public void Pattern2() {
		for(int i=1;i<=4;i++) {
			for(int j=1;j<=9;j++) {
				if(j<5) {
					if(j==i)
						System.out.print('*');
					else
						System.out.print('0');
				} else if (j==5) {
					System.out.print('*');
				} else {
					if(i+j==10)
					System.out.print('*');
					else
						System.out.print('0');
				}	
			}
			System.out.println();
		}
	}
}
