package com.xitianfo.redis;


import com.xitianfo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


import static com.xitianfo.enums.RedisKeyTemplate.T_VERIFICATION_CODE;
import static com.xitianfo.util.RedisKeyUtil.buildKey;


/**
 * @author 宋煜超
 */
@Component
public class RedisRepositoryImpl implements RedisRepository {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void saveMessageCode(String phone, String messageCode) {
        RedisUtil.set(redisTemplate, buildKey(T_VERIFICATION_CODE, phone), messageCode);
    }

    @Override
    public String selectMessageCodeByPhone(String phone) {
        return RedisUtil.<String>get(redisTemplate, buildKey(T_VERIFICATION_CODE, phone), String.class);
    }

    @Override
    public void deleteMessageCode(String phone) {
        RedisUtil.del(redisTemplate, buildKey(T_VERIFICATION_CODE, phone));
    }




}
