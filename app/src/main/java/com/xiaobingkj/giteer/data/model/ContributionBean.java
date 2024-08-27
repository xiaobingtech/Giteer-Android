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

import java.util.List;

public class ContributionBean {

    private int status;
    private DataDTO data;
    private Object message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public static class DataDTO {
        private ContributionCalendarDTO contribution_calendar;
        private PinnedProjectsDTO pinned_projects;
        private ProjectsDTO projects;
        private GroupsDTO groups;
        private CodesDTO codes;

        public ContributionCalendarDTO getContribution_calendar() {
            return contribution_calendar;
        }

        public void setContribution_calendar(ContributionCalendarDTO contribution_calendar) {
            this.contribution_calendar = contribution_calendar;
        }

        public PinnedProjectsDTO getPinned_projects() {
            return pinned_projects;
        }

        public void setPinned_projects(PinnedProjectsDTO pinned_projects) {
            this.pinned_projects = pinned_projects;
        }

        public ProjectsDTO getProjects() {
            return projects;
        }

        public void setProjects(ProjectsDTO projects) {
            this.projects = projects;
        }

        public GroupsDTO getGroups() {
            return groups;
        }

        public void setGroups(GroupsDTO groups) {
            this.groups = groups;
        }

        public CodesDTO getCodes() {
            return codes;
        }

        public void setCodes(CodesDTO codes) {
            this.codes = codes;
        }

        public static class ContributionCalendarDTO {
            private List<YearStreakDTO> year_streak;
            private int current_streak;
            private int longest_streak;
            private int last_year_total;

            public List<YearStreakDTO> getYear_streak() {
                return year_streak;
            }

            public void setYear_streak(List<YearStreakDTO> year_streak) {
                this.year_streak = year_streak;
            }

            public int getCurrent_streak() {
                return current_streak;
            }

            public void setCurrent_streak(int current_streak) {
                this.current_streak = current_streak;
            }

            public int getLongest_streak() {
                return longest_streak;
            }

            public void setLongest_streak(int longest_streak) {
                this.longest_streak = longest_streak;
            }

            public int getLast_year_total() {
                return last_year_total;
            }

            public void setLast_year_total(int last_year_total) {
                this.last_year_total = last_year_total;
            }

            public static class YearStreakDTO {
                private int count;
                @SerializedName("class")
                private String classX;
                private String date;
                private int day;
                private int month;

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public String getClassX() {
                    return classX;
                }

                public void setClassX(String classX) {
                    this.classX = classX;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public int getMonth() {
                    return month;
                }

                public void setMonth(int month) {
                    this.month = month;
                }
            }
        }

        public static class PinnedProjectsDTO {
            private List<ItemsDTO> items;

            public List<ItemsDTO> getItems() {
                return items;
            }

            public void setItems(List<ItemsDTO> items) {
                this.items = items;
            }

            public static class ItemsDTO {
                private String path;
                private String name_with_namespace;
                @SerializedName("class")
                private String classX;
                private String icon_class;
                private boolean in_enterprise;
                private boolean in_education;
                @SerializedName("private")
                private boolean privateX;
                private int stars_count;
                private int forked_count;
                private int watches_count;
                private LanguageDTO language;
                private Object description;

                public String getPath() {
                    return path;
                }

                public void setPath(String path) {
                    this.path = path;
                }

                public String getName_with_namespace() {
                    return name_with_namespace;
                }

                public void setName_with_namespace(String name_with_namespace) {
                    this.name_with_namespace = name_with_namespace;
                }

                public String getClassX() {
                    return classX;
                }

                public void setClassX(String classX) {
                    this.classX = classX;
                }

                public String getIcon_class() {
                    return icon_class;
                }

                public void setIcon_class(String icon_class) {
                    this.icon_class = icon_class;
                }

                public boolean isIn_enterprise() {
                    return in_enterprise;
                }

                public void setIn_enterprise(boolean in_enterprise) {
                    this.in_enterprise = in_enterprise;
                }

                public boolean isIn_education() {
                    return in_education;
                }

                public void setIn_education(boolean in_education) {
                    this.in_education = in_education;
                }

                public boolean isPrivateX() {
                    return privateX;
                }

                public void setPrivateX(boolean privateX) {
                    this.privateX = privateX;
                }

                public int getStars_count() {
                    return stars_count;
                }

                public void setStars_count(int stars_count) {
                    this.stars_count = stars_count;
                }

                public int getForked_count() {
                    return forked_count;
                }

                public void setForked_count(int forked_count) {
                    this.forked_count = forked_count;
                }

                public int getWatches_count() {
                    return watches_count;
                }

                public void setWatches_count(int watches_count) {
                    this.watches_count = watches_count;
                }

                public LanguageDTO getLanguage() {
                    return language;
                }

                public void setLanguage(LanguageDTO language) {
                    this.language = language;
                }

                public Object getDescription() {
                    return description;
                }

                public void setDescription(Object description) {
                    this.description = description;
                }

                public static class LanguageDTO {
                    private int id;
                    private String name;
                    private String ident;
                    private String detail;
                    private int parent_id;
                    private int order;
                    private String created_at;
                    private String updated_at;
                    private int projects_count;
                    private int codes_order;
                    private int root_id;
                    private String name_zh_tw;
                    private String name_en;

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

                    public String getIdent() {
                        return ident;
                    }

                    public void setIdent(String ident) {
                        this.ident = ident;
                    }

                    public String getDetail() {
                        return detail;
                    }

                    public void setDetail(String detail) {
                        this.detail = detail;
                    }

                    public int getParent_id() {
                        return parent_id;
                    }

                    public void setParent_id(int parent_id) {
                        this.parent_id = parent_id;
                    }

                    public int getOrder() {
                        return order;
                    }

                    public void setOrder(int order) {
                        this.order = order;
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

                    public int getProjects_count() {
                        return projects_count;
                    }

                    public void setProjects_count(int projects_count) {
                        this.projects_count = projects_count;
                    }

                    public int getCodes_order() {
                        return codes_order;
                    }

                    public void setCodes_order(int codes_order) {
                        this.codes_order = codes_order;
                    }

                    public int getRoot_id() {
                        return root_id;
                    }

                    public void setRoot_id(int root_id) {
                        this.root_id = root_id;
                    }

                    public String getName_zh_tw() {
                        return name_zh_tw;
                    }

                    public void setName_zh_tw(String name_zh_tw) {
                        this.name_zh_tw = name_zh_tw;
                    }

                    public String getName_en() {
                        return name_en;
                    }

                    public void setName_en(String name_en) {
                        this.name_en = name_en;
                    }
                }
            }
        }

        public static class ProjectsDTO {
            private boolean has_more;
            private String projects_url;
            private int count;
            private List<ItemsDTO> items;

            public boolean isHas_more() {
                return has_more;
            }

            public void setHas_more(boolean has_more) {
                this.has_more = has_more;
            }

            public String getProjects_url() {
                return projects_url;
            }

            public void setProjects_url(String projects_url) {
                this.projects_url = projects_url;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public List<ItemsDTO> getItems() {
                return items;
            }

            public void setItems(List<ItemsDTO> items) {
                this.items = items;
            }

            public static class ItemsDTO {
                private String path;
                private String name_with_namespace;
                @SerializedName("class")
                private String classX;
                private String icon_class;
                private boolean in_enterprise;
                private boolean in_education;
                @SerializedName("private")
                private boolean privateX;
                private int stars_count;
                private int forked_count;
                private int watches_count;
                private Object language;
                private String description;

                public String getPath() {
                    return path;
                }

                public void setPath(String path) {
                    this.path = path;
                }

                public String getName_with_namespace() {
                    return name_with_namespace;
                }

                public void setName_with_namespace(String name_with_namespace) {
                    this.name_with_namespace = name_with_namespace;
                }

                public String getClassX() {
                    return classX;
                }

                public void setClassX(String classX) {
                    this.classX = classX;
                }

                public String getIcon_class() {
                    return icon_class;
                }

                public void setIcon_class(String icon_class) {
                    this.icon_class = icon_class;
                }

                public boolean isIn_enterprise() {
                    return in_enterprise;
                }

                public void setIn_enterprise(boolean in_enterprise) {
                    this.in_enterprise = in_enterprise;
                }

                public boolean isIn_education() {
                    return in_education;
                }

                public void setIn_education(boolean in_education) {
                    this.in_education = in_education;
                }

                public boolean isPrivateX() {
                    return privateX;
                }

                public void setPrivateX(boolean privateX) {
                    this.privateX = privateX;
                }

                public int getStars_count() {
                    return stars_count;
                }

                public void setStars_count(int stars_count) {
                    this.stars_count = stars_count;
                }

                public int getForked_count() {
                    return forked_count;
                }

                public void setForked_count(int forked_count) {
                    this.forked_count = forked_count;
                }

                public int getWatches_count() {
                    return watches_count;
                }

                public void setWatches_count(int watches_count) {
                    this.watches_count = watches_count;
                }

                public Object getLanguage() {
                    return language;
                }

                public void setLanguage(Object language) {
                    this.language = language;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }
            }
        }

        public static class GroupsDTO {
            private boolean has_more;
            private String groups_url;
            private int count;
            private List<ItemsDTO> items;

            public boolean isHas_more() {
                return has_more;
            }

            public void setHas_more(boolean has_more) {
                this.has_more = has_more;
            }

            public String getGroups_url() {
                return groups_url;
            }

            public void setGroups_url(String groups_url) {
                this.groups_url = groups_url;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public List<ItemsDTO> getItems() {
                return items;
            }

            public void setItems(List<ItemsDTO> items) {
                this.items = items;
            }

            public static class ItemsDTO {
                private String name;
                private String path;
                private String avatar_url;
                @SerializedName("class")
                private String classX;
                private boolean in_enterprise;
                private boolean in_education;
                private boolean is_public;
                private String current_user_role;

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

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }

                public String getClassX() {
                    return classX;
                }

                public void setClassX(String classX) {
                    this.classX = classX;
                }

                public boolean isIn_enterprise() {
                    return in_enterprise;
                }

                public void setIn_enterprise(boolean in_enterprise) {
                    this.in_enterprise = in_enterprise;
                }

                public boolean isIn_education() {
                    return in_education;
                }

                public void setIn_education(boolean in_education) {
                    this.in_education = in_education;
                }

                public boolean isIs_public() {
                    return is_public;
                }

                public void setIs_public(boolean is_public) {
                    this.is_public = is_public;
                }

                public String getCurrent_user_role() {
                    return current_user_role;
                }

                public void setCurrent_user_role(String current_user_role) {
                    this.current_user_role = current_user_role;
                }
            }
        }

        public static class CodesDTO {
            private boolean has_more;
            private String codes_url;
            private int count;
            private List<ItemsDTO> items;

            public boolean isHas_more() {
                return has_more;
            }

            public void setHas_more(boolean has_more) {
                this.has_more = has_more;
            }

            public String getCodes_url() {
                return codes_url;
            }

            public void setCodes_url(String codes_url) {
                this.codes_url = codes_url;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public List<ItemsDTO> getItems() {
                return items;
            }

            public void setItems(List<ItemsDTO> items) {
                this.items = items;
            }

            public static class ItemsDTO {
                private String path;
                @SerializedName("class")
                private String classX;
                private String name;
                @SerializedName("public")
                private boolean publicX;
                private int stars_count;

                public String getPath() {
                    return path;
                }

                public void setPath(String path) {
                    this.path = path;
                }

                public String getClassX() {
                    return classX;
                }

                public void setClassX(String classX) {
                    this.classX = classX;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public boolean isPublicX() {
                    return publicX;
                }

                public void setPublicX(boolean publicX) {
                    this.publicX = publicX;
                }

                public int getStars_count() {
                    return stars_count;
                }

                public void setStars_count(int stars_count) {
                    this.stars_count = stars_count;
                }
            }
        }
    }
}
