package monkhub.service;

import org.springframework.stereotype.Service;

@Service
public class Task1Service {

	//to find HCF
	public Long findHCF(Long num1, Long num2) {
	
		if(num1==0)
			return num2;
		if(num2==0)
			return num1;
		if(num1==num2)
			return num1;
		if(num1>num2)
			return findHCF(num1-num2, num2);
		return findHCF(num1, num2-num1);
	}
	
	// to find LCM
	public Long findLCM(Long num1, Long num2) {
		return (num1*num2)/findHCF(num1,num2);
	}
	
	
	//to find uppercase
	public String uppercase(String str) {
        char[] ch = str.toCharArray();
		
		for(int i=0;i<str.length();i++) {
			if(ch[i]>='a' && ch[i]<='z') {
				ch[i] = (char)(ch[i]-32);
			}
		}
		
		str = String.valueOf(ch);
		return str;
	}
	
	
	// to reverse a string
	public String reverse(String str) {
		char[] ch = str.toCharArray();
		
		int i=0,n=ch.length-1;
		while(i<n) {
			char temp = ch[i];
			ch[i] = ch[n];
			ch[n] = temp;
			i++;
			n--;
		}
		
		str = String.valueOf(ch);
		return str;
	}
	
	// to find vowels in string
	public String findVowel(String str) {
		String vowels="";
		for(int i=0;i<str.length();i++) {
			char temp = str.charAt(i);
			if(temp=='A' || temp=='E' || temp=='I' || temp == 'O' || temp=='U' ||
					temp=='a' || temp=='e' || temp=='i' || temp == 'o' || temp=='u')
				vowels= vowels+temp+" ";
		}
		return vowels;
	}
	
	// to remove whitespaces from string
	public String removeSpaces(String str) {
        char[] ch = new char[str.length()];
		int c=0;
		for(int i=0;i<str.length();i++) {
			if(str.charAt(i)!=' ')
				ch[c++]=str.charAt(i);
		}
		
		str = String.copyValueOf(ch, 0, c);
		return str;
	}
}
