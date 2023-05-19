package teamguu.backend.config.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import teamguu.backend.exception.situation.TokenExpiredException;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class RedisService {
    private final RedisTemplate<String, String> redisTemplate;

//    public void setValues(String key, String data) {
//        ValueOperations<String, String> values = redisTemplate.opsForValue();
//        values.set(key, data);
//    }

    public void setValues(String key, String data, Duration duration) {
        redisTemplate.opsForValue().set(key, data, duration);
    }

    public String getValues(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteValues(String key) {
        redisTemplate.delete(key);
    }

//    public void checkRefreshToken(String username, String refreshToken) {
//        if(!refreshToken.equals(this.getValues("RT: " + username))) {
//            throw new TokenExpiredException();
//        }
//    }
}