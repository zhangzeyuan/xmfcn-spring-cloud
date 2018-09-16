package com.cn.xmf.service.sys;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${base-service.sys-service}")// 配置远程服务名以及自定义权限验证配置
@RequestMapping("/server/redis/")// 配置远程服务路径
public interface RedisService {
    /**
     * saveCache:(设置缓存-带有效期)
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    @RequestMapping(value = "saveCache", consumes = MediaType.APPLICATION_JSON_VALUE )
    public String saveCache(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value, @RequestParam(value = "seconds") int seconds);

    /**
     * 获取单个值
     * @param key
     * @return
     */
    @RequestMapping(value = "getCache", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getCache(@RequestParam(value = "key") String key);


    /**
     * 入队key 是消息频道 ，value 消息内容
     *
     * @param key
     * @param value
     * @return
     */
    @RequestMapping(value = "putToQueue", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long putToQueue(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value);

    /**
     * 读取消息 （读过队列中消息就没有了）key 是消息频道
     *
     * @param key
     * @return
     */
    @RequestMapping(value = "getFromQueue",consumes = MediaType.APPLICATION_JSON_VALUE )
    public String getFromQueue(@RequestParam(value = "key") String key);


    /**
     * 将 key 缓存数据删除
     * @param key
     * @return
     */
    @RequestMapping(value = "delCache",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long delCache(@RequestParam(value = "key") String key);

    /**
     * 根据通配符删除所有key
     *
     * @param pattern
     * @return
     */
    @RequestMapping(value = "delCaches",consumes = MediaType.APPLICATION_JSON_VALUE )
    public long delCaches(@RequestParam(value = "pattern") String pattern);


    /**
     * 获取分布式锁
     * @param key
     * @return
     */
    @RequestMapping(value ="getLock",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long getLock(@RequestParam(value = "key") String key);

    /**
     * getQueueLength（获取队列长度)key 是消息频道
     * @param key
     * @return
     */
    @RequestMapping(value ="getQueueLength",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long getQueueLength(@RequestParam(value = "key") String key);

    /**
     * getOnlyNo(获取唯一编号)
     * @param prefix
     * @return
     */
    @RequestMapping(value = "getOnlyNo",consumes = MediaType.APPLICATION_JSON_VALUE)
    public  String getOnlyNo(@RequestParam(value = "prefix") String prefix);
}