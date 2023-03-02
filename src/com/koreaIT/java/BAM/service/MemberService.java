package com.koreaIT.java.BAM.service;

import com.koreaIT.java.BAM.container.Container;
import com.koreaIT.java.BAM.dao.MemberDao;
import com.koreaIT.java.BAM.dto.Member;

public class MemberService {
	private MemberDao memberDao;

	public MemberService() {
		this.memberDao = Container.memberDao;

	}

	public int getLastId() {
		return memberDao.getLastId();
	}

	public void add(Member member) {
		Container.memberDao.add(member);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public boolean loginIdDupChk(String loginId) {
		return Container.memberDao.loginIdDupChk(loginId);
	}

	public String getWriterName(int memberId) {
		return memberDao.getWriterName(memberId);
	}


}
