/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.6.10 : Database - hibernatetest
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hibernatetest` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `hibernatetest`;

/*Table structure for table `dobavlja` */

DROP TABLE IF EXISTS `dobavlja`;

CREATE TABLE `dobavlja` (
  `dobavljacID` int(11) NOT NULL,
  `proizvodID` int(11) NOT NULL,
  PRIMARY KEY (`dobavljacID`,`proizvodID`),
  CONSTRAINT `FK_dobavlja` FOREIGN KEY (`dobavljacID`) REFERENCES `dobavljac` (`dobavljacID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `dobavlja` */

insert  into `dobavlja`(`dobavljacID`,`proizvodID`) values (1,1),(1,3),(2,1),(3,2),(4,4),(4,5),(5,6),(6,6);

/*Table structure for table `dobavljac` */

DROP TABLE IF EXISTS `dobavljac`;

CREATE TABLE `dobavljac` (
  `dobavljacID` int(11) NOT NULL,
  `naziv` text,
  `adresa` text,
  PRIMARY KEY (`dobavljacID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `dobavljac` */

insert  into `dobavljac`(`dobavljacID`,`naziv`,`adresa`) values (1,'Soko Stark','Beograd'),(2,'Swiss Lion','Gornji Milanovac'),(3,'Crvenka','Crvenka'),(4,'Centroprom','Beograd'),(5,'Sloboda','Cacak'),(6,'Sarajevska pita','Beograd, Svetogorska'),(7,'Aroma Kotor','Beograd, Sarajevska 41');

/*Table structure for table `prehrambeniproizvod` */

DROP TABLE IF EXISTS `prehrambeniproizvod`;

CREATE TABLE `prehrambeniproizvod` (
  `proizvodID` int(11) NOT NULL,
  `roktrajanja` text,
  `sastav` text,
  PRIMARY KEY (`proizvodID`),
  CONSTRAINT `FK_prehrambeniproizvod` FOREIGN KEY (`proizvodID`) REFERENCES `proizvod` (`proizvodID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `prehrambeniproizvod` */

insert  into `prehrambeniproizvod`(`proizvodID`,`roktrajanja`,`sastav`) values (1,'6 mjeseci','Mlijeko, jaja, kakao, E330'),(2,'4 mjeseca','Jaja, mlijeko, kakao, vjestacka boja, E330'),(3,'1 godina','Krompir'),(8,'12 mjeseci','Kafa arabika');

/*Table structure for table `proizvod` */

DROP TABLE IF EXISTS `proizvod`;

CREATE TABLE `proizvod` (
  `proizvodID` int(11) NOT NULL,
  `naziv` text,
  `cena` double DEFAULT NULL,
  PRIMARY KEY (`proizvodID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `proizvod` */

insert  into `proizvod`(`proizvodID`,`naziv`,`cena`) values (1,'pera',89),(2,'peric',10),(3,'Smoki',25),(4,'Bla',0),(5,'BlaBla',0),(6,'Kotorska krempita',100),(7,'Pegla',1500),(8,'Kafa',100),(9,'Kesa za vino',100),(55,'Brasno',40);

/*Table structure for table `racun` */

DROP TABLE IF EXISTS `racun`;

CREATE TABLE `racun` (
  `racunID` int(11) NOT NULL,
  `datum` date DEFAULT NULL,
  PRIMARY KEY (`racunID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `racun` */

insert  into `racun`(`racunID`,`datum`) values (111,'2017-10-24');

/*Table structure for table `stavkaracuna` */

DROP TABLE IF EXISTS `stavkaracuna`;

CREATE TABLE `stavkaracuna` (
  `racunID` int(11) NOT NULL,
  `rb` int(11) NOT NULL,
  `proizvodID` int(11) DEFAULT NULL,
  `kolicina` double DEFAULT '0',
  PRIMARY KEY (`racunID`,`rb`),
  KEY `FK_stavkaracuna_2` (`proizvodID`),
  CONSTRAINT `FK_stavkaracuna` FOREIGN KEY (`racunID`) REFERENCES `racun` (`racunID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_stavkaracuna_2` FOREIGN KEY (`proizvodID`) REFERENCES `proizvod` (`proizvodID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `stavkaracuna` */

insert  into `stavkaracuna`(`racunID`,`rb`,`proizvodID`,`kolicina`) values (111,1,1,3),(111,2,2,1);

/*Table structure for table `tehnickiproizvod` */

DROP TABLE IF EXISTS `tehnickiproizvod`;

CREATE TABLE `tehnickiproizvod` (
  `proizvodID` int(11) NOT NULL,
  `tehnickeosobine` text,
  `garancija` int(11) DEFAULT NULL,
  `uputstvo` text,
  PRIMARY KEY (`proizvodID`),
  CONSTRAINT `FK_tehnickiproizvod` FOREIGN KEY (`proizvodID`) REFERENCES `proizvod` (`proizvodID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tehnickiproizvod` */

insert  into `tehnickiproizvod`(`proizvodID`,`tehnickeosobine`,`garancija`,`uputstvo`) values (6,'2 ringle\r\nRerna',1,'Ukljucis i uzivas!'),(7,'2000w, Baterija 12V',1,'Ukljucis, peglas, pakujes.');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
