package com.yed.glhf.controller;


import com.yed.glhf.common.enums.YesOrNoEnum;
import com.yed.glhf.common.rpc.RpcResult;
import com.yed.glhf.entity.GameUser;
import com.yed.glhf.service.IGameUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ApiImplicitParam(name = "gameUser", value = "游戏用户实体gameUser", required = true, dataType = "GameUser")
    @PostMapping(value = "addGameUser")
    public RpcResult<String> addGameUser(@RequestBody GameUser gameUser) {
        RpcResult<String> rpcResult = new RpcResult<>();
        gameUser.setGainedRedPack(YesOrNoEnum.no);
        gameUser.setVerified(YesOrNoEnum.yes);
        iGameUserService.save(gameUser);
        rpcResult.setData(gameUser.getId());
        return rpcResult;
    }


    @ApiOperation(value = "查询一个游戏用户", notes = "根据id查询游戏用户")
    @ApiImplicitParam(name = "id", value = "游戏用户id", required = true, dataType = "String")
    @GetMapping(value = "getGameUser/{id}")
    public RpcResult<GameUser> getGameUser(@PathVariable("id") String id) {
        RpcResult<GameUser> rpcResult = new RpcResult<>();
        GameUser byId = iGameUserService.getById(id);
        rpcResult.setData(byId);
        return rpcResult;
    }

}
