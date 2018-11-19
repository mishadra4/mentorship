DROP TABLE IF EXISTS AUTHORITIES;
DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS LOG;

--USER TABLE
CREATE TABLE IF NOT EXISTS LECTURE (
    IDLECTURE INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL PRIMARY KEY
);

--USER TABLE
CREATE TABLE IF NOT EXISTS USER (
    IDUSER INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
    USERNAME VARCHAR(255) NOT NULL PRIMARY KEY,
    PASSWORD  VARCHAR(255) NOT NULL,
    ENABLED BOOLEAN NOT NULL
    );

CREATE TABLE IF NOT EXISTS STUDENT (
    ID INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
    FIRST_NAME VARCHAR(255) NOT NULL,
    LAST_NAME VARCHAR(255) NOT NULL,
    USERNAME VARCHAR(255) NOT NULL PRIMARY KEY,
    PASSWORD  VARCHAR(255) NOT NULL,
    ID_LECTURE INT,
    CONSTRAINT FK_LECTURE_STUDENT FOREIGN KEY(IDLECTURE) REFERENCES LECTURE(IDLECTURE)
    );


--AUTHORITIES TABLE
CREATE TABLE IF NOT EXISTS AUTHORITIES(
      USERNAME VARCHAR(255) NOT NULL,
      AUTHORITY VARCHAR(255) NOT NULL,
      CONSTRAINT FK_AUTHORITIES_USER FOREIGN KEY(USERNAME) REFERENCES USER(USERNAME)
      );
--LOG TABLE
CREATE TABLE IF NOT EXISTS LOG (
	  IDLOG INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
    LOGSTRING VARCHAR(1000) NULL,
    PRIMARY KEY (IDLOG)
);

--TEST TABLE
CREATE TABLE IF NOT EXISTS TEST (
	  IDTEST INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
    TESTCOLUMN VARCHAR(1000) NULL,
    PRIMARY KEY (IDTEST)
);



