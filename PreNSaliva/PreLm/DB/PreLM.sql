
-- Table: tblStack
CREATE TABLE tblStack ( 
    ID   SMALLINT        NULL,
    QVar NVARCHAR( 20 )  NULL 
);


-- Table: tblVersion
CREATE TABLE tblVersion ( 
    Slno      INT             NOT NULL,
    VersionNo NVARCHAR( 50 )  NULL,
    Vdate     DATETIME        NULL 
);


-- Table: tblUser
CREATE TABLE tblUser ( 
    ID     NCHAR( 10 )     PRIMARY KEY
                           NOT NULL,
    Name   NVARCHAR( 50 )  NOT NULL,
    NameB  NVARCHAR( 50 )  NULL,
    Pwd    NVARCHAR( 10 )  NOT NULL,
    Active NCHAR( 1 )      NULL 
);


-- Table: tblSamplesInfo
CREATE TABLE tblSamplesInfo ( 
    sampleid  NVARCHAR( 15 )  PRIMARY KEY
                              NOT NULL,
    randomid  NVARCHAR( 15 )  NOT NULL,
    serial    INT,
    clusterid NVARCHAR( 10 ) 
);


-- Table: tblMainQues
CREATE TABLE tblMainQues ( 
    dataid        VARCHAR( 15 )   NOT NULL,
    clusterid     VARCHAR( 10 ),
    mid           VARCHAR( 10 ),
    FaId          VARCHAR( 15 ),
    SampleColDate VARCHAR( 100 ),
    NumChildren   VARCHAR( 15 ),
    VersionNo     VARCHAR( 50 )   NOT NULL,
    EntryBy       VARCHAR( 10 )   NOT NULL,
    EntryDate     VARCHAR( 100 )  NOT NULL,
    EditBy        VARCHAR( 10 ),
    EditDate      VARCHAR( 100 ),
    AssetID       VARCHAR( 10 ),
    IsTransfered  INT,
    PRIMARY KEY ( dataid, EntryDate, AssetID ) 
);


-- Table: tblEpisodes_Hour5
CREATE TABLE tblEpisodes_Hour5 ( 
    dataid       VARCHAR( 15 )   NOT NULL,
    childNo      VARCHAR( 100 ),
    EpisodeNo    VARCHAR( 1 ),
    q15_hour5    VARCHAR( 5 ),
    q16_hour5    VARCHAR( 3 ),
    q17_hour5    INTEGER,
    q18_hour5    INTEGER,
    EntryBy      NVARCHAR( 10 )  NOT NULL,
    EntryDate    VARCHAR( 100 )  NOT NULL,
    EditBy       NVARCHAR( 10 ),
    EditDate     VARCHAR( 100 ),
    IsTransfered INT,
    PRIMARY KEY ( dataid, childNo, EpisodeNo, EntryDate ) 
);


-- Table: tblEpisodes_Hour2
CREATE TABLE tblEpisodes_Hour2 ( 
    dataid       VARCHAR( 15 )   NOT NULL,
    childNo      VARCHAR( 100 ),
    EpisodeNo    VARCHAR( 1 ),
    q43_hour2    VARCHAR( 5 ),
    q44_hour2    VARCHAR( 3 ),
    q45_hour2    INTEGER,
    q46_hour2    INTEGER,
    EntryBy      NVARCHAR( 10 )  NOT NULL,
    EntryDate    VARCHAR( 100 )  NOT NULL,
    EditBy       NVARCHAR( 10 ),
    EditDate     VARCHAR( 100 ),
    IsTransfered INT,
    PRIMARY KEY ( dataid, childNo, EpisodeNo, EntryDate ) 
);


-- Table: tblSamples
CREATE TABLE tblSamples ( 
    dataid         VARCHAR( 15 )   NOT NULL,
    childNo        VARCHAR( 100 ),
    q5             VARCHAR( 10 ),
    q6             VARCHAR( 200 ),
    q7             INTEGER,
    q8             INTEGER,
    q8_other       VARCHAR( 200 ),
    q41            VARCHAR( 5 ),
    q47            VARCHAR( 5 ),
    q48            VARCHAR( 3 ),
    q49            VARCHAR( 2 ),
    q50            VARCHAR( 1 ),
    q51            VARCHAR( 3 ),
    q52s1hour2     VARCHAR( 10 ),
    q52s2hour2     VARCHAR( 10 ),
    q52s3hour2     VARCHAR( 10 ),
    q52s4hour2     VARCHAR( 10 ),
    q52s5hour2     VARCHAR( 10 ),
    q52s6hour2     VARCHAR( 10 ),
    q53s1hour2     VARCHAR( 6 ),
    q53s2hour2     VARCHAR( 6 ),
    q53s3hour2     VARCHAR( 6 ),
    q53s4hour2     VARCHAR( 6 ),
    q53s5hour2     VARCHAR( 6 ),
    q53s6hour2     VARCHAR( 6 ),
    q54s1hour2     VARCHAR( 1 ),
    q54s2hour2     VARCHAR( 1 ),
    q54s3hour2     VARCHAR( 1 ),
    q54s4hour2     VARCHAR( 1 ),
    q54s5hour2     VARCHAR( 1 ),
    q54s6hour2     VARCHAR( 1 ),
    q55s1hour2     VARCHAR( 1 ),
    q55s2hour2     VARCHAR( 1 ),
    q55s3hour2     VARCHAR( 1 ),
    q55s4hour2     VARCHAR( 1 ),
    q55s5hour2     VARCHAR( 1 ),
    q55s6hour2     VARCHAR( 1 ),
    q56hour2       VARCHAR( 1 ),
    q56hour2_other VARCHAR( 200 ),
    q57hour2       VARCHAR( 5 ),
    EntryBy        NVARCHAR( 10 )  NOT NULL,
    EntryDate      VARCHAR( 100 )  NOT NULL,
    EditBy         NVARCHAR( 10 ),
    EditDate       VARCHAR( 100 ),
    IsTransfered   INT,
    PRIMARY KEY ( dataid, childNo, EntryDate ) 
);

