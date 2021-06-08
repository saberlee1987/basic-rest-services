package com.saber.util.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
@Slf4j
public class ServiceUtil {

    @Value(value = "${server.port}")
    private String port;

    private String serviceAddress = null;

    public String getServiceAddress() {
        if (serviceAddress == null) {
            this.serviceAddress = findMyHostname() + "/" + findMyIpAddress() + ":" + port;
        }
        return this.serviceAddress;
    }

    private String findMyHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            return "unknown host name";
        }
    }

    private String findMyIpAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            return "unknown ip address";
        }
    }


}
