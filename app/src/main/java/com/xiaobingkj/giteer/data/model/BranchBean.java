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

public class BranchBean {

    private String name;
    private CommitDTO commit;
    @SerializedName("protected")
    private boolean protectedX;
    private String protection_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommitDTO getCommit() {
        return commit;
    }

    public void setCommit(CommitDTO commit) {
        this.commit = commit;
    }

    public boolean isProtectedX() {
        return protectedX;
    }

    public void setProtectedX(boolean protectedX) {
        this.protectedX = protectedX;
    }

    public String getProtection_url() {
        return protection_url;
    }

    public void setProtection_url(String protection_url) {
        this.protection_url = protection_url;
    }

    public static class CommitDTO {
        private String sha;
        private String url;
        private CommitDetailDTO commit;

        public String getSha() {
            return sha;
        }

        public void setSha(String sha) {
            this.sha = sha;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public CommitDetailDTO getCommit() {
            return commit;
        }

        public void setCommit(CommitDetailDTO commit) {
            this.commit = commit;
        }

        public static class CommitDetailDTO {
            private CommitDTO.CommitDetailDTO.AuthorDTO author;
            private CommitDTO.CommitDetailDTO.CommitterDTO committer;
            private String message;

            public AuthorDTO getAuthor() {
                return author;
            }

            public void setAuthor(AuthorDTO author) {
                this.author = author;
            }

            public CommitterDTO getCommitter() {
                return committer;
            }

            public void setCommitter(CommitterDTO committer) {
                this.committer = committer;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public static class AuthorDTO {
                private String name;
                private String date;
                private String email;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }
            }

            public static class CommitterDTO {
                private String name;
                private String date;
                private String email;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }
            }
        }
    }
}
