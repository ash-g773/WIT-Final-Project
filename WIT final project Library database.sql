create database BookLibrary;
use BookLibrary;

-- employee
create table employee(
employeeId int primary key,
employeeName varchar(25),
password varchar(25),
bookQuantity int);

insert into employee
values(1, "Bob", "password1", 2),
(2, "Martin", "password2", 4),
(3, "Sarah", "password3", 5),
(4, "Alice", "password4", 0);

select * from employee;

-- book
create table book(
bookId int primary key, 
bookName varchar(500),
bookType varchar(25),
bookAuthor varchar(30),
bookDescription varchar(3000),
numberOfCopies int);


-- inserting into book
insert into book
values(111, "The Founders: Elon Musk, Peter Thiel, and the Company that Made the Modern Internet", "Data Analytics", "Jimmy Soni", "In The Founders, award-winning author Jimmy Soni narrates how a once-in-a-generation collaboration turned a scrappy start-up into one of the most successful businesses of all time. Facing bruising competition, internal strife, the emergence of widespread online fraud, and the devastating dot-com bust of the 2000s, their success was anything but certain. But they would go on to change our world forever.", 100),
(222, "Steve Jobs ","Technology", "Walter Isaacson", "Steve Jobs is the authorized self-titled biography of American business magnate and Apple co-founder Steve Jobs. The book was written at the request of Jobs by Walter Isaacson, a former executive at CNN and TIME who has written best-selling biographies of Benjamin Franklin and Albert Einstein.", 50),
(333, "Elon Musk: Tesla, SpaceX, and the Quest for a Fantastic Future", "Management", "Ashlee Vans", "Elon Musk: Tesla, SpaceX, and the Quest for a Fantastic Future is Ashlee Vance's biography of Elon Musk, published in 2015. The book traces Elon Musk's life from his childhood up to the time he spent at Zip2 and PayPal, and then onto SpaceX, Tesla, and SolarCity. ", 1000),
(444, "Big Data: A Revolution That Will Transform How We Live, Work, and Think", "Data Analytics", "Viktor Mayer-SchÃ¶nberger", "A revelatory exploration of the hottest trend in technology and the dramatic impact it will have on the economy, science, and society at large.", 38),
(555, "Deep Learning", "Data Analytics", "Yoshua Benigo", "An introduction to a broad range of topics in deep learning, covering mathematical and conceptual background, deep learning techniques used in industry, and research perspectives.", 255),
(666, "Leaders Eat Last", "Management", "Simon Sinek", "Leadership is not a rank, it is a responsibility. Leadership is not about being in charge, it is about taking care of those in your charge.", 82),
(777, "The Making of a Manager: What to Do When Everyone Looks to You", "Management", "Julie Zhou", "No idea what you're doing? No problem. Good managers are made, not born. ", 15),
(888, "The Age of Surveillance Capitalism", "Management", "Shoshana Zuboff", "The Age of Surveillance Capitalism: The Fight for a Human Future at the New Frontier of Power is a 2019 non-fiction book by Shoshana Zuboff which looks at the development of digital companies like Google and Amazon, and suggests that their business models represent a new form of capitalist accumulation that she calls surveillance capitalism.", 2),
(999, "Race After Technology", "Management", "Ruha Benjamin", "Race After Technology: Abolitionist Tools for the New Jim Code is a 2019 American book focusing on a range of ways in which social hierarchies, particularly racism, are embedded in the logical layer of internet-based technologies.", 8),
(112, "Lean In: Women, Work, and the Will to Lead", "Management", "Sheryl Sandberg", "Lean In: Women, Work, and the Will to Lead is a 2013 book encouraging women to assert themselves at work and at home, co-written by business executive Sheryl Sandberg and media writer Nell Scovell.", 30),
(113, "Lean Out", "Management", "Dawn Foster", "In her powerful debut work Lean Out, acclaimed journalist Dawn Foster unpicks how the purportedly feminist message of Sandberg's book neatly exempts patriarchy, capitalism and business from any responsibility for changing the position of women in contemporary culture. It looks at the rise of a corporate '1% feminism', and at how feminism has been defanged and depoliticised at a time when women have borne the brunt of the financial crash and the gap between rich and poor is widening faster than ever.", 5),
(114, "Pivot: The Only Move That Matters Is Your Next One", "Management", "Jenny Blake", "What's next? is a question we all have to ask and answer more frequently in an economy where the average job tenure is only four years, roles change constantly even within that time, and smart, motivated people find themselves hitting professional plateaus.", 48),
(115, "Numsense! Data Science for the Layman: No Math Added", "Data Analytics", "Annalyn Ng", "Used in Stanford's CS102 Big Data (Spring 2017) course. Want to get started on data science? Our promise: no math added. This book has been written in layman's terms as a gentle introduction to data science and its algorithms.", 77),
(116, "Artificial Intelligence: A Guide for Thinking Humans", "Data Analytics", "Melanie Mitchell", "Artificial Intelligence: A Guide for Thinking Humans is a 2019 nonfiction book by Santa Fe Institute professor Melanie Mitchell. The book provides an overview of artificial intelligence technology, and argues that people tend to overestimate the abilities of artificial intelligence.", 18),
(117, "Invisible Women: Exposing Data Bias in a World Designed for Men", "Data Analytics", "Caroline Criado-Perez", "Invisible Women: Exposing Data Bias in a World Designed for Men is a 2019 book by British feminist author Caroline Criado Perez. The book describes the adverse effects on women caused by gender bias in big data collection. ", 0);

select * from book;

-- library
create table library(
transactionId varChar(30) primary key,
employeeId int,
employeeName varchar(25),
bookId int,
bookType varchar(25),
issueDate date,
expectedReturnDate date,
returnDate date, -- set as today -> default(current_date())
lateFee int,
numberOfCopies int);

-- inserting into library 
insert into library(transactionId, employeeId, employeeName, bookId, bookType, issueDate, expectedReturnDate, returnDate, lateFee, numberOfCopies)
values("11112022-10-01", 1, "Bob", 111, "Data Analytics", '2022-10-01', '2022-10-08', '2022-10-08', 0, 1), -- extremely late
("11122022-12-15", 1, "Bob", 112, "Management", '2022-12-15', '2022-12-22', '2022-12-22', 0, 1), -- not late
("27772022-12-02", 2, "Martin", 777, "Management", '2022-12-02', '2022-12-09', '2022-12-09', 0, 3), -- medium late
("29992022-12-15", 2, "Martin", 999, "Management", '2022-12-15', '2022-12-22', '2022-12-22', 0, 1), -- not late
("38882022-12-07", 3, "Sarah", 888, "Management", '2022-12-07', '2022-12-14', '2022-12-14', 0, 2), -- small late
("38882022-12-15", 3, "Sarah", 222, "Technology", '2022-12-15', '2022-12-22', '2022-12-21', 0, 3); -- not late

select * from library; 