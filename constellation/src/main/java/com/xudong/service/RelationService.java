package com.xudong.service;

import com.xudong.dao.RelationMapper;
import com.xudong.entity.Relation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RelationService {

    @Autowired
    private RelationMapper relationMapper;

    public Relation selectAll(){
        Relation relation = relationMapper.selectByPrimaryKey("865352033467662");
        System.out.println(relation);
        return relation;
    }

    public static void main(String[] args) {
        RelationService r = new RelationService();
        r.selectAll();
    }
}
