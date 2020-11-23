CREATE TABLE `question2Q` (
  `question2Q_id` int(11) NOT NULL,
  `question2Q_name` varchar(1000) DEFAULT NULL,
  `inspection_id` int(11) DEFAULT NULL,
  `status` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `question8Q` (
  `question8Q_id` int(11) NOT NULL,
  `question8Q_name` varchar(1000) DEFAULT NULL,
  `inspection_id` int(11) DEFAULT NULL,
  `status` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE `question8Q`
  ADD PRIMARY KEY (`question8Q_id`);
 
 ALTER TABLE `question2Q`
  ADD PRIMARY KEY (`question2Q_id`);
 
INSERT INTO `question2Q` (`question2Q_id`, `question2Q_name`, `inspection_id`, `status`) VALUES
(1, 'ใน 2 สัปดาห์ที่ผ่านมา รวมวันนี้ท่านรู้สึก หดหู่ เศร้า หรือท้อเเท้สิ้นหวัง หรือไม่', 2, 'A'),
(2, 'ใน 2 สัปดาห์ที่ผ่านมา รวมวันนี้ท่านรู้สึก เบื่อ ทำอะไรก็ไม่เพลิดเพลิน หรือไม่', 2, 'A');

INSERT INTO `question8Q` (`question8Q_id`, `question8Q_name`, `inspection_id`, `status`) VALUES
(1, 'คิดอยากตาย หรือ คิดว่าตายไปจะดีกว่า', 2, 'A'),
(2, 'อยากทำร้ายตัวเอง หรือ ทำให้ตัวเองบาดเจ็บ', 2, 'A'),
(3, 'ในช่วง 1 เดือนที่ผ่านมารวมวันนี้ คิดเกี่ยวกับการฆ่าตัวตาย', 2, 'A'),
(4, 'ในช่วง 1 เดือนที่ผ่านมารวมวันนี้ (ถ้าตอบว่าคิดเกี่ยวกับฆ่าตัวตายให้ถามต่อ)....ท่านสามารถควบคุมความอยากฆ่าตัวตายที่ท่านคิดอยู่นั้นได้หรือไม่ หรือบอกได้ไหมว่าคงจะไม่ทำตามความคิดนั้นในขณะนี้', 2, 'A'),
(5, 'ในช่วง 1 เดือนที่ผ่านมารวมวันนี้ มีแผนการที่จะฆ่าตัวตาย', 2, 'A'),
(6, 'ในช่วง 1 เดือนที่ผ่านมารวมวันนี้  ได้เตรียมการที่จะทำร้ายตัวเองหรือเตรียมการฆ่าตัวตายโดยตั้งใจว่าจะให้ตายจริง ๆ', 2, 'A'),
(7, 'ได้ทำให้ตนเองบาดเจ็บแต่ไม่ได้ตั้งใจที่จะทำให้เสียชีวิต', 2, 'A'),
(8, 'ได้พยายามฆ่าตัวตายโดยคาดหวัง/ตั้งใจที่จะให้ตาย', 2, 'A'),
(9, 'ตลอดชีวิตที่ผ่านมา ท่านเคยพยายามฆ่าตัวตาย', 2, 'A');
