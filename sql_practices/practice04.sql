-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select count(emp_no) from salaries
where to_date= '9999-01-01' and salary > (select avg(salary) from salaries where to_date='9999-01-01');

-- 문제2. (x)
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요. 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 

-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 
select a.emp_no, first_name, salary from employees a, salaries b, dept_emp c, 
(select dept_no, avg(salary) as avg_salary from dept_emp a, salaries b
where a.emp_no = b.emp_no
group by dept_no) d
where a.emp_no = b.emp_no and a.emp_no = c.emp_no and c.dept_no = d.dept_no
and b.to_date = '9999-01-01' and salary > avg_salary;


-- 문제4.select a.emp_no, first_name, b.dept_no, c.dept_name from employees a, dept_emp b, departments c where a.emp_no = b.emp_no and b.dept_no = c.dept_no;
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.
select a.emp_no, a.first_name, a.dept_name, manager_name
from (select a.emp_no, first_name, b.dept_no, c.dept_name from employees a, dept_emp b, departments c where a.emp_no = b.emp_no and b.dept_no = c.dept_no) a 
left join (select dept_no, first_name as manager_name from employees a, dept_manager b where a.emp_no = b.emp_no and to_date='9999-01-01') b 
on a.dept_no = b. dept_no;

-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.
select a.emp_no, first_name, title, salary, d.dept_no, d.to_date
from employees a, titles b, salaries c, dept_emp d,
 (select dept_no, avg(salary) as avg_salary from dept_emp a, salaries b where a.emp_no =b.emp_no and a.to_date= '9999-01-01' and b.to_date ='9999-01-01' group by dept_no) e
where a.emp_no = b.emp_no and a.emp_no = c.emp_no and a.emp_no = d.emp_no and d.dept_no = e.dept_no and c.to_date = '9999-01-01' and d.to_date= '9999-01-01'
and avg_salary >= all(select avg(salary) from salaries a, dept_emp b where a.emp_no = b.emp_no and a.to_date='9999-01-01' and b.to_date= '9999-01-01' group by dept_no);
 
-- 문제6.
-- 평균 연봉이 가장 높은 부서는? 
select dept_no, avg(salary) as avg_salary from dept_emp a, salaries b where a.emp_no = b.emp_no group by dept_no
having avg_salary >= all(select avg(salary) from salaries a, dept_emp b where a.emp_no = b.emp_no group by dept_no);


-- 문제7.
-- 평균 연봉이 가장 높은 직책?
select title, avg(salary) as avg_salary from titles a, salaries b where a.emp_no = b.emp_no group by title
having avg_salary >= all(select avg(salary) from titles a, salaries b where a.emp_no =b.emp_no group by title);

-- 문제8.
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.
select dept_name, first_name, salary, manager_name, manager_salary
from (select a.emp_no, first_name, b.dept_no, c.dept_name, salary from employees a, dept_emp b, departments c, salaries d 
where a.emp_no = b.emp_no and b.dept_no = c.dept_no and a.emp_no = d.emp_no
and b.to_date='9999-01-01' and d.to_date='9999-01-01') a 
left join (select dept_no, first_name as manager_name, salary as manager_salary from employees a, dept_manager b, salaries c 
where a.emp_no = b.emp_no and b.emp_no = c.emp_no and b.to_date='9999-01-01' and c.to_date='9999-01-01') b
on a.dept_no = b.dept_no where salary > manager_salary;
