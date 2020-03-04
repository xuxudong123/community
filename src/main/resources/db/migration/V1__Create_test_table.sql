CREATE TABLE `community`.`test`(
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20),
  `account_id` VARCHAR(100),
  `mgt_create` BIGINT(20),
  `mgt_modified` BIGINT(20),
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;