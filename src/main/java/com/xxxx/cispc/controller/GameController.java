package com.xxxx.cispc.controller;

import com.xxxx.cispc.base.BaseController;
import com.xxxx.cispc.base.ResultInfo;
import com.xxxx.cispc.query.GameQuery;
import com.xxxx.cispc.query.MyGameQuery;
import com.xxxx.cispc.service.GameService;
import com.xxxx.cispc.utils.UserIDBase64;
import com.xxxx.cispc.vo.Game;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("game")
public class GameController extends BaseController {
    @Resource
    private GameService gameService;

    /**
     * 通过条件查询
     * @param gameQuery
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public Map<String,Object> queryGameByParams(GameQuery gameQuery){
        return gameService.queryGameByParams(gameQuery);
    }



    /**
     * 添加
     * @param game
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo addGame(Game game,HttpServletRequest request){
        Cookie[] cookie = request.getCookies();
        String decode = null;
        for (Cookie name: cookie) {
            if (name.getName().equals("userId")){
                decode = URLDecoder.decode(name.getValue());
            };
        }
        Integer userId = UserIDBase64.decoderUserID(decode);
        gameService.addGame(game,userId);
        return success("添加成功");
    }

    /**
     * 修改
     * @param game
     * @return
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo updateGame(Game game){
        gameService.updateGame(game);
        return success("修改成功");
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo deleteGame(Integer[] ids) {
        gameService.deleteGame(ids);
        return success("删除成功");
    }

    @RequestMapping("index")
    public String index(){
        return "game/game";
    }

    @RequestMapping("toAddOrUpdateGame")
    public String toAddOrUpdateGame(Integer id, HttpServletRequest request){
        if (id != null){
            Game game = gameService.selectByPrimaryKey(id);
            request.setAttribute("game",game);
        }
        return "game/add_update";
    }

    @RequestMapping("myGame")
    public String myGame(){
        return "game/my_game";
    }

    /**
     * 查询我的
     * @param myGameQuery
     * @return
     */
    @RequestMapping("queryMyGame")
    @ResponseBody
    public Map<String,Object> queryMyGame(MyGameQuery myGameQuery,HttpServletRequest request){
        Cookie[] cookie = request.getCookies();
        String decode = null;
        for (Cookie name: cookie) {
            if (name.getName().equals("userId")){
                decode = URLDecoder.decode(name.getValue());
            };
        }
        Integer userId = UserIDBase64.decoderUserID(decode);
        myGameQuery.setUserId(userId);
        return gameService.queryMyGame(myGameQuery);
    }

    /**
     * 查询状态
     * @param userId
     * @return
     */
    @RequestMapping("queryAllStater")
    @ResponseBody
    public List<Map<String,Object>> queryAllStater(Integer userId){
        return gameService.queryAllStater(userId);
    }


}
