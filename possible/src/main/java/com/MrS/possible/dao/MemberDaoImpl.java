package com.MrS.possible.dao;

import com.MrS.possible.domain.Member;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl implements MemberDao {
    @Autowired
    @Qualifier("sqlSession")
    SqlSession sqlSession;

    private Member member;

    private static final String Namespace = "memberMapper.";

    public void setMember(Member member) {      // 생성자에서 member를 받아서 member 값을 저장해둔다. <= 나중에 다른 곳에서 Dao에 접근하여 가져갈 수 있다.
        this.member = member;
    }

    public Member getMember(){
        return this.member;
    }

    // Logon
    public Member logon(Member member){
        Member result = new Member();
        result = sqlSession.selectOne(Namespace + "login", member);
        System.out.println(result);
        return result;
    }

    // Check ID Redundant
    public Member check_id(Member member) {
        return(sqlSession.selectOne(Namespace + "check_id", member));
    }

    // User Register
    public void register(Member member) {
        sqlSession.insert(Namespace + "register", member);
    }

    // User Info
    public Member detail(Member member) {
        member = sqlSession.selectOne(Namespace + "detail", member);
        return member;
    }

    @Override
    public void infoChange(Member member) {
        sqlSession.update(Namespace + "infoChange", member);
//        sqlSession.close();   <- session.close() is not allowed in Spring Manager
    }
}

