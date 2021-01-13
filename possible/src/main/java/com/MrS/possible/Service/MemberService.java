package com.MrS.possible.Service;

import com.MrS.possible.domain.Member;

public interface MemberService {

    public Member logon(Member member);
    public Member check_id(Member account);
    public void register(Member member);
    public Member detail(Member member);
}
