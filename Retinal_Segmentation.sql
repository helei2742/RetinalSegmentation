/*
 Navicat Premium Data Transfer

 Source Server         : macLocal
 Source Server Type    : MySQL
 Source Server Version : 80029 (8.0.29)
 Source Host           : localhost:3306
 Source Schema         : Retinal_Segmentation

 Target Server Type    : MySQL
 Target Server Version : 80029 (8.0.29)
 File Encoding         : 65001

 Date: 15/04/2023 14:53:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_patient_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_patient_info`;
CREATE TABLE `tb_patient_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '病人信息id',
  `doctor_id` bigint DEFAULT NULL COMMENT '诊断医生的id也就是tb_user的主键',
  `name` varchar(30) NOT NULL COMMENT '姓名',
  `gender` bit(1) NOT NULL DEFAULT b'0' COMMENT '性别，0为男，1为女',
  `id_card` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证号',
  `native_area` varchar(30) NOT NULL COMMENT '籍贯',
  `diagnose_type` int NOT NULL COMMENT '诊断类型，0为左眼，1为右眼，2为双眼',
  `left_diagnose_record_id` bigint DEFAULT NULL COMMENT '左眼诊断记录id,tb_user_upload_record的主键',
  `right_diagnose_record_id` bigint DEFAULT NULL COMMENT '右眼诊断记录id，tb_user_upload_record的主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_valid` bit(1) DEFAULT NULL COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_patient_info
-- ----------------------------
BEGIN;
INSERT INTO `tb_patient_info` (`id`, `doctor_id`, `name`, `gender`, `id_card`, `native_area`, `diagnose_type`, `left_diagnose_record_id`, `right_diagnose_record_id`, `create_time`, `update_time`, `is_valid`) VALUES (1, 7, '何磊大哥', b'1', '522228200103280816', '上海', 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_patient_info` (`id`, `doctor_id`, `name`, `gender`, `id_card`, `native_area`, `diagnose_type`, `left_diagnose_record_id`, `right_diagnose_record_id`, `create_time`, `update_time`, `is_valid`) VALUES (2, 7, '何磊大哥', b'1', '522228200103280816', '上海', 1, NULL, NULL, '2023-04-14 12:55:11', '2023-04-14 12:55:11', b'1');
INSERT INTO `tb_patient_info` (`id`, `doctor_id`, `name`, `gender`, `id_card`, `native_area`, `diagnose_type`, `left_diagnose_record_id`, `right_diagnose_record_id`, `create_time`, `update_time`, `is_valid`) VALUES (3, 7, '何磊大哥', b'1', '522228200103280816', '上海', 2, NULL, NULL, '2023-04-14 12:56:28', '2023-04-14 12:56:28', b'1');
INSERT INTO `tb_patient_info` (`id`, `doctor_id`, `name`, `gender`, `id_card`, `native_area`, `diagnose_type`, `left_diagnose_record_id`, `right_diagnose_record_id`, `create_time`, `update_time`, `is_valid`) VALUES (4, 7, 'zhangsan1', b'1', '522228200103280816', '上海', 2, NULL, NULL, '2023-04-14 15:23:42', '2023-04-14 15:23:42', b'1');
INSERT INTO `tb_patient_info` (`id`, `doctor_id`, `name`, `gender`, `id_card`, `native_area`, `diagnose_type`, `left_diagnose_record_id`, `right_diagnose_record_id`, `create_time`, `update_time`, `is_valid`) VALUES (5, 7, 'zhangsan2', b'1', '522228200103280816', '上海', 2, NULL, NULL, '2023-04-14 15:23:42', '2023-04-14 15:23:42', b'1');
INSERT INTO `tb_patient_info` (`id`, `doctor_id`, `name`, `gender`, `id_card`, `native_area`, `diagnose_type`, `left_diagnose_record_id`, `right_diagnose_record_id`, `create_time`, `update_time`, `is_valid`) VALUES (6, 7, 'zhangsan3', b'1', '522228200103280816', '上海', 2, NULL, NULL, '2023-04-14 15:23:42', '2023-04-14 15:23:42', b'1');
INSERT INTO `tb_patient_info` (`id`, `doctor_id`, `name`, `gender`, `id_card`, `native_area`, `diagnose_type`, `left_diagnose_record_id`, `right_diagnose_record_id`, `create_time`, `update_time`, `is_valid`) VALUES (7, 7, 'zhangsan4', b'1', '522228200103280816', '上海', 2, NULL, NULL, '2023-04-14 15:23:42', '2023-04-14 15:23:42', b'1');
INSERT INTO `tb_patient_info` (`id`, `doctor_id`, `name`, `gender`, `id_card`, `native_area`, `diagnose_type`, `left_diagnose_record_id`, `right_diagnose_record_id`, `create_time`, `update_time`, `is_valid`) VALUES (8, 7, 'zhangsan5', b'1', '522228200103280816', '上海', 1, NULL, NULL, '2023-04-14 15:23:42', '2023-04-14 15:23:42', b'1');
INSERT INTO `tb_patient_info` (`id`, `doctor_id`, `name`, `gender`, `id_card`, `native_area`, `diagnose_type`, `left_diagnose_record_id`, `right_diagnose_record_id`, `create_time`, `update_time`, `is_valid`) VALUES (9, 7, 'zhangsan6', b'0', '522228200103280816', '上海', 1, NULL, 25, '2023-04-14 15:23:42', '2023-04-14 15:23:42', b'1');
INSERT INTO `tb_patient_info` (`id`, `doctor_id`, `name`, `gender`, `id_card`, `native_area`, `diagnose_type`, `left_diagnose_record_id`, `right_diagnose_record_id`, `create_time`, `update_time`, `is_valid`) VALUES (10, 7, 'zhangsan7', b'1', '522228200103280816', '上海', 1, NULL, NULL, '2023-04-14 15:23:42', '2023-04-14 15:23:42', b'1');
INSERT INTO `tb_patient_info` (`id`, `doctor_id`, `name`, `gender`, `id_card`, `native_area`, `diagnose_type`, `left_diagnose_record_id`, `right_diagnose_record_id`, `create_time`, `update_time`, `is_valid`) VALUES (11, 7, 'zhangsan8', b'1', '522228200103280816', '上海', 1, NULL, NULL, '2023-04-14 15:23:42', '2023-04-14 15:23:42', b'1');
INSERT INTO `tb_patient_info` (`id`, `doctor_id`, `name`, `gender`, `id_card`, `native_area`, `diagnose_type`, `left_diagnose_record_id`, `right_diagnose_record_id`, `create_time`, `update_time`, `is_valid`) VALUES (12, 7, 'zhangsan9', b'0', '522228200103280816', '上海', 0, NULL, NULL, '2023-04-14 15:23:42', '2023-04-14 15:23:42', b'1');
INSERT INTO `tb_patient_info` (`id`, `doctor_id`, `name`, `gender`, `id_card`, `native_area`, `diagnose_type`, `left_diagnose_record_id`, `right_diagnose_record_id`, `create_time`, `update_time`, `is_valid`) VALUES (13, 7, 'zhangsan10', b'0', '522228200103280816', '上海', 0, NULL, NULL, '2023-04-14 15:23:42', '2023-04-14 15:23:42', b'1');
INSERT INTO `tb_patient_info` (`id`, `doctor_id`, `name`, `gender`, `id_card`, `native_area`, `diagnose_type`, `left_diagnose_record_id`, `right_diagnose_record_id`, `create_time`, `update_time`, `is_valid`) VALUES (14, 7, 'zhangsan11', b'0', '522228200103280816', '上海', 0, NULL, NULL, '2023-04-14 15:23:42', '2023-04-14 15:23:42', b'1');
INSERT INTO `tb_patient_info` (`id`, `doctor_id`, `name`, `gender`, `id_card`, `native_area`, `diagnose_type`, `left_diagnose_record_id`, `right_diagnose_record_id`, `create_time`, `update_time`, `is_valid`) VALUES (15, 7, 'zhangsan12', b'0', '522228200103280816', '上海', 0, NULL, NULL, '2023-04-14 15:23:42', '2023-04-14 15:23:42', b'1');
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
  `icon` varchar(200) DEFAULT NULL COMMENT '头像图标地址',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `is_valid` bit(1) DEFAULT NULL COMMENT '用户是否有效',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` (`id`, `username`, `password`, `icon`, `email`, `is_valid`, `create_time`, `update_time`) VALUES (2, 'zhangsan', 'eestonwg2omet6quvgj2@dbd28b54928494b6097e716ae5b6f64a', 'user/icon/defalut.png', '914577981@qq.com', b'1', '2022-12-08 19:12:19', '2022-12-08 19:12:19');
INSERT INTO `tb_user` (`id`, `username`, `password`, `icon`, `email`, `is_valid`, `create_time`, `update_time`) VALUES (7, 'heleidage666', 'wgp4vfq09zx7w08ob48d@6b6f7748e8e0cf7a92169d8920df8c05', 'user/icon/defalut.png', '914577981@qq.com', b'1', '2022-12-29 14:38:05', '2022-12-29 14:38:05');
COMMIT;

-- ----------------------------
-- Table structure for tb_user_upload_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_upload_record`;
CREATE TABLE `tb_user_upload_record` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `patient_id` bigint DEFAULT NULL COMMENT '该分割记录绑定的患者id',
  `state` int DEFAULT NULL COMMENT '当前状态0:上传文件但没开始分割，1：上传文件正在分割，2:上传文件且分割完毕',
  `segmentation_time` int DEFAULT NULL COMMENT '分割耗时，毫秒',
  `src_location` varchar(255) DEFAULT NULL COMMENT '源文件地址',
  `res_location` varchar(255) DEFAULT NULL COMMENT '分割结果文件地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_valid` bit(1) DEFAULT NULL COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_user_upload_record
-- ----------------------------
BEGIN;
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (1, NULL, 1, 13, '/images/user/zhangsan/srcImages/Src_167264843996417103703056_p0_master1200.jpeg', '/images/user/zhangsan/resImages/Res_167264843996417103703056_p0_master1200.jpeg', '2023-01-02 16:34:00', '2023-01-02 16:34:50', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (2, NULL, 2, 13, '/images/user/zhangsan/srcImages/Src_167264843996417103703056_p0_master1200.jpeg', '/images/user/zhangsan/resImages/Res_167264843996417103703056_p0_master1200.jpeg', '2023-01-02 16:34:00', '2023-01-02 16:34:50', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (3, NULL, 2, 13, '/images/user/zhangsan/srcImages/Src_167264843996417103703056_p0_master1200.jpeg', '/images/user/zhangsan/resImages/Res_167264843996417103703056_p0_master1200.jpeg', '2023-01-02 16:34:00', '2023-01-02 16:34:50', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (4, NULL, 2, 13, '/images/user/zhangsan/srcImages/Src_167264843996417103703056_p0_master1200.jpeg', '/images/user/zhangsan/resImages/Res_167264843996417103703056_p0_master1200.jpeg', '2023-01-02 16:34:00', '2023-01-02 16:34:50', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (5, NULL, 2, 23, '/images/user/zhangsan/srcImages/Src_167264869075138103703056_p0_master1200.jpeg', '/images/user/zhangsan/resImages/Res_167264869075138103703056_p0_master1200.jpeg', '2023-01-02 16:38:11', '2023-01-02 16:38:22', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (6, NULL, 2, 17, '/images/user/zhangsan/srcImages/Src_167309002391079DX.jpg', '/images/user/zhangsan/resImages/Res_167309002391079DX.jpg', '2023-01-07 19:13:44', '2023-01-13 15:01:06', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (7, NULL, 2, 9, '/images/user/zhangsan/srcImages/Src_167359518956971DX.jpg', '/images/user/zhangsan/resImages/Res_167359518956971DX.jpg', '2023-01-13 15:33:10', '2023-01-13 15:33:45', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (8, NULL, 0, NULL, '/images/user/heleidage666/srcImages/Src_167636149682391DX.jpg', NULL, '2023-02-14 15:58:17', '2023-02-14 15:58:17', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (9, NULL, 0, NULL, '/images/user/zhangsan/srcImages/Src_167636173235819DX.jpg', NULL, '2023-02-14 16:02:12', '2023-02-14 16:02:12', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (10, NULL, 2, 29, '/images/user/zhangsan/srcImages/Src_167636273952144DX.jpg', '/images/user/zhangsan/resImages/Res_167636273952144DX.jpg', '2023-02-14 16:19:00', '2023-03-04 23:52:52', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (11, NULL, 1, NULL, '/images/user/zhangsan/srcImages/Src_167636293521382DX.jpg', NULL, '2023-02-14 16:22:15', '2023-02-14 16:22:15', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (12, NULL, 0, NULL, '/images/user/zhangsan/srcImages/Src_3628_blob', NULL, '2023-02-20 13:14:15', '2023-02-20 13:14:15', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (13, NULL, 0, NULL, '/images/user/zhangsan/srcImages/Src_4611_blob', NULL, '2023-02-20 13:20:20', '2023-02-20 13:20:20', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (14, NULL, 1, NULL, '/images/user/zhangsan/srcImages/Src_8604_103729470_p0_master1200.jpeg', NULL, '2023-02-20 13:29:58', '2023-02-20 13:29:58', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (15, NULL, 2, 27, '/images/user/zhangsan/srcImages/Src_2425_Snipaste_2022-12-21_10-05-26.png', '/images/user/zhangsan/resImages/Res_2425_Snipaste_2022-12-21_10-05-26.png', '2023-02-20 13:43:02', '2023-02-20 14:08:16', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (16, NULL, 0, NULL, '/images/user/zhangsan/srcImages/Src_4984_test2.png', NULL, '2023-03-06 15:57:36', '2023-03-06 15:57:36', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (17, NULL, 2, NULL, '/images/user/zhangsan/srcImages/Src_5518_test2.png', '/images/im0003.png', '2023-03-06 15:57:44', '2023-03-06 15:57:44', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (18, NULL, 2, 78, '/images/user/zhangsan/srcImages/Src_8419_2022-9实习证明.jpg', '/Users/helei/develop/ideaworkspace/RetinalSegmentation/images/user/zhangsan/resImages/Res_8419_2022-9实习证明.jpg', '2023-03-25 15:15:14', '2023-03-25 15:15:14', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (19, NULL, 0, NULL, '/images/user/zhangsan/srcImages/Src_1112_2022-9实习证明.jpg', NULL, '2023-03-25 15:19:02', '2023-03-25 15:19:02', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (20, NULL, 0, NULL, '/images/user/zhangsan/srcImages/Src_3266_2022-9实习证明.jpg', NULL, '2023-03-25 15:28:07', '2023-03-25 15:28:07', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (21, NULL, 0, NULL, '/images/user/zhangsan/srcImages/Src_1748_2022-12实习证明.jpg', NULL, '2023-03-25 15:35:45', '2023-03-25 15:35:45', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (22, NULL, 0, NULL, '/images/user/zhangsan/srcImages/Src_3916_im0003.png', NULL, '2023-03-25 15:39:21', '2023-03-25 15:39:21', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (23, NULL, 0, NULL, '/images/user/zhangsan/srcImages/Src_0522_im0002.png', NULL, '2023-03-25 15:40:14', '2023-03-25 15:40:14', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (24, NULL, 0, NULL, '/images/user/zhangsan/srcImages/Src_7410_IMG_0280.jpg', NULL, '2023-03-25 15:41:00', '2023-03-25 15:41:00', b'1');
INSERT INTO `tb_user_upload_record` (`id`, `patient_id`, `state`, `segmentation_time`, `src_location`, `res_location`, `create_time`, `update_time`, `is_valid`) VALUES (25, 9, 2, 98, '/images/user/heleidage666/srcImages/Src_6588_1681439831179src_img.png', '/images/user/heleidage666/resImages/Res_6588_1681439831179src_img.png', '2023-04-14 10:37:11', '2023-04-14 10:37:11', b'1');
COMMIT;

-- ----------------------------
-- Table structure for tb_user_user_upload_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_user_upload_record`;
CREATE TABLE `tb_user_user_upload_record` (
  `user_id` bigint DEFAULT NULL,
  `record_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tb_user_user_upload_record
-- ----------------------------
BEGIN;
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 1);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 2);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 3);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 4);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 5);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 6);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 7);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (7, 8);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 9);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 10);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 11);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 12);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 13);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 14);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 15);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 16);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 17);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 18);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 19);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 20);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 21);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 22);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 23);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (2, 24);
INSERT INTO `tb_user_user_upload_record` (`user_id`, `record_id`) VALUES (7, 25);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
