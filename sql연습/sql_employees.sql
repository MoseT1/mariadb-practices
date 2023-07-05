select concat(first_name, ' ', last_name) as '이름' , hire_date as '입사일' from employees;

-- distinct
select distinct (title) from titles;

--
-- where절
-- 예제1 예제 : employees 테이블에서 1991년 이전에 입사한 직원의 이름, 성별, 입사일을 출력
select concat(first_name, ' ', last_name) as '이름' , hire_date as '입사일' from employees where hire_date < '1991-01-01' ;

-- 
-- like 검색
--
select first_name, hire_date from employees where hire_date Like '1989%';


--
-- order by
--

select first_name, gender, hire_date from employees order by hire_date asc;

select emp_no, salary from salaries where from_date like '2001-%' order by salary asc, emp_no asc;