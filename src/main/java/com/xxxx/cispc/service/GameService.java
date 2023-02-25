package com.xxxx.cispc.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxxx.cispc.base.BaseService;
import com.xxxx.cispc.dao.GameMapper;
import com.xxxx.cispc.enums.DevResult;
import com.xxxx.cispc.query.GameQuery;
import com.xxxx.cispc.query.MyGameQuery;
import com.xxxx.cispc.utils.AssertUtil;
import com.xxxx.cispc.vo.Game;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameService extends BaseService<Game,Integer> {
    @Resource
    private GameMapper gameMapper;

    /**
     * 通过条件查询
     * @param gameQuery
     * @return
     */
    public Map<String,Object> queryGameByParams(GameQuery gameQuery){
        Map<String ,Object> map = new HashMap<>();
        PageHelper.startPage(gameQuery.getPage(),gameQuery.getLimit());
        PageInfo<Game> pageInfo = new PageInfo<>(gameMapper.selectByParams(gameQuery));
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    /**
     * 添加
     *      名字
     *      价格
     *      内容
     *      保证金
     * @param game
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addGame(Game game,Integer userId){
        AssertUtil.isTrue(StringUtils.isBlank(game.getGameName()),"游戏名字不能为空");
        AssertUtil.isTrue(game.getGamePrice() == null,"游戏价格不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(game.getGameProfile()),"游戏内容不能为空");
        AssertUtil.isTrue(game.getBond() == null,"保证金不能为空");
        System.out.println(userId);
        game.setgId(userId);
        game.setStater(DevResult.UNDEV.getStatus());
        game.setCreateDate(new Date());
        game.setUpdateDate(new Date());
        AssertUtil.isTrue(gameMapper.insertSelective(game) != 1,"添加失败");
    }

    /**
     * 修改
     * @param game
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateGame(Game game){
        System.out.println(game);
        AssertUtil.isTrue(game.getId() == null || gameMapper.selectByPrimaryKey(game.getId()) == null,"待更新记录不存在");
        AssertUtil.isTrue(StringUtils.isBlank(game.getGameName()),"游戏名字不能为空");
        AssertUtil.isTrue(game.getGamePrice() == null,"游戏价格不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(game.getGameProfile()),"游戏内容不能为空");
        AssertUtil.isTrue(game.getBond() == null,"保证金不能为空");

        game.setUpdateDate(new Date());
        game.setStater(game.getStater());
        AssertUtil.isTrue(gameMapper.updateByPrimaryKeySelective(game) != 1,"修改失败");

    }

    /**
     * 删除
     * @param ids
     */
    public void deleteGame(Integer[] ids){
        AssertUtil.isTrue(ids == null,"待删除记录不存在");
        AssertUtil.isTrue(gameMapper.deleteBatch(ids) != ids.length,"删除失败");
    }


    /**
     * 查询我的
     * @param myGameQuery
     * @return
     */
    public Map<String, Object> queryMyGame(MyGameQuery myGameQuery) {
        Map<String ,Object> map = new HashMap<>();
        PageHelper.startPage(myGameQuery.getPage(),myGameQuery.getLimit());
        PageInfo<Game> pageInfo = new PageInfo<>(gameMapper.queryMyGame(myGameQuery));
        map.put("code",0);
        map.put("msg","success");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }


    /**
     * 查询状态
     * @param userId
     * @return
     */
    public List<Map<String, Object>> queryAllStater(Integer userId) {
        return gameMapper.queryAllStater(userId);
    }
}
