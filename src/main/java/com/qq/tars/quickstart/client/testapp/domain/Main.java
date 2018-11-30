/**
 * Tencent is pleased to support the open source community by making Tars available.
 *
 * Copyright (C) 2016 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.qq.tars.quickstart.client.testapp.domain;

import com.qq.tars.client.Communicator;
import com.qq.tars.client.CommunicatorConfig;
import com.qq.tars.client.CommunicatorFactory;
import com.qq.tars.quickstart.client.testapp.HelloJavaPrx;
import com.qq.tars.quickstart.client.testapp.HelloJavaPrxCallback;

public class Main {

    public static void main(String[] args) {
        CommunicatorConfig cfg = new CommunicatorConfig();
        Communicator communicator = CommunicatorFactory.getInstance().getCommunicator(cfg);
        HelloJavaPrx proxy = communicator.stringToProxy(HelloJavaPrx.class, "TestApp.HelloJavaService.HelloObj@tcp -h 192.168.57.3 -p 10013 -t 60000");
        //同步调用
        String ret = proxy.hello(1000, "Hello World");
        System.out.println(ret);

        //单向调用
        proxy.async_hello(null, 1000, "Hello World");

        //异步调用
        proxy.async_hello(new HelloJavaPrxCallback() {

            @Override
            public void callback_expired() {
            }

            @Override
            public void callback_exception(Throwable ex) {
            }

            @Override
            public void callback_hello(String ret) {
                System.out.println(ret);
            }
        }, 1000, "Hello World");
    }
}
