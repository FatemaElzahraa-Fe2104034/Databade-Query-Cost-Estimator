create table Instructor (
ID number(10) primary key, Rank number(10), IName varchar2(15), IOffice number(10), IPhone number(10), I_DCode number(10));

insert into Instructor (ID,Rank,IName,IOffice,IPhone,I_DCode) values (12345, 100, 'Ali', 531, 11111, 54321);
insert into Instructor (ID,Rank,IName,IOffice,IPhone,I_DCode) values (23456, 300, 'Mohamed', 138, 22222, 65432);
insert into Instructor (ID,Rank,IName,IOffice,IPhone,I_DCode) values (34567, 250, 'Ahmed', 244, 33333, 76543);
insert into Instructor (ID,Rank,IName,IOffice,IPhone,I_DCode) values (45678, 174, 'Amr', 463, 44444, 87654);
insert into Instructor (ID,Rank,IName,IOffice,IPhone,I_DCode) values (56789, 552, 'Omar', 123, 55555, 98765);

select * from Instructor;
drop table Instructor;


create table College (
CName varchar2(15) primary key, COffice number(10), CPhone number(10), Dean_Id number(10), foreign key (Dean_Id) references Instructor(Id) on delete cascade);
drop table College;
insert into College (CName,COffice,CPhone,Dean_Id) values ('Engineering', 147, 22222, 12345);
insert into College (CName,COffice,CPhone,Dean_Id) values ('Education', 274, 44444, 23456);
insert into College (CName,COffice,CPhone,Dean_Id) values ('Science', 322, 66666, 34567);
insert into College (CName,COffice,CPhone,Dean_Id) values ('Medicine', 441, 88888, 45678);
insert into College (CName,COffice,CPhone,Dean_Id) values ('Pharmacy', 183, 10000, 56789);

select * from College;

drop table College;

create table Department (
DCode number(10) primary key, DName varchar2(15), DOffice number(10), DPhone number(10), D_CName varchar2(15), foreign key (D_CName) references College(CName) on delete cascade);
alter table Department disable constraint I_DCode_fk;
insert into Department (DCode,DName,DOffice,DPhone,D_CName) values (54321, 'Computer', 555, 22222, 'Engineering');
insert into Department (DCode,DName,DOffice,DPhone,D_CName) values (65432, 'Electrical', 111, 44444, 'Engineering');
insert into Department (DCode,DName,DOffice,DPhone,D_CName) values (76543, 'Physics', 222, 66666, 'Science');
insert into Department (DCode,DName,DOffice,DPhone,D_CName) values (87654, 'Chemistry', 333, 88888, 'Science');
insert into Department (DCode,DName,DOffice,DPhone,D_CName) values (98765, 'English', 444, 10000, 'Education');

select * from Department;
ALTER TABLE Instructor ADD CONSTRAINT I_DCode_fk FOREIGN KEY (I_DCode) REFERENCES Department (DCode);
alter table Instructor disable constraint I_DCode_fk;
drop table Department;

-- Drop the Department table
DROP TABLE Department;

-- Drop the Instructor table
DROP TABLE Instructor;
ALTER TABLE Instructor DROP CONSTRAINT I_DCode_fk;
ALTER TABLE College DROP CONSTRAINT Dean_Id;

-- Drop the College table
DROP TABLE College;

create table Chair (
ID number(10), DCode number(10), CStartDate date, foreign key (ID) references Instructor(ID) on delete cascade, foreign key (DCode) references Department(DCode) on delete cascade);

insert into Chair (ID,DCode,CStartDate) values (12345, 54321, '12-JUNE-2022');
insert into Chair (ID,DCode,CStartDate) values (23456, 65432, '01-JULY-2020');
insert into Chair (ID,DCode,CStartDate) values (34567, 76543, '18,SEPTEMBER,2025');
insert into Chair (ID,DCode,CStartDate) values (45678, 87654, '01,AUGUST,2017');
insert into Chair (ID,DCode,CStartDate) values (56789, 98765, '24,JANUARY,2018');

select * from Chair;



create table Course (
      "CCode" number(10),
      "Credits" number(1),
      "CoName" varchar2(20),
      "Level" varchar2(20),
      "CDesc" varchar2(20),
      "Co_Dcode" number(10),
      constraint course_ccode_pk primary key("CCode"), constraint Co_DCode_fk foreign key ("Co_Dcode") references department (DCode));
  alter table Coirse disable constraint Co_DCode_fk;
select * from Course;



create table Student(
        Sid number(10),
        DOB Date,
        SName varchar2(20),
        Addr varchar2(20),
        SPhone number(10),
        Major varchar2(20),
        S_DCode number(10),
        constraint student_sid_pk primary key (Sid), 
        constraint S_DCode_fk foreign key (S_DCode) references Department (DCode)) ;
  alter table Student disable constraint S_DCode_fk;
select * from Student; 

create table Section (
        secId number(10),
        secNo number(10),
        sem varchar2(20),
        Year number(4),
        Blgd varchar2(10),
        RoomNo varchar2 (10),
        DaysTime varchar2 (20),
        Sec_CCode number(10),
        Instructor_id number(10),
        constraint section_secid_pk primary key (secId),
        constraint sec_CCode_fk foreign key (Sec_CCode) references Course ("CCode"),
        constraint instructor_id_fk foreign key (Instructor_id) references Instructor (ID));

select * from Section;
  alter table Section disable constraint instructor_id_fk;  
      

create table Takes(
        takes_Sid number(10),
        takes_secId number(10),
        Grade varchar2(1),
        constraint sid_secid_pk primary key (takes_secId,takes_Sid),
        constraint takes_sid_fk foreign key (takes_Sid) references Student (Sid),
        constraint takes_secId_fk foreign key (takes_secId) references Section (secId));
        
select * from Takes;
alter table Takes disable constraint takes_secId_fk;

drop table takes;
drop table Section;
drop table Student;
drop table Course;
drop table Chair;

