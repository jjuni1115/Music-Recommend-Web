package com.MrS.possible.Service;

import com.MrS.possible.domain.Member;
import com.MrS.possible.dao.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberDao memberDao;

    public Member logon(Member member) {
        return memberDao.logon(member);
    }

    // Check ID Redundant
    public Member check_id(Member member) {
        member = memberDao.check_id(member);
        return member;
    }

    // User Register Service
    public void register(Member member) {

        // Cutting Too Long name
        String FN = member.getFirst_name();
        String LN = member.getLast_name();
        if (FN.length() > 20){
            FN.substring(0, 20);
            member.setFirst_name(FN);
        }
        if (LN.length() > 20){
            LN.substring(0, 20);
            member.setLast_name(LN);
        }
        System.out.println(member);

        // DB Access and Register(insert)
        memberDao.register(member);
    }

    // Show User's Detail info
    public Member detail(Member member) {
        member = memberDao.detail(member);
        return member;
    }

    @Override
    public void infoChange(Member member) {
        memberDao.infoChange(member);
    }
}

