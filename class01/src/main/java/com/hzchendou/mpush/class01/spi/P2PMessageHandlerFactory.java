package com.hzchendou.mpush.class01.spi;

import java.util.Map;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hzchendou.mpush.class01.common.utils.MpushContainerUtils;
import com.mpush.api.connection.Connection;
import com.mpush.api.message.MessageHandler;
import com.mpush.api.protocol.Command;
import com.mpush.api.protocol.JsonPacket;
import com.mpush.api.protocol.Packet;
import com.mpush.api.spi.handler.PushHandlerFactory;
import com.mpush.common.handler.BaseMessageHandler;
import com.mpush.common.message.AckMessage;
import com.mpush.common.message.OkMessage;
import com.mpush.common.message.PushMessage;
import com.mpush.core.router.LocalRouter;

/**
 * p2p推送消息处理器.
 *
 * @author hzchendou
 * @date 19-1-1
 * @since 1.0
 */
public class P2PMessageHandlerFactory extends BaseMessageHandler<PushMessage> implements PushHandlerFactory {
    @Override
    public PushMessage decode(Packet packet, Connection connection) {
        return new PushMessage(packet, connection);
    }

    /**
     * 消息推送
     *
     * @param message
     */
    @Override
    public void handle(PushMessage message) {
        if (message.autoAck()) {
            AckMessage.from(message).sendRaw();
        }
        JSONObject contentJson = JSON.parseObject((String) message.encodeJsonBody().get("content"));

        Set<LocalRouter> localRouters =
                MpushContainerUtils.getMpushServer().getRouterCenter().getLocalRouterManager()
                        .lookupAll(contentJson.getString("userId"));
        if (!CollectionUtils.isEmpty(localRouters)) {
            for (LocalRouter router : localRouters) {
                JsonPacket packet = new JsonPacket(Command.PUSH);
                PushMessage pushMessage = new PushMessage(packet, router.getRouteValue());
                OkMessage.from(pushMessage).setData(contentJson.getString("msg")).sendRaw();
            }
        }
    }

    @Override
    public MessageHandler get() {
        return this;
    }
}
