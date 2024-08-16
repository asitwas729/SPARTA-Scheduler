CREATE TABLE `manager` (
    `managerId` bigint NOT NULL AUTO_INCREMENT COMMENT '매니저id',
    `managerName` varchar(100) NOT NULL COMMENT '매니저이름',
    `managerEmail` varchar(100) NOT NULL COMMENT '매니저이메일',
    `createDate` datetime NOT NULL COMMENT '생성일',
    `uptodate` datetime NOT NULL COMMENT '수정일',
    `memo` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`managerId`)
)

CREATE TABLE `scheduler` (
    `eventId` bigint NOT NULL AUTO_INCREMENT,
    `eventName` varchar(100) NOT NULL,
    `managerId` bigint NOT NULL,
    `password` varchar(100) NOT NULL,
    `upToDate` datetime NOT NULL,
    `createDate` datetime NOT NULL,
    PRIMARY KEY (`eventId`),
    CONSTRAINT `scheduler_manager__fk` FOREIGN KEY (`managerId`) REFERENCES `manager` (`managerId`)
)