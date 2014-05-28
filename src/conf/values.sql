insert into Bank(id,name)
values
(2,'OneBank');

insert into Postal(code,district)
values
(2635,'Ishøj'),
(3400,'Hillerød'),
(2200,'Nørrebro');

insert into Roles(id, name)
values
(1, 'Customer'),
(2, 'Employee'),
(3, 'SuperEmployee');

INSERT INTO Persons (email,cpr,bank_id,title,firstName,lastName,Street,phone,zipcode,password,role_id) 
VALUES 
('pl@bank.dk','242232-2301',2, 'mr', 'Peter', 'Larsen', 'månevej 12', 24322432, 2635,'onebank',2),
('pil@bank.dk','242232-2302',2, 'mrs', 'Pia', 'Larsen', 'månevej 12', 24322432, 2635,'onebank',3),
('tt@bank.dk','242232-2303',2, 'mr', 'Torben', 'Torbensen', 'månevej 14', 23326439, 3400,'onebank',1),
('hh@bank.dk','242232-2304',2, 'mrs', 'Hans', 'Hansen', 'månevej 13', 24322432, 2200,'onebank',1),
('ll@bank.dk','242232-2305',2, 'mrs', 'Lars', 'Larsen', 'månevej 14', 14322437, 2200,'onebank',1);


insert into Employees(email, salary, dateOfEmployment)
values
('pl@bank.dk', 30000.2, date(2001-01-01)),
('pil@bank.dk', 30000.2, date(2001-01-01));

insert into AccountType(type, id)
values
('Checking Account',1),
('Time Deposit Account',2),
('Money Market Account',3);

insert into Accounts(account_nr, bank_id, owner_cpr, manager_mail, balance, interest, type_id)
values
('1000',2,'242232-2304','pl@bank.dk', 80000, 2, 1),
('1001',2,'242232-2304','pil@bank.dk', 80000, 2, 2),
('1002',2,'242232-2303','pl@bank.dk', 80000, 2, 1),
('1003',2,'242232-2305','pil@bank.dk', 80000, 2, 1),
('1004',2,'242232-2305','pl@bank.dk', 80000, 2, 3);


insert into Checking(account_nr)
values
('1000'),
('1002'),
('1003');

insert into TimeDeposit(account_nr, release_date)
values
('1001', date(2015-01-01));

insert into MoneyMarket(account_nr, min_balance)
values
('1004', 20000.2);
