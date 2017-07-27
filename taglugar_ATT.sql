CREATE TABLE IF NOT EXISTS `taglugar` (
  `idtaglugar` int(11) NOT NULL,
  `idtag` int(11) NOT NULL,
  `idlugar` int(11) NOT NULL,
  PRIMARY KEY (`idtaglugar`),
  FOREIGN KEY (`idlugar`) REFERENCES `cidade` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (`idtag`) REFERENCES `tag` (`idtag`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('1', '2','1');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('2', '2','5');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('3', '2','10');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('4', '4','12');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('5', '7','31');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('6', '7','32');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('7', '7','43');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('8', '7','34');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('9', '7','35');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('10', '8','43');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('11', '8', '44');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('12', '8', '45');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('13', '8', '46');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('14', '8', '47');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('15', '8', '48');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('16', '8', '49');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('17', '8', '50');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('18', '8', '51');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('19', '8', '52');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('20', '8', '53');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('21', '8', '6');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('22', '8', '35');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('23', '4', '11');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('24', '4', '12');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('25', '4', '13');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('26', '4', '14');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('27', '4', '15');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('28', '4', '16');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('29', '4', '17');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('30', '4', '18');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('31', '3', '19');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('32', '3', '20');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('33', '3', '9');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('34', '3', '21');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('35', '3', '22');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('36', '3', '23');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('37', '3', '24');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('38', '3', '25');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('39', '3', '26');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('40', '3', '27');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('41', '3', '28');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('42', '3', '29');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('43', '3', '30');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('44', '2', '1');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('45', '2', '2');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('46', '2', '3');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('47', '2', '4');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('48', '2', '5');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('49', '2', '6');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('50', '2', '7');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('51', '2', '8');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('52', '2', '9');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('53', '2', '10');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('54', '6', '54');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('55', '6', '55');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('56', '6', '56');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('57', '6', '42');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('58', '6', '6');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('59', '6', '54');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('60', '6', '55');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('61', '6', '56');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('62', '6', '42');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('63', '6', '6');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('64', '5', '36');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('65', '5', '37');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('66', '5', '13');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('67', '5', '39');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('68', '5', '40');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('69', '5', '41');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('70', '5', '42');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('71', '5', '5');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('72', '9', '38');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('73', '9', '57');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('74', '9', '58');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('75', '9', '59');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('76', '9', '60');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('77', '9', '61');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('78', '9', '62');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('79', '1', '63');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('80', '1', '64');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('81', '1', '65');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('82', '1', '66');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('83', '1', '11');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('84', '1', '67');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('85', '1', '68');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('86', '1', '69');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('87', '1', '70');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('88', '1', '71');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('89', '1', '72');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('90', '1', '15');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('91', '1', '7');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('92', '1', '73');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('93', '1', '74');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('94', '1', '75');
INSERT INTO `whereverigo`.`taglugar` (`idtaglugar`, `idtag`, `idlugar`) VALUES ('95', '1', '76');



