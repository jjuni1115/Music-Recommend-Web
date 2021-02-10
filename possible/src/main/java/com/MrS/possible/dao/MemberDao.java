package com.MrS.possible.dao;

import com.MrS.possible.domain.Member;

public interface MemberDao {

    public Member logon(Member member);
    public Member check_id(Member member);
    public void register(Member member);
    public Member detail(Member member);
    public void infoChange(Member member);
}
