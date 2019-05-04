package com.blog.ln.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.blog.ln.bean.UserInfo;

/**
 * 用户管理的数据访问接口
 * @author Administrator
 * 
 */
@Mapper
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    /**
     * 根据编号查询用户信息
     * @param userId
     * @return
     */
    UserInfo selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    
    /**
     * 根据分页条件查询用户信息
     * @param user 查询条件
     * @return 返回多个用户信息
     */
    public List<UserInfo> getUserList(UserInfo user);
    
    /**
     * 根据条件查询用户的数量 （根据这个数量来进行分页）
     * @param user 查询条件
     * @return 用户数量
     */
    public Long getUserCount(UserInfo user);
    
    /**
     * 用户登录
     * @param user
     * @return
     */
    public UserInfo isLogin(UserInfo user);
    
}