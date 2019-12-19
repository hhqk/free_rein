package com.hhqk.common.rpc.rpcServerImpl;

import com.hhqk.common.rpc.rpcInterfaces.IRpcCommon;

public class RpcCommonImpl implements IRpcCommon {

    @Override
    public String testRpc() {
        return "RpcCommonImpl testRpc";
    }
}
