-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema financa
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema financa
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `financa` DEFAULT CHARACTER SET utf8 ;
USE `financa` ;

-- -----------------------------------------------------
-- Table `financa`.`bandeira`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `financa`.`bandeira` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `nome` VARCHAR(35) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `financa`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `financa`.`usuario` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `login` VARCHAR(25) NOT NULL COMMENT '',
  `senha` VARCHAR(40) NOT NULL COMMENT '',
  `nome` VARCHAR(50) NOT NULL COMMENT '',
  `email` VARCHAR(100) NOT NULL COMMENT '',
  `ativo` TINYINT(1) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `financa`.`cartao_credito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `financa`.`cartao_credito` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `descricao` VARCHAR(45) NOT NULL COMMENT '',
  `limite` DOUBLE NOT NULL COMMENT '',
  `dia_fecha` INT(11) NULL DEFAULT NULL COMMENT '',
  `dia_paga` INT(11) NULL DEFAULT NULL COMMENT '',
  `usuario_id` INT(10) UNSIGNED NOT NULL COMMENT '',
  `bandeira_id` INT(10) UNSIGNED NOT NULL COMMENT '',
  PRIMARY KEY (`id`, `usuario_id`, `bandeira_id`)  COMMENT '',
  INDEX `fk_cartao_credito_usuario1_idx` (`usuario_id` ASC)  COMMENT '',
  INDEX `fk_cartao_credito_bandeira1_idx` (`bandeira_id` ASC)  COMMENT '',
  CONSTRAINT `fk_cartao_credito_bandeira1`
    FOREIGN KEY (`bandeira_id`)
    REFERENCES `financa`.`bandeira` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cartao_credito_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `financa`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `financa`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `financa`.`categoria` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `nome` VARCHAR(25) NOT NULL COMMENT '',
  `tipo_categoria` CHAR(1) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `financa`.`compras_cartao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `financa`.`compras_cartao` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `valor` DOUBLE NOT NULL COMMENT '',
  `data` DATE NOT NULL COMMENT '',
  `descricao` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  `parcelas` INT(10) UNSIGNED NULL DEFAULT NULL COMMENT '',
  `valor_parcela` DOUBLE UNSIGNED NULL DEFAULT NULL COMMENT '',
  `cartao_credito_id` INT(10) UNSIGNED NOT NULL COMMENT '',
  `categoria_id` INT(10) UNSIGNED NOT NULL COMMENT '',
  PRIMARY KEY (`id`, `cartao_credito_id`, `categoria_id`)  COMMENT '',
  INDEX `fk_compras_cartao_cartao_credito1_idx` (`cartao_credito_id` ASC)  COMMENT '',
  INDEX `fk_compras_cartao_categoria1_idx` (`categoria_id` ASC)  COMMENT '',
  CONSTRAINT `fk_compras_cartao_cartao_credito1`
    FOREIGN KEY (`cartao_credito_id`)
    REFERENCES `financa`.`cartao_credito` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_compras_cartao_categoria1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `financa`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `financa`.`tipo_conta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `financa`.`tipo_conta` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `tipo` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `financa`.`conta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `financa`.`conta` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `nome` VARCHAR(45) NOT NULL COMMENT '',
  `saldo_inicial` DOUBLE NOT NULL COMMENT '',
  `usuario_id` INT(10) UNSIGNED NOT NULL COMMENT '',
  `tipo_conta_id` INT(10) UNSIGNED NOT NULL COMMENT '',
  PRIMARY KEY (`id`, `usuario_id`, `tipo_conta_id`)  COMMENT '',
  INDEX `fk_conta_usuario1_idx` (`usuario_id` ASC)  COMMENT '',
  INDEX `fk_conta_tipo_conta1_idx` (`tipo_conta_id` ASC)  COMMENT '',
  CONSTRAINT `fk_conta_tipo_conta1`
    FOREIGN KEY (`tipo_conta_id`)
    REFERENCES `financa`.`tipo_conta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_conta_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `financa`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `financa`.`despesa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `financa`.`despesa` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `valor` DOUBLE NOT NULL COMMENT '',
  `data` DATE NOT NULL COMMENT '',
  `descricao` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  `pago` TINYINT(4) NOT NULL COMMENT '',
  `conta_id` INT(10) UNSIGNED NOT NULL COMMENT '',
  `categoria_id` INT(10) UNSIGNED NOT NULL COMMENT '',
  PRIMARY KEY (`id`, `conta_id`, `categoria_id`)  COMMENT '',
  INDEX `fk_despesa_conta1_idx` (`conta_id` ASC)  COMMENT '',
  INDEX `fk_despesa_categoria1_idx` (`categoria_id` ASC)  COMMENT '',
  CONSTRAINT `fk_despesa_categoria1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `financa`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_despesa_conta1`
    FOREIGN KEY (`conta_id`)
    REFERENCES `financa`.`conta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `financa`.`receita`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `financa`.`receita` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `valor` DOUBLE NOT NULL COMMENT '',
  `data` DATE NOT NULL COMMENT '',
  `descricao` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  `recebido` TINYINT(4) NOT NULL COMMENT '',
  `conta_id` INT(10) UNSIGNED NOT NULL COMMENT '',
  `categoria_id` INT(10) UNSIGNED NOT NULL COMMENT '',
  PRIMARY KEY (`id`, `conta_id`, `categoria_id`)  COMMENT '',
  INDEX `fk_receita_conta1_idx` (`conta_id` ASC)  COMMENT '',
  INDEX `fk_receita_categoria1_idx` (`categoria_id` ASC)  COMMENT '',
  CONSTRAINT `fk_receita_categoria1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `financa`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_receita_conta1`
    FOREIGN KEY (`conta_id`)
    REFERENCES `financa`.`conta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
