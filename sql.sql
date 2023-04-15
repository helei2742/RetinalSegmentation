/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.5.56 : Database - retinal_segmentation
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `tb_diagnostic_record` */

DROP TABLE IF EXISTS `tb_diagnostic_record`;

CREATE TABLE `tb_diagnostic_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `doctor_id` bigint(20) DEFAULT NULL,
  `record_id` bigint(20) DEFAULT NULL,
  `patient_id` bigint(20) DEFAULT NULL,
  `diagnose_text` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_valid` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `record_id` (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_diagnostic_record` */

insert  into `tb_diagnostic_record`(`id`,`doctor_id`,`record_id`,`patient_id`,`diagnose_text`,`create_time`,`update_time`,`is_valid`) values 
(1,7,30,5,'             start\ndawdawdawdaw\n\nend','2023-04-15 17:45:05','2023-04-15 18:13:44','');

/*Table structure for table `tb_patient_info` */

DROP TABLE IF EXISTS `tb_patient_info`;

CREATE TABLE `tb_patient_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `doctor_id` bigint(20) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `gender` bit(1) NOT NULL DEFAULT b'0',
  `id_card` varchar(30) NOT NULL,
  `email` varchar(40) DEFAULT NULL,
  `native_area` varchar(30) NOT NULL,
  `diagnose_type` int(11) NOT NULL,
  `left_diagnose_record_id` bigint(20) DEFAULT NULL,
  `right_diagnose_record_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_valid` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `tb_patient_info` */

insert  into `tb_patient_info`(`id`,`doctor_id`,`name`,`gender`,`id_card`,`email`,`native_area`,`diagnose_type`,`left_diagnose_record_id`,`right_diagnose_record_id`,`create_time`,`update_time`,`is_valid`) values 
(1,7,'何磊大哥','','522228200103280816',NULL,'上海',1,NULL,NULL,NULL,NULL,NULL),
(2,7,'何磊大哥','','522228200103280816',NULL,'上海',1,NULL,NULL,'2023-04-14 12:55:11','2023-04-14 12:55:11',''),
(3,7,'何磊大哥','','522228200103280816',NULL,'上海',2,NULL,NULL,'2023-04-14 12:56:28','2023-04-14 12:56:28',''),
(4,7,'zhangsan1','','522228200103280816',NULL,'上海',2,NULL,NULL,'2023-04-14 15:23:42','2023-04-14 15:23:42',''),
(5,7,'zhangsan2','','522228200103280816',NULL,'上海',2,30,NULL,'2023-04-14 15:23:42','2023-04-14 15:23:42',''),
(6,7,'zhangsan3','','522228200103280816',NULL,'上海',2,NULL,NULL,'2023-04-14 15:23:42','2023-04-14 15:23:42',''),
(7,7,'zhangsan4','','522228200103280816',NULL,'上海',2,NULL,NULL,'2023-04-14 15:23:42','2023-04-14 15:23:42',''),
(8,7,'zhangsan5','','522228200103280816',NULL,'上海',1,NULL,NULL,'2023-04-14 15:23:42','2023-04-14 15:23:42',''),
(9,7,'zhangsan6','\0','522228200103280816',NULL,'上海',1,NULL,25,'2023-04-14 15:23:42','2023-04-14 15:23:42',''),
(10,7,'zhangsan7','','522228200103280816',NULL,'上海',1,NULL,NULL,'2023-04-14 15:23:42','2023-04-14 15:23:42',''),
(11,7,'zhangsan8','','522228200103280816',NULL,'上海',1,NULL,NULL,'2023-04-14 15:23:42','2023-04-14 15:23:42',''),
(12,7,'zhangsan9','\0','522228200103280816',NULL,'上海',0,NULL,NULL,'2023-04-14 15:23:42','2023-04-14 15:23:42',''),
(13,7,'zhangsan10','\0','522228200103280816',NULL,'上海',0,NULL,NULL,'2023-04-14 15:23:42','2023-04-14 15:23:42',''),
(14,7,'zhangsan11','\0','522228200103280816',NULL,'上海',0,NULL,NULL,'2023-04-14 15:23:42','2023-04-14 15:23:42',''),
(15,7,'zhangsan12','\0','522228200103280816',NULL,'上海',0,NULL,NULL,'2023-04-14 15:23:42','2023-04-14 15:23:42','');

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL COMMENT '用户名',
  `password` varchar(200) DEFAULT NULL COMMENT '密码',
  `icon` varchar(200) DEFAULT NULL COMMENT '头像图标地址',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `is_valid` bit(1) DEFAULT NULL COMMENT '用户是否有效',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`username`,`password`,`icon`,`email`,`is_valid`,`create_time`,`update_time`) values 
(2,'zhangsan','eestonwg2omet6quvgj2@dbd28b54928494b6097e716ae5b6f64a','user/icon/defalut.png','914577981@qq.com','','2022-12-08 19:12:19','2022-12-08 19:12:19'),
(7,'heleidage666','wgp4vfq09zx7w08ob48d@6b6f7748e8e0cf7a92169d8920df8c05','user/icon/defalut.png','914577981@qq.com','','2022-12-29 14:38:05','2022-12-29 14:38:05');

/*Table structure for table `tb_user_upload_record` */

DROP TABLE IF EXISTS `tb_user_upload_record`;

CREATE TABLE `tb_user_upload_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `patient_id` bigint(20) DEFAULT NULL,
  `state` int(11) DEFAULT NULL COMMENT '当前状态0:上传文件但没开始分割，1：上传文件正在分割，2:上传文件且分割完毕',
  `segmentation_time` int(11) DEFAULT NULL COMMENT '分割耗时，毫秒',
  `src_location` varchar(255) DEFAULT NULL COMMENT '源文件地址',
  `res_location` varchar(255) DEFAULT NULL COMMENT '分割结果文件地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_valid` bit(1) DEFAULT NULL COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `tb_user_upload_record` */

insert  into `tb_user_upload_record`(`id`,`patient_id`,`state`,`segmentation_time`,`src_location`,`res_location`,`create_time`,`update_time`,`is_valid`) values 
(1,NULL,1,13,'/images/user/zhangsan/srcImages/Src_167264843996417103703056_p0_master1200.jpeg','/images/user/zhangsan/resImages/Res_167264843996417103703056_p0_master1200.jpeg','2023-01-02 16:34:00','2023-01-02 16:34:50',''),
(2,NULL,2,13,'/images/user/zhangsan/srcImages/Src_167264843996417103703056_p0_master1200.jpeg','/images/user/zhangsan/resImages/Res_167264843996417103703056_p0_master1200.jpeg','2023-01-02 16:34:00','2023-01-02 16:34:50',''),
(3,NULL,2,13,'/images/user/zhangsan/srcImages/Src_167264843996417103703056_p0_master1200.jpeg','/images/user/zhangsan/resImages/Res_167264843996417103703056_p0_master1200.jpeg','2023-01-02 16:34:00','2023-01-02 16:34:50',''),
(4,NULL,2,13,'/images/user/zhangsan/srcImages/Src_167264843996417103703056_p0_master1200.jpeg','/images/user/zhangsan/resImages/Res_167264843996417103703056_p0_master1200.jpeg','2023-01-02 16:34:00','2023-01-02 16:34:50',''),
(5,NULL,2,23,'/images/user/zhangsan/srcImages/Src_167264869075138103703056_p0_master1200.jpeg','/images/user/zhangsan/resImages/Res_167264869075138103703056_p0_master1200.jpeg','2023-01-02 16:38:11','2023-01-02 16:38:22',''),
(6,NULL,2,17,'/images/user/zhangsan/srcImages/Src_167309002391079DX.jpg','/images/user/zhangsan/resImages/Res_167309002391079DX.jpg','2023-01-07 19:13:44','2023-01-13 15:01:06',''),
(7,NULL,2,9,'/images/user/zhangsan/srcImages/Src_167359518956971DX.jpg','/images/user/zhangsan/resImages/Res_167359518956971DX.jpg','2023-01-13 15:33:10','2023-01-13 15:33:45',''),
(8,NULL,0,NULL,'/images/user/heleidage666/srcImages/Src_167636149682391DX.jpg',NULL,'2023-02-14 15:58:17','2023-02-14 15:58:17','\0'),
(9,NULL,0,NULL,'/images/user/zhangsan/srcImages/Src_167636173235819DX.jpg',NULL,'2023-02-14 16:02:12','2023-02-14 16:02:12',''),
(10,NULL,2,29,'/images/user/zhangsan/srcImages/Src_167636273952144DX.jpg','/images/user/zhangsan/resImages/Res_167636273952144DX.jpg','2023-02-14 16:19:00','2023-03-04 23:52:52',''),
(11,NULL,1,NULL,'/images/user/zhangsan/srcImages/Src_167636293521382DX.jpg',NULL,'2023-02-14 16:22:15','2023-02-14 16:22:15',''),
(12,NULL,0,NULL,'/images/user/zhangsan/srcImages/Src_3628_blob',NULL,'2023-02-20 13:14:15','2023-02-20 13:14:15',''),
(13,NULL,0,NULL,'/images/user/zhangsan/srcImages/Src_4611_blob',NULL,'2023-02-20 13:20:20','2023-02-20 13:20:20',''),
(14,NULL,1,NULL,'/images/user/zhangsan/srcImages/Src_8604_103729470_p0_master1200.jpeg',NULL,'2023-02-20 13:29:58','2023-02-20 13:29:58',''),
(15,NULL,2,27,'/images/user/zhangsan/srcImages/Src_2425_Snipaste_2022-12-21_10-05-26.png','/images/user/zhangsan/resImages/Res_2425_Snipaste_2022-12-21_10-05-26.png','2023-02-20 13:43:02','2023-02-20 14:08:16',''),
(16,NULL,0,NULL,'/images/user/zhangsan/srcImages/Src_4984_test2.png',NULL,'2023-03-06 15:57:36','2023-03-06 15:57:36',''),
(17,NULL,2,NULL,'/images/user/zhangsan/srcImages/Src_5518_test2.png','/images/im0003.png','2023-03-06 15:57:44','2023-03-06 15:57:44',''),
(18,NULL,2,78,'/images/user/zhangsan/srcImages/Src_8419_2022-9实习证明.jpg','/Users/helei/develop/ideaworkspace/RetinalSegmentation/images/user/zhangsan/resImages/Res_8419_2022-9实习证明.jpg','2023-03-25 15:15:14','2023-03-25 15:15:14',''),
(19,NULL,0,NULL,'/images/user/zhangsan/srcImages/Src_1112_2022-9实习证明.jpg',NULL,'2023-03-25 15:19:02','2023-03-25 15:19:02',''),
(20,NULL,0,NULL,'/images/user/zhangsan/srcImages/Src_3266_2022-9实习证明.jpg',NULL,'2023-03-25 15:28:07','2023-03-25 15:28:07',''),
(21,NULL,0,NULL,'/images/user/zhangsan/srcImages/Src_1748_2022-12实习证明.jpg',NULL,'2023-03-25 15:35:45','2023-03-25 15:35:45',''),
(22,NULL,0,NULL,'/images/user/zhangsan/srcImages/Src_3916_im0003.png',NULL,'2023-03-25 15:39:21','2023-03-25 15:39:21',''),
(23,NULL,0,NULL,'/images/user/zhangsan/srcImages/Src_0522_im0002.png',NULL,'2023-03-25 15:40:14','2023-03-25 15:40:14',''),
(24,NULL,0,NULL,'/images/user/zhangsan/srcImages/Src_7410_IMG_0280.jpg',NULL,'2023-03-25 15:41:00','2023-03-25 15:41:00',''),
(25,NULL,1,NULL,'\\images\\user\\heleidage666\\srcImages\\Src_0581_Image_01L.jpg',NULL,'2023-03-28 20:07:32','2023-03-28 20:07:32','\0'),
(26,NULL,2,105,'\\images\\user\\heleidage666\\srcImages\\Src_9316_Image_11L.jpg','D:\\ideaworkspace\\retinalsegmentation\\retinalsegmentation\\images\\user\\heleidage666\\resImages/Res_9316_Image_11L.jpg','2023-03-28 20:19:16','2023-03-28 20:19:16','\0'),
(27,NULL,2,4987,'\\images\\user\\zhangsan\\srcImages\\Src_0444_1680008857780Image_11L.jpg','D:\\ideaworkspace\\retinalsegmentation\\retinalsegmentation\\images\\user\\zhangsan\\resImages/Res_0444_1680008857780Image_11L.jpg','2023-03-28 21:07:37','2023-03-28 21:07:37',''),
(28,NULL,2,40682,'\\images\\user\\zhangsan\\srcImages\\Src_9134_1680012966319Image_11L.jpg','D:\\ideaworkspace\\retinalsegmentation\\retinalsegmentation\\images\\user\\zhangsan\\resImages\\Res_9134_1680012966319Image_11L.jpg','2023-03-28 22:16:06','2023-03-28 22:16:06',''),
(29,NULL,0,NULL,'\\images\\user\\zhangsan\\srcImages\\Src_8374_1680013491564Image_11R.jpg',NULL,'2023-03-28 22:24:51','2023-03-28 22:24:51',''),
(30,5,2,22174,'\\images\\user\\heleidage666\\srcImages\\Src_1248_1680013563039Image_11L.jpg','\\images\\user\\heleidage666\\resImages\\Res_1248_1680013563039Image_11L.png','2023-03-28 22:26:03','2023-03-28 22:26:03',''),
(31,NULL,0,NULL,'\\images\\user\\heleidage666\\srcImages\\Src_9348_1680016677233Image_11R.jpg',NULL,'2023-03-28 23:17:57','2023-03-28 23:17:57',''),
(32,NULL,1,NULL,'\\images\\user\\heleidage666\\srcImages\\Src_4166_168048804033521_training.png',NULL,'2023-04-03 10:14:00','2023-04-03 10:14:00','');

/*Table structure for table `tb_user_user_upload_record` */

DROP TABLE IF EXISTS `tb_user_user_upload_record`;

CREATE TABLE `tb_user_user_upload_record` (
  `user_id` bigint(20) DEFAULT NULL,
  `record_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_user_user_upload_record` */

insert  into `tb_user_user_upload_record`(`user_id`,`record_id`) values 
(2,1),
(2,2),
(2,3),
(2,4),
(2,5),
(2,6),
(2,7),
(7,8),
(2,9),
(2,10),
(2,11),
(2,12),
(2,13),
(2,14),
(2,15),
(2,16),
(2,17),
(2,18),
(2,19),
(2,20),
(2,21),
(2,22),
(2,23),
(2,24),
(7,25),
(7,26),
(2,27),
(2,28),
(2,29),
(7,30),
(7,31),
(7,32);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
