package cn.cvzhanshi.wechatpush.controller;

import cn.cvzhanshi.wechatpush.config.Pusher;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc：
 * @author：wanghui
 * @date：2024-04-11
 */
@RestController
@RequestMapping("/test/push")
public class TestController {

    @Resource
    private Pusher pusher;

    @GetMapping("/testPush")
    public void testPush() {
        pusher.push();
    }

}
