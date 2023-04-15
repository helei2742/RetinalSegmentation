package org.helei.retinalsegmentation.utils;



public class RedisConstants {
    /**
     * 临时使用，验证码key
     */
    public static final String INIT_CHECKCODE_KEY = "init:checkcode:";
    /**
     * 验证码过期时间，单位分
     */
    public static final Long INIT_CHECKCODE_TTL = 5l;


    /**
     * 临时用户id的key
     */
    public static final String INIT_USER_IDNUMBER_KEY = "init:user_idnumber:";
    /**
     * 临时用户id过期时间，分
     */
    public static final Long INIT_USER_IDNUMBER_TTL = 15l;



    /**
     * 注册用户，登陆验证码key
     */
    public static final String USER_LOGIN_CHECKCODE_KEY = "user:login:checkcode:";
    /**
     * 组成用户登陆验证码过期时间，分
     */
    public static final Long USER_LOGIN_CHECKCODE_TTL = 5l;


    /**
     * 登陆用户存在redis的key
     */
    public static final String USER_LOGIN_TOKEN_KEY = "user:login:token:";
    /**
     * 登陆用户存在redis的key的过期时间，天
     */
    public static final long USER_LOGIN_TOKEN_TTL = 7;

    /**
     * 病人绑定码 key
     */
    public static final String PATIENT_BIND_CODE_KEY = "patient:bind:code:";
    /**
     * 病人绑定码 过期时间， 分钟
     */
    public static final Long PATIENT_BIND_CODE_TTL = 30l;

}
