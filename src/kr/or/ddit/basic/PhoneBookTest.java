package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PhoneBookTest {
	Scanner scan = new Scanner(System.in);
	Map<String, Phone> map = new HashMap<>();

	public static void main(String[] args) {
		new PhoneBookTest().start();
	}

	public void start() {
		System.out.println("+++++++++++++++++++++++++++");
		System.out.println("  전 화 번 호 관 리 프 로 그 램   ");
		System.out.println("+++++++++++++++++++++++++++");
		System.out.println();
		
		while (true) {
			System.out.println("	메  뉴");
			System.out.println("1. 전화번호 등록");
			System.out.println("2. 전화번호 수정");
			System.out.println("3. 전화번호 삭제");
			System.out.println("4. 전화번호 검색");
			System.out.println("5. 전화번호 전체 출력");
			System.out.println("0. 프로그램 종료");
			System.out.println("------------------");
			System.out.print("번호입력 >> ");
			int menu = Integer.parseInt(scan.nextLine());

			switch (menu) {
			case 1:
				System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
				System.out.print("이름 >> ");
				String name = scan.nextLine();
				if (map.containsKey(name)) {
					System.out.println(name + "은 이미 등록된 사람입니다.");
					continue;
				}
				System.out.print("전화번호 >> ");
				String tel = scan.nextLine();
				System.out.print("주소 >> ");
				String addr = scan.nextLine();
				map.put(name, new Phone(name, addr, tel));
				System.out.println(name + "전화번호 등록 완료!!");
				break;
			case 2:
				System.out.println("수정할 이름을 입력하세요.");
				System.out.print("이름 >> ");
				String uName = scan.nextLine();
				System.out.print("전화번호 >> ");
				String uTel = scan.nextLine();
				System.out.print("주소 >> ");
				String uAddr = scan.nextLine();
				Phone phone = map.get(uName);
				phone.setAddr(uAddr);
				phone.setTel(uTel);
				System.out.println(uName + "수정완료!!");
				break;
			case 3:
				System.out.println("삭제할 이름을 입력하세요.");
				System.out.print("이름 >> ");
				String dName = scan.nextLine();
				map.remove(dName);
				System.out.println(dName + "삭제완료!!");
				break;
			case 4:
				System.out.println("검색할 이름을 입력하세요.");
				System.out.print("이름 >> ");
				String sName = scan.nextLine();
				Phone rPhone = map.get(sName);
				System.out.println("----------------------------------------");
				System.out.println("이름\t전화번호\t주소");
				System.out.println("----------------------------------------");
				System.out.println(rPhone);
				break;
			case 5:
				Set<String> phoneKeySet = map.keySet();
				
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
						String namename = keyIt.next(); // 키값(이름)가져오기
						Phone p = map.get(namename);
						System.out.println(" " + num + "\t" + p.getName() + "\t" + p.getTel() + "\t" + p.getAddr());
						
					}
				}
				System.out.println("----------------------------------------");
//				int num = 0;
//				for (Phone v : map.values()) {
//					System.out.println(++num + "\t" + v.toString());
//				}
				break;
			case 0:
				System.out.println("프로그램을 종료합니다...");
				System.exit(0);
				break;
			default:
				System.out.println();
				System.out.println("작업번호를 잘못선택했습니다. 다시 선택하세요.");

			}

		}

	}
}

class Phone {
	private String name;
	private String addr;
	private String tel;

	public Phone(String name, String addr, String tel) {
		super();
		this.name = name;
		this.addr = addr;
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return String.format("%s\t%s\t%s", name, tel, addr);
	}

}
