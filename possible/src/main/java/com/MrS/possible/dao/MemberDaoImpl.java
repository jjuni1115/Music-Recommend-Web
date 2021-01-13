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

    private static final String Namespace = "memberMapper.";

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
}

