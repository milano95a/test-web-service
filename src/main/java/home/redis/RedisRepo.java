package home.redis;

import home.entity.Question;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.*;

@Repository
public class RedisRepo {

    @Inject
    private RedisTemplate<String,Object> redisTemplate;

    public void save(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Transactional(readOnly = true)
    public Object findByKey(String key) {

        if(redisTemplate.hasKey(key)){
            try{
                return redisTemplate.opsForValue().get(key);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return null;
    }

    public HashMap<String,Object> findAll() {

        HashMap<String,Object> hashMap = new HashMap<>();

        Set<String> keys = redisTemplate.keys("*");
        Iterator<String> it = keys.iterator();

        while(it.hasNext()){
            String key = it.next();
            Object value = findByKey(key);
            hashMap.put(key,value);
        }

        return hashMap;
    }

    public boolean isKeyExist(String key){
        return redisTemplate.hasKey(key);
    }
    public void deleteAll(){
        Set<String> keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);
    }

    public Object getNumber(){
//        if(redisTemplate.hasKey("num")){
//            Object obj = redisTemplate.opsForValue().get("num");
//            Worker worker = new Worker("num",this);
//            worker.start();
//            return obj;
//        }else{
//            save("num",repository.getNumber() + "");
//            return redisTemplate.opsForValue().get("num");
//        }
        return null;
    }
}
