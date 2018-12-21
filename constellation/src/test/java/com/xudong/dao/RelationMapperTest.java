package com.xudong.dao;

import com.xudong.entity.Relation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.Reader;

public class RelationMapperTest {

    @Autowired
    RelationMapper relationMapper;
    @Test
    public void tearDown() {
        System.out.println(relationMapper);
    }


    public static void main(String[] args) {
        String resource ="mybatis/mybatis-config.xml";
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sqlSessionFactory.openSession();
            Relation relation = session.selectOne("relationMapper.selectByPrimaryKey",2);
            session.commit();
            System.out.println(relation.getPid());
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
