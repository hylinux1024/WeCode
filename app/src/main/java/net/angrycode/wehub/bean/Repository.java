package net.angrycode.wehub.bean;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by lancelot on 2016/11/13.
 */

public class Repository {

    /**
     * id : 15858219
     * name : parallella-examples
     * full_name : parallella/parallella-examples
     * owner : {"login":"parallella","id":2919930,"avatar_url":"https://avatars.githubusercontent.com/u/2919930?v=3","gravatar_id":"","url":"https://api.github.com/users/parallella","html_url":"https://github.com/parallella","followers_url":"https://api.github.com/users/parallella/followers","following_url":"https://api.github.com/users/parallella/following{/other_user}","gists_url":"https://api.github.com/users/parallella/gists{/gist_id}","starred_url":"https://api.github.com/users/parallella/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/parallella/subscriptions","organizations_url":"https://api.github.com/users/parallella/orgs","repos_url":"https://api.github.com/users/parallella/repos","events_url":"https://api.github.com/users/parallella/events{/privacy}","received_events_url":"https://api.github.com/users/parallella/received_events","type":"Organization","site_admin":false}
     * private : false
     * html_url : https://github.com/parallella/parallella-examples
     * description : Community created parallella projects
     * fork : false
     * url : https://api.github.com/repos/parallella/parallella-examples
     * forks_url : https://api.github.com/repos/parallella/parallella-examples/forks
     * keys_url : https://api.github.com/repos/parallella/parallella-examples/keys{/key_id}
     * collaborators_url : https://api.github.com/repos/parallella/parallella-examples/collaborators{/collaborator}
     * teams_url : https://api.github.com/repos/parallella/parallella-examples/teams
     * hooks_url : https://api.github.com/repos/parallella/parallella-examples/hooks
     * issue_events_url : https://api.github.com/repos/parallella/parallella-examples/issues/events{/number}
     * events_url : https://api.github.com/repos/parallella/parallella-examples/events
     * assignees_url : https://api.github.com/repos/parallella/parallella-examples/assignees{/user}
     * branches_url : https://api.github.com/repos/parallella/parallella-examples/branches{/branch}
     * tags_url : https://api.github.com/repos/parallella/parallella-examples/tags
     * blobs_url : https://api.github.com/repos/parallella/parallella-examples/git/blobs{/sha}
     * git_tags_url : https://api.github.com/repos/parallella/parallella-examples/git/tags{/sha}
     * git_refs_url : https://api.github.com/repos/parallella/parallella-examples/git/refs{/sha}
     * trees_url : https://api.github.com/repos/parallella/parallella-examples/git/trees{/sha}
     * statuses_url : https://api.github.com/repos/parallella/parallella-examples/statuses/{sha}
     * languages_url : https://api.github.com/repos/parallella/parallella-examples/languages
     * stargazers_url : https://api.github.com/repos/parallella/parallella-examples/stargazers
     * contributors_url : https://api.github.com/repos/parallella/parallella-examples/contributors
     * subscribers_url : https://api.github.com/repos/parallella/parallella-examples/subscribers
     * subscription_url : https://api.github.com/repos/parallella/parallella-examples/subscription
     * commits_url : https://api.github.com/repos/parallella/parallella-examples/commits{/sha}
     * git_commits_url : https://api.github.com/repos/parallella/parallella-examples/git/commits{/sha}
     * comments_url : https://api.github.com/repos/parallella/parallella-examples/comments{/number}
     * issue_comment_url : https://api.github.com/repos/parallella/parallella-examples/issues/comments{/number}
     * contents_url : https://api.github.com/repos/parallella/parallella-examples/contents/{+path}
     * compare_url : https://api.github.com/repos/parallella/parallella-examples/compare/{base}...{head}
     * merges_url : https://api.github.com/repos/parallella/parallella-examples/merges
     * archive_url : https://api.github.com/repos/parallella/parallella-examples/{archive_format}{/ref}
     * downloads_url : https://api.github.com/repos/parallella/parallella-examples/downloads
     * issues_url : https://api.github.com/repos/parallella/parallella-examples/issues{/number}
     * pulls_url : https://api.github.com/repos/parallella/parallella-examples/pulls{/number}
     * milestones_url : https://api.github.com/repos/parallella/parallella-examples/milestones{/number}
     * notifications_url : https://api.github.com/repos/parallella/parallella-examples/notifications{?since,all,participating}
     * labels_url : https://api.github.com/repos/parallella/parallella-examples/labels{/name}
     * releases_url : https://api.github.com/repos/parallella/parallella-examples/releases{/id}
     * deployments_url : https://api.github.com/repos/parallella/parallella-examples/deployments
     * created_at : 2014-01-13T04:45:46Z
     * updated_at : 2016-11-10T21:39:15Z
     * pushed_at : 2016-10-26T17:32:08Z
     * git_url : git://github.com/parallella/parallella-examples.git
     * ssh_url : git@github.com:parallella/parallella-examples.git
     * clone_url : https://github.com/parallella/parallella-examples.git
     * svn_url : https://github.com/parallella/parallella-examples
     * homepage : null
     * size : 61861
     * stargazers_count : 261
     * watchers_count : 261
     * language : VHDL
     * has_issues : true
     * has_downloads : true
     * has_wiki : true
     * has_pages : false
     * forks_count : 104
     * mirror_url : null
     * open_issues_count : 3
     * forks : 104
     * open_issues : 3
     * watchers : 261
     * default_branch : master
     * score : 13.055051
     */

    private int id;
    private String name;
    private String full_name;
    private Owner owner;
    @SerializedName("private")
    private boolean privateX;
    private String html_url;
    private String description;
    private boolean fork;
    private String created_at;
    private String updated_at;
    private String pushed_at;
    private Object homepage;
    private int size;
    private int stargazers_count;
    private int watchers_count;
    private String language;
    private boolean has_issues;
    private boolean has_downloads;
    private boolean has_wiki;
    private boolean has_pages;
    private int forks_count;
    private Object mirror_url;
    private int open_issues_count;
    private int forks;
    private int watchers;
    private String default_branch;
    private double score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public boolean isPrivateX() {
        return privateX;
    }

    public void setPrivateX(boolean privateX) {
        this.privateX = privateX;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getPushed_at() {
        return pushed_at;
    }

    public void setPushed_at(String pushed_at) {
        this.pushed_at = pushed_at;
    }

    public Object getHomepage() {
        return homepage;
    }

    public void setHomepage(Object homepage) {
        this.homepage = homepage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public int getWatchers_count() {
        return watchers_count;
    }

    public void setWatchers_count(int watchers_count) {
        this.watchers_count = watchers_count;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isHas_issues() {
        return has_issues;
    }

    public void setHas_issues(boolean has_issues) {
        this.has_issues = has_issues;
    }

    public boolean isHas_downloads() {
        return has_downloads;
    }

    public void setHas_downloads(boolean has_downloads) {
        this.has_downloads = has_downloads;
    }

    public boolean isHas_wiki() {
        return has_wiki;
    }

    public void setHas_wiki(boolean has_wiki) {
        this.has_wiki = has_wiki;
    }

    public boolean isHas_pages() {
        return has_pages;
    }

    public void setHas_pages(boolean has_pages) {
        this.has_pages = has_pages;
    }

    public int getForks_count() {
        return forks_count;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }

    public Object getMirror_url() {
        return mirror_url;
    }

    public void setMirror_url(Object mirror_url) {
        this.mirror_url = mirror_url;
    }

    public int getOpen_issues_count() {
        return open_issues_count;
    }

    public void setOpen_issues_count(int open_issues_count) {
        this.open_issues_count = open_issues_count;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public int getWatchers() {
        return watchers;
    }

    public void setWatchers(int watchers) {
        this.watchers = watchers;
    }

    public String getDefault_branch() {
        return default_branch;
    }

    public void setDefault_branch(String default_branch) {
        this.default_branch = default_branch;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public TrendingRepo convert2TrendingRepo() {
        TrendingRepo repo = new TrendingRepo(null, owner.getAvatar_url(), description, html_url, name, owner.getLogin(), String.valueOf(stargazers_count), new Date());
        return repo;
    }

}
