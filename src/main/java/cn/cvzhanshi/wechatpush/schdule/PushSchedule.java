package cn.cvzhanshi.wechatpush.schdule;

import cn.cvzhanshi.wechatpush.config.Pusher;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @desc：
 * @author：wanghui
 * @date：2024-04-11
 */
@Component
public class PushSchedule {

    @Resource
    private Pusher pusher;


    @Scheduled(cron = "0 0 8 * * ?")
    public void goodMorning(){
        pusher.push();

        System.gc();
    }


}
