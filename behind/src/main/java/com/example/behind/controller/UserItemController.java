package com.example.behind.controller;

import com.example.behind.common.MyPage;
import com.example.behind.common.Result;
import com.example.behind.domain.CDKey;
import com.example.behind.domain.Goods;
import com.example.behind.domain.dto.BuyItemData;
import com.example.behind.domain.vo.CDKeyVO;
import com.example.behind.domain.vo.GoodVO;
import com.example.behind.domain.vo.PageData;
import com.example.behind.service.GoodsService;
import com.example.behind.utils.DSTransTool;
import com.example.behind.utils.MyPageTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserItemController {
    @Autowired
    private GoodsService goodsService;
    @PostMapping("/getItems")
    public Result getItems() {
        try{
            List<Goods> goods = goodsService.getAllGoods();
            List<GoodVO> goodVOs = new ArrayList<>();
            goods.forEach(good -> {
                goodVOs.add(DSTransTool.Good2VO(good));
            });
            return Result.succ(goodVOs);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(623,"获取可兑换物信息失败");
        }

    }

    @PostMapping("/buyItem")
    public Result buyItem(HttpServletRequest request, @RequestBody BuyItemData buyItemData) {
        try{
            String userID = request.getHeader("userID");
            boolean succ = goodsService.buyGoods(userID,buyItemData.getItemID());
            if(succ)
                return Result.succ(null,"兑换成功");
            else
                return Result.fail(624,"兑换失败");
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(625,"兑换失败");
        }
    }

    @PostMapping("/myItem")
    public Result myItem(HttpServletRequest request, @RequestBody PageData pageData) {
        try{
            String userID = request.getHeader("userID");
            List<CDKey> cdKeys = goodsService.getUserItems(userID);
            List<CDKeyVO> cdKeyVOs = new ArrayList<>();
            cdKeys.forEach(cdKey -> {
                cdKeyVOs.add(DSTransTool.CDKey2VO(cdKey));
            });
            MyPage<CDKeyVO> ret= MyPageTool.getPage(cdKeyVOs, pageData.getPageSize(), pageData.getPageNum());
            return Result.succ(ret);
        }catch (Exception e){
            e.printStackTrace();
            return Result.fail(625,"获取历史记录失败");
        }
    }
}
