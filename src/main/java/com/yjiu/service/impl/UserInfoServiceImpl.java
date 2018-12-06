package com.yjiu.service.impl;

import com.yjiu.pojo.UserInfo;
import com.yjiu.mapper.UserInfoMapper;
import com.yjiu.service.UserInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yjiu123
 * @since 2018-12-05
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
