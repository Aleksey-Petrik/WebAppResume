DROP TABLE IF EXISTS resume, contact;

CREATE TABLE resume
(
    uuid      VARCHAR(36) PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL
);

CREATE TABLE contact
(
    id          SERIAL PRIMARY KEY,
    type        VARCHAR(30) NOT NULL,
    value       VARCHAR(100) NOT NULL,
    resume_uuid VARCHAR(36) REFERENCES resume (uuid) ON DELETE CASCADE,
    UNIQUE (resume_uuid, type)
);