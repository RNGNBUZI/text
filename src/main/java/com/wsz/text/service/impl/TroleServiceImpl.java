package com.wsz.text.service.impl;

import com.wsz.text.dao.TroleMapper;
import com.wsz.text.entity.Trole;
import com.wsz.text.service.TroleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TroleServiceImpl implements TroleService {
    @Autowired
    private TroleMapper troleMapper;
    @Override
    public List<Trole> queryAll() {

        return troleMapper.selectAll();
    }
}
