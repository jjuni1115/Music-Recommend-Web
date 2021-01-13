package com.jinwoo.possible;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@Repository
public class member_dao {
//    @Inject
//    private final SqlSession session;
    @Autowired
    protected SqlSessionTemplate sqlSession;

    private static final String NameSpace = "memberMapper";

    public String TestPage(String m, HttpSession session){
        return sqlSession.selectOne(NameSpace + "Login", m);
    }

    public String try_login(member m, HttpSession session){
        return sqlSession.selectOne(NameSpace + "Login", m);
    }

//    @Inject
//    private SqlSession session;
//
//
//
//    public int check_id(member m){
//        try{
//            return session.selectOne(NameSpace + "check_id", m);  //중복 id
//        }
//        catch(Exception e){
//            e.printStackTrace();
//            return -1;
//        }
//    }
//
//    public int Register(member m){
//        int result_of_check = check_id(m);
//        if (result_of_check != 0) return result_of_check;  // redundant ID -> return
//
//        try{
//            session.insert(NameSpace + "Register", m);
//            return 0;
//        }
//        catch(Exception e){
//            e.printStackTrace();
//            return -2;
//        }
//    }
//
//    public int Login(member m, HttpSession Hsession) {
//        // member m : {'#member_id', '#member_pw'}  from index.jsp AJAX
//        // member field in member class
//        int Id_Search_Result = -1;
//        try {
//            Id_Search_Result = session.selectOne(NameSpace + "Login", m); // select result 1 piece -> result
//        } // more than 1 piece -> TooManyResultsException, 0 piece : null return
//        catch (Exception e) {
//            e.printStackTrace();
//            return -1;
//        }
//
//        if(Id_Search_Result != 1) return Id_Search_Result;
//
//        try {
//            member m_info = session.selectOne(NameSpace + "Login_Info", m);
//            Hsession.setAttribute("m", m_info);
//            return 1;
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            return -2;
//        }
//    }
}