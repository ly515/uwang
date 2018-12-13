package com.uwang.x28.demo;

public class Rabbit {
	public static void main(String [] args) {
		int n = 8;
		System.out.println(Rabbit.fun(n));
	}
	private static int fun(int n){
		if(n==1 || n==2) {
			   return 1;
		}else {
			   return fun(n-1)+fun(n-2);
		}
	}

}
