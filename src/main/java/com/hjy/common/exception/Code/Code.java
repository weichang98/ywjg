package com.hjy.common.exception.Code;

public enum Code {
    /*
    INVALID REQUEST - [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
     */
    C400(400, "INVALID REQUEST"),
    /*
   Unauthorized - [*]：表示用户没有权限（令牌、用户名、密码错误）
    */
    C401(401, "Unauthorized"),
    /*
    NOT FOUND - [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的
     */
    C404(404, "Not Found"),
    /*
    method not allowed：该http方法不被允许
     */
    C405(405, "method not allowed"),
    /*
    Not Acceptable - [GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）
     */
    C406(406, "Not Acceptable"),
    /*
    不支持的媒体类型。例如上传文件只允许png图片，上传gif文件时，此时返回415
     */
    C415(415, "Error Type"),
    /*
    Unprocesable entity - [POST/PUT/PATCH]：当创建一个对象时，发生一个验证错误。请求格式正确，但语义错误。此时错误描述信息中最好有错误详情。
     */
    C422(422, "Unprocesable entity "),
    /*
    too many request - 请求过多
     */
    C429(429, "too many request"),
    /*
    INTERNAL SERVER ERROR - [*]：服务器发生错误，用户将无法判断发出的请求是否成功
     */
    C500(500, "Internal Server Error");
    /**
     * Code 状态码
     */
    private Integer code;
    /**
     * desc 描述
     */
    private String desc;

    Code(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
