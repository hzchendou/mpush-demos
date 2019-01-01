package com.hzchendou.mpush.class01.common.utils;

import com.mpush.bootstrap.ServerLauncher;
import com.mpush.core.MPushServer;

/**
 * .
 *
 * @author hzchendou
 * @date 19-1-1
 * @since
 */
public class MpushContainerUtils {

    private static ServerLauncher serverLauncher;

    public static MPushServer getMpushServer() {
        if (serverLauncher == null) {
            return null;
        }
        return serverLauncher.getMPushServer();
    }

    public static void setServerLauncher(ServerLauncher serverLauncher) {
        MpushContainerUtils.serverLauncher = serverLauncher;
    }
}
