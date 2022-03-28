package com.shopping.wx.controller.wx;

import cn.binarywang.wx.miniapp.api.WxMaMsgService;
import cn.binarywang.wx.miniapp.api.WxMaQrcodeService;
import cn.binarywang.wx.miniapp.api.WxMaSecCheckService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaCodeLineColor;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.framework.redis.service.RedisService;
import com.shopping.wx.conf.WxMaConfiguration;
import com.shopping.wx.service.bc.BcUserWxService;
import lombok.extern.log4j.Log4j2;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信小程序用户接口
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Log4j2
@RestController
@RequestMapping("/wx/user/{appid}")
public class WxMaUserController {

    @Autowired
    RedisService redisService;

    @Autowired
    BcUserWxService bcUserWxService;

    /**
     * 登陆接口
     */
    @GetMapping("/login")
    public ActionResult login(@PathVariable String appid, String code,String loginType) {
        Map wxMap = new HashMap();
        if (StringUtils.isBlank(code)) {
            return ActionResult.error("empty jscode");
        }
        final WxMaService wxService = WxMaConfiguration.getMaServices().get(appid);
        if (wxService == null) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%d]的配置，请核实！", appid));
        }
        WxMaJscode2SessionResult session = null;
        try {
            session = wxService.getUserService().getSessionInfo(code);
        } catch (WxErrorException e) {
            log.info("WxMaJscode2SessionResult获取失败",e);
        }
        if(session == null){
            wxMap.put("status", -2);
            wxMap.put("msg", "session获取失败");
            return ActionResult.ok(wxMap);
        }
        try {
            log.info(session.getOpenid());
            //TODO OPENID OR unionid 进行登陆
            return  this.bcUserWxService.findUserByOpenIdOrUnionid(appid,session,loginType);
        } catch (Exception e) {
            return ActionResult.error(e.getMessage());
        }
    }

    /**
     * <pre>
     * 获取用户信息接口,并且进行初步注册
     * </pre>
     */
    @GetMapping("/info")
    public ActionResult info(@PathVariable String appid, String sessionKey,
                       String signature, String rawData, String encryptedData, String iv,String openid) {
        try {
            final WxMaService wxService = WxMaConfiguration.getMaServices().get(appid);
            if (wxService == null) {
                throw new IllegalArgumentException(String.format("未找到对应appid=[%d]的配置，请核实！", appid));
            }
            // 用户信息校验
            if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
                return ActionResult.error("user check failed");
            }

            // 解密用户信息
            WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
            if(userInfo != null){
                return this.bcUserWxService.saveOrUpdate(appid,userInfo,openid);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("微信获取info信息异常",e);
        }
        return ActionResult.error("获取信息异常");
    }

    /**
     * <pre>
     * 获取用户手机号码信息接口
     * </pre>
     */
    @GetMapping("/phoneNumberInfo")
    public ActionResult phoneNumberInfo(@PathVariable String appid, String sessionKey, String encryptedData, String iv) {
        try {
            final WxMaService wxService = WxMaConfiguration.getMaServices().get(appid);
            if (wxService == null) {
                throw new IllegalArgumentException(String.format("未找到对应appid=[%d]的配置，请核实！", appid));
            }

            // 解密手机号码信息
            WxMaPhoneNumberInfo phoneNumberInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);
            return ActionResult.ok(phoneNumberInfo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("微信获取info信息异常",e);
        }
        return ActionResult.error("获取信息异常");
    }

    @GetMapping("/getUnlimitMiniappCode")
    public String getMiniappQrCode(@PathVariable String appid, String scene) throws Exception{
        // 获取小程序服务实例
        final WxMaService wxService = WxMaConfiguration.getMaServices().get(appid);
        // 获取小程序二维码生成实例
        WxMaQrcodeService wxMaQrcodeService = wxService.getQrcodeService();

        // 设置小程序二维码线条颜色为黑色
        WxMaCodeLineColor lineColor = new WxMaCodeLineColor("0", "0", "0");

        // 生成二维码图片字节流
        byte[] qrCodeBytes = null;
        try{
            qrCodeBytes = wxMaQrcodeService.createWxaCodeUnlimitBytes(scene, null, 430, false, lineColor, false);
        } catch(Exception e){
            log.error("生成小程序码出错", e);
            e.printStackTrace();
        }

        return Base64.getMimeEncoder().encodeToString(qrCodeBytes).replaceAll("\r\n", "");
    }

    @GetMapping("/msgSecCheck")
    public boolean msgSecCheck(@PathVariable String appid, String msg) {
        // 获取小程序服务实例
        final WxMaService wxService = WxMaConfiguration.getMaServices().get(appid);
        WxMaSecCheckService wxMaSecCheckService = wxService.getSecCheckService();
        boolean msgSecCheckResult = false;
        try{
            msgSecCheckResult = wxMaSecCheckService.checkMessage(msg);
        } catch(Exception e){
            log.error("文本内容安全检测异常", e);
            e.printStackTrace();
        }
        return msgSecCheckResult;
    }

}
