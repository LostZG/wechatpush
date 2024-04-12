package cn.cvzhanshi.wechatpush.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @desc：
 * @author：wanghui
 * @date：2024-04-12
 */
@AllArgsConstructor
@Getter
public enum WeatherEnum {

    //1=风、2=云、3=雨、4=雪、5=霜、6=露 、7=雾、8=雷、9=晴、10=阴

    晴("晴", 9),
    多云("多云", 2),
    阴("阴", 10),
    阵雨("阵雨", 3),
    雷阵雨("雷阵雨", 8),
    雷阵雨伴有冰雹("雷阵雨伴有冰雹", 3),
    雨夹雪("雨夹雪", 3),
    小雨("小雨", 3),
    中雨("中雨", 3),
    大雨("大雨", 3),
    暴雨("暴雨", 3),
    大暴雨("大暴雨", 3),
    特大暴雨("特大暴雨", 3),
    阵雪("阵雪", 4),
    小雪("小雪", 4),
    中雪("中雪", 4),
    大雪("大雪", 4),
    暴雪("暴雪", 4),
    雾("雾", 7),
    冻雨("冻雨", 3),
    沙尘暴("沙尘暴", 0),
    小到中雨("小到中雨", 3),
    中到大雨("中到大雨", 3),
    大到暴雨("大到暴雨", 3),
    暴雨到大暴雨("暴雨到大暴雨", 3),
    大暴雨到特大暴雨("大暴雨到特大暴雨", 3),
    小到中雪("小到中雪", 4),
    中到大雪("中到大雪", 4),
    大到暴雪("大到暴雪", 4),
    浮尘("浮尘", 0),
    扬沙("扬沙", 0),
    强沙尘暴("强沙尘暴", 0),
    浓雾("浓雾", 7),
    龙卷风("龙卷风", 1),
    弱高吹雪("弱高吹雪", 4),
    轻雾("轻雾", 7),
    强浓雾("强浓雾", 7),
    霾("霾", 7),
    中度霾("中度霾", 7),
    重度霾("重度霾", 7),
    严重霾("严重霾", 7),
    大雾("大雾", 7),
    特强浓雾("特强浓雾", 7),
    雨("雨", 3),
    雪("雪", 4);
    private String weather;

    private int tqtype;


    public static int getTqtype(String weather) {
        for (WeatherEnum weatherEnum : WeatherEnum.values()) {
            if (weatherEnum.getWeather().equals(weather)) {
                return weatherEnum.getTqtype();
            }
        }
        return 0;
    }
}
