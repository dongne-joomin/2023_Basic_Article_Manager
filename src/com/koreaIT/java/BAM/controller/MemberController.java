package com.koreaIT.java.BAM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.koreaIT.java.BAM.dto.Member;
import com.koreaIT.java.BAM.util.Util;

public class MemberController extends Controller {

	private List<Member> members;
	private Scanner sc;
	private int lastMemberId;
	

	public MemberController(Scanner sc) {
		this.sc = sc;
		this.members = new ArrayList<>();
		this.lastMemberId = 3;

	}

	@Override
	public void doAction(String cmd, String methodName) {

		switch (methodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		case "profile":
			showProfile();
			break;

		default:
			System.out.println("존재하지 않는 명령어 입니다.");
			break;
		}

	}

	private void doJoin() {
		
		if (isLogined()) {
			System.out.println("로그아웃 후 이용해주세요.");
			return;
		}
		int id = lastMemberId + 1;
		lastMemberId = id;

		String regDate = Util.getDate();
		String loginId = null;
		while (true) {
			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (loginIdDupChk(loginId) == false) {
				System.out.printf("%s은(는) 이미 사용중인 아이디입니다.\n", loginId);
				continue;
			}
			System.out.printf("%s은(는) 사용가능한 아이디입니다.\n", loginId);
			break;
		}
		String loginPw = null;
		String loginPwChk = null;
		while (true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("로그인 비밀번호 확인 : ");
			loginPwChk = sc.nextLine();

			if (loginPw.equals(loginPwChk) == false) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}
			break;
		}

		System.out.printf("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, regDate, loginId, loginPw, name);
		members.add(member);

		System.out.printf("%s 회원님 환영합니다.\n", loginId);
	}

	private void doLogin() {

		if (isLogined()) {
			System.out.println("이미 로그인 상태입니다.");
			return;
		}

		System.out.printf("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();

		Member member = getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println("존재하지 않는 아이디 입니다.");
			return;
		}
		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호를 확인해주세요.");
			return;
		}

		loginedMember = member;

		System.out.printf("%s님 환영합니다.\n", member.name);

	}

	private void showProfile() {
		
		if (isLogined() == false) {
			System.out.println("로그인후 이용해 주세요.");
			return;
		}
		System.out.println("== 내 정보 ==");
		System.out.printf("로그인 아이디 : %s\n", loginedMember.loginId);
		System.out.printf("이름 : %s\n", loginedMember.name);
		System.out.printf("로그인 비밀번호 : %s\n", loginedMember.loginPw);
		System.out.printf("회원번호 : %d\n", loginedMember.id);
		System.out.printf("가입날짜 : %s\n", loginedMember.regDate);

	}

	private void doLogout() {
		if (isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.");
			return;
			
		}
		loginedMember = null;
		System.out.println("로그아웃 되었습니다.");
	}



	private Member getMemberByLoginId(String loginId) {
		for (Member member : members) {

			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	private boolean loginIdDupChk(String loginId) {
		Member member = getMemberByLoginId(loginId);

		if (member == null) {
			return true;
		}
		return false;
	}

	public void makeTestDats() {
		System.out.println("회원 테스트 데이터를 생성합니다.");
		members.add(new Member(1, Util.getDate(), "test1", "test1", "test1"));
		members.add(new Member(2, Util.getDate(), "test2", "test2", "test2"));
		members.add(new Member(3, Util.getDate(), "test3", "test3", "test3"));

	}

}
