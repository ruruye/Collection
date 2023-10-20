package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 	문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다.
 	이 Student클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화 처리를 한다.
 	
 	이 Student객체는 List에 저장하여 관리한다.
 	
 	List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부정렬기준을 구현하고,
 	총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬되는 외부정렬기준 클래스를 작성하여 
 	정렬된 결과를 출력하시오.
 	
 	등수는 List에 전체 데이터가 추가된 후에 구해서 저장되도록 한다. (StudentTest클래스에 처리)
 */

public class StudentTest {
	
	// 등수를 구하는 메서드
	public void createRank(ArrayList<Student> rankStdList) {
		for(Student std1 : rankStdList) { // 기준 데이터를 구하기 위한 반복문
			int rank = 1; // 처음에는 등수를 1로 설정해놓고 시작한다.
			
			for(Student std2 : rankStdList) { // 비교대상을 나타내는 반복문
				if(std1.getTot() < std2.getTot()) { // 기준값보다 비교대상이 크면
					rank++;
					
				}
			}
			//instance~ 다 객체를 생성해야 쓸 수 있음
			
			// 구해진 등수를 기준데이터의 rank변수에 저장한다.
			std1.setRank(rank);
		}
	}
	
	
	public static void main(String[] args) {
		ArrayList<Student> stdList = new ArrayList<>();
		
		stdList.add(new Student(1, "홍길동", 90, 95, 80));
		stdList.add(new Student(3, "성춘향", 90, 75, 70));
		stdList.add(new Student(7, "강감찬", 95, 95, 80));
		stdList.add(new Student(5, "변학도", 80, 95, 90));
		stdList.add(new Student(2, "일지매", 100, 85, 80));
		stdList.add(new Student(4, "이순신", 60, 65, 70));
		stdList.add(new Student(6, "이몽룡", 90, 100, 80));
		
		
		StudentTest test = new StudentTest();
		// 등수를 구하는 메서드 호출
		test.createRank(stdList);
		
		
		System.out.println("정렬전");
		for(Student std : stdList) {
			System.out.println(std);
		}
		System.out.println("-------------------------------------");
		
		Collections.sort(stdList);
		System.out.println("학번의 오름차순 정렬후");
		for(Student std : stdList) {
			System.out.println(std);
		}
		System.out.println("-------------------------------------");
		
		Collections.sort(stdList, new SortByTotal());
		System.out.println("총점의 역순으로 정렬후");
		for(Student std : stdList) {
			System.out.println(std);
		}
		System.out.println("-------------------------------------");
	}
}


//  문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다.
//	이 Student클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화 처리를 한다.

class Student implements Comparable<Student>{
	private int num;
	private String name;
	private int kor;
	private int eng;
	private int mat;
	private int tot;
	private int rank;
	
	public Student(int num, String name, int kor, int eng, int mat) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.tot = kor + eng+ mat;
		
		
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMat() {
		return mat;
	}

	public void setMat(int mat) {
		this.mat = mat;
	}

	public int getTot() {
		return tot;
	}

	public void setTot(int tot) {
		this.tot = tot;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", mat=" + mat + ", tot="
				+ tot + ", rank=" + rank + "]";
	}
	// 내부정렬기준은 현재객체(this)와 매개변수에 저장되는 객체와 비교해서 처리한다.
	// 현재객체(this)가 앞쪽 데이터, 매개변수에 저장된 객체가 뒤쪽 데이터라고 하고 코딩한다.
	@Override
	public int compareTo(Student std) {
		// 학번의 오름차순
		return Integer.compare(this.getNum(), std.getNum());
	}	
}

//총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬되는 외부정렬기준 클래스
class SortByTotal implements Comparator<Student>{
	@Override
	public int compare(Student std1, Student std2) {
		if(std1.getTot() == std2.getTot()) { // 총점이 같으면
			return std1.getName().compareTo(std2.getName()); // 이름의 오름차순 정렬
		}else {
			return Integer.compare(std1.getTot(), std2.getTot()) * -1; //총점의 내림차순 정렬
		}
	}
}

