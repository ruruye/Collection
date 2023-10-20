package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HotelTest {
	Scanner scan = new Scanner(System.in);
	Map<Integer, Room> map = new HashMap<>();
	
	// 생성자
	public HotelTest() {
		// 초기화
		map = new HashMap<>();
		scan = new Scanner(System.in);
		
		for(int i=2; i<=4; i++) {
			String type=null;
			switch(i) {
			case 2 : type = "싱글룸"; break;
			case 3 : type = "더블룸"; break;
			case 4 : type = "스위트룸"; break;
			}
			
			for(int j=1; j<=9; j++) {
				int num = i * 100 + j;
				map.put(num, new Room(num, type));
			}
		}// 객체 초기화 끝
		
//		for(int roomNum=201; roomNum<=209 ; roomNum++) {
//			map.put(roomNum, new Room(roomNum, "싱글룸", null));	
//		}
//		for(int roomNum=301; roomNum<=309 ; roomNum++) {
//			map.put(roomNum, new Room(roomNum, "더블룸", null));	
//		}	
//		for(int roomNum=401; roomNum<=409 ; roomNum++) {
//			map.put(roomNum, new Room(roomNum, "스위트룸", null));	
//		}	
		
	}
	
	public static void main(String[] args) {
		new HotelTest().start();
	}
	
	// 시작 메서드
	public void start() {
		System.out.println("*********************************************");
		System.out.println("       호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*********************************************");
		System.out.println();
		
		while(true) {
			int choice = displayMenu();
			
			switch (choice) {
			case 1:
				//체크인
				checkIn();
				break;
			case 2:
				//체크아웃
				CheckOut();
				break;
			case 3:
				//객실상태
				View();
				break;
			case 4:
				//업무종료
				System.out.println("*********************************************");
				System.out.println("       호텔문을 닫았습니다.");
				System.out.println("*********************************************");
				return;
			default:
				System.out.println();
				System.out.println("번호를 잘못선택했습니다. 다시 선택하세요.");
			}
			
		}
	}
	
	// 체크인
	private void checkIn() {
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("   체크인 작업");
		System.out.println("----------------------------------------------");
		System.out.println(" * 201~209 : 싱글룸");
		System.out.println(" * 301~309 : 더블룸");
		System.out.println(" * 401~409 : 스위트룸");
		System.out.println("----------------------------------------------");
		System.out.print("방 번호 입력 >> ");
		int num = scan.nextInt();
		
		// 입력한 방번호 있는지 검사하기
		if(!map.containsKey(num)) { // 해당 방 번호가 없으면
			System.out.println(num + "호 객실은 존재하지 않습니다.");
			return; // 더 이상 진행하면 안되니까
		}
		
		// 입력한 방번호의 객실에 손님이 이미 있는지 검사
		if(map.get(num).getGuest() != null) {
			System.out.println(num +"호 객실에는 이미 손님이 있습니다");
			return;
		}
//		Room r = map.get(num);
//		if(r.getGuest() !=null) { // 해당 방에 이미 손님이 있다면
//			System.out.println(num +"호 객실에는 이미 손님이 있습니다");
//			return;
//		}
		
		System.out.println();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 >> ");
		String name = scan.next();
		
		// 입력받은 투숙객이름을 해당 객실의 투숙객 명단에 저장한다.
		map.get(num).setGuest(name);		
//		r.setGuest(name);
		System.out.println(name+ "씨가 " + num+ "호 객실의 "+"체크인이 완료되었습니다.");
	}
	
	// 객실상태
	private void View() {
		// 방번호를 순서대로 나오게 하기 위해서 방번호(map의 key값)만 List에 넣은후 정렬해서 사용
		ArrayList<Integer> roomNumList = new ArrayList<>(map.keySet());
		//Set<Integer> roomKeySet = map.keySet();
		
		// 방번호를 기준으로 오름차순 정렬하기
		Collections.sort(roomNumList);
		
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("     현     재      객      실     상      태");
		System.out.println("----------------------------------------------");
		System.out.println("방 번호\t 방 종류\t 투숙객 이름");
		System.out.println("----------------------------------------------");
		
		
		// List에서 방번호를 하나씩 꺼내와 Map에서 해당 방번호에 대한 Room객체를 구해서 출력한다.
		for(int num : roomNumList) {
			Room r = map.get(num);
			
			System.out.print(r.getRoomNum() + "\t" + r.getRoomType() + "\t");
			String name = "-";
			if(r.getGuest()!=null) { // 투숙객이 있으면
				name = r.getGuest();
			}
			System.out.println(name);
		}
		System.out.println("----------------------------------------------");
		System.out.println();
//			Iterator<Integer> keyIt = roomKeySet.iterator();
//			while(keyIt.hasNext()) {
//				int roomNum = keyIt.next();
//				Room r = map.get(roomNum);
//				String roomType ="";
//				if(roomNum>=201 && roomNum<=209) {
//					roomType = "싱글룸";
//				} else if(roomNum>=301 && roomNum<=309) {
//					roomType = "더블룸";
//				} else if(roomNum>=401 && roomNum<=409) {
//					roomType = "스위트룸";
//				}
//				System.out.println(" "  + roomNum + "\t" + r.getRoomType() + "\t" + 
//				(r.getGuest() !=null ? r.getGuest() : "-"));
//			}
				
		}
		
	
	
	// 체크아웃
	private void CheckOut() {
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("   체크아웃 작업");
		System.out.println("----------------------------------------------");
		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		System.out.print("방 번호 입력 >> ");
		int num = scan.nextInt();
		
		if(!map.containsKey(num)) { //해당 방 번호가 없으면
			System.out.println(num + "호 객실은 존재하지 않습니다.");
	//		return; return 안쓰고 싶으면 else
		}else if(map.get(num).getGuest()==null) { // 손님이 없는 방일때
			System.out.println(num + "호 객실에는 체크인 한 손님이 없습니다.");
//			Room r = map.get(num);
//			if(r.getGuest() == null) { //해당 방에 체크인 한 사람이 없으면
//				System.out.println(num + "호 객실에는 체크인 한 사람이 없습니다.");
//				return;
//			}
		}else {
			// 체크아웃 작성 진행 ==> 해당 객실의 투숙객이름을 null로 변경하면 된다.
			
			// 현재 객실의 손님 이름 구하기
			String name = map.get(num).getGuest();
			
			// 투숙객 이름을 null로 변경하기
			map.get(num).setGuest(null);
			
			System.out.println(num + "호 객실의 " + name + "님 체크아웃을 완료했습니다.");
//			map.remove(num);
//			System.out.println(num + "호 객실의 " + r.getGuest() + "님 체크아웃을 완료했습니다.");
		}

	}
	
	// 메뉴를 출력하고 작업번호를 입력받아 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("-----------------------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
		System.out.println("-----------------------------------------------------------");
		System.out.print("선택>> ");
		return scan.nextInt();
	}
}

class Room{
	private int roomNum;     //방번호
	private String roomType; //방종류
	private String guest;    //투숙객이름
	
	//생성자
	public Room(int roomNum, String roomType) {
		super();
		this.roomNum = roomNum;
		this.roomType = roomType;
		
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

	@Override
	public String toString() {
		return "Room [roomNum=" + roomNum + ", roomType=" + roomType + ", guest=" + guest + "]";
	}
}

	
	
