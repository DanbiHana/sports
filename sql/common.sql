-- 선수(축구, 야구) 번호
CREATE SEQUENCE PLAYERNUM_SEQ;

-- 축구선수 테이블
    CREATE TABLE PLAYER_SOCCER (
      PLAYERNUM VARCHAR2(20 BYTE) 
    , TNAME VARCHAR2(20 BYTE) 
    , PNAME VARCHAR2(20 BYTE) 
    , PNUMBER NUMBER(3, 0) 
    , PBIRTH DATE 
    , HEIGHT NUMBER(3, 0) 
    , WEIGHT NUMBER(3, 0) 
    , MAIN VARCHAR2(20 BYTE) 
    , PIMAGE VARCHAR2(100 BYTE) 
    , PCHAR VARCHAR2(100 BYTE) 
    , CLICKUP NUMBER(10, 0) DEFAULT 0 
    , STEP NUMBER(6, 0) DEFAULT 0 
    , PLAY VARCHAR2(20 BYTE) 
    );
    
 -- 야구선수 테이블
    CREATE TABLE PLAYER_BASEBALL (
      PLAYERNUM VARCHAR2(20 BYTE) 
    , TNAME VARCHAR2(20 BYTE) 
    , PNAME VARCHAR2(20 BYTE) 
    , PNUMBER NUMBER(3, 0) 
    , PBIRTH DATE 
    , HEIGHT NUMBER(3, 0) 
    , WEIGHT NUMBER(3, 0) 
    , MAIN VARCHAR2(20 BYTE) 
    , PIMAGE VARCHAR2(100 BYTE) 
    , PCHAR VARCHAR2(100 BYTE) 
    , CLICKUP NUMBER(10, 0) DEFAULT 0 
    , STEP NUMBER(6, 0) DEFAULT 0 
    , PLAY VARCHAR2(20 BYTE) 
    );
    
-- 댓글 테이블
CREATE TABLE PLAYER_COMMENT (
  PLAYERNUM NUMBER(6, 0) 
, STEP NUMBER 
, ID VARCHAR2(20 BYTE) 
, WRITER VARCHAR2(100 BYTE) 
, UCOMMENT VARCHAR2(500 BYTE) 
, CDATE DATE DEFAULT SYSDATE 
, HEART NUMBER(10, 0) DEFAULT 0 
) ;

-- 캘린더 테이블 
    CREATE TABLE DIRECT_TRADE (
      TRNAME VARCHAR2(30 BYTE) 
    , TRDATE DATE 
    , TRDATE2 DATE 
    , TRMEMBER VARCHAR2(1000 BYTE) 
    , TRPLACE VARCHAR2(100 BYTE) 
    , TRCONTENT VARCHAR2(1000 BYTE) 
    , TRMEMO VARCHAR2(1000 BYTE) 
    , TRNUM NUMBER(6, 0) 
    , TRINFO VARCHAR2(20 BYTE) 
    );
CREATE SEQUENCE TRNUM_SEQ;

-- 전략 테이블
    CREATE TABLE DIRECT_STRATEGY (
      STNUM NUMBER(6, 0) 
    , STNAME VARCHAR2(30 BYTE) 
    , STKIND VARCHAR2(20 BYTE) 
    , STDATE DATE 
    , STAREA VARCHAR2(30 BYTE) 
    , STINFO VARCHAR2(1000 BYTE) 
    );
CREATE SEQUENCE STNUM_SEQ;

-- 회원가입 테이블
    CREATE TABLE sportsmember(
    ID VARCHAR2(100 BYTE) NOT NULL 
    , PW VARCHAR2(100 BYTE) 
    , NAME VARCHAR2(20 BYTE) 
    , BIRTH VARCHAR2(50 BYTE) 
    , TEL VARCHAR2(20 BYTE) 
    , EMAIL VARCHAR2(100 BYTE) 
    , ZZIP_CODE VARCHAR2(20 BYTE) 
    , USER_ADD1 VARCHAR2(1000 BYTE) 
    , USER_ADD2 VARCHAR2(1000 BYTE) 
    , SPORT VARCHAR2(50 BYTE) 
    , TEAM VARCHAR2(50 BYTE) 
    , VOE VARCHAR2(500 BYTE) 
    , RR VARCHAR2(500 BYTE) 
    , PART VARCHAR2(20 BYTE) 
    , INPUTACCESS VARCHAR2(20 BYTE) 
    );
    
    -- 축구 경기 일정 테이블
    CREATE TABLE SOCCER_GAME (
  TEAM1 VARCHAR2(50 BYTE) 
, TEAM2 VARCHAR2(50 BYTE) 
, GAMEDATE DATE 
, GAMETIME VARCHAR2(20 BYTE) 
, GAMEPLACE VARCHAR2(50 BYTE) 
, GAMENUM NUMBER(10, 0) 
, SEASON VARCHAR2(50 BYTE) 
) ;
    -- 야구 경기 일정 테이블
    CREATE TABLE BASEBALL_GAME (
  TEAM1 VARCHAR2(50 BYTE) 
, TEAM2 VARCHAR2(50 BYTE) 
, GAMEDATE DATE 
, GAMETIME VARCHAR2(20 BYTE) 
, GAMEPLACE VARCHAR2(50 BYTE) 
, GAMENUM NUMBER(10, 0) 
, SEASON VARCHAR2(50 BYTE) 
) ;