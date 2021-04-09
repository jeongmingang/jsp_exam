select user (), database ();

select * from department;
select * from title;
select * from employee;

desc title;
desc department; 
desc employee;

-- title
select title_No, title_Name from title;
select title_No, title_Name from title where title_No = 1;

insert into title values(6,'인턴');

update title set title_Name = '계약직' where title_No = 6;
delete from title where title_No = 6;


-- department
select dept_No, dept_Name, floor from department;
select dept_No, dept_Name, floor from department where dept_No = 1;

insert into department values(5, '마케팅', 20);

update department set dept_Name = '회계' where dept_No = 5;
delete from department where dept_No = 5;


-- employee
select emp_no, emp_name, tno, manager, salary, dno, hiredate from employee;
select emp_no, emp_name, tno, manager, salary, dno, hiredate from employee where emp_no = 2106;

insert into employee values(1004, '박규영', 5, 1003, 2000000, 3, '2006-03-01');

update employee 
set emp_name = '천사', tno = 3, manager = 4377, salary = 4000000, dno = 2, hiredate = '2006-03-12'
where emp_no = 1004;

delete from employee where emp_no = 1004;

create or replace view vw_full_employee
as
select e.emp_no
     , e.emp_name
     , t.title_No as title_no
     , t.title_Name as title_name
     , e.manager as manager_no
     , m.emp_name as manager_name
     , e.salary
     , d.dept_No
     , d.dept_Name
     , d.floor
     , e.hiredate 
  from employee e join title t on e.tno = t.title_No 
       left join employee m on e.manager = m.emp_no 
       join department d on e.dno = d.dept_No;
  
select emp_no, emp_name, title_no, title_name, manager_no, manager_name, salary, dept_No, dept_Name, floor, hiredate
  from vw_full_employee;

select emp_no, emp_name, title_no, title_name, manager_no, manager_name, salary, dept_No, dept_Name, floor, hiredate
    from vw_full_employee
 where emp_no = 1003;
    	
select * from vw_full_employee;

select emp_no, emp_name, tno as title_no, manager as manager_no, salary, dno as deptNo
  from employee;
 
-- 부서가 1인 사원정보를 출력
select emp_no, emp_name, tno, manager, salary, dno
  from employee 
 where dno = (select dept_No from department where dept_No = 3);































