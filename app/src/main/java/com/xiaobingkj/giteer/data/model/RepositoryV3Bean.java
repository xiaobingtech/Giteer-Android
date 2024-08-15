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

import com.google.gson.annotations.SerializedName;

public class RepositoryV3Bean {

    private Integer id;
    private String name;
    private String default_branch;
    private String description;
    private OwnerDTO owner;
    @SerializedName("public")
    private Boolean publicX;
    private String path;
    private String path_with_namespace;
    private String name_with_namespace;
    private Boolean issues_enabled;
    private Boolean pull_requests_enabled;
    private Boolean wiki_enabled;
    private String created_at;
    private Boolean is_pull_request_readonly;
    private NamespaceDTO namespace;
    private String last_push_at;
    private Integer parent_id;
    @SerializedName("fork?")
    private Boolean _$Fork127;// FIXME check this code
    private Integer forks_count;
    private Integer stars_count;
    private Integer watches_count;
    private String language;
    private Object paas;
    private Object stared;
    private Object watched;
    private Object relation;
    private Integer recomm;
    private Object parent_path_with_namespace;
    private Boolean project_is_started;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefault_branch() {
        return default_branch;
    }

    public void setDefault_branch(String default_branch) {
        this.default_branch = default_branch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OwnerDTO getOwner() {
        return owner;
    }

    public void setOwner(OwnerDTO owner) {
        this.owner = owner;
    }

    public Boolean getPublicX() {
        return publicX;
    }

    public void setPublicX(Boolean publicX) {
        this.publicX = publicX;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath_with_namespace() {
        return path_with_namespace;
    }

    public void setPath_with_namespace(String path_with_namespace) {
        this.path_with_namespace = path_with_namespace;
    }

    public String getName_with_namespace() {
        return name_with_namespace;
    }

    public void setName_with_namespace(String name_with_namespace) {
        this.name_with_namespace = name_with_namespace;
    }

    public Boolean getIssues_enabled() {
        return issues_enabled;
    }

    public void setIssues_enabled(Boolean issues_enabled) {
        this.issues_enabled = issues_enabled;
    }

    public Boolean getPull_requests_enabled() {
        return pull_requests_enabled;
    }

    public void setPull_requests_enabled(Boolean pull_requests_enabled) {
        this.pull_requests_enabled = pull_requests_enabled;
    }

    public Boolean getWiki_enabled() {
        return wiki_enabled;
    }

    public void setWiki_enabled(Boolean wiki_enabled) {
        this.wiki_enabled = wiki_enabled;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Boolean getIs_pull_request_readonly() {
        return is_pull_request_readonly;
    }

    public void setIs_pull_request_readonly(Boolean is_pull_request_readonly) {
        this.is_pull_request_readonly = is_pull_request_readonly;
    }

    public NamespaceDTO getNamespace() {
        return namespace;
    }

    public void setNamespace(NamespaceDTO namespace) {
        this.namespace = namespace;
    }

    public String getLast_push_at() {
        return last_push_at;
    }

    public void setLast_push_at(String last_push_at) {
        this.last_push_at = last_push_at;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Boolean get_$Fork127() {
        return _$Fork127;
    }

    public void set_$Fork127(Boolean _$Fork127) {
        this._$Fork127 = _$Fork127;
    }

    public Integer getForks_count() {
        return forks_count;
    }

    public void setForks_count(Integer forks_count) {
        this.forks_count = forks_count;
    }

    public Integer getStars_count() {
        return stars_count;
    }

    public void setStars_count(Integer stars_count) {
        this.stars_count = stars_count;
    }

    public Integer getWatches_count() {
        return watches_count;
    }

    public void setWatches_count(Integer watches_count) {
        this.watches_count = watches_count;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Object getPaas() {
        return paas;
    }

    public void setPaas(Object paas) {
        this.paas = paas;
    }

    public Object getStared() {
        return stared;
    }

    public void setStared(Object stared) {
        this.stared = stared;
    }

    public Object getWatched() {
        return watched;
    }

    public void setWatched(Object watched) {
        this.watched = watched;
    }

    public Object getRelation() {
        return relation;
    }

    public void setRelation(Object relation) {
        this.relation = relation;
    }

    public Integer getRecomm() {
        return recomm;
    }

    public void setRecomm(Integer recomm) {
        this.recomm = recomm;
    }

    public Object getParent_path_with_namespace() {
        return parent_path_with_namespace;
    }

    public void setParent_path_with_namespace(Object parent_path_with_namespace) {
        this.parent_path_with_namespace = parent_path_with_namespace;
    }

    public Boolean getProject_is_started() {
        return project_is_started;
    }

    public void setProject_is_started(Boolean project_is_started) {
        this.project_is_started = project_is_started;
    }

    public static class OwnerDTO {
        private Integer id;
        private String username;
        private String state;
        private String created_at;
        private String portrait_url;
        private String email;
        private String name;
        private String new_portrait;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getPortrait_url() {
            return portrait_url;
        }

        public void setPortrait_url(String portrait_url) {
            this.portrait_url = portrait_url;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNew_portrait() {
            return new_portrait;
        }

        public void setNew_portrait(String new_portrait) {
            this.new_portrait = new_portrait;
        }
    }

    public static class NamespaceDTO {
        private Integer id;
        private String name;
        private String path;
        private Integer owner_id;
        private String created_at;
        private String updated_at;
        private String description;
        private String address;
        private Object email;
        private Object url;
        private Object location;
        @SerializedName("public")
        private Object publicX;
        private Integer enterprise_id;
        private Integer level;
        private Object from;
        private Boolean outsourced;
        private String initial_branch;
        private String complete_path;
        private String complete_name;
        private Integer parent_id;
        private Object avatar;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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

        public Integer getOwner_id() {
            return owner_id;
        }

        public void setOwner_id(Integer owner_id) {
            this.owner_id = owner_id;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getUrl() {
            return url;
        }

        public void setUrl(Object url) {
            this.url = url;
        }

        public Object getLocation() {
            return location;
        }

        public void setLocation(Object location) {
            this.location = location;
        }

        public Object getPublicX() {
            return publicX;
        }

        public void setPublicX(Object publicX) {
            this.publicX = publicX;
        }

        public Integer getEnterprise_id() {
            return enterprise_id;
        }

        public void setEnterprise_id(Integer enterprise_id) {
            this.enterprise_id = enterprise_id;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public Object getFrom() {
            return from;
        }

        public void setFrom(Object from) {
            this.from = from;
        }

        public Boolean getOutsourced() {
            return outsourced;
        }

        public void setOutsourced(Boolean outsourced) {
            this.outsourced = outsourced;
        }

        public String getInitial_branch() {
            return initial_branch;
        }

        public void setInitial_branch(String initial_branch) {
            this.initial_branch = initial_branch;
        }

        public String getComplete_path() {
            return complete_path;
        }

        public void setComplete_path(String complete_path) {
            this.complete_path = complete_path;
        }

        public String getComplete_name() {
            return complete_name;
        }

        public void setComplete_name(String complete_name) {
            this.complete_name = complete_name;
        }

        public Integer getParent_id() {
            return parent_id;
        }

        public void setParent_id(Integer parent_id) {
            this.parent_id = parent_id;
        }

        public Object getAvatar() {
            return avatar;
        }

        public void setAvatar(Object avatar) {
            this.avatar = avatar;
        }
    }
}
