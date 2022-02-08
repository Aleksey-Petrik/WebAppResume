DROP TABLE IF EXISTS resume, contact;

CREATE TABLE resume
(
    uuid      VARCHAR(36) PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL
);

CREATE TABLE contact
(
    id          SERIAL PRIMARY KEY,
    type        VARCHAR(20) NOT NULL,
    value       VARCHAR(30) NOT NULL,
    resume_uuid VARCHAR(36) REFERENCES resume (uuid),
    UNIQUE (resume_uuid, type)
);