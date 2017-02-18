package net.angrycode.wehub.bean;

public class Owner {
        /**
         * login : parallella
         * id : 2919930
         * avatar_url : https://avatars.githubusercontent.com/u/2919930?v=3
         * gravatar_id :
         * url : https://api.github.com/users/parallella
         * html_url : https://github.com/parallella
         * followers_url : https://api.github.com/users/parallella/followers
         * following_url : https://api.github.com/users/parallella/following{/other_user}
         * gists_url : https://api.github.com/users/parallella/gists{/gist_id}
         * starred_url : https://api.github.com/users/parallella/starred{/owner}{/repo}
         * subscriptions_url : https://api.github.com/users/parallella/subscriptions
         * organizations_url : https://api.github.com/users/parallella/orgs
         * repos_url : https://api.github.com/users/parallella/repos
         * events_url : https://api.github.com/users/parallella/events{/privacy}
         * received_events_url : https://api.github.com/users/parallella/received_events
         * type : Organization
         * site_admin : false
         */

        private String login;
        private int id;
        private String avatar_url;
        private String gravatar_id;
        private String url;
        private String html_url;
        private String type;
        private boolean site_admin;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getGravatar_id() {
            return gravatar_id;
        }

        public void setGravatar_id(String gravatar_id) {
            this.gravatar_id = gravatar_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHtml_url() {
            return html_url;
        }

        public void setHtml_url(String html_url) {
            this.html_url = html_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isSite_admin() {
            return site_admin;
        }

        public void setSite_admin(boolean site_admin) {
            this.site_admin = site_admin;
        }
    }