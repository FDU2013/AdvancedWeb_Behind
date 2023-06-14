package com.example.behind.service.impl;

import com.example.behind.common.domain_attributes.CDKeyState;
import com.example.behind.domain.CDKey;
import com.example.behind.domain.Goods;
import com.example.behind.domain.User;
import com.example.behind.repository.CDKeyRepository;
import com.example.behind.repository.GoodsRepository;
import com.example.behind.repository.UserRepository;
import com.example.behind.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    GoodsRepository goodsRepository;
    UserRepository userRepository;
    CDKeyRepository cdKeyRepository;
    @Autowired
    GoodsServiceImpl(GoodsRepository goodsRepository, UserRepository userRepository, CDKeyRepository cdKeyRepository){
        this.goodsRepository = goodsRepository;
        this.userRepository = userRepository;
        this.cdKeyRepository = cdKeyRepository;
    }
    @Override
    public List<Goods> getAllGoods() {
        return goodsRepository.findAll();
    }

    @Override
    public boolean buyGoods(String userID, Long goodsID) throws Exception {
        User user = userRepository.findByAccount_UserID(userID);
        if(user == null){
            throw new Exception("user不存在");
        }
        Goods goods = goodsRepository.findById(goodsID).orElse(null);
        if(goods == null){
            throw new Exception("goods不存在");
        }
        Integer cost = goods.getCost();
        if(cost > user.getCredit()){
            return false;
        }
        user.subtractCredit(cost);
        user = userRepository.save(user);
        CDKey key = cdKeyRepository.save(new CDKey(0L, "KFCVME50", user, goods, new Date(), CDKeyState.Unused));
        key.setCode(key.getCode() + key.getId().toString());
        cdKeyRepository.save(key);
        return true;
    }

    @Override
    public List<CDKey> getUserItems(String userID) throws Exception {
        User user = userRepository.findByAccount_UserID(userID);
        if(user == null){
            throw new Exception("user不存在");
        }
        return cdKeyRepository.findByUser(user);
    }
}
