package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;


/*
  	문제) 5명의 사람 이름을 입력받아 ArrayList에 저장한 후에
  		 이 ArrayList에 저장된 이름 중에서 '김'씨 성의 이름을 모두 출력하시오.
  		 (입력은 Scanner객체를 이용)
 */
public class ArrayListTest02 {
	   public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);
	    
	    ArrayList<String> nameList = new ArrayList<>();
	    
	    System.out.println("5명의 이름을 입력하세요");
	    for(int i=1; i<=5; i++) {
	       System.out.print(i + "번째 사람 이름: ");
	       String name = scanner.nextLine();
	       nameList.add(name);
	   }
	   System.out.println();
	   System.out.println("김씨 성을 가진 사람들");
	   for(int i=0; i<nameList.size(); i++) {
//		   if(nameList.get(i).substring(0, 1).equals("김")) {
//			   System.out.println(nameList.get(i));
//		   }
		   
//		   if(nameList.get(i).indexOf("김")==0) {
//			   System.out.println(nameList.get(i));
//		   }
		   
//		   if(nameList.get(i).charAt(0)=='김') {
//			   System.out.println(nameList.get(i));
//		   }
		   
		   
		   //true 생략가능
		   
		   //false이면 김씨 성이 아닌 사람 찾아라
		   //if(!nameList.get(i).startsWith("김"))
//		   if(nameList.get(i).startsWith("김")==true) {
//			   System.out.println(nameList.get(i));
//		   }
		   
		   //안됨 - contains는 일부분만 찾아줌 
//		   if(nameList.get(i).contains("김")==true) {
//			   System.out.println(nameList.get(i));
//		   }
		   
	   }
	  
	 
  }
}
