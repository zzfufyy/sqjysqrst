package com.shopping.wx.controller.bc;

import com.shopping.base.foundation.dao.bc.TemplateDAO;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.base.repository.bc.BcUserRepository;
import com.shopping.base.utils.CommUtils;
import com.shopping.base.utils.Md5Encrypt;
import com.shopping.framework.redis.service.RedisService;
import com.shopping.wx.constant.BcUserCons;
import com.shopping.wx.constant.RedisCons;
import com.shopping.wx.form.bc.BcUserAddForm;
import com.shopping.wx.form.bc.BcUserQueryForm;
import com.shopping.wx.service.bc.BcUserService;
import com.shopping.wx.service.bc.BcUserWxService;
import com.shopping.wx.token.authorization.manager.BcLoginUtils;
import com.shopping.wx.util.BaseSMS;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/bc/{appid}/BcUser")
@Log4j2
public class BcUserController {
    @Autowired
    BcUserService bcUserService;
    @Autowired
    BaseSMS baseSMS;
    @Autowired
    RedisService redisService;
    @Autowired
    BcUserWxService bcUserWxService;
    @Autowired
    BcLoginUtils bcLoginUtils;
    @Autowired
    BcUserRepository bcUserRepository;

    @Autowired
    TemplateDAO templateDAO;

    /**
     * 根据openId查询用户部门信息
     * @param appid
     * @param openId
     * @return
     */
    @RequestMapping(value = "/getDeptByOpenId", method = RequestMethod.GET)
    public List<Map<String, Object>> getDeptByOpenId(@PathVariable String appid, String openId) {
        try {
            Map<String,Object> map = new HashMap<>(2);
            map.put("appId", appid);
            map.put("openId", openId);
            String sql ="SELECT u.id, u.name, u.mobile, d.name as department, u.status, u.user_department_id, wx.openid, u.user_dw_id " +
                    " FROM bc_user u" +
                    " left join bc_user_department d ON (u.app_id = d.app_id AND u.user_department_id = d.id)" +
                    " LEFT JOIN bc_user_wx wx ON (u.app_id = wx.app_id AND u.id = wx.bc_user_id)" +
                    " WHERE u.app_id =:appId AND wx.openId =:openId";

            List<Map<String, Object>> result = this.templateDAO.queryByNativeSQL(sql, map);
            return result;
        } catch (Exception e) {
            log.error("报餐用户获取部门信息异常", e);
            e.printStackTrace();
        }
        return new ArrayList<Map<String, Object>>();
    }

    /**
     * 报餐用户注册信息保存
     * @param bcUserAddForm
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public ActionResult bcUserRegister(@PathVariable String appid, BcUserAddForm bcUserAddForm){
        try {
            return bcUserService.bcUserRegister(appid,bcUserAddForm);
        }catch (Exception e){
            log.error("报餐用户注册信息保存异常",e);
        }
        return ActionResult.error("服务器异常");
    }


    /**
     * 用户注册的时候发送验证码
     * @param mobile
     * @param openid
     * @return
     */
    @ApiOperation(value = "发送短信验证码", tags = {"BcUser"}, notes = "")
    @RequestMapping(value = "/bcUserBuyerCode", method = RequestMethod.GET)
    public ActionResult<Map> verificationCode(String mobile, String openid) {
        try {
            /*声明返回map*/
            Map map = new HashMap(2);
            //发送短信
            Map<String, Object> paramMap = baseSMS.sendSMS(mobile);
            //验证码
            String authCode = paramMap.get("authCode").toString();
            //发送状态
            boolean flag = (boolean) paramMap.get("flag");
            if (flag) {
                map.put("status", "1");
                map.put("message", "验证码已发送");
            } else {
                map.put("status", "0");
                map.put("message", "发送频繁");
            }
            redisService.set(mobile, Md5Encrypt.md5(authCode), RedisCons.SMS_TIMEOUT);
            return ActionResult.ok(map);
        } catch (Exception e) {
            log.error("发送短信验证码异常", e);
        }
        return ActionResult.error("服务器异常");
    }

    @ApiOperation(value = "验证短信验证码有效性", tags = {"BcUser"}, notes = "")
    @RequestMapping(value = "/bcUserValiCode", method = RequestMethod.GET)
    public ActionResult<Map> valiCode(HttpServletRequest request) {
        try {
//            /*获取数据*/
//            String mobileNo = request.getParameter("mobile");
//            String codeNo = request.getParameter("codeNo");
//            HttpSession session = request.getSession();
//            /*获取数据*/
//
//            /*声明返回map*/
//            Map map = new HashMap(2);
//
//            if (StringUtils.isNotBlank(mobileNo)) {
//                String sessionCode = redisService.get(mobileNo)!=null ? redisService.get(mobileNo).replaceAll("\"","") : "";
//                if (StringUtils.isBlank(sessionCode)) {
//                    //验证码失效
//                    map.put("status", "-1");
//                    map.put("message", "验证码失效");
//                } else if (StringUtils.isNotBlank(codeNo)) {
//                    if (Md5Encrypt.md5(codeNo).equalsIgnoreCase(sessionCode)) {
//                        //验证通过
//                        map.put("status", "1");
//                        map.put("message", "验证通过");
//                    } else {
//                        //验证没通过
//                        map.put("status", "0");
//                        map.put("message", "验证失败");
//                    }
//                } else {
//                    //验证没通过
//                    map.put("status", "0");
//                    map.put("message", "验证失败");
//                }
//
//            } else {
//                //验证没通过
//                map.put("status", "0");
//                map.put("message", "验证失败");
//            }
            //验证没通过


            /*声明返回map*/
            Map map = new HashMap(2);
            map.put("status", "1");
            map.put("message", "验证通过");
            return ActionResult.ok(map);
        } catch (Exception e) {
            log.error("验证短信验证码有效性异常", e);
        }
        return ActionResult.error("服务器异常");
    }

    /**
     * 获取员工信息列表
     * @param appid
     * @param queryForm
     * @return
     */
    @ApiOperation(value = "获取员工信息列表", tags = {"BcUser"}, notes = "")
    @GetMapping("/getUserPageList")
    public ActionResult getUserPageList(@PathVariable String appid, BcUserQueryForm queryForm,int currentPage,int pageSize){
        try{
            Map<Object,Object>  map = new HashMap(2);
            queryForm.setAppId(appid);
            List<Map<String,Object>> list =this.bcUserService.getUserPageList(queryForm,currentPage,pageSize);
            BigInteger total = this.bcUserService.getTotalByFields(queryForm);
            map.put("data",list);
            map.put("total",total);
            return ActionResult.ok(map);
        }catch (Exception e){
            log.error("获取员工信息列表异常",e);
        }
        return  ActionResult.error("服务器异常!");
    }

    /**
     * 更改员工状态
     * @param appid
     * @param id
     * @param isActive
     * @return
     */
    @ApiOperation(value = "更改员工状态", tags = {"BcUser"}, notes = "")
    @GetMapping("/updateStatusById")
    public ActionResult updateStatusById(@PathVariable String appid,Long id,Boolean isActive){
        try{
            int result=this.bcUserService.updateStatusById(appid,isActive? BcUserCons.STATUS_ACTIVE :BcUserCons.STATUS_NOACTIVE,id);
            return ActionResult.ok(result);
        }catch(Exception e){
            log.error("更改员工状态",e);
        }
        return ActionResult.error("服务器异常");
    }

    @ApiOperation(value = "修改员工状态", tags = {"BcUser"}, notes = "")
    @GetMapping("/updatedwIdById")
    public ActionResult updatedwIdById(@PathVariable String appid,Long id,Long myid){
        try{
            int result1 =this.bcUserService.updatemydwIdById(appid,myid);
            int result=this.bcUserService.updatedwIdById(appid,id);
            return ActionResult.ok(result);
        }catch(Exception e){
            log.error("修改员工状态",e);
        }
        return ActionResult.error("服务器异常");
    }

    /**
     * 删除员工
     * @param appid
     * @param id
     * @return
     */
    @ApiOperation(value = "删除员工",tags = {"BcUser"} ,notes = "")
    @DeleteMapping("/delete")
    public ActionResult deleteById(@PathVariable String appid,Long id){
        try{
            this.bcUserService.delete(id);
            return  ActionResult.ok();
        }catch (Exception e){
            log.error("删除员工异常",e);
        }
        return ActionResult.error("服务器异常");
    }

    /**
     * 导出
     * @param response
     * @param appid
     * @param queryForm
     * @return
     */
    @ApiOperation(value = "导出",tags ={"BcUser"},notes = "")
    @PostMapping("/export")
    public ActionResult export(HttpServletResponse response, @PathVariable String appid, BcUserQueryForm queryForm){
        try{
            queryForm.setAppId(appid);
            this.bcUserService.export(response,queryForm);
            return  ActionResult.ok();
        }catch (Exception e){
            log.error("导出信息异常",e);
        }
        return  ActionResult.error("服务器异常");
    }

    @ApiOperation(value = "修改部门", tags ={"BcUser"},notes = "")
    @GetMapping("/editUserDepartmentId")
    public ActionResult editUserDepartmentId(@PathVariable String appid,Long userDepartmentId,Long id){
        try{
            this.bcUserService.updateStatusById(appid, BcUserCons.STATUS_UNACTIVE ,id);
            int result = this.bcUserService.editDepartmentIdById(appid,userDepartmentId,id);
            return ActionResult.ok(result);
        }catch (Exception e){
            log.error("修改部门异常",e);
        }
        return ActionResult.error("服务器异常");
    }
}
