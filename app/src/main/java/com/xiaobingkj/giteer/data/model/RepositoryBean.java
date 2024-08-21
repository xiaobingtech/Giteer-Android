/*
 *    sora-editor - the awesome code editor for Android
 *    https://github.com/Rosemoe/sora-editor
 *    Copyright (C) 2020-2024  Rosemoe
 *
 *     This library is free software; you can redistribute it and/or
 *     modify it under the terms of the GNU Lesser General Public
 *     License as published by the Free Software Foundation; either
 *     version 2.1 of the License, or (at your option) any later version.
 *
 *     This library is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *     Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public
 *     License along with this library; if not, write to the Free Software
 *     Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301
 *     USA
 *
 *     Please contact Rosemoe by email 2073412493@qq.com if you need
 *     additional information or have any questions
 */
package com.xiaobingkj.giteer.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepositoryBean implements Parcelable {

    private Integer id;
    private String full_name;
    private String human_name;
    private String url;
    private NamespaceDTO namespace;
    private String path;
    private String name;
    private OwnerDTO owner;
    private AssignerDTO assigner;
    private String description;
    @SerializedName("private")
    private Boolean privateX;
    @SerializedName("public")
    private Boolean publicX;
    private Boolean internal;
    private Boolean fork;
    private String html_url;
    private String ssh_url;
    private String forks_url;
    private String keys_url;
    private String collaborators_url;
    private String hooks_url;
    private String branches_url;
    private String tags_url;
    private String blobs_url;
    private String stargazers_url;
    private String contributors_url;
    private String commits_url;
    private String comments_url;
    private String issue_comment_url;
    private String issues_url;
    private String pulls_url;
    private String milestones_url;
    private String notifications_url;
    private String labels_url;
    private String releases_url;
    private Boolean recommend;
    private Boolean gvp;
    private String homepage;
    private String language;
    private Integer forks_count;
    private Integer stargazers_count;
    private Integer watchers_count;
    private String default_branch;
    private Integer open_issues_count;
    private Boolean has_issues;
    private Boolean has_wiki;
    private Boolean issue_comment;
    private Boolean can_comment;
    private Boolean pull_requests_enabled;
    private Boolean has_page;
    private Object license;
    private Boolean outsourced;
    private String project_creator;
    private List<String> members;
    private String pushed_at;
    private String created_at;
    private String updated_at;
    private Object parent;
    private Object paas;
    private Integer assignees_number;
    private Integer testers_number;
    private List<AssigneeDTO> assignee;
    private List<TestersDTO> testers;
    private String status;
    private List<?> programs;
    private Object enterprise;
    private List<?> project_labels;
    private String issue_template_source;

    public RepositoryBean() {
        super();
    }

    protected RepositoryBean(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        full_name = in.readString();
        human_name = in.readString();
        url = in.readString();
        path = in.readString();
        name = in.readString();
        description = in.readString();
        byte tmpPrivateX = in.readByte();
        privateX = tmpPrivateX == 0 ? null : tmpPrivateX == 1;
        byte tmpPublicX = in.readByte();
        publicX = tmpPublicX == 0 ? null : tmpPublicX == 1;
        byte tmpInternal = in.readByte();
        internal = tmpInternal == 0 ? null : tmpInternal == 1;
        byte tmpFork = in.readByte();
        fork = tmpFork == 0 ? null : tmpFork == 1;
        html_url = in.readString();
        ssh_url = in.readString();
        forks_url = in.readString();
        keys_url = in.readString();
        collaborators_url = in.readString();
        hooks_url = in.readString();
        branches_url = in.readString();
        tags_url = in.readString();
        blobs_url = in.readString();
        stargazers_url = in.readString();
        contributors_url = in.readString();
        commits_url = in.readString();
        comments_url = in.readString();
        issue_comment_url = in.readString();
        issues_url = in.readString();
        pulls_url = in.readString();
        milestones_url = in.readString();
        notifications_url = in.readString();
        labels_url = in.readString();
        releases_url = in.readString();
        byte tmpRecommend = in.readByte();
        recommend = tmpRecommend == 0 ? null : tmpRecommend == 1;
        byte tmpGvp = in.readByte();
        gvp = tmpGvp == 0 ? null : tmpGvp == 1;
        homepage = in.readString();
        language = in.readString();
        if (in.readByte() == 0) {
            forks_count = null;
        } else {
            forks_count = in.readInt();
        }
        if (in.readByte() == 0) {
            stargazers_count = null;
        } else {
            stargazers_count = in.readInt();
        }
        if (in.readByte() == 0) {
            watchers_count = null;
        } else {
            watchers_count = in.readInt();
        }
        default_branch = in.readString();
        if (in.readByte() == 0) {
            open_issues_count = null;
        } else {
            open_issues_count = in.readInt();
        }
        byte tmpHas_issues = in.readByte();
        has_issues = tmpHas_issues == 0 ? null : tmpHas_issues == 1;
        byte tmpHas_wiki = in.readByte();
        has_wiki = tmpHas_wiki == 0 ? null : tmpHas_wiki == 1;
        byte tmpIssue_comment = in.readByte();
        issue_comment = tmpIssue_comment == 0 ? null : tmpIssue_comment == 1;
        byte tmpCan_comment = in.readByte();
        can_comment = tmpCan_comment == 0 ? null : tmpCan_comment == 1;
        byte tmpPull_requests_enabled = in.readByte();
        pull_requests_enabled = tmpPull_requests_enabled == 0 ? null : tmpPull_requests_enabled == 1;
        byte tmpHas_page = in.readByte();
        has_page = tmpHas_page == 0 ? null : tmpHas_page == 1;
        byte tmpOutsourced = in.readByte();
        outsourced = tmpOutsourced == 0 ? null : tmpOutsourced == 1;
        project_creator = in.readString();
        members = in.createStringArrayList();
        pushed_at = in.readString();
        created_at = in.readString();
        updated_at = in.readString();
        if (in.readByte() == 0) {
            assignees_number = null;
        } else {
            assignees_number = in.readInt();
        }
        if (in.readByte() == 0) {
            testers_number = null;
        } else {
            testers_number = in.readInt();
        }
        status = in.readString();
        issue_template_source = in.readString();
    }

    public static final Creator<RepositoryBean> CREATOR = new Creator<RepositoryBean>() {
        @Override
        public RepositoryBean createFromParcel(Parcel in) {
            return new RepositoryBean(in);
        }

        @Override
        public RepositoryBean[] newArray(int size) {
            return new RepositoryBean[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getHuman_name() {
        return human_name;
    }

    public void setHuman_name(String human_name) {
        this.human_name = human_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public NamespaceDTO getNamespace() {
        return namespace;
    }

    public void setNamespace(NamespaceDTO namespace) {
        this.namespace = namespace;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OwnerDTO getOwner() {
        return owner;
    }

    public void setOwner(OwnerDTO owner) {
        this.owner = owner;
    }

    public AssignerDTO getAssigner() {
        return assigner;
    }

    public void setAssigner(AssignerDTO assigner) {
        this.assigner = assigner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPrivateX() {
        return privateX;
    }

    public void setPrivateX(Boolean privateX) {
        this.privateX = privateX;
    }

    public Boolean getPublicX() {
        return publicX;
    }

    public void setPublicX(Boolean publicX) {
        this.publicX = publicX;
    }

    public Boolean getInternal() {
        return internal;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
    }

    public Boolean getFork() {
        return fork;
    }

    public void setFork(Boolean fork) {
        this.fork = fork;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getSsh_url() {
        return ssh_url;
    }

    public void setSsh_url(String ssh_url) {
        this.ssh_url = ssh_url;
    }

    public String getForks_url() {
        return forks_url;
    }

    public void setForks_url(String forks_url) {
        this.forks_url = forks_url;
    }

    public String getKeys_url() {
        return keys_url;
    }

    public void setKeys_url(String keys_url) {
        this.keys_url = keys_url;
    }

    public String getCollaborators_url() {
        return collaborators_url;
    }

    public void setCollaborators_url(String collaborators_url) {
        this.collaborators_url = collaborators_url;
    }

    public String getHooks_url() {
        return hooks_url;
    }

    public void setHooks_url(String hooks_url) {
        this.hooks_url = hooks_url;
    }

    public String getBranches_url() {
        return branches_url;
    }

    public void setBranches_url(String branches_url) {
        this.branches_url = branches_url;
    }

    public String getTags_url() {
        return tags_url;
    }

    public void setTags_url(String tags_url) {
        this.tags_url = tags_url;
    }

    public String getBlobs_url() {
        return blobs_url;
    }

    public void setBlobs_url(String blobs_url) {
        this.blobs_url = blobs_url;
    }

    public String getStargazers_url() {
        return stargazers_url;
    }

    public void setStargazers_url(String stargazers_url) {
        this.stargazers_url = stargazers_url;
    }

    public String getContributors_url() {
        return contributors_url;
    }

    public void setContributors_url(String contributors_url) {
        this.contributors_url = contributors_url;
    }

    public String getCommits_url() {
        return commits_url;
    }

    public void setCommits_url(String commits_url) {
        this.commits_url = commits_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public String getIssue_comment_url() {
        return issue_comment_url;
    }

    public void setIssue_comment_url(String issue_comment_url) {
        this.issue_comment_url = issue_comment_url;
    }

    public String getIssues_url() {
        return issues_url;
    }

    public void setIssues_url(String issues_url) {
        this.issues_url = issues_url;
    }

    public String getPulls_url() {
        return pulls_url;
    }

    public void setPulls_url(String pulls_url) {
        this.pulls_url = pulls_url;
    }

    public String getMilestones_url() {
        return milestones_url;
    }

    public void setMilestones_url(String milestones_url) {
        this.milestones_url = milestones_url;
    }

    public String getNotifications_url() {
        return notifications_url;
    }

    public void setNotifications_url(String notifications_url) {
        this.notifications_url = notifications_url;
    }

    public String getLabels_url() {
        return labels_url;
    }

    public void setLabels_url(String labels_url) {
        this.labels_url = labels_url;
    }

    public String getReleases_url() {
        return releases_url;
    }

    public void setReleases_url(String releases_url) {
        this.releases_url = releases_url;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public Boolean getGvp() {
        return gvp;
    }

    public void setGvp(Boolean gvp) {
        this.gvp = gvp;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getForks_count() {
        return forks_count;
    }

    public void setForks_count(Integer forks_count) {
        this.forks_count = forks_count;
    }

    public Integer getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(Integer stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public Integer getWatchers_count() {
        return watchers_count;
    }

    public void setWatchers_count(Integer watchers_count) {
        this.watchers_count = watchers_count;
    }

    public String getDefault_branch() {
        return default_branch;
    }

    public void setDefault_branch(String default_branch) {
        this.default_branch = default_branch;
    }

    public Integer getOpen_issues_count() {
        return open_issues_count;
    }

    public void setOpen_issues_count(Integer open_issues_count) {
        this.open_issues_count = open_issues_count;
    }

    public Boolean getHas_issues() {
        return has_issues;
    }

    public void setHas_issues(Boolean has_issues) {
        this.has_issues = has_issues;
    }

    public Boolean getHas_wiki() {
        return has_wiki;
    }

    public void setHas_wiki(Boolean has_wiki) {
        this.has_wiki = has_wiki;
    }

    public Boolean getIssue_comment() {
        return issue_comment;
    }

    public void setIssue_comment(Boolean issue_comment) {
        this.issue_comment = issue_comment;
    }

    public Boolean getCan_comment() {
        return can_comment;
    }

    public void setCan_comment(Boolean can_comment) {
        this.can_comment = can_comment;
    }

    public Boolean getPull_requests_enabled() {
        return pull_requests_enabled;
    }

    public void setPull_requests_enabled(Boolean pull_requests_enabled) {
        this.pull_requests_enabled = pull_requests_enabled;
    }

    public Boolean getHas_page() {
        return has_page;
    }

    public void setHas_page(Boolean has_page) {
        this.has_page = has_page;
    }

    public Object getLicense() {
        return license;
    }

    public void setLicense(Object license) {
        this.license = license;
    }

    public Boolean getOutsourced() {
        return outsourced;
    }

    public void setOutsourced(Boolean outsourced) {
        this.outsourced = outsourced;
    }

    public String getProject_creator() {
        return project_creator;
    }

    public void setProject_creator(String project_creator) {
        this.project_creator = project_creator;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public String getPushed_at() {
        return pushed_at;
    }

    public void setPushed_at(String pushed_at) {
        this.pushed_at = pushed_at;
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

    public Object getParent() {
        return parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }

    public Object getPaas() {
        return paas;
    }

    public void setPaas(Object paas) {
        this.paas = paas;
    }

    public Integer getAssignees_number() {
        return assignees_number;
    }

    public void setAssignees_number(Integer assignees_number) {
        this.assignees_number = assignees_number;
    }

    public Integer getTesters_number() {
        return testers_number;
    }

    public void setTesters_number(Integer testers_number) {
        this.testers_number = testers_number;
    }

    public List<AssigneeDTO> getAssignee() {
        return assignee;
    }

    public void setAssignee(List<AssigneeDTO> assignee) {
        this.assignee = assignee;
    }

    public List<TestersDTO> getTesters() {
        return testers;
    }

    public void setTesters(List<TestersDTO> testers) {
        this.testers = testers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<?> getPrograms() {
        return programs;
    }

    public void setPrograms(List<?> programs) {
        this.programs = programs;
    }

    public Object getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Object enterprise) {
        this.enterprise = enterprise;
    }

    public List<?> getProject_labels() {
        return project_labels;
    }

    public void setProject_labels(List<?> project_labels) {
        this.project_labels = project_labels;
    }

    public String getIssue_template_source() {
        return issue_template_source;
    }

    public void setIssue_template_source(String issue_template_source) {
        this.issue_template_source = issue_template_source;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(full_name);
        dest.writeString(human_name);
        dest.writeString(url);
        dest.writeString(path);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeByte((byte) (privateX == null ? 0 : privateX ? 1 : 2));
        dest.writeByte((byte) (publicX == null ? 0 : publicX ? 1 : 2));
        dest.writeByte((byte) (internal == null ? 0 : internal ? 1 : 2));
        dest.writeByte((byte) (fork == null ? 0 : fork ? 1 : 2));
        dest.writeString(html_url);
        dest.writeString(ssh_url);
        dest.writeString(forks_url);
        dest.writeString(keys_url);
        dest.writeString(collaborators_url);
        dest.writeString(hooks_url);
        dest.writeString(branches_url);
        dest.writeString(tags_url);
        dest.writeString(blobs_url);
        dest.writeString(stargazers_url);
        dest.writeString(contributors_url);
        dest.writeString(commits_url);
        dest.writeString(comments_url);
        dest.writeString(issue_comment_url);
        dest.writeString(issues_url);
        dest.writeString(pulls_url);
        dest.writeString(milestones_url);
        dest.writeString(notifications_url);
        dest.writeString(labels_url);
        dest.writeString(releases_url);
        dest.writeByte((byte) (recommend == null ? 0 : recommend ? 1 : 2));
        dest.writeByte((byte) (gvp == null ? 0 : gvp ? 1 : 2));
        dest.writeString(homepage);
        dest.writeString(language);
        if (forks_count == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(forks_count);
        }
        if (stargazers_count == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(stargazers_count);
        }
        if (watchers_count == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(watchers_count);
        }
        dest.writeString(default_branch);
        if (open_issues_count == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(open_issues_count);
        }
        dest.writeByte((byte) (has_issues == null ? 0 : has_issues ? 1 : 2));
        dest.writeByte((byte) (has_wiki == null ? 0 : has_wiki ? 1 : 2));
        dest.writeByte((byte) (issue_comment == null ? 0 : issue_comment ? 1 : 2));
        dest.writeByte((byte) (can_comment == null ? 0 : can_comment ? 1 : 2));
        dest.writeByte((byte) (pull_requests_enabled == null ? 0 : pull_requests_enabled ? 1 : 2));
        dest.writeByte((byte) (has_page == null ? 0 : has_page ? 1 : 2));
        dest.writeByte((byte) (outsourced == null ? 0 : outsourced ? 1 : 2));
        dest.writeString(project_creator);
        dest.writeStringList(members);
        dest.writeString(pushed_at);
        dest.writeString(created_at);
        dest.writeString(updated_at);
        if (assignees_number == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(assignees_number);
        }
        if (testers_number == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(testers_number);
        }
        dest.writeString(status);
        dest.writeString(issue_template_source);
    }

    public static class NamespaceDTO implements Parcelable {
        private Integer id;
        private String type;
        private String name;
        private String path;
        private String html_url;

        protected NamespaceDTO(Parcel in) {
            if (in.readByte() == 0) {
                id = null;
            } else {
                id = in.readInt();
            }
            type = in.readString();
            name = in.readString();
            path = in.readString();
            html_url = in.readString();
        }

        public static final Creator<NamespaceDTO> CREATOR = new Creator<NamespaceDTO>() {
            @Override
            public NamespaceDTO createFromParcel(Parcel in) {
                return new NamespaceDTO(in);
            }

            @Override
            public NamespaceDTO[] newArray(int size) {
                return new NamespaceDTO[size];
            }
        };

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getHtml_url() {
            return html_url;
        }

        public void setHtml_url(String html_url) {
            this.html_url = html_url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
            if (id == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(id);
            }
            dest.writeString(type);
            dest.writeString(name);
            dest.writeString(path);
            dest.writeString(html_url);
        }
    }

    public static class OwnerDTO implements Parcelable {
        private Integer id;
        private String login;
        private String name;
        private String avatar_url;
        private String url;
        private String html_url;
        private String remark;
        private String followers_url;
        private String following_url;
        private String gists_url;
        private String starred_url;
        private String subscriptions_url;
        private String organizations_url;
        private String repos_url;
        private String events_url;
        private String received_events_url;
        private String type;

        protected OwnerDTO(Parcel in) {
            if (in.readByte() == 0) {
                id = null;
            } else {
                id = in.readInt();
            }
            login = in.readString();
            name = in.readString();
            avatar_url = in.readString();
            url = in.readString();
            html_url = in.readString();
            remark = in.readString();
            followers_url = in.readString();
            following_url = in.readString();
            gists_url = in.readString();
            starred_url = in.readString();
            subscriptions_url = in.readString();
            organizations_url = in.readString();
            repos_url = in.readString();
            events_url = in.readString();
            received_events_url = in.readString();
            type = in.readString();
        }

        public static final Creator<OwnerDTO> CREATOR = new Creator<OwnerDTO>() {
            @Override
            public OwnerDTO createFromParcel(Parcel in) {
                return new OwnerDTO(in);
            }

            @Override
            public OwnerDTO[] newArray(int size) {
                return new OwnerDTO[size];
            }
        };

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getFollowers_url() {
            return followers_url;
        }

        public void setFollowers_url(String followers_url) {
            this.followers_url = followers_url;
        }

        public String getFollowing_url() {
            return following_url;
        }

        public void setFollowing_url(String following_url) {
            this.following_url = following_url;
        }

        public String getGists_url() {
            return gists_url;
        }

        public void setGists_url(String gists_url) {
            this.gists_url = gists_url;
        }

        public String getStarred_url() {
            return starred_url;
        }

        public void setStarred_url(String starred_url) {
            this.starred_url = starred_url;
        }

        public String getSubscriptions_url() {
            return subscriptions_url;
        }

        public void setSubscriptions_url(String subscriptions_url) {
            this.subscriptions_url = subscriptions_url;
        }

        public String getOrganizations_url() {
            return organizations_url;
        }

        public void setOrganizations_url(String organizations_url) {
            this.organizations_url = organizations_url;
        }

        public String getRepos_url() {
            return repos_url;
        }

        public void setRepos_url(String repos_url) {
            this.repos_url = repos_url;
        }

        public String getEvents_url() {
            return events_url;
        }

        public void setEvents_url(String events_url) {
            this.events_url = events_url;
        }

        public String getReceived_events_url() {
            return received_events_url;
        }

        public void setReceived_events_url(String received_events_url) {
            this.received_events_url = received_events_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
            if (id == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(id);
            }
            dest.writeString(login);
            dest.writeString(name);
            dest.writeString(avatar_url);
            dest.writeString(url);
            dest.writeString(html_url);
            dest.writeString(remark);
            dest.writeString(followers_url);
            dest.writeString(following_url);
            dest.writeString(gists_url);
            dest.writeString(starred_url);
            dest.writeString(subscriptions_url);
            dest.writeString(organizations_url);
            dest.writeString(repos_url);
            dest.writeString(events_url);
            dest.writeString(received_events_url);
            dest.writeString(type);
        }
    }

    public static class AssignerDTO implements Parcelable {
        private Integer id;
        private String login;
        private String name;
        private String avatar_url;
        private String url;
        private String html_url;
        private String remark;
        private String followers_url;
        private String following_url;
        private String gists_url;
        private String starred_url;
        private String subscriptions_url;
        private String organizations_url;
        private String repos_url;
        private String events_url;
        private String received_events_url;
        private String type;

        protected AssignerDTO(Parcel in) {
            if (in.readByte() == 0) {
                id = null;
            } else {
                id = in.readInt();
            }
            login = in.readString();
            name = in.readString();
            avatar_url = in.readString();
            url = in.readString();
            html_url = in.readString();
            remark = in.readString();
            followers_url = in.readString();
            following_url = in.readString();
            gists_url = in.readString();
            starred_url = in.readString();
            subscriptions_url = in.readString();
            organizations_url = in.readString();
            repos_url = in.readString();
            events_url = in.readString();
            received_events_url = in.readString();
            type = in.readString();
        }

        public static final Creator<AssignerDTO> CREATOR = new Creator<AssignerDTO>() {
            @Override
            public AssignerDTO createFromParcel(Parcel in) {
                return new AssignerDTO(in);
            }

            @Override
            public AssignerDTO[] newArray(int size) {
                return new AssignerDTO[size];
            }
        };

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getFollowers_url() {
            return followers_url;
        }

        public void setFollowers_url(String followers_url) {
            this.followers_url = followers_url;
        }

        public String getFollowing_url() {
            return following_url;
        }

        public void setFollowing_url(String following_url) {
            this.following_url = following_url;
        }

        public String getGists_url() {
            return gists_url;
        }

        public void setGists_url(String gists_url) {
            this.gists_url = gists_url;
        }

        public String getStarred_url() {
            return starred_url;
        }

        public void setStarred_url(String starred_url) {
            this.starred_url = starred_url;
        }

        public String getSubscriptions_url() {
            return subscriptions_url;
        }

        public void setSubscriptions_url(String subscriptions_url) {
            this.subscriptions_url = subscriptions_url;
        }

        public String getOrganizations_url() {
            return organizations_url;
        }

        public void setOrganizations_url(String organizations_url) {
            this.organizations_url = organizations_url;
        }

        public String getRepos_url() {
            return repos_url;
        }

        public void setRepos_url(String repos_url) {
            this.repos_url = repos_url;
        }

        public String getEvents_url() {
            return events_url;
        }

        public void setEvents_url(String events_url) {
            this.events_url = events_url;
        }

        public String getReceived_events_url() {
            return received_events_url;
        }

        public void setReceived_events_url(String received_events_url) {
            this.received_events_url = received_events_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
            if (id == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(id);
            }
            dest.writeString(login);
            dest.writeString(name);
            dest.writeString(avatar_url);
            dest.writeString(url);
            dest.writeString(html_url);
            dest.writeString(remark);
            dest.writeString(followers_url);
            dest.writeString(following_url);
            dest.writeString(gists_url);
            dest.writeString(starred_url);
            dest.writeString(subscriptions_url);
            dest.writeString(organizations_url);
            dest.writeString(repos_url);
            dest.writeString(events_url);
            dest.writeString(received_events_url);
            dest.writeString(type);
        }
    }

    public static class AssigneeDTO implements Parcelable {
        private Integer id;
        private String login;
        private String name;
        private String avatar_url;
        private String url;
        private String html_url;
        private String remark;
        private String followers_url;
        private String following_url;
        private String gists_url;
        private String starred_url;
        private String subscriptions_url;
        private String organizations_url;
        private String repos_url;
        private String events_url;
        private String received_events_url;
        private String type;

        protected AssigneeDTO(Parcel in) {
            if (in.readByte() == 0) {
                id = null;
            } else {
                id = in.readInt();
            }
            login = in.readString();
            name = in.readString();
            avatar_url = in.readString();
            url = in.readString();
            html_url = in.readString();
            remark = in.readString();
            followers_url = in.readString();
            following_url = in.readString();
            gists_url = in.readString();
            starred_url = in.readString();
            subscriptions_url = in.readString();
            organizations_url = in.readString();
            repos_url = in.readString();
            events_url = in.readString();
            received_events_url = in.readString();
            type = in.readString();
        }

        public static final Creator<AssigneeDTO> CREATOR = new Creator<AssigneeDTO>() {
            @Override
            public AssigneeDTO createFromParcel(Parcel in) {
                return new AssigneeDTO(in);
            }

            @Override
            public AssigneeDTO[] newArray(int size) {
                return new AssigneeDTO[size];
            }
        };

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getFollowers_url() {
            return followers_url;
        }

        public void setFollowers_url(String followers_url) {
            this.followers_url = followers_url;
        }

        public String getFollowing_url() {
            return following_url;
        }

        public void setFollowing_url(String following_url) {
            this.following_url = following_url;
        }

        public String getGists_url() {
            return gists_url;
        }

        public void setGists_url(String gists_url) {
            this.gists_url = gists_url;
        }

        public String getStarred_url() {
            return starred_url;
        }

        public void setStarred_url(String starred_url) {
            this.starred_url = starred_url;
        }

        public String getSubscriptions_url() {
            return subscriptions_url;
        }

        public void setSubscriptions_url(String subscriptions_url) {
            this.subscriptions_url = subscriptions_url;
        }

        public String getOrganizations_url() {
            return organizations_url;
        }

        public void setOrganizations_url(String organizations_url) {
            this.organizations_url = organizations_url;
        }

        public String getRepos_url() {
            return repos_url;
        }

        public void setRepos_url(String repos_url) {
            this.repos_url = repos_url;
        }

        public String getEvents_url() {
            return events_url;
        }

        public void setEvents_url(String events_url) {
            this.events_url = events_url;
        }

        public String getReceived_events_url() {
            return received_events_url;
        }

        public void setReceived_events_url(String received_events_url) {
            this.received_events_url = received_events_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
            if (id == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(id);
            }
            dest.writeString(login);
            dest.writeString(name);
            dest.writeString(avatar_url);
            dest.writeString(url);
            dest.writeString(html_url);
            dest.writeString(remark);
            dest.writeString(followers_url);
            dest.writeString(following_url);
            dest.writeString(gists_url);
            dest.writeString(starred_url);
            dest.writeString(subscriptions_url);
            dest.writeString(organizations_url);
            dest.writeString(repos_url);
            dest.writeString(events_url);
            dest.writeString(received_events_url);
            dest.writeString(type);
        }
    }

    public static class TestersDTO implements Parcelable {
        private Integer id;
        private String login;
        private String name;
        private String avatar_url;
        private String url;
        private String html_url;
        private String remark;
        private String followers_url;
        private String following_url;
        private String gists_url;
        private String starred_url;
        private String subscriptions_url;
        private String organizations_url;
        private String repos_url;
        private String events_url;
        private String received_events_url;
        private String type;

        protected TestersDTO(Parcel in) {
            if (in.readByte() == 0) {
                id = null;
            } else {
                id = in.readInt();
            }
            login = in.readString();
            name = in.readString();
            avatar_url = in.readString();
            url = in.readString();
            html_url = in.readString();
            remark = in.readString();
            followers_url = in.readString();
            following_url = in.readString();
            gists_url = in.readString();
            starred_url = in.readString();
            subscriptions_url = in.readString();
            organizations_url = in.readString();
            repos_url = in.readString();
            events_url = in.readString();
            received_events_url = in.readString();
            type = in.readString();
        }

        public static final Creator<TestersDTO> CREATOR = new Creator<TestersDTO>() {
            @Override
            public TestersDTO createFromParcel(Parcel in) {
                return new TestersDTO(in);
            }

            @Override
            public TestersDTO[] newArray(int size) {
                return new TestersDTO[size];
            }
        };

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getFollowers_url() {
            return followers_url;
        }

        public void setFollowers_url(String followers_url) {
            this.followers_url = followers_url;
        }

        public String getFollowing_url() {
            return following_url;
        }

        public void setFollowing_url(String following_url) {
            this.following_url = following_url;
        }

        public String getGists_url() {
            return gists_url;
        }

        public void setGists_url(String gists_url) {
            this.gists_url = gists_url;
        }

        public String getStarred_url() {
            return starred_url;
        }

        public void setStarred_url(String starred_url) {
            this.starred_url = starred_url;
        }

        public String getSubscriptions_url() {
            return subscriptions_url;
        }

        public void setSubscriptions_url(String subscriptions_url) {
            this.subscriptions_url = subscriptions_url;
        }

        public String getOrganizations_url() {
            return organizations_url;
        }

        public void setOrganizations_url(String organizations_url) {
            this.organizations_url = organizations_url;
        }

        public String getRepos_url() {
            return repos_url;
        }

        public void setRepos_url(String repos_url) {
            this.repos_url = repos_url;
        }

        public String getEvents_url() {
            return events_url;
        }

        public void setEvents_url(String events_url) {
            this.events_url = events_url;
        }

        public String getReceived_events_url() {
            return received_events_url;
        }

        public void setReceived_events_url(String received_events_url) {
            this.received_events_url = received_events_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
            if (id == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(id);
            }
            dest.writeString(login);
            dest.writeString(name);
            dest.writeString(avatar_url);
            dest.writeString(url);
            dest.writeString(html_url);
            dest.writeString(remark);
            dest.writeString(followers_url);
            dest.writeString(following_url);
            dest.writeString(gists_url);
            dest.writeString(starred_url);
            dest.writeString(subscriptions_url);
            dest.writeString(organizations_url);
            dest.writeString(repos_url);
            dest.writeString(events_url);
            dest.writeString(received_events_url);
            dest.writeString(type);
        }
    }
}
