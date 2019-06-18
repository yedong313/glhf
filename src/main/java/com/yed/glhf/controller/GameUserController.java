package com.yed.glhf.controller;


import com.yed.glhf.common.rpc.RpcResult;
import com.yed.glhf.entity.GameUser;
import com.yed.glhf.service.IGameUserService;
import com.yed.glhf.service.impl.GameUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
public class GameUserController {
    @Autowired
    private IGameUserService  iGameUserService;

    @PostMapping(value = "addGameUser")
    public RpcResult<String> addGameUser(@RequestBody GameUser gameUser){
        RpcResult<String> rpcResult = new RpcResult<String>();
        iGameUserService.save(gameUser);
        rpcResult.setData(gameUser.getId());
        return rpcResult;
    }
 }
