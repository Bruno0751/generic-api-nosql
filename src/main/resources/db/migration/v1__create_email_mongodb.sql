-- ms_email.email_mongodb definition

CREATE TABLE `email_mongodb` (
  `id_email` binary(16) NOT NULL,
  `email_from` varchar(255) DEFAULT NULL,
  `email_to` varchar(255) NOT NULL,
  `id` varchar(255) DEFAULT NULL,
  `send_data_time` datetime(6) DEFAULT NULL,
  `status_email` tinyint DEFAULT NULL,
  `subject` varchar(255) NOT NULL,
  `text` text NOT NULL,
  PRIMARY KEY (`id_email`),
  CONSTRAINT `email_mongodb_chk_1` CHECK ((`status_email` between 0 and 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;