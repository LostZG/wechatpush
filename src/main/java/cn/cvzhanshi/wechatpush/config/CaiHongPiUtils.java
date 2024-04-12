package cn.cvzhanshi.wechatpush.config;

import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * @author cVzhanshi
 * @create 2022-08-04 22:58
 */
@Component
public class CaiHongPiUtils {

    public String getCaiHongPi() {
        String httpUrl = "https://apis.tianapi.com/caihongpi/index?key=eaf20cc41cc9594640d63d23dacb390c";
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

    public Map<String, String> getEnsentence() {
        String httpUrl = "https://apis.tianapi.com/ensentence/index?key=eaf20cc41cc9594640d63d23dacb390c";
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
        JSONObject content = jsonObject.getJSONObject("result");
        String en = content.getString("en");
        String zh = content.getString("zh");
        Map<String, String> map = new HashMap<>();
        map.put("zh", zh);
        map.put("en", en);
        return map;
    }

    public String getZaoAn() {
        String httpUrl = "https://apis.tianapi.com/zaoan/index?key=eaf20cc41cc9594640d63d23dacb390c";
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
