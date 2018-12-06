package com.yjiu.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yjiu.jwt.AccessToken;
import com.yjiu.jwt.Audience;
import com.yjiu.jwt.JwtHelper;
import com.yjiu.jwt.LoginPara;
import com.yjiu.pojo.UserInfo;
import com.yjiu.service.UserInfoService;
import com.yjiu.tools.MyUtils;
import com.yjiu.tools.ResultMsg;
import com.yjiu.tools.ResultStatusCode;
 
@RestController
public class JsonWebToken {
	@Autowired
	private UserInfoService userRepositoy;
	
	@Autowired
	private Audience audience;
	
	@RequestMapping("oauth/token")
	@ResponseBody
	public Object getAccessToken(@RequestBody LoginPara loginPara)
	{
		ResultMsg resultMsg;
		try
		{
			System.out.println(audience.getClientId());
			System.out.println(loginPara.getClientId());
			if(loginPara.getClientId() == null 
					|| (loginPara.getClientId().compareTo(audience.getClientId()) != 0))
			{
				resultMsg = new ResultMsg(ResultStatusCode.INVALID_CLIENTID.getErrcode(), 
						ResultStatusCode.INVALID_CLIENTID.getErrmsg(), null);
				return resultMsg;
			}
			
			//验证码校验在后面章节添加
			
			
			//验证用户名密码
			List<UserInfo> users = userRepositoy.selectList(new EntityWrapper<UserInfo>().eq("name", loginPara.getUserName()));//findUserInfoByName(loginPara.getUserName());
			UserInfo user = users.get(0);
			if (user == null)
			{
				resultMsg = new ResultMsg(ResultStatusCode.INVALID_PASSWORD.getErrcode(),
						ResultStatusCode.INVALID_PASSWORD.getErrmsg(), null);
				return resultMsg;
			}
			else
			{
				String md5Password = MyUtils.getMD5(loginPara.getPassword()+user.getSalt());
				
				if (md5Password.compareTo(user.getPassword()) != 0)
				{
					resultMsg = new ResultMsg(ResultStatusCode.INVALID_PASSWORD.getErrcode(),
							ResultStatusCode.INVALID_PASSWORD.getErrmsg(), null);
					return resultMsg;
				}
			}
			
			//拼装accessToken
			String accessToken = JwtHelper.createJWT(loginPara.getUserName(), String.valueOf(user.getName()),
					user.getRole(), audience.getClientId(), audience.getName(),
					audience.getExpiresSecond() * 1000, audience.getBase64Secret());
			
			//返回accessToken
			AccessToken accessTokenEntity = new AccessToken();
			accessTokenEntity.setAccess_token(accessToken);
			accessTokenEntity.setExpires_in(audience.getExpiresSecond());
			accessTokenEntity.setToken_type("bearer");
			resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), 
					ResultStatusCode.OK.getErrmsg(), accessTokenEntity);
			return resultMsg;
			
		}
		catch(Exception ex)
		{
			resultMsg = new ResultMsg(ResultStatusCode.SYSTEM_ERR.getErrcode(), 
					ResultStatusCode.SYSTEM_ERR.getErrmsg(), null);
			return resultMsg;
		}
	}
}