package com.yw.shirotemplate.shiroUtils.core.shiro;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.yw.shirotemplate.myShiro.entity.User;
import com.yw.shirotemplate.myShiro.service.IUserService;
import com.yw.shirotemplate.shiroUtils.core.shiro.JwtToken.JWTToken;
import com.yw.shirotemplate.shiroUtils.core.shiro.utils.JWTUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/10/11.
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userCode = JWTUtil.getUserCode(principals.toString());
        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
        Map<String, Object> map = null;
        try {
            map = this.userService.getRolesAndOrgsByUserCode(userCode);
            auth.setRoles((Set<String>) map.get("allRoles"));
            auth.setStringPermissions((Set<String>) map.get("allPermissions"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return auth;
    }

    /**
     * 认证
     *
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得userCode，用于和数据库进行对比
        String userCode = JWTUtil.getUserCode(token);
        User vo = this.userService.findByUserCode(userCode);
        String redisUserInfo = (String) redisTemplate.opsForValue().get("token_jwt_" + userCode);

        Map result = JWTUtil.verify(token, userCode, vo.getUserPwd());
        Exception exception = (Exception) result.get("exception");

        if (vo == null) {
            throw new UnknownAccountException("该帐号不存在！");
        } else if (vo.getUserStatus() == "0" || vo.getUserStatus().equals("1")) {
            throw new UnknownAccountException("该帐号已被锁定！");
        } else if (exception != null && exception instanceof SignatureVerificationException) {
            throw new AuthenticationException("Token错误(Token incorrect.)！");
        } else if (exception != null && exception instanceof TokenExpiredException) {
            throw new AuthenticationException("Token已过期(Token expired.)！");
            //被T
        } else if(StringUtils.isEmpty(redisUserInfo)){
            throw new AuthenticationException("Token已失效(Token invalid.)！");
        }else {
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(token, token, vo.getUserCode());
            return authcInfo;
        }
    }
}
