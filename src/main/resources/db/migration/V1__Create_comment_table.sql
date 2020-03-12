CREATE TABLE `comment`(
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `parent_id` BIGINT NOT NULL COMMENT '父类ID',
  `type` INT NOT NULL COMMENT '父类类型',
  `commentator` INT NOT NULL COMMENT '评论人id',
  `gmt_create` BIGINT NOT NULL COMMENT '创建时间',
  `gmt_modified` BIGINT NOT NULL COMMENT '更新时间',
  `like_count` BIGINT DEFAULT 0 COMMENT '点赞数',
  `content` VARCHAR(1024) NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8 COLLATE=utf8_general_ci;