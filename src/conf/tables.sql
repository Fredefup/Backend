drop table Transfers;
drop table MoneyMarket;
drop table TimeDeposit;
drop table Checking;
drop table Accounts;
drop table Employees;
drop table Persons;
drop table Bank;
drop table Postal;
drop table Roles;
drop table AccountType;

drop sequence Transfer_Seq restrict;
drop sequence Account_Seq restrict;

---

create sequence Transfer_Seq as int start with 1000 increment by 1;
create sequence Account_Seq as int start with 9000 increment by 1;

create table Bank(
id int primary key,
name varchar(20)
);

create table Postal(
code int primary key,
district varchar(20) not null
);

create table Roles(
id int primary key,
name varchar(20) not null
);

create table Persons(
email varchar(100) primary key,
cpr varchar(11) unique not null,
bank_id int references Bank(id),
title varchar(5)not null,
firstName varchar(20)not null,
lastName varchar(20)not null,
street varchar(60)not null,
phone int not null,
zipcode int references Postal(code),
password varchar(64)not null,
role_id int references Roles(id)
);

create table Employees(
email varchar(100) primary key references Persons(email),
salary float not null,
dateOfEmployment date not null
); 

create table AccountType(
type varchar(100) not null,
id int primary key 
);

create table Accounts(
account_nr varchar(20) primary key,
bank_id int references Bank(id),
owner_cpr varchar(11) references Persons(cpr),
manager_mail varchar(100) references Employees(email),
balance float not null,
interest float not null,
type_id int references AccountType(id)
);

create table Checking(
account_nr varchar(20) primary key
 references accounts(account_nr)
);

create table TimeDeposit(
account_nr varchar(20) primary key
 references accounts(account_nr),
release_date date not null
);

create table MoneyMarket(
account_nr varchar(20) primary key
 references accounts(account_nr),
min_balance float not null
);

create table Transfers(
transferid int primary key,
source_account varchar(20)
 references Accounts(account_nr),
target_account varchar(20)
 references Accounts(account_nr),
amount float not null,
transaction_date date not null
);
