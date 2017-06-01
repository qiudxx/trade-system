package com.ace.trade.common.contants;

/**
 * <B>描述: </B><br/>
 * <B>作者: </B>carl.yu<br/>
 * <B>时间: </B>2017/2/21<br/>
 * <B>版本: </B>v1.0.0 <br/>
 * <B>历史: </B> (版本 作者 时间 注释) <br/>
 */
public class TradeEnums {

    public enum RestServerEnum {
        ORDER("localhost", "order", 8080),
        PAY("localhost", "pay", 8081),
        COUPON("localhost", "coupon", 8082),
        GOODS("localhost", "goods", 8083),
        USERS("localhost", "user", 8084);

        private int port;
        private String host;
        private String contextPath;


        RestServerEnum(String host, String contextPath, int port) {
            this.port = port;
            this.host = host;
            this.contextPath = contextPath;
        }

        public int getPort() {
            return port;
        }

        public String getHost() {
            return host;
        }

        public String getContextPath() {
            return contextPath;
        }

        public String getServerUrl() {
            return "http://" + getHost() + ":" + getPort() + "/" + contextPath;
        }

    }


    /**
     * <pre>
     *     响应对象
     * </pre>
     */
    public enum RetEnum {
        SUCCESS("1", "成功"), FAIL("-1", "失败");

        private String code;
        private String desc;

        RetEnum(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum OrderStatusEnum {
        UN_CONFIRMED("0", "未确认"),
        CONFIRMED("1", "已确认"),
        CANCELED("2", "已取消"),
        INVALID("3", "已取消"),
        RETURNED("4", "已退货");
        private String statusCode;

        private String desc;

        OrderStatusEnum(String statusCode, String desc) {
            this.statusCode = statusCode;
            this.desc = desc;
        }

        public String getStatusCode() {
            return statusCode;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum PayStatusEnum {
        UN_PAID("0", "未付款"),
        PAYING("1", "付款中"),
        PAID("2", "已付款");
        private String statusCode;

        private String desc;

        PayStatusEnum(String statusCode, String desc) {
            this.statusCode = statusCode;
            this.desc = desc;
        }

        public String getStatusCode() {
            return statusCode;
        }

        public String getDesc() {
            return desc;
        }
    }


    public enum ShippingStatusEnum {
        UN_SHIPPED("0", "未发货"),
        SHIPPED("1", "已发货"),
        RECEIVED("2", "已收货");
        private String statusCode;

        private String desc;

        ShippingStatusEnum(String statusCode, String desc) {
            this.statusCode = statusCode;
            this.desc = desc;
        }

        public String getStatusCode() {
            return statusCode;
        }

        public String getDesc() {
            return desc;
        }
    }

    public enum YesNoEnum {
        NO("0", "否"),
        YES("1", "是");


        private String code, desc;


        YesNoEnum(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }
}
