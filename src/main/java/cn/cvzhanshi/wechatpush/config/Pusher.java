package cn.cvzhanshi.wechatpush.config;


import cn.cvzhanshi.wechatpush.entity.Weather;
import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.stereotype.Service;

/**
 * @author cVzhanshi
 * @create 2022-08-04 21:09
 */
@Slf4j
@Service
public class Pusher {

    @Resource
    private WeatherUtils weatherUtils;

    @Resource
    private CaiHongPiUtils caiHongPiUtils;

    private static String appId = "x";
    private static String secret = "x";

    private static List<String> users = Lists.newArrayList("oMrF_6GVXaA93R0Te-lzfV2urwx4", "oMrF_6OM3JlC_RH0wB9e4TuxLBFo"
            , "oMrF_6ClDFovSMHs__MJ0xSIeuDc", "oMrF_6Btu2eGv3ZAEzO6Th5GnJ0g");

//    private static List<String> users = Lists.newArrayList("oMrF_6GVXaA93R0Te-lzfV2urwx4");


    public void push() {
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appId);
        wxStorage.setSecret(secret);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        //2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .templateId("9Cm-meyt87fQzB4WxoxG8pNf46dOcnq_lJ154pEJpss")
                .build();
        //3,如果是正式版发送模版消息，这里需要配置你的信息
        Weather weather = weatherUtils.getWeather();
        Map<String, String> map = caiHongPiUtils.getEnsentence();
        //日期
        templateMessage.addData(new WxMpTemplateData("riqi", weather.getDate() + "  " + weather.getWeek(), "#00BFFF"));
        //天气
        templateMessage.addData(new WxMpTemplateData("tianqi", weather.getText_now(), "#00FFFF"));
        //最低温度
        templateMessage.addData(new WxMpTemplateData("low", weather.getLow() + "", "#173177"));
        //当前温度
        templateMessage.addData(new WxMpTemplateData("temp", weather.getTemp() + "", "#EE212D"));
        //最高温度
        templateMessage.addData(new WxMpTemplateData("high", weather.getHigh() + "", "#FF6347"));
        //风力
        templateMessage.addData(new WxMpTemplateData("windclass", weather.getWind_class() + "", "#42B857"));
        //风向
        templateMessage.addData(new WxMpTemplateData("winddir", weather.getWind_dir() + "", "#B95EA3"));
        //每日彩虹屁
        templateMessage.addData(new WxMpTemplateData("caihongpi", caiHongPiUtils.getCaiHongPi(), "#FF69B4"));
        //恋爱天数
        templateMessage.addData(new WxMpTemplateData("lianai", JiNianRiUtils.getLianAi() + "", "#FF1493"));

        templateMessage.addData(new WxMpTemplateData("shengri1", JiNianRiUtils.getBirthday_Jo() + "", "#FFA500"));
        templateMessage.addData(new WxMpTemplateData("shengri2", JiNianRiUtils.getBirthday_Hui() + "", "#FFA500"));
        //英文鸡汤
        templateMessage.addData(new WxMpTemplateData("en", map.get("en") + "", "#C71585"));
        //中文鸡汤
        templateMessage.addData(new WxMpTemplateData("zh", map.get("zh") + "", "#C71585"));
        String beizhu = "欣竹❤灰灰";
        if (JiNianRiUtils.getLianAi() % 365 == 0) {
            beizhu = "今天是恋爱" + (JiNianRiUtils.getLianAi() / 365) + "周年纪念日！";
        }
        if (JiNianRiUtils.getBirthday_Jo() == 0) {
            beizhu = "今天是刘欣竹的生日，生日快乐呀！";
        }
        if (JiNianRiUtils.getBirthday_Hui() == 0) {
            beizhu = "今天是王辉的生日，生日快乐呀！";
        }
        templateMessage.addData(new WxMpTemplateData("beizhu", beizhu, "#FF0000"));

        int tqtype = WeatherEnum.getTqtype(weather.getText_now());
        String tqsj = weatherUtils.getTqsj(tqtype);
        templateMessage.addData(new WxMpTemplateData("tqsj", tqsj, "#FF0000"));

        String zaoAn = caiHongPiUtils.getZaoAn();
        templateMessage.addData(new WxMpTemplateData("zaoan", zaoAn, "#FF0000"));

        try {
            for (String user : users) {
                templateMessage.setToUser(user);
                wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            }
        } catch (Exception e) {
            log.error("推送失败,", e);
        }
    }
}
