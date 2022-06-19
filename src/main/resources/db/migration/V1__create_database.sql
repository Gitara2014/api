
CREATE SCHEMA IF NOT EXISTS blog;

CREATE TABLE IF NOT EXISTS posts (
   id UUID NOT NULL,
   title VARCHAR(255) NOT NULL,
   author VARCHAR(255) NOT NULL,
   content VARCHAR(255) NOT NULL,
   update_time TIMESTAMP with time zone,
   creation_time TIMESTAMP with time zone NOT NULL,
   CONSTRAINT pk_posts PRIMARY KEY (id)
);