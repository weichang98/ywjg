package com.hjy.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.util.Enumeration;

public class IPUtil {

    private static final String UNKNOWN = "unknown";

    protected IPUtil() {

    }
	private static final String[] HEADERS = {
			"X-Forwarded-For",
			"Proxy-Client-IP",
			"WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR",
			"HTTP_X_FORWARDED",
			"HTTP_X_CLUSTER_CLIENT_IP",
			"HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR",
			"HTTP_FORWARDED",
			"HTTP_VIA",
			"REMOTE_ADDR",
			"X-Real-IP"
	};
	/**
	 * 判断ip是否为空，空返回true
	 * @param ip
	 * @return
	 */
	public static boolean isEmptyIp(final String ip){
		return (ip == null || ip.length() == 0 || ip.trim().equals("") || UNKNOWN.equalsIgnoreCase(ip));
	}
	/**
	 * 判断ip是否不为空，不为空返回true
	 * @param ip
	 * @return
	 */
	public static boolean isNotEmptyIp(final String ip){
		return !isEmptyIp(ip);
	}
    /**
     * 1获取 IP地址
     * 使用 Nginx等反向代理软件， 则不能通过 request.getRemoteAddr()获取 IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，
     * X-Forwarded-For中第一个非 unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddress1(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

	/***
	 * 2获取客户端ip地址(可以穿透代理)
	 * @param request HttpServletRequest
	 * @return
	 */
	public static String getIpAddress2(HttpServletRequest request) {
		String ip = "";
		for (String header : HEADERS) {
			ip = request.getHeader(header);
			if(isNotEmptyIp(ip)) {
				break;
			}
		}
		if(isEmptyIp(ip)){
			ip = request.getRemoteAddr();
		}
		if(isNotEmptyIp(ip) && ip.contains(",")){
			ip = ip.split(",")[0];
		}
		if("0:0:0:0:0:0:0:1".equals(ip)){
			ip = "127.0.0.1";
		}
		return ip;
	}

    //3获取用户真实ip
    public static String getIpAddress3(HttpServletRequest request) throws UnknownHostException {
        String ip = "";
        // 有的user可能使用代理，为处理用户使用代理的情况，使用x-forwarded-for
        if (request.getHeader("x-forwarded-for") == null) {
            ip = request.getRemoteAddr();
        } else {
            ip = request.getHeader("x-forwarded-for");
        }
        if ("127.0.0.1".equals(ip)) {
            // 获取本机真正的ip地址
            ip = InetAddress.getLocalHost().getHostAddress();
        }
        return ip;
    }
	/**
	 * 4获取登录用户的IP地址
	 * @param request
	 * @return
	 */
	public static String getIpAddress4(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if ("0:0:0:0:0:0:0:1".equals(ip)) {
			ip = "127.0.0.1";
		}
		if (ip.split(",").length > 1) {
			ip = ip.split(",")[0];
		}
		return ip;
	}

	//5此方法只能获取到该服务器所在机器的ip
	public static String getIpAddress5() {
		String ip = "";
		try {
			InetAddress ip4 = Inet4Address.getLocalHost();
			ip = ip4.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ip;
	}
	/**
	 * 6获取本机的局域网ip地址，兼容Linux
	 * @return String
	 * @throws SocketException
	 */
	public static String getIpAddress6() throws SocketException {
		Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		String localHostAddress = "";
		while(allNetInterfaces.hasMoreElements()){
			NetworkInterface networkInterface = allNetInterfaces.nextElement();
			Enumeration<InetAddress> address = networkInterface.getInetAddresses();
			while(address.hasMoreElements()){
				InetAddress inetAddress = address.nextElement();
				if(inetAddress != null && inetAddress instanceof Inet4Address){
					localHostAddress = inetAddress.getHostAddress();
				}
			}
		}
		return localHostAddress;
	}
}
