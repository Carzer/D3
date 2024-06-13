package com.github.d3.util;

import com.github.d3.constants.CommonConstants;
import com.github.d3.constants.PunctuationConstants;
import com.github.d3.exception.MsgException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Set;
import java.util.TreeSet;


/**
 * 网络工具类
 *
 * @author Carzer1020@163.com
 * @since 1.0
 */
@UtilityClass
@Slf4j
public class NetUtil {

    /**
     * 获取请求方的真实IP
     *
     * @param request 请求
     * @return 求方的真实IP
     */
    public String getRemoteIpAddress(HttpServletRequest request) {
        String[] headers = new String[]{"x-forwarded-for", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR", "X-Real-IP"};
        String ip = null;
        // 逐级查询真实IP
        for (String header : headers) {
            ip = request.getHeader(header);
            if (readableIp(ip)) {
                break;
            }
        }
        if (!readableIp(ip)) {
            ip = request.getRemoteAddr();
        }
        // 如果拼接了多个IP，则第一个可读IP才是真实IP
        if (readableIp(ip) && ip.contains(PunctuationConstants.COMMA)) {
            String[] args = ip.split(PunctuationConstants.COMMA);
            for (String arg : args) {
                if (readableIp(arg)) {
                    ip = arg;
                    break;
                }
            }
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    /**
     * 判断是否可读的IP类型（非空，不等于"unknown"）
     *
     * @param ip IP
     * @return 是否可读
     */
    private boolean readableIp(String ip) {
        return StringUtils.hasText(ip) && !CommonConstants.UNKNOWN.equalsIgnoreCase(ip);
    }

    /**
     * 根据mac地址获取workId
     *
     * @return workId
     * @since 1.0
     */
    public long generateWorkerIdBaseOnMac() {
        try {
            // 获取所有网卡信息
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            // 创建一个根据网卡index排序的set
            Set<NetworkInterface> actualNetworkInterfaces = new TreeSet<>(Comparator.comparingInt(NetworkInterface::getIndex));
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                boolean isLoopback = networkInterface.isLoopback();
                boolean isVirtual = networkInterface.isVirtual();
                if (isLoopback || isVirtual) {
                    continue;
                }
                // 只添加真实的网卡进行排序
                actualNetworkInterfaces.add(networkInterface);
            }
            for (NetworkInterface ni : actualNetworkInterfaces) {
                // 找到排序后的第一个非空的mac地址信息，生成workId
                byte[] mac = ni.getHardwareAddress();
                if (mac != null) {
                    return ((mac[4] & 0B11) << 8) | (mac[5] & 0xFF);
                }
            }
        } catch (SocketException e) {
            log.error("获取workId异常");
        }
        throw new MsgException("no available mac found");
    }

}
