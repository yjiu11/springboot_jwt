package com.yjiu.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yjiu.jwt.Audience;
import com.yjiu.pojo.UserInfo;
import com.yjiu.service.UserInfoService;
import com.yjiu.tools.ResultMsg;
import com.yjiu.tools.ResultStatusCode;

@Controller
public class IndexController {

	@Autowired 
	private Audience audience;
	@Autowired
	private UserInfoService userRepositoy;
	@RequestMapping("api/getaudience")
	@ResponseBody
	public Object getAudience()
	{
		ResultMsg resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), audience);
		return resultMsg;
	}
	
	@RequestMapping("api/test")
	@ResponseBody
	public List<UserInfo> test()
	{
		List<UserInfo> lists = userRepositoy.selectList(new EntityWrapper<UserInfo>().eq("name", "test"));
		return lists;
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}
}
