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

public class EventBean {

    private Integer id;
    private String type;
    private ActorDTO actor;
    private RepoDTO repo;
    @SerializedName("public")
    private Boolean publicX;
    private String created_at;
    private PayloadDTO payload;
    private OrgDTO org;

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

    public ActorDTO getActor() {
        return actor;
    }

    public void setActor(ActorDTO actor) {
        this.actor = actor;
    }

    public RepoDTO getRepo() {
        return repo;
    }

    public void setRepo(RepoDTO repo) {
        this.repo = repo;
    }

    public Boolean getPublicX() {
        return publicX;
    }

    public void setPublicX(Boolean publicX) {
        this.publicX = publicX;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public PayloadDTO getPayload() {
        return payload;
    }

    public void setPayload(PayloadDTO payload) {
        this.payload = payload;
    }

    public OrgDTO getOrg() {
        return org;
    }

    public void setOrg(OrgDTO org) {
        this.org = org;
    }

    public static class ActorDTO {
        private Integer id;
        private String login;
        private String name;
        private String avatar_url;
        private String url;
        private String html_url;
        private String remark;

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
    }

    public static class RepoDTO {
        private Integer id;
        private String full_name;
        private String human_name;
        private String url;
        private NamespaceDTO namespace;

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

        public static class NamespaceDTO {
            private Integer id;
            private String type;
            private String name;
            private String path;
            private String html_url;

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
        }
    }

    public static class PayloadDTO {
        private TargetDTO target;
        private String target_type;
        private boolean followed;
        private String action;
        private int id;
        private String url;
        private String html_url;
        private String diff_url;
        private String patch_url;
        private String issue_url;
        private String commits_url;
        private String review_comments_url;
        private String review_comment_url;
        private String comments_url;
        private String number;
        private int close_related_issue;
        private boolean prune_branch;
        private String state;
        private int assignees_number;
        private int testers_number;
        private List<AssigneesDTO> assignees;
        private List<TestersDTO> testers;
        private Object milestone;
        private List<?> labels;
        private boolean locked;
        private String created_at;
        private String updated_at;
        private Object closed_at;
        private boolean draft;
        private Object merged_at;
        private boolean mergeable;
        private boolean can_merge_check;
        private LinksDTO _links;
        private UserDTO user;
        private List<?> ref_pull_requests;
        private String title;
        private String body;
        private HeadDTO head;
        private BaseDTO base;
        private PullRequestDTO pull_request;
        private CommentDTO comment;
        private RepositoryDTO repository;
        private String ref_type;
        private String ref;
        private String default_branch;
        private Object description;
        private String before;
        private String after;
        private boolean created;
        private boolean deleted;
        private String compare;
        private int size;
        private List<CommitsDTO> commits;

        public TargetDTO getTarget() {
            return target;
        }

        public void setTarget(TargetDTO target) {
            this.target = target;
        }

        public String getTarget_type() {
            return target_type;
        }

        public void setTarget_type(String target_type) {
            this.target_type = target_type;
        }

        public boolean isFollowed() {
            return followed;
        }

        public void setFollowed(boolean followed) {
            this.followed = followed;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getDiff_url() {
            return diff_url;
        }

        public void setDiff_url(String diff_url) {
            this.diff_url = diff_url;
        }

        public String getPatch_url() {
            return patch_url;
        }

        public void setPatch_url(String patch_url) {
            this.patch_url = patch_url;
        }

        public String getIssue_url() {
            return issue_url;
        }

        public void setIssue_url(String issue_url) {
            this.issue_url = issue_url;
        }

        public String getCommits_url() {
            return commits_url;
        }

        public void setCommits_url(String commits_url) {
            this.commits_url = commits_url;
        }

        public String getReview_comments_url() {
            return review_comments_url;
        }

        public void setReview_comments_url(String review_comments_url) {
            this.review_comments_url = review_comments_url;
        }

        public String getReview_comment_url() {
            return review_comment_url;
        }

        public void setReview_comment_url(String review_comment_url) {
            this.review_comment_url = review_comment_url;
        }

        public String getComments_url() {
            return comments_url;
        }

        public void setComments_url(String comments_url) {
            this.comments_url = comments_url;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public int getClose_related_issue() {
            return close_related_issue;
        }

        public void setClose_related_issue(int close_related_issue) {
            this.close_related_issue = close_related_issue;
        }

        public boolean isPrune_branch() {
            return prune_branch;
        }

        public void setPrune_branch(boolean prune_branch) {
            this.prune_branch = prune_branch;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getAssignees_number() {
            return assignees_number;
        }

        public void setAssignees_number(int assignees_number) {
            this.assignees_number = assignees_number;
        }

        public int getTesters_number() {
            return testers_number;
        }

        public void setTesters_number(int testers_number) {
            this.testers_number = testers_number;
        }

        public List<AssigneesDTO> getAssignees() {
            return assignees;
        }

        public void setAssignees(List<AssigneesDTO> assignees) {
            this.assignees = assignees;
        }

        public List<TestersDTO> getTesters() {
            return testers;
        }

        public void setTesters(List<TestersDTO> testers) {
            this.testers = testers;
        }

        public Object getMilestone() {
            return milestone;
        }

        public void setMilestone(Object milestone) {
            this.milestone = milestone;
        }

        public List<?> getLabels() {
            return labels;
        }

        public void setLabels(List<?> labels) {
            this.labels = labels;
        }

        public boolean isLocked() {
            return locked;
        }

        public void setLocked(boolean locked) {
            this.locked = locked;
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

        public Object getClosed_at() {
            return closed_at;
        }

        public void setClosed_at(Object closed_at) {
            this.closed_at = closed_at;
        }

        public boolean isDraft() {
            return draft;
        }

        public void setDraft(boolean draft) {
            this.draft = draft;
        }

        public Object getMerged_at() {
            return merged_at;
        }

        public void setMerged_at(Object merged_at) {
            this.merged_at = merged_at;
        }

        public boolean isMergeable() {
            return mergeable;
        }

        public void setMergeable(boolean mergeable) {
            this.mergeable = mergeable;
        }

        public boolean isCan_merge_check() {
            return can_merge_check;
        }

        public void setCan_merge_check(boolean can_merge_check) {
            this.can_merge_check = can_merge_check;
        }

        public LinksDTO get_links() {
            return _links;
        }

        public void set_links(LinksDTO _links) {
            this._links = _links;
        }

        public UserDTO getUser() {
            return user;
        }

        public void setUser(UserDTO user) {
            this.user = user;
        }

        public List<?> getRef_pull_requests() {
            return ref_pull_requests;
        }

        public void setRef_pull_requests(List<?> ref_pull_requests) {
            this.ref_pull_requests = ref_pull_requests;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public HeadDTO getHead() {
            return head;
        }

        public void setHead(HeadDTO head) {
            this.head = head;
        }

        public BaseDTO getBase() {
            return base;
        }

        public void setBase(BaseDTO base) {
            this.base = base;
        }

        public PullRequestDTO getPull_request() {
            return pull_request;
        }

        public void setPull_request(PullRequestDTO pull_request) {
            this.pull_request = pull_request;
        }

        public CommentDTO getComment() {
            return comment;
        }

        public void setComment(CommentDTO comment) {
            this.comment = comment;
        }

        public RepositoryDTO getRepository() {
            return repository;
        }

        public void setRepository(RepositoryDTO repository) {
            this.repository = repository;
        }

        public String getRef_type() {
            return ref_type;
        }

        public void setRef_type(String ref_type) {
            this.ref_type = ref_type;
        }

        public String getRef() {
            return ref;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }

        public String getDefault_branch() {
            return default_branch;
        }

        public void setDefault_branch(String default_branch) {
            this.default_branch = default_branch;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public String getBefore() {
            return before;
        }

        public void setBefore(String before) {
            this.before = before;
        }

        public String getAfter() {
            return after;
        }

        public void setAfter(String after) {
            this.after = after;
        }

        public boolean isCreated() {
            return created;
        }

        public void setCreated(boolean created) {
            this.created = created;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public String getCompare() {
            return compare;
        }

        public void setCompare(String compare) {
            this.compare = compare;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public List<CommitsDTO> getCommits() {
            return commits;
        }

        public void setCommits(List<CommitsDTO> commits) {
            this.commits = commits;
        }

        public static class TargetDTO {
            private int id;
            private String login;
            private String name;
            private String avatar_url;
            private String url;
            private String html_url;
            private String remark;

            public int getId() {
                return id;
            }

            public void setId(int id) {
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
        }

        public static class LinksDTO {
            private SelfDTO self;
            private HtmlDTO html;
            private IssueDTO issue;
            private CommentsDTO comments;
            private ReviewCommentsDTO review_comments;
            private ReviewCommentDTO review_comment;
            private CommitsDTO commits;

            public SelfDTO getSelf() {
                return self;
            }

            public void setSelf(SelfDTO self) {
                this.self = self;
            }

            public HtmlDTO getHtml() {
                return html;
            }

            public void setHtml(HtmlDTO html) {
                this.html = html;
            }

            public IssueDTO getIssue() {
                return issue;
            }

            public void setIssue(IssueDTO issue) {
                this.issue = issue;
            }

            public CommentsDTO getComments() {
                return comments;
            }

            public void setComments(CommentsDTO comments) {
                this.comments = comments;
            }

            public ReviewCommentsDTO getReview_comments() {
                return review_comments;
            }

            public void setReview_comments(ReviewCommentsDTO review_comments) {
                this.review_comments = review_comments;
            }

            public ReviewCommentDTO getReview_comment() {
                return review_comment;
            }

            public void setReview_comment(ReviewCommentDTO review_comment) {
                this.review_comment = review_comment;
            }

            public CommitsDTO getCommits() {
                return commits;
            }

            public void setCommits(CommitsDTO commits) {
                this.commits = commits;
            }

            public static class SelfDTO {
                private String href;

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }
            }

            public static class HtmlDTO {
                private String href;

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }
            }

            public static class IssueDTO {
                private String href;

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }
            }

            public static class CommentsDTO {
                private String href;

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }
            }

            public static class ReviewCommentsDTO {
                private String href;

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }
            }

            public static class ReviewCommentDTO {
                private String href;

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }
            }

            public static class CommitsDTO {
                private String href;

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }
            }
        }

        public static class UserDTO {
            private int id;
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

            public int getId() {
                return id;
            }

            public void setId(int id) {
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
        }

        public static class HeadDTO {
            private String label;
            private String ref;
            private String sha;
            private UserDTO user;
            private RepoDTO repo;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getRef() {
                return ref;
            }

            public void setRef(String ref) {
                this.ref = ref;
            }

            public String getSha() {
                return sha;
            }

            public void setSha(String sha) {
                this.sha = sha;
            }

            public UserDTO getUser() {
                return user;
            }

            public void setUser(UserDTO user) {
                this.user = user;
            }

            public RepoDTO getRepo() {
                return repo;
            }

            public void setRepo(RepoDTO repo) {
                this.repo = repo;
            }

            public static class UserDTO {
                private int id;
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

                public int getId() {
                    return id;
                }

                public void setId(int id) {
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
            }

            public static class RepoDTO {
                private int id;
                private String full_name;
                private String human_name;
                private String url;
                private NamespaceDTO namespace;
                private String path;
                private String name;
                private OwnerDTO owner;
                private AssignerDTO assigner;
                private Object description;
                @SerializedName("private")
                private boolean privateX;
                @SerializedName("public")
                private boolean publicX;
                private boolean internal;
                private boolean fork;
                private String html_url;
                private String ssh_url;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
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

                public Object getDescription() {
                    return description;
                }

                public void setDescription(Object description) {
                    this.description = description;
                }

                public boolean isPrivateX() {
                    return privateX;
                }

                public void setPrivateX(boolean privateX) {
                    this.privateX = privateX;
                }

                public boolean isPublicX() {
                    return publicX;
                }

                public void setPublicX(boolean publicX) {
                    this.publicX = publicX;
                }

                public boolean isInternal() {
                    return internal;
                }

                public void setInternal(boolean internal) {
                    this.internal = internal;
                }

                public boolean isFork() {
                    return fork;
                }

                public void setFork(boolean fork) {
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

                public static class NamespaceDTO {
                    private int id;
                    private String type;
                    private String name;
                    private String path;
                    private String html_url;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
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
                }

                public static class OwnerDTO {
                    private int id;
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

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
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
                }

                public static class AssignerDTO {
                    private int id;
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

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
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
                }
            }
        }

        public static class BaseDTO {
            private String label;
            private String ref;
            private String sha;
            private UserDTO user;
            private RepoDTO repo;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getRef() {
                return ref;
            }

            public void setRef(String ref) {
                this.ref = ref;
            }

            public String getSha() {
                return sha;
            }

            public void setSha(String sha) {
                this.sha = sha;
            }

            public UserDTO getUser() {
                return user;
            }

            public void setUser(UserDTO user) {
                this.user = user;
            }

            public RepoDTO getRepo() {
                return repo;
            }

            public void setRepo(RepoDTO repo) {
                this.repo = repo;
            }

            public static class UserDTO {
                private int id;
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

                public int getId() {
                    return id;
                }

                public void setId(int id) {
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
            }

            public static class RepoDTO {
                private int id;
                private String full_name;
                private String human_name;
                private String url;
                private NamespaceDTO namespace;
                private String path;
                private String name;
                private OwnerDTO owner;
                private AssignerDTO assigner;
                private Object description;
                @SerializedName("private")
                private boolean privateX;
                @SerializedName("public")
                private boolean publicX;
                private boolean internal;
                private boolean fork;
                private String html_url;
                private String ssh_url;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
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

                public Object getDescription() {
                    return description;
                }

                public void setDescription(Object description) {
                    this.description = description;
                }

                public boolean isPrivateX() {
                    return privateX;
                }

                public void setPrivateX(boolean privateX) {
                    this.privateX = privateX;
                }

                public boolean isPublicX() {
                    return publicX;
                }

                public void setPublicX(boolean publicX) {
                    this.publicX = publicX;
                }

                public boolean isInternal() {
                    return internal;
                }

                public void setInternal(boolean internal) {
                    this.internal = internal;
                }

                public boolean isFork() {
                    return fork;
                }

                public void setFork(boolean fork) {
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

                public static class NamespaceDTO {
                    private int id;
                    private String type;
                    private String name;
                    private String path;
                    private String html_url;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
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
                }

                public static class OwnerDTO {
                    private int id;
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

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
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
                }

                public static class AssignerDTO {
                    private int id;
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

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
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
                }
            }
        }

        public static class PullRequestDTO {
            private int id;
            private String url;
            private String html_url;
            private String diff_url;
            private String patch_url;
            private String issue_url;
            private String commits_url;
            private String review_comments_url;
            private String review_comment_url;
            private String comments_url;
            private int number;
            private int close_related_issue;
            private boolean prune_branch;
            private String state;
            private int assignees_number;
            private int testers_number;
            private List<AssigneesDTO> assignees;
            private List<TestersDTO> testers;
            private Object milestone;
            private List<?> labels;
            private boolean locked;
            private String created_at;
            private String updated_at;
            private Object closed_at;
            private boolean draft;
            private Object merged_at;
            private boolean mergeable;
            private boolean can_merge_check;
            private LinksDTO _links;
            private UserDTO user;
            private List<?> ref_pull_requests;
            private String title;
            private String body;
            private HeadDTO head;
            private BaseDTO base;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public String getDiff_url() {
                return diff_url;
            }

            public void setDiff_url(String diff_url) {
                this.diff_url = diff_url;
            }

            public String getPatch_url() {
                return patch_url;
            }

            public void setPatch_url(String patch_url) {
                this.patch_url = patch_url;
            }

            public String getIssue_url() {
                return issue_url;
            }

            public void setIssue_url(String issue_url) {
                this.issue_url = issue_url;
            }

            public String getCommits_url() {
                return commits_url;
            }

            public void setCommits_url(String commits_url) {
                this.commits_url = commits_url;
            }

            public String getReview_comments_url() {
                return review_comments_url;
            }

            public void setReview_comments_url(String review_comments_url) {
                this.review_comments_url = review_comments_url;
            }

            public String getReview_comment_url() {
                return review_comment_url;
            }

            public void setReview_comment_url(String review_comment_url) {
                this.review_comment_url = review_comment_url;
            }

            public String getComments_url() {
                return comments_url;
            }

            public void setComments_url(String comments_url) {
                this.comments_url = comments_url;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public int getClose_related_issue() {
                return close_related_issue;
            }

            public void setClose_related_issue(int close_related_issue) {
                this.close_related_issue = close_related_issue;
            }

            public boolean isPrune_branch() {
                return prune_branch;
            }

            public void setPrune_branch(boolean prune_branch) {
                this.prune_branch = prune_branch;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public int getAssignees_number() {
                return assignees_number;
            }

            public void setAssignees_number(int assignees_number) {
                this.assignees_number = assignees_number;
            }

            public int getTesters_number() {
                return testers_number;
            }

            public void setTesters_number(int testers_number) {
                this.testers_number = testers_number;
            }

            public List<AssigneesDTO> getAssignees() {
                return assignees;
            }

            public void setAssignees(List<AssigneesDTO> assignees) {
                this.assignees = assignees;
            }

            public List<TestersDTO> getTesters() {
                return testers;
            }

            public void setTesters(List<TestersDTO> testers) {
                this.testers = testers;
            }

            public Object getMilestone() {
                return milestone;
            }

            public void setMilestone(Object milestone) {
                this.milestone = milestone;
            }

            public List<?> getLabels() {
                return labels;
            }

            public void setLabels(List<?> labels) {
                this.labels = labels;
            }

            public boolean isLocked() {
                return locked;
            }

            public void setLocked(boolean locked) {
                this.locked = locked;
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

            public Object getClosed_at() {
                return closed_at;
            }

            public void setClosed_at(Object closed_at) {
                this.closed_at = closed_at;
            }

            public boolean isDraft() {
                return draft;
            }

            public void setDraft(boolean draft) {
                this.draft = draft;
            }

            public Object getMerged_at() {
                return merged_at;
            }

            public void setMerged_at(Object merged_at) {
                this.merged_at = merged_at;
            }

            public boolean isMergeable() {
                return mergeable;
            }

            public void setMergeable(boolean mergeable) {
                this.mergeable = mergeable;
            }

            public boolean isCan_merge_check() {
                return can_merge_check;
            }

            public void setCan_merge_check(boolean can_merge_check) {
                this.can_merge_check = can_merge_check;
            }

            public LinksDTO get_links() {
                return _links;
            }

            public void set_links(LinksDTO _links) {
                this._links = _links;
            }

            public UserDTO getUser() {
                return user;
            }

            public void setUser(UserDTO user) {
                this.user = user;
            }

            public List<?> getRef_pull_requests() {
                return ref_pull_requests;
            }

            public void setRef_pull_requests(List<?> ref_pull_requests) {
                this.ref_pull_requests = ref_pull_requests;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public HeadDTO getHead() {
                return head;
            }

            public void setHead(HeadDTO head) {
                this.head = head;
            }

            public BaseDTO getBase() {
                return base;
            }

            public void setBase(BaseDTO base) {
                this.base = base;
            }

            public static class LinksDTO {
                private SelfDTO self;
                private HtmlDTO html;
                private IssueDTO issue;
                private CommentsDTO comments;
                private ReviewCommentsDTO review_comments;
                private ReviewCommentDTO review_comment;
                private CommitsDTO commits;

                public SelfDTO getSelf() {
                    return self;
                }

                public void setSelf(SelfDTO self) {
                    this.self = self;
                }

                public HtmlDTO getHtml() {
                    return html;
                }

                public void setHtml(HtmlDTO html) {
                    this.html = html;
                }

                public IssueDTO getIssue() {
                    return issue;
                }

                public void setIssue(IssueDTO issue) {
                    this.issue = issue;
                }

                public CommentsDTO getComments() {
                    return comments;
                }

                public void setComments(CommentsDTO comments) {
                    this.comments = comments;
                }

                public ReviewCommentsDTO getReview_comments() {
                    return review_comments;
                }

                public void setReview_comments(ReviewCommentsDTO review_comments) {
                    this.review_comments = review_comments;
                }

                public ReviewCommentDTO getReview_comment() {
                    return review_comment;
                }

                public void setReview_comment(ReviewCommentDTO review_comment) {
                    this.review_comment = review_comment;
                }

                public CommitsDTO getCommits() {
                    return commits;
                }

                public void setCommits(CommitsDTO commits) {
                    this.commits = commits;
                }

                public static class SelfDTO {
                    private String href;

                    public String getHref() {
                        return href;
                    }

                    public void setHref(String href) {
                        this.href = href;
                    }
                }

                public static class HtmlDTO {
                    private String href;

                    public String getHref() {
                        return href;
                    }

                    public void setHref(String href) {
                        this.href = href;
                    }
                }

                public static class IssueDTO {
                    private String href;

                    public String getHref() {
                        return href;
                    }

                    public void setHref(String href) {
                        this.href = href;
                    }
                }

                public static class CommentsDTO {
                    private String href;

                    public String getHref() {
                        return href;
                    }

                    public void setHref(String href) {
                        this.href = href;
                    }
                }

                public static class ReviewCommentsDTO {
                    private String href;

                    public String getHref() {
                        return href;
                    }

                    public void setHref(String href) {
                        this.href = href;
                    }
                }

                public static class ReviewCommentDTO {
                    private String href;

                    public String getHref() {
                        return href;
                    }

                    public void setHref(String href) {
                        this.href = href;
                    }
                }

                public static class CommitsDTO {
                    private String href;

                    public String getHref() {
                        return href;
                    }

                    public void setHref(String href) {
                        this.href = href;
                    }
                }
            }

            public static class UserDTO {
                private int id;
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

                public int getId() {
                    return id;
                }

                public void setId(int id) {
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
            }

            public static class HeadDTO {
                private String label;
                private String ref;
                private String sha;
                private UserDTO user;
                private RepoDTO repo;

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }

                public String getRef() {
                    return ref;
                }

                public void setRef(String ref) {
                    this.ref = ref;
                }

                public String getSha() {
                    return sha;
                }

                public void setSha(String sha) {
                    this.sha = sha;
                }

                public UserDTO getUser() {
                    return user;
                }

                public void setUser(UserDTO user) {
                    this.user = user;
                }

                public RepoDTO getRepo() {
                    return repo;
                }

                public void setRepo(RepoDTO repo) {
                    this.repo = repo;
                }

                public static class UserDTO {
                    private int id;
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

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
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
                }

                public static class RepoDTO {
                    private int id;
                    private String full_name;
                    private String human_name;
                    private String url;
                    private NamespaceDTO namespace;
                    private String path;
                    private String name;
                    private OwnerDTO owner;
                    private AssignerDTO assigner;
                    private Object description;
                    @SerializedName("private")
                    private boolean privateX;
                    @SerializedName("public")
                    private boolean publicX;
                    private boolean internal;
                    private boolean fork;
                    private String html_url;
                    private String ssh_url;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
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

                    public Object getDescription() {
                        return description;
                    }

                    public void setDescription(Object description) {
                        this.description = description;
                    }

                    public boolean isPrivateX() {
                        return privateX;
                    }

                    public void setPrivateX(boolean privateX) {
                        this.privateX = privateX;
                    }

                    public boolean isPublicX() {
                        return publicX;
                    }

                    public void setPublicX(boolean publicX) {
                        this.publicX = publicX;
                    }

                    public boolean isInternal() {
                        return internal;
                    }

                    public void setInternal(boolean internal) {
                        this.internal = internal;
                    }

                    public boolean isFork() {
                        return fork;
                    }

                    public void setFork(boolean fork) {
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

                    public static class NamespaceDTO {
                        private int id;
                        private String type;
                        private String name;
                        private String path;
                        private String html_url;

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
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
                    }

                    public static class OwnerDTO {
                        private int id;
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

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
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
                    }

                    public static class AssignerDTO {
                        private int id;
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

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
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
                    }
                }
            }

            public static class BaseDTO {
                private String label;
                private String ref;
                private String sha;
                private UserDTO user;
                private RepoDTO repo;

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }

                public String getRef() {
                    return ref;
                }

                public void setRef(String ref) {
                    this.ref = ref;
                }

                public String getSha() {
                    return sha;
                }

                public void setSha(String sha) {
                    this.sha = sha;
                }

                public UserDTO getUser() {
                    return user;
                }

                public void setUser(UserDTO user) {
                    this.user = user;
                }

                public RepoDTO getRepo() {
                    return repo;
                }

                public void setRepo(RepoDTO repo) {
                    this.repo = repo;
                }

                public static class UserDTO {
                    private int id;
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

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
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
                }

                public static class RepoDTO {
                    private int id;
                    private String full_name;
                    private String human_name;
                    private String url;
                    private NamespaceDTO namespace;
                    private String path;
                    private String name;
                    private OwnerDTO owner;
                    private AssignerDTO assigner;
                    private Object description;
                    @SerializedName("private")
                    private boolean privateX;
                    @SerializedName("public")
                    private boolean publicX;
                    private boolean internal;
                    private boolean fork;
                    private String html_url;
                    private String ssh_url;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
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

                    public Object getDescription() {
                        return description;
                    }

                    public void setDescription(Object description) {
                        this.description = description;
                    }

                    public boolean isPrivateX() {
                        return privateX;
                    }

                    public void setPrivateX(boolean privateX) {
                        this.privateX = privateX;
                    }

                    public boolean isPublicX() {
                        return publicX;
                    }

                    public void setPublicX(boolean publicX) {
                        this.publicX = publicX;
                    }

                    public boolean isInternal() {
                        return internal;
                    }

                    public void setInternal(boolean internal) {
                        this.internal = internal;
                    }

                    public boolean isFork() {
                        return fork;
                    }

                    public void setFork(boolean fork) {
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

                    public static class NamespaceDTO {
                        private int id;
                        private String type;
                        private String name;
                        private String path;
                        private String html_url;

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
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
                    }

                    public static class OwnerDTO {
                        private int id;
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

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
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
                    }

                    public static class AssignerDTO {
                        private int id;
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

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
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
                    }
                }
            }

            public static class AssigneesDTO {
                private int id;
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
                private boolean assignee;
                private boolean code_owner;
                private boolean accept;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
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

                public boolean isAssignee() {
                    return assignee;
                }

                public void setAssignee(boolean assignee) {
                    this.assignee = assignee;
                }

                public boolean isCode_owner() {
                    return code_owner;
                }

                public void setCode_owner(boolean code_owner) {
                    this.code_owner = code_owner;
                }

                public boolean isAccept() {
                    return accept;
                }

                public void setAccept(boolean accept) {
                    this.accept = accept;
                }
            }

            public static class TestersDTO {
                private int id;
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
                private boolean assignee;
                private boolean code_owner;
                private boolean accept;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
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

                public boolean isAssignee() {
                    return assignee;
                }

                public void setAssignee(boolean assignee) {
                    this.assignee = assignee;
                }

                public boolean isCode_owner() {
                    return code_owner;
                }

                public void setCode_owner(boolean code_owner) {
                    this.code_owner = code_owner;
                }

                public boolean isAccept() {
                    return accept;
                }

                public void setAccept(boolean accept) {
                    this.accept = accept;
                }
            }
        }

        public static class CommentDTO {
            private String url;
            private String html_url;
            private String pull_request_url;
            private int id;
            private Object line;
            private Object commit_id;
            private UserDTO user;
            private String created_at;
            private String updated_at;
            private String body;

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

            public String getPull_request_url() {
                return pull_request_url;
            }

            public void setPull_request_url(String pull_request_url) {
                this.pull_request_url = pull_request_url;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getLine() {
                return line;
            }

            public void setLine(Object line) {
                this.line = line;
            }

            public Object getCommit_id() {
                return commit_id;
            }

            public void setCommit_id(Object commit_id) {
                this.commit_id = commit_id;
            }

            public UserDTO getUser() {
                return user;
            }

            public void setUser(UserDTO user) {
                this.user = user;
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

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public static class UserDTO {
                private int id;
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

                public int getId() {
                    return id;
                }

                public void setId(int id) {
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
            }
        }

        public static class RepositoryDTO {
            private int id;
            private String full_name;
            private String human_name;
            private String url;
            private NamespaceDTO namespace;
            private String path;
            private String name;
            private OwnerDTO owner;
            private AssignerDTO assigner;
            private Object description;
            @SerializedName("private")
            private boolean privateX;
            @SerializedName("public")
            private boolean publicX;
            private boolean internal;
            private boolean fork;
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
            private boolean recommend;
            private boolean gvp;
            private Object homepage;
            private Object language;
            private int forks_count;
            private int stargazers_count;
            private int watchers_count;
            private String default_branch;
            private int open_issues_count;
            private boolean has_issues;
            private boolean has_wiki;
            private Object issue_comment;
            private boolean can_comment;
            private boolean pull_requests_enabled;
            private boolean has_page;
            private Object license;
            private boolean outsourced;
            private String project_creator;
            private List<String> members;
            private String pushed_at;
            private String created_at;
            private String updated_at;
            private Object parent;
            private Object paas;
            private int assignees_number;
            private int testers_number;
            private List<AssigneeDTO> assignee;
            private List<TestersDTO> testers;
            private String status;
            private List<?> programs;
            private Object enterprise;
            private List<?> project_labels;
            private String issue_template_source;

            public int getId() {
                return id;
            }

            public void setId(int id) {
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

            public Object getDescription() {
                return description;
            }

            public void setDescription(Object description) {
                this.description = description;
            }

            public boolean isPrivateX() {
                return privateX;
            }

            public void setPrivateX(boolean privateX) {
                this.privateX = privateX;
            }

            public boolean isPublicX() {
                return publicX;
            }

            public void setPublicX(boolean publicX) {
                this.publicX = publicX;
            }

            public boolean isInternal() {
                return internal;
            }

            public void setInternal(boolean internal) {
                this.internal = internal;
            }

            public boolean isFork() {
                return fork;
            }

            public void setFork(boolean fork) {
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

            public boolean isRecommend() {
                return recommend;
            }

            public void setRecommend(boolean recommend) {
                this.recommend = recommend;
            }

            public boolean isGvp() {
                return gvp;
            }

            public void setGvp(boolean gvp) {
                this.gvp = gvp;
            }

            public Object getHomepage() {
                return homepage;
            }

            public void setHomepage(Object homepage) {
                this.homepage = homepage;
            }

            public Object getLanguage() {
                return language;
            }

            public void setLanguage(Object language) {
                this.language = language;
            }

            public int getForks_count() {
                return forks_count;
            }

            public void setForks_count(int forks_count) {
                this.forks_count = forks_count;
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

            public String getDefault_branch() {
                return default_branch;
            }

            public void setDefault_branch(String default_branch) {
                this.default_branch = default_branch;
            }

            public int getOpen_issues_count() {
                return open_issues_count;
            }

            public void setOpen_issues_count(int open_issues_count) {
                this.open_issues_count = open_issues_count;
            }

            public boolean isHas_issues() {
                return has_issues;
            }

            public void setHas_issues(boolean has_issues) {
                this.has_issues = has_issues;
            }

            public boolean isHas_wiki() {
                return has_wiki;
            }

            public void setHas_wiki(boolean has_wiki) {
                this.has_wiki = has_wiki;
            }

            public Object getIssue_comment() {
                return issue_comment;
            }

            public void setIssue_comment(Object issue_comment) {
                this.issue_comment = issue_comment;
            }

            public boolean isCan_comment() {
                return can_comment;
            }

            public void setCan_comment(boolean can_comment) {
                this.can_comment = can_comment;
            }

            public boolean isPull_requests_enabled() {
                return pull_requests_enabled;
            }

            public void setPull_requests_enabled(boolean pull_requests_enabled) {
                this.pull_requests_enabled = pull_requests_enabled;
            }

            public boolean isHas_page() {
                return has_page;
            }

            public void setHas_page(boolean has_page) {
                this.has_page = has_page;
            }

            public Object getLicense() {
                return license;
            }

            public void setLicense(Object license) {
                this.license = license;
            }

            public boolean isOutsourced() {
                return outsourced;
            }

            public void setOutsourced(boolean outsourced) {
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

            public int getAssignees_number() {
                return assignees_number;
            }

            public void setAssignees_number(int assignees_number) {
                this.assignees_number = assignees_number;
            }

            public int getTesters_number() {
                return testers_number;
            }

            public void setTesters_number(int testers_number) {
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

            public static class NamespaceDTO {
                private int id;
                private String type;
                private String name;
                private String path;
                private String html_url;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
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
            }

            public static class OwnerDTO {
                private int id;
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

                public int getId() {
                    return id;
                }

                public void setId(int id) {
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
            }

            public static class AssignerDTO {
                private int id;
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

                public int getId() {
                    return id;
                }

                public void setId(int id) {
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
            }

            public static class AssigneeDTO {
                private int id;
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

                public int getId() {
                    return id;
                }

                public void setId(int id) {
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
            }

            public static class TestersDTO {
                private int id;
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

                public int getId() {
                    return id;
                }

                public void setId(int id) {
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
            }
        }

        public static class AssigneesDTO {
            private int id;
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
            private boolean assignee;
            private boolean code_owner;
            private boolean accept;

            public int getId() {
                return id;
            }

            public void setId(int id) {
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

            public boolean isAssignee() {
                return assignee;
            }

            public void setAssignee(boolean assignee) {
                this.assignee = assignee;
            }

            public boolean isCode_owner() {
                return code_owner;
            }

            public void setCode_owner(boolean code_owner) {
                this.code_owner = code_owner;
            }

            public boolean isAccept() {
                return accept;
            }

            public void setAccept(boolean accept) {
                this.accept = accept;
            }
        }

        public static class TestersDTO {
            private int id;
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
            private boolean assignee;
            private boolean code_owner;
            private boolean accept;

            public int getId() {
                return id;
            }

            public void setId(int id) {
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

            public boolean isAssignee() {
                return assignee;
            }

            public void setAssignee(boolean assignee) {
                this.assignee = assignee;
            }

            public boolean isCode_owner() {
                return code_owner;
            }

            public void setCode_owner(boolean code_owner) {
                this.code_owner = code_owner;
            }

            public boolean isAccept() {
                return accept;
            }

            public void setAccept(boolean accept) {
                this.accept = accept;
            }
        }

        public static class CommitsDTO {
            private String sha;
            private AuthorDTO author;
            private String message;
            private String url;

            public String getSha() {
                return sha;
            }

            public void setSha(String sha) {
                this.sha = sha;
            }

            public AuthorDTO getAuthor() {
                return author;
            }

            public void setAuthor(AuthorDTO author) {
                this.author = author;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public static class AuthorDTO {
                private String email;
                private String name;

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
            }
        }
    }

    public static class OrgDTO {
        private Integer id;
        private String login;
        private String name;
        private String url;
        private String avatar_url;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }
    }
}
