package com.example.behind.service;

import com.example.behind.domain.CDKey;
import com.example.behind.domain.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> getAllGoods();
    boolean buyGoods(String userID, Long goodsID) throws Exception;
    List<CDKey> getUserItems(String userID) throws Exception;
}
