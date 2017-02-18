package net.angrycode.wehub.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.json.JSONObject;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Date;

/**
 * https://github.com/trending
 * Created by lancelot on 2016/12/2.
 */
@Entity(indexes = {
        @Index(value = "name, date DESC", unique = true)
})
public class TrendingRepo {
    @Id
    private Long id;

    private String avatar;
    private String desc;
    @NotNull
    private String url;
    @NotNull
    private String name;
    private String owner;
    private String stars_today;

    private Date date;

    public TrendingRepo(JSONObject jsonObject) {
        avatar = jsonObject.optString("avatar");
        desc = jsonObject.optString("desc");
        url = jsonObject.optString("url");
        name = jsonObject.optString("name");
        owner = jsonObject.optString("owner");
        stars_today = jsonObject.optString("stars_today");
    }



    @Generated(hash = 34610607)
    public TrendingRepo(Long id, String avatar, String desc, @NotNull String url,
            @NotNull String name, String owner, String stars_today, Date date) {
        this.id = id;
        this.avatar = avatar;
        this.desc = desc;
        this.url = url;
        this.name = name;
        this.owner = owner;
        this.stars_today = stars_today;
        this.date = date;
    }



    @Generated(hash = 843815768)
    public TrendingRepo() {
    }



    public String getAvatar() {
        return avatar;
    }

    public String getDesc() {
        return desc;
    }


    public String getStars_today() {
        return stars_today;
    }

    public String getOwner() {
        return owner;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setStars_today(String stars_today) {
        this.stars_today = stars_today;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrendingRepo repo1 = (TrendingRepo) o;

        if (!url.equals(repo1.url)) return false;
        if (!name.equals(repo1.name)) return false;
        return owner != null ? owner.equals(repo1.owner) : repo1.owner == null;

    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }
}
