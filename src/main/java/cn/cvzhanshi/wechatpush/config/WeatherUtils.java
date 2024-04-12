package cn.cvzhanshi.wechatpush.config;

import cn.cvzhanshi.wechatpush.entity.Weather;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author cVzhanshi
 * @create 2022-08-04 22:02
 */
@Component
public class WeatherUtils {

    public static void main(String[] args) {
        System.out.println(new WeatherUtils().getTqsj(4));
    }

    public Weather getWeather() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<String, String>();
        // map.put("district_id","320583"); // 昆山行政代码
        map.put("district_id", "340100"); // 赣州行政代码
        map.put("data_type", "all");//这个是数据类型
        map.put("ak", "taRBlMPypqywS1Xu9UbV1LsElgPIEK4n");
        String res = restTemplate.getForObject(
                "https://api.map.baidu.com/weather/v1/?district_id={district_id}&data_type={data_type}&ak={ak}",
                String.class,
                map);
        JSONObject json = JSONObject.parseObject(res);
        JSONArray forecasts = json.getJSONObject("result").getJSONArray("forecasts");
        List<Weather> weathers = forecasts.toJavaList(Weather.class);
        JSONObject now = json.getJSONObject("result").getJSONObject("now");
        Weather weather = weathers.get(0);
        weather.setText_now(now.getString("text"));
        weather.setTemp(now.getString("temp"));
        weather.setWind_class(now.getString("wind_class"));
        weather.setWind_dir(now.getString("wind_dir"));
        return weather;
    }

    public String getTqsj(int tqType) {
        String httpUrl = "https://apis.tianapi.com/tianqishiju/index?key=eaf20cc41cc9594640d63d23dacb390c";
        if (tqType != 0) {
            httpUrl = httpUrl + "&tqtype=" + tqType;
        }

        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONObject newslist = jsonObject.getJSONObject("result");
        return newslist.getString("content");
    }
}
