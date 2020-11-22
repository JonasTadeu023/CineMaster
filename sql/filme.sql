CREATE TABLE `locamaster`.`filme` ( `filme_titulo` VARCHAR(50) NOT NULL , 
	`filme_sinopse` VARCHAR(500) NOT NULL , 
	`filme_categoria` VARCHAR(50) NOT NULL , 
	`filme_tempo` FLOAT(7) NOT NULL , 
	`filme_imagem` BOOLEAN NOT NULL , 
	`filme_audio` BOOLEAN NOT NULL , 
	`filme_id` INT(2) NOT NULL AUTO_INCREMENT , 
	PRIMARY KEY (`filme_id`)) ENGINE = InnoDB;