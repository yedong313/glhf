package com.yed.glhf.controller;


import com.google.common.collect.Lists;
import com.yed.glhf.common.rpc.RpcResult;
import com.yed.glhf.common.util.easypoi.ExcelUtil;
import com.yed.glhf.common.util.mapper.BeanUtils;
import com.yed.glhf.entity.GameUser;
import com.yed.glhf.form.GameUserForm;
import com.yed.glhf.service.IGameUserService;
import com.yed.glhf.view.GameUserView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 游戏用户表 前端控制器
 * </p>
 *
 * @author yed
 * @since 2019-06-17
 */
@RestController
@RequestMapping("/gameUser")
@Api(description = "游戏用户前端控制器")
public class GameUserController {
    @Autowired
    private IGameUserService iGameUserService;

    @ApiOperation(value = "添加游戏用户", notes = "根据gameUser添加游戏用户")
    @ApiImplicitParam(name = "gameUserForm", value = "游戏用户实体gameUserForm", required = true, dataType = "GameUserForm")
    @PostMapping(value = "addGameUser")
    public RpcResult<String> addGameUser(@RequestBody GameUserForm gameUserForm) {
        RpcResult<String> rpcResult = new RpcResult<>();
        GameUser gameUser = BeanUtils.deepCopy(gameUserForm, GameUser.class);
        iGameUserService.save(gameUser);
        rpcResult.setData(gameUser.getId());
        return rpcResult;
    }


    @ApiOperation(value = "查询一个游戏用户", notes = "根据id查询游戏用户")
    @ApiImplicitParam(name = "id", value = "游戏用户id", required = true, dataType = "String")
    @GetMapping(value = "getGameUser/{id}")
    public RpcResult<GameUserView> getGameUser(@PathVariable("id") String id) {
        RpcResult<GameUserView> rpcResult = new RpcResult<>();
        GameUser byId = iGameUserService.getById(id);
        GameUserView gameUserView = BeanUtils.deepCopy(byId, GameUserView.class);
        rpcResult.setData(gameUserView);
        return rpcResult;
    }

    @ApiOperation(value = "导出", notes = "导出数据")
    @ApiImplicitParam()
    @GetMapping("export")
    public void export(HttpServletResponse response) {
        GameUser byId = iGameUserService.getById(3);
        GameUserView gameUserView = BeanUtils.deepCopy(byId, GameUserView.class);
        List<GameUserView> list = Lists.newArrayList(gameUserView);
        ExcelUtil.exportExcel(list, "测试名", "什么名字", GameUserView.class, "测试.xls", response);
    }


    @ApiOperation(value = "测试redis session", notes = "测试redis session")
    @ApiImplicitParam()
    @GetMapping("sessionTest")
    public void sessionTest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("test1", "nimahai1");
        session.setAttribute("test2", "nimahai2");
        return;
    }


}
