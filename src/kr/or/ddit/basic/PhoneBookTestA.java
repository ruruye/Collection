package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class PhoneBookTestA {
	private HashMap<String, Phone> phoneBookMap;
	private Scanner scan;
	
	//생성자
	public PhoneBookTestA() {
		scan = new Scanner(System.in);
		phoneBookMap = new HashMap<>();
	}
	
	public static void main(String[] args) {
		new PhoneBookTestA().phoneBookStart();
	}
	
	//프로그램을 시작하는 메서드
	public void phoneBookStart() {
		System.out.println("+++++++++++++++++++++++++++");
		System.out.println("  전 화 번 호 관 리 프 로 그 램   ");
		System.out.println("+++++++++++++++++++++++++++");
		System.out.println();
		
		
		while(true) {
			int choice = displayMenu();
			
			switch (choice) {
				case 1: //등록
					insert();
					break;
				case 2: //수정
					update();
					break;
				case 3: //삭제
					delete();
					break;	
				case 4: //검색
					search();
					break;
				case 5: //전체출력
					displayAll();
					break;
				case 0: //종료
					System.out.println();
					System.out.println("프로그램을 종료합니다.");
					return;
				default:
					System.out.println();
					System.out.println("작업번호를 잘못선택했습니다. 다시 선택하세요.");
					
			}
		}
	}
	
	// 전화번호 정보를 검색하는 메서드
	private void search() {
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = scan.next();
		if(!phoneBookMap.containsKey(name)) { //해당이름이 없으면
			System.out.println();
			System.out.println(name + "씨 전화번호 정보가 없습니다");
			//return; //return 안하고 싶으면 else
		}else {
			Phone p = phoneBookMap.get(name);
			System.out.println(name + "씨 전화번호 정보");
			System.out.println("------------------------------");
//			System.out.println("이름:" + name);
			System.out.println("이름:" + p.getName());
			System.out.println("전화번호:" + p.getTel());
			System.out.println("주소:" + p.getAddr());
			System.out.println("------------------------------");
		}
		System.out.println();
	}
	
	// 전화번호 정보를 삭제하는 메서드
	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = scan.next();
		if(!phoneBookMap.containsKey(name)) { //해당이름이 없으면
			System.out.println();
			System.out.println(name + "씨 전화번호 정보가 없습니다");
			return;
		}
		phoneBookMap.remove(name); //key값(이름)을 이용해서 삭제한다.
		System.out.println(name + "씨 전화번호 정보를 삭제완료");
		
	}
	
	// 전화번호 정보를 수정하는 메서드
	private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = scan.next();
		if(!phoneBookMap.containsKey(name)) {
			System.out.println();
			System.out.println(name + "씨 전화번호 정보가 없습니다");
			return;
		}
		System.out.println("새로운 전화번호 >> ");
		String newTel = scan.next();
		
		scan.nextLine(); // 버퍼비워줌
		
		System.out.println("새로운 주소 >> ");
		String newAddr = scan.nextLine();
		
		// 같은 key값에 새로운 전화번호 정보를 저장한다. 
		phoneBookMap.put(name, new Phone(name, newTel, newAddr));
		System.out.println(name + "씨 전화번호 정보를 변경완료");
		
	}
	
	// 전체 전화번호 정보를 출력하는 메서드
	private void displayAll() {
		System.out.println();
		
		Set<String> phoneKeySet = phoneBookMap.keySet();
		
		System.out.println("----------------------------------------");
		System.out.println("번호\t이름\t전화번호\t주소");
		System.out.println("----------------------------------------");
		
		if(phoneKeySet.size()==0) {
			System.out.println(" 등록된 전화번호 정보가 하나도 없습니다");
		}else {
			int num = 0; // 번호 출력용 변수선언
			Iterator<String> keyIt = phoneKeySet.iterator();
			while(keyIt.hasNext()) {
				num++;
				String name = keyIt.next(); // 키값(이름)가져오기
				Phone p = phoneBookMap.get(name);
				System.out.println(" " + num + "\t" + p.getName() + "\t" + p.getTel() + "\t" + p.getAddr());
				
			}
		}
		System.out.println("----------------------------------------");
		System.out.println("출력끝");
		
	}
	
	/*
	 - Scanner객체의 next(), nextInt(), nextDouble().. 등 nextLine()이 아닌 메서드
	 	==> 사이띄기, Tab키, Enter 키를 구분문자로 분리해서 분리된 자료만 읽어간다.
	 	
	 - Scanner객체의 nextLine()메서드
	 	==> 한 줄 단위로 입력한다. 즉, 자료를 입력하고 Enter키를 누르면 Enter키까지 읽어가서
	 		Enter키를 뺀 나머지 데이터를 반환한다.
	 
	 - 그래서 nextLine()메서드를 사용하기 전에 nextLine()이외의 메서드를 입력한 이력이 있는 경우에는
	 	nextLine()메서드를 한번 호출해서 입력버퍼를 비워주어야 한다.
	 */
	
	// 새로운 전화번호 정보를 등록하는 메서드
	private void insert() {
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이름 >> ");
		String name = scan.next();
		
		// 이미 등록된 사람인지 여부 검사
		if(phoneBookMap.containsKey(name)) { //이미 있으면
			System.out.println(name + "씨는 이미 등록된 사람입니다.");
			return;
		}
		
		System.out.print("전화번호 >> ");
		String tel = scan.next();
		
		scan.nextLine(); // 버퍼비워줌
		
		System.out.print("주소 >> ");
		String addr = scan.nextLine();
		
//		Phone p = new Phone(name, tel, addr);
//		phoneBookMap.put(name, p);
		
		phoneBookMap.put(name, new Phone(name, tel, addr));
		System.out.println(name + " 전화번호 정보 등록 완료");
		
		
	}
	
	
	//메뉴를 출력하고 작업번호를 입력받아 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("=======메  뉴========");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("0. 프로그램 종료");
		System.out.println("===================");
		System.out.print("번호입력 >> ");
		return scan.nextInt();
	}
	
	
	
}

//이름,주소,전화번호를 멤버로 갖는 Phone클래스
//class Phone{
//	private String name;
//	private String tel;
//	private String addr;
//	
//	public Phone(String name, String tel, String addr) {
//		super();
//		this.name = name;
//		this.tel = tel;
//		this.addr = addr;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getTel() {
//		return tel;
//	}
//
//	public void setTel(String tel) {
//		this.tel = tel;
//	}
//
//	public String getAddr() {
//		return addr;
//	}
//
//	public void setAddr(String addr) {
//		this.addr = addr;
//	}
//
//	@Override
//	public String toString() {
//		return "Phone [name=" + name + ", tel=" + tel + ", addr=" + addr + "]";
//	}
//	
//	
//}
