package com.example.wxrobot;

import com.example.wxrobot.util.TaoBaoUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WxrobotApplicationTests {
    @Autowired
    private TaoBaoUtils taoBaoUtils;

    @Test
    void contextLoads() {

       /* String url="https://m.tb.cn/h.VSG0lyo?sm=18119f";

        StringBuffer stringBuffer = new StringBuffer();

        try {

            //打开连接
            URL realUrl = new URL(null, url, new sun.net.www.protocol.https.Handler());
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) realUrl.openConnection();
            // 设置请求方式（GET/POST）
            httpsURLConnection.setRequestMethod("GET");
           // httpsURLConnection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream(),"utf-8"));
            String line=null;
            while ((line=bufferedReader.readLine())!=null){
                if (line.contains("url")){
                  System.out.println(line.substring(line.indexOf("i")+1,line.indexOf(".htm")));
                    break;
                }
            }
            bufferedReader.close();
        } catch (ConnectException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        taoBaoUtils.tranWord(taoBaoUtils.taoBaoCoupons("fu植这行话€DZyY1la3mbS€转移至τаo宝аρρ【美肌颜冰肌透亮寡肽冻干粉套盒烟酰胺原液淡化痘印提亮44支盒装】；或https://m.tb.cn/h.V76eJ1H?sm=1ac149 點击鏈→接，再选择瀏..覽..噐dakai",null));
    }
}
