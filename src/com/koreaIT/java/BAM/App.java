package com.koreaIT.java.BAM;

import java.util.Scanner;

import com.koreaIT.java.BAM.controller.ArticleController;
import com.koreaIT.java.BAM.controller.Controller;
import com.koreaIT.java.BAM.controller.MemberController;

public class App {

	public void run() {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

		ArticleController articleController = new ArticleController(sc);
		MemberController memberController = new MemberController(sc);

		articleController.makeTestDats();
		memberController.makeTestDats();

		while (true) {
			System.out.printf("명령어)");
			String cmd = sc.nextLine().trim();

			if (cmd.length() == 0) {
				System.out.println("명령어를 입력해주세요.");
				continue;
			}

			if (cmd.equals("exit")) {
				break;
			}

			String[] cmdBits = cmd.split(" ");

			if (cmdBits.length == 1) {
				System.out.println("명령어를 확인해주세요");
				continue;
			}
			String controllerName = cmdBits[0];
			String methodName = cmdBits[1];

			Controller controller = null;

			if (controllerName.equals("member")) {
				controller = memberController;
			} else if (controllerName.equals("article")) {
				controller = articleController;
			} else {
				System.out.println("존재하지 않는 명령어 입니다.");
				continue;
			}

			String actionName = controllerName + "/" + methodName;

			switch (actionName) {
			case "article/write":
			case "article/modify":
			case "article/delete":
			case "member/profile":
			case "member/logout":
				if (Controller.isLogined() == false) {
					System.out.println("로그인 후 이용해주세요.");
					continue;
				}
				break;
			case "member/join":
			case "member/login":
				if (Controller.isLogined()) {
					System.out.println("로그아웃 후 이용해주세요.");
					continue;
				}
				break;
			}

			controller.doAction(cmd, methodName);

		}
		System.out.println("==프로그램 끝==");

		sc.close();
	}

}
