package JJTalk.pojo;

import java.io.Serializable;

public class ClientInfo implements Serializable {

    /**
     * 客户端id
     */
    private String clientId;
    /**
     * 客户端ip
     */
    private String ip;
    /**
     * 客户端端口号
     */
    private String port;
    /**
     * 是否活跃
     */
    private String isActive;
    /**
     * 是否查询
     */
    private String isQuery;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String isActive() {
        return isActive;
    }

    public void setActive(String active) {
        isActive = active;
    }

    public String isQuery() {
        return isQuery;
    }

    public void setQuery(String query) {
        isQuery = query;
    }
    @Override
    public String toString() {
        return "ClientInfo{" +
                "clientId='" + clientId + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", isActive='" + isActive + '\'' +
                ", isQuery='" + isQuery + '\'' +
                '}';
    }
}
