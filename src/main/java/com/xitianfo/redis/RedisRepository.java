package com.xitianfo.redis;



/**
 * @author 宋煜超
 */
public interface RedisRepository {

    /**
     * 保存手机号和验证码的键值对<phone, messageCode>
     * @param phone
     * @param messageCode 验证码
     */
    void saveMessageCode(String phone, String messageCode);

    /**
     * 根据key：手机号 查询验证码
     * @param phone
     * @return
     */
    String selectMessageCodeByPhone(String phone);

    /**
     * 删除手机号和验证码的键值对
     * @param phone
     */
    void deleteMessageCode(String phone);



}
