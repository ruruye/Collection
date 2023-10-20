package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Objects;

public class EqualsHashCodeTest {

	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setId(1);
		p1.setName("홍길동");
		
		Person p2 = new Person();
//		p2.setId(2);
//		p2.setName("일지매");
		p2.setId(1); 
		p2.setName("홍길동"); //-->false true**
		
		Person p3 = p1;
		
		System.out.println(p1 == p2); //false
		System.out.println(p1 == p3); //true

		System.out.println(p1.equals(p2)); //true(1) false(2)
		System.out.println(p1.equals(p3));
		
		HashSet<Person> testSet = new HashSet<>();
		testSet.add(p1);
		testSet.add(p2);
		
		System.out.println("Set의 개수 : " + testSet.size());
		
		System.out.println("p1 : " + p1.hashCode());
		System.out.println("p2 : " + p2.hashCode());
		System.out.println("p3 : " + p3.hashCode());
	}

}

/*
- equals()메서드   => 두 객체의 내용이 같은지를 비교하는 메서드 ==> 동등성
- hashCode()메서드 => 두 객체가 같은 객체인지 비교하는 메서드 ==> 동일성

- Hashtable, HashMap, HashSet과 같이 Hash로 시작하는 컬렉션 객체들은
  객체의 의미상의 동일성 비교를 위해서 hashCode()메서드를 호출하여 비교한다.
  그러므로 객체가 같은지 여부를 결정하려면 equals()메서드와 hashCode()메서드를 같이 재정의 해야한다.

- hashCode()메서드에서 사용하는 '해싱알고리즘'은 서로 다른 객체들에 대해 같은 hashCode값을 만들어 낼 수 있다.

 */




// Person객체의 id값과 name값이 모두 같으면 같은 객체로 처리되도록 하기**
//  ==> equals()메서드를 재정의해서 처리한다.

class Person{
	private int id;
	private String name;
	
//	public Person() {
//		this; 자기자신 참조값이 들어있는 변수
//		this();
//	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
// Person객체의 id값과 name값이 모두 같으면 같은 객체로 처리되도록 하기**
//  ==> equals()메서드를 재정의해서 처리한다.
	
	@Override
	public boolean equals(Object obj) {
		// 참조값(주소값)이 같은지 검사
		if(this==obj) {
			return true;
		}
		
		if(obj==null) {
			return false;
		}
		
		// (참조값도 같지않고 null도 아닐때) 같은 유형의 클래스인지 검사
		if(this.getClass() != obj.getClass()) {
			return false;
		}
		
		// 매개변수에 저장된 객체를 현재 객체 유형으로 형변환한다.
		Person that = (Person) obj;
		
		// 비교 대상의 멤버 변수들이 같은지 여부를 검사한다.
		return this.id == that.id && Objects.equals(this.name, that.name);
		
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}
