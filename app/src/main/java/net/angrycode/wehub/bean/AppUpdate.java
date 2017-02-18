package net.angrycode.wehub.bean;

/**
 * Created by lancelot on 2016/12/21.
 */

public class AppUpdate {
    private int code;
    private String msg;
    private Data data;

    public AppUpdate() {
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class Data {
        private String url;
        private int has_new;
        private String version;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getHas_new() {
            return has_new;
        }

        public void setHas_new(int has_new) {
            this.has_new = has_new;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
