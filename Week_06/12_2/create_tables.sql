CREATE DATABASE IF NOT EXISTS `shop`;

CREATE TABLE IF NOT EXISTS `goods`
(
    `id`          CHAR(32) PRIMARY KEY COMMENT '商品id',
    `create_time` TIMESTAMP               NOT NULL COMMENT '创建时间',
    `update_time` TIMESTAMP DEFAULT NULL COMMENT '修改时间',
    `price`       DECIMAL(19, 2) UNSIGNED NOT NULL COMMENT '价格',
    `name`        VARCHAR(20)             NOT NULL COMMENT '商品名',
    `info`        BLOB COMMENT '商品信息'
) ENGINE = MyISAM COMMENT '商品表';

CREATE TABLE IF NOT EXISTS `customer`
(
    `id`          CHAR(32) PRIMARY KEY COMMENT '消费者id',
    `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
    `update_time` TIMESTAMP DEFAULT NULL COMMENT '修改时间',
    `info`        BLOB COMMENT '消费者信息'
) ENGINE = MyISAM COMMENT '消费者表';

CREATE TABLE IF NOT EXISTS 'delivery'
(
    `id`           CHAR(32) PRIMARY KEY COMMENT '收货信息id',
    `customer_id`  CHAR(32)    NOT NULL COMMENT '消费者id',
    `create_time`  TIMESTAMP   NOT NULL COMMENT '创建时间',
    `update_time`  TIMESTAMP DEFAULT NULL COMMENT '修改时间',
    `consignee`    VARCHAR(10) NOT NULL COMMENT '收件人姓名',
    `mobile_phone` CHAR(11)    NOT NULL COMMENT '收件人手机',
    `address`      TINYBLOB    NOT NULL COMMENT '收件人地址',
    INDEX (`customer_id`)
) ENGINE = InnoDB COMMENT '配送信息表';

# 配送信息取快照，总价于订单-商品关系-商品表有冗余，简化查询过程
CREATE TABLE IF NOT EXISTS `order`
(
    `id`          CHAR(32) PRIMARY KEY COMMENT '订单id',
    `create_time` TIMESTAMP               NOT NULL COMMENT '创建时间',
    `update_time` TIMESTAMP                        DEFAULT NULL COMMENT '修改时间',
    `customer_id` CHAR(32)                NOT NULL COMMENT '消费者id',
    `amount`      DECIMAL(19, 4) UNSIGNED NOT NULL COMMENT '总价',
    `paid`        BOOLEAN                 NOT NULL DEFAULT FALSE COMMENT '支付状态',
    `delivery`    BLOB                    NOT NULL COMMENT '配送信息id',
    INDEX (`customer_id`)
) ENGINE = InnoDB COMMENT '订单表';

CREATE TABLE IF NOT EXISTS `order_goods`
(
    `order_id`    CHAR(32)         NOT NULL COMMENT '订单id',
    `goods_id`    CHAR(32)         NOT NULL COMMENT '商品id',
    `goods_num`   INT(11) UNSIGNED NOT NULL COMMENT '商品数量',
    `create_time` TIMESTAMP        NOT NULL COMMENT '创建时间',
    `update_time` TIMESTAMP DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`order_id`, `goods_id`) COMMENT '订单-商品id'
) ENGINE = InnoDB COMMENT '订单-商品关系表';