

CREATE TABLE `directory_files`.`directories` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NULL,
    `parent_id` BIGINT(20) NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `parent_id_fk`
        FOREIGN KEY (`parent_id`) REFERENCES `directory_files`.`directories` (`id`));


CREATE TABLE `directory_files`.`files` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NULL,
    `size` VARCHAR(255) NULL,
    `directory_id` BIGINT(20) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `directory_id_fk`
        FOREIGN KEY (`directory_id`) REFERENCES `directory_files`.`directories` (`id`));

