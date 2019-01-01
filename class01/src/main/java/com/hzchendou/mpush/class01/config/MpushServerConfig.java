package com.hzchendou.mpush.class01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hzchendou.mpush.class01.common.utils.MpushContainerUtils;
import com.mpush.bootstrap.ServerLauncher;

/**
 * mpush服务配置.
 *
 * @author hzchendou
 * @date 19-01-01
 * @since 1.0
 */
@Configuration
public class MpushServerConfig {


    @Bean(initMethod = "start", destroyMethod = "stop")
    public ServerLauncher serverLauncher() {
        ServerLauncher serverLauncher = new ServerLauncher();
        MpushContainerUtils.setServerLauncher(serverLauncher);
        serverLauncher.init();
        return serverLauncher;
    }
}
