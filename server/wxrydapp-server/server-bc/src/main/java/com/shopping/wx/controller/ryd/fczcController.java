package com.shopping.wx.controller.ryd;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shopping.wx.conf.DB;
import com.shopping.wx.model.Bankinfo;
import com.shopping.wx.model.Bankqx;
import com.shopping.wx.model.Organ;
import com.shopping.wx.model.Policy;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.entity.Example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Log4j2
@RestController
@RequestMapping("/fczc")
public class fczcController {

    @Autowired
    private DB db;

    @RequestMapping(value = "/searchShangJia", method = RequestMethod.GET)
    public JSONObject searchShangJia() throws IOException {
        JSONObject json = new JSONObject();
        Example example = new Example(Policy.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("yl1", "1");
        example.setOrderByClause("fbtime DESC");
        List<Policy> policyList = db.selectAllByExample(Policy.class, example);
        json.put("policyList", policyList);
        return json;
    }

    @RequestMapping(value = "/addPageviews", method = RequestMethod.GET)
    public JSONObject addPageviews(String id){
        JSONObject json = new JSONObject();
        Policy policy = db.selectById(id,Policy.class);
        int pageviews = policy.getYl2();
        pageviews = pageviews + 1;
        policy = new Policy();
        policy.setId(id);
        policy.setYl2(pageviews);
        db.update(policy);
        json.put("msg","点击+1");
        return json;
    }

    @RequestMapping(value = "/selectPolicyById", method = RequestMethod.GET)
    public JSONObject selectPolicyById(String id){
        JSONObject json = new JSONObject();
        Policy policy = db.selectById(id,Policy.class);
        json.put("policy",policy);
        return json;
    }

    @RequestMapping(value = "/searchfczc", method = RequestMethod.GET)
    public JSONObject httpURLGETCase() {
        String methodUrl = "http://frqserver.bpsip.com:8081/frq-app/newLoan/getMoreHelpPolicy";
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String line = null;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(methodUrl );
            connection = (HttpURLConnection) url.openConnection();// 根据URL生成HttpURLConnection
            connection.setRequestMethod("GET");// 默认GET请求
            connection.connect();// 建立TCP连接
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));// 发送http请求

                // 循环读取流
                while ((line = reader.readLine()) != null) {
                    result.append(line).append(System.getProperty("line.separator"));// "\n"
                }
                System.out.println(result.toString());

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.disconnect();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sj", JSONObject.parseObject(result.toString()));
        return jsonObject;
    }

    @RequestMapping(value = "/searchfczc11", method = RequestMethod.GET)
    public JSONObject httpURLGETCase1(String fid) {
        String methodUrl = "http://frqserver.bpsip.com:8081/frq-app/companyMainPage/loadOnePolicy";
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String line = null;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(methodUrl + "?fid="+fid+"");
            connection = (HttpURLConnection) url.openConnection();// 根据URL生成HttpURLConnection
            connection.setRequestMethod("GET");// 默认GET请求
            connection.connect();// 建立TCP连接
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));// 发送http请求

                // 循环读取流
                while ((line = reader.readLine()) != null) {
                    result.append(line).append(System.getProperty("line.separator"));// "\n"
                }
                System.out.println(result.toString());

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.disconnect();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sj", JSONObject.parseObject(result.toString()));
        return jsonObject;
    }




    @RequestMapping(value = "/searchtoken", method = RequestMethod.GET)
    public JSONObject httpURLGETCase1(String grant_type ,String appid,String secret) {
        String methodUrl = "https://api.weixin.qq.com/cgi-bin/token";
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String line = null;
        appid="wx96d6e30c9f677d52";
        secret="1488cdb592b5103c5f25448c2022db9e";
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(methodUrl + "?grant_type="+grant_type+"&appid="+appid+"&secret="+secret);
            connection = (HttpURLConnection) url.openConnection();// 根据URL生成HttpURLConnection
            connection.setRequestMethod("GET");// 默认GET请求
            connection.connect();// 建立TCP连接
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));// 发送http请求

                // 循环读取流
                while ((line = reader.readLine()) != null) {
                    result.append(line).append(System.getProperty("line.separator"));// "\n"
                }
                System.out.println(result.toString());

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.disconnect();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sj", JSONObject.parseObject(result.toString()));
        return jsonObject;
    }


    public static Map<String, String> getDataMap(String msg){
        Map<String, String> map = new HashMap<>();
        map.put("value", msg);
        return map;
    }

    @RequestMapping(value = "/getnumbers", method = RequestMethod.GET)
    public JSONObject getnumbers(String access_token ,String touser,String template_id,String qyname,String lxr ,String phone ,String dkje ,String yhid,String organid) {
        Example example = new Example(Bankqx.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", 2);
        criteria.andEqualTo("state", 1);
        criteria.andEqualTo("bankid", yhid);
        if (!organid.equals("undefined")) {
            System.out.println(1111);
            criteria.andEqualTo("orgonid", organid);
        }
        List<Bankqx> bankqxList = db.selectAllByExample(Bankqx.class, example);
        JSONObject result = new JSONObject();
        result.put("bankqxList",bankqxList);
        return result;
    }
    @RequestMapping(value = "/sendinfos", method = RequestMethod.GET)
    public JSONObject sendinfos(String access_token ,String touser,String template_id,String qyname,String lxr ,String phone ,String dkje ,String yhid,String organid) {
        String methodUrl = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token="+access_token;

            Map<String,Object> param = new HashMap<>();
            param.put("touser", touser);
            param.put("template_id", template_id);
            param.put("page", "/pages/bankdataStatistics/bankdataStatistics?yhid="+ yhid);
            Map<String,Object> data = new HashMap<>();
            if(!organid.equals("undefined")){
                Organ organ =db.selectById(organid, Organ.class);
                System.out.println(2222);
                data.put("thing1", getDataMap(qyname));
                data.put("thing2", getDataMap(lxr));
                data.put("phone_number3", getDataMap(phone));
                data.put("amount4", getDataMap(dkje+"0000元"));
                data.put("thing5", getDataMap(organ.getOrganname()));
                param.put("data", data);
            }else {
                System.out.println(3333);
                Bankinfo bankinfo = db.selectById(yhid,Bankinfo.class);
                data.put("thing1", getDataMap(qyname));
                data.put("thing2", getDataMap(lxr));
                data.put("phone_number3", getDataMap(phone));
                data.put("amount4", getDataMap(dkje+"0000元"));
                data.put("thing5", getDataMap(bankinfo.getBankname()));
                param.put("data", data);
            }
            //data
            String jsonString = JSON.toJSONString(param);
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(methodUrl);
            StringEntity entity = new StringEntity(jsonString, StandardCharsets.UTF_8);

            httpPost.setEntity(entity);
            // 使用 JSON 格式传递参数
            httpPost.setHeader("Content-Type", "application/json;charset=utf8");

            // 响应模型
            CloseableHttpResponse response = null;
            try {
                response = client.execute(httpPost);
                HttpEntity responseEntity = response.getEntity();

                System.out.println("响应状态为:" + response.getStatusLine());
                if (responseEntity != null) {
                    System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                    System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
                }

                JSONObject result = new JSONObject();
                return result;

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    // 释放资源
                    if (client != null) {
                        client.close();
                    }
                    if (response != null) {
                        response.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        JSONObject jsonObject = new JSONObject();

        return jsonObject;
    }
}
