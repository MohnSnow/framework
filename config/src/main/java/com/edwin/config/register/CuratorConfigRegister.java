package com.edwin.config.register;

import com.edwin.config.constant.ZKConstants;
import com.edwin.config.helper.ByteHelper;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.io.IOException;

/**
 * 利用curator实现的zk注册器（安全有保障，API很赞，少掉了如重连、父节点确认等额外代码）
 * 
 * @author: edwin
 * @date: 15-5-9 20:46
 */
public class CuratorConfigRegister extends AbstractConfigRegister {

    private CuratorFramework curatorFramework;

    public CuratorConfigRegister(String connectionString) {
        this.connectionString = connectionString;
    }

    @Override
    public void init() throws IOException {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(ZKConstants.BASE_SLEEP_MS, ZKConstants.MAX_TRY_TIMES);

        // fluent style
        curatorFramework = CuratorFrameworkFactory.builder().connectString(connectionString).sessionTimeoutMs(sessionTimeout).connectionTimeoutMs(connectionTimeout).retryPolicy(retryPolicy).namespace(namespace).build();

        curatorFramework.start();
    }

    @Override
    public void registerValue(String key, String value) throws Exception {

        if (curatorFramework.checkExists().forPath(key) == null) {
            curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/" + key,
                                                                                                        ByteHelper.stringToBytes(value));
        } else {
            curatorFramework.setData().withVersion(ZKConstants.LATEST_VERSION).forPath("/" + key,
                                                                                       ByteHelper.stringToBytes(value));
        }
    }

    @Override
    public void unregister(String key) throws Exception {
        curatorFramework.delete().deletingChildrenIfNeeded().forPath(key);
    }

    @Override
    public void destroy() throws Exception {
        curatorFramework.close();
    }
}
