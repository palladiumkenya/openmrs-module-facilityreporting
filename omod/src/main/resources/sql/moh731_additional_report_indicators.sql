

set foreign_key_checks=0;
TRUNCATE TABLE kenyaemr_facilityreporting_indicator;
TRUNCATE TABLE kenyaemr_facilityreporting_dataset;
TRUNCATE TABLE kenyaemr_facilityreporting_report;


INSERT INTO kenyaemr_facilityreporting_report(id,uuid,name,description,mapping,date_created) 
VALUES(1,'4063de77-e17d-442c-b4a4-ccdb915b17e4','MOH 731','Monthly Report for MOH',"",'2019-03-07');

INSERT INTO kenyaemr_facilityreporting_dataset(id,uuid,name,description,mapping,report_id,date_created) 
VALUES(1,'19edbf05-90a9-478c-9b1b-520562348f6e','VMMC','MOH 731-4 Medical Male Circumcision Revision 2018','bbMNLyKCnkm',1,'2019-03-07'),
(2,'408e21a7-5fb1-4075-92e3-cd7c38e1f005','PEP','MOH 731-5 Post Exposure Prophylaxis Revision 2018','do43iCbnDrs',1,'2019-03-07'),
(3,'80af0234-5598-4b3c-8496-738dea5d26ee','Methadone Assisted Therapy','MOH 731-6 Methadone Assisted Therapy revision 2018','RRC2sWfhVqF',1,'2019-03-07');

INSERT INTO kenyaemr_facilityreporting_indicator(id,uuid,name,description,mapping,dataset_id,date_created)
VALUES(1,'cc484ce4-40b6-11e9-b210-d663bd873d93','HV04-01','MOH 731 Circumcised_<1 HV04-01','ZPcn7zMAZ1V',1,'2019-03-06'),
(2,'cc484f6e-40b6-11e9-b210-d663bd873d93','HV04-02','MOH 731 Circumcised_1-9yr HV04-02','GGKtWopm7vD',1,'2019-03-06'),
(3,'cc4850c2-40b6-11e9-b210-d663bd873d93','HV04-03','MOH 731 Circumcised_10-14 HV04-03','hx68TrqkSh1',1,'2019-03-06'),
(4,'cc48520c-40b6-11e9-b210-d663bd873d93','HV04-04','MOH 731 Circumcised_15-19 HV04-04','i99a9CT4YeV',1,'2019-03-06'),
(5,'cc48534c-40b6-11e9-b210-d663bd873d93','HV04-05','MOH 731 Circumcised_20-24 HV04-05','WyVWHKRa4i8',1,'2019-03-06'),
(6,'cc485482-40b6-11e9-b210-d663bd873d93','HV04-06','MOH 731 Circumcised_25+ HV04-06','yivJXC2pAOM',1,'2019-03-06'),
(7,'cc48586a-40b6-11e9-b210-d663bd873d93','HV04-07','MOH 731 Circumcised_Total HV04-07','dcuHzdP43Tt',1,'2019-03-06'),
(8,'cc4859b4-40b6-11e9-b210-d663bd873d93','HV04-08','MOH 731 Circumcised_ HIV+ HV04-08','H6zXw6KeKb7',1,'2019-03-06'),
(9,'cc485af4-40b6-11e9-b210-d663bd873d93','HV04-09','MOH 731 Circumcised_ HIV- HV04-09','zhY6Bj4mKt8',1,'2019-03-06'),
(10,'cc485c34-40b6-11e9-b210-d663bd873d93','HV04-10','MOH 731 Circumcised_HIV NK HV04-10','R8GqrTWygR4',1,'2019-03-06'),
(11,'cc485d6a-40b6-11e9-b210-d663bd873d93','HV04-11','MOH 731 Surgical HV04-11','JYYtp6AwPD6',1,'2019-03-06'),
(12,'cc485ea0-40b6-11e9-b210-d663bd873d93','HV04-12','MOH 731 Devices HV04-12','BOJNBgkeXBu',1,'2019-03-06'),
(13,'cc48624c-40b6-11e9-b210-d663bd873d93','HV04-13','MOH 731 AE_During_Moderate HV04-13','KzhBouepg13',1,'2019-03-06'),
(14,'cc4863aa-40b6-11e9-b210-d663bd873d93','HV04-14','MOH 731 AE_During_Severe HV04-14','zVZywwIJxcR',1,'2019-03-06'),
(15,'cc4864ea-40b6-11e9-b210-d663bd873d93','HV04-15','MOH 731 AE_Post_Moderate HV04-15','OseYV3376eB',1,'2019-03-06'),
(16,'cc486620-40b6-11e9-b210-d663bd873d93','HV04-16','MOH 731 AE_Post_Severe HV04-16','kzlGNwD5SMc',1,'2019-03-06'),
(17,'cc486756-40b6-11e9-b210-d663bd873d93','HV04-17','MOH 731 Follow up visit <14d HV04-17','ZkAhKhZxRp5',1,'2019-03-06'),
(18,'cc486882-40b6-11e9-b210-d663bd873d93','HV05-01','MOH 731 Exposed_Occupational HV05-01','v3dZh9KqTv0',2,'2019-03-06'),
(19,'cc486bde-40b6-11e9-b210-d663bd873d93','HV05-02','MOH 731 Exposed_Other HV05-02','BHbWgZR2CAQ',2,'2019-03-06'),
(20,'cc486d3c-40b6-11e9-b210-d663bd873d93','HV05-03','MOH 731 Exposed_Total HV05-03','DO8TG2tc7M0',2,'2019-03-06'),
(21,'96561750-40b7-11e9-b210-d663bd873d93','HV05-05','MOH 731 PEP_Occupational HV05-05','SMUVPzuyFAB',2,'2019-03-06'),
(22,'96561a2a-40b7-11e9-b210-d663bd873d93','HV05-06','MOH 731 PEP_Other HV05-06','KrDWcJMS9Vi',2,'2019-03-06'),
(23,'96561c64-40b7-11e9-b210-d663bd873d93','HV05-07','MOH 731 PEP_Total HV05-07','WQlvPW3w3IJ',2,'2019-03-06'),
(24,'96561e44-40b7-11e9-b210-d663bd873d93','HV06-03','MOH 731 HIV+ MAT Clients on ART HV06-03','joRfQbNfg23',3,'2019-03-06'),
(25,'96561f8e-40b7-11e9-b210-d663bd873d93','HV06-01','MOH 731 KeyPop on MAT HV06-01','a3GYG7B0AZU',3,'2019-03-06'),
(26,'96562146-40b7-11e9-b210-d663bd873d93','HV06-04','MOH 731 KeyPop who are PWID HV06-04','AzuCQPugful',3,'2019-03-06'),
(27,'9656231c-40b7-11e9-b210-d663bd873d93','HV06-02','MOH 731 MAT clients HIV+ HV06-02','OuqOHS02uvU',3,'2019-03-06');

set foreign_key_checks=1;