select a.emp_no, a.first_name, b.title from employees a, titles b where a.emp_no = b.emp_no 
and b.to_date = '9999-01-01';

select first_name, title from employees a, titles b where a.emp_no = b.emp_no and to_date = '9999-01-01' and gender= 'F' and title = 'Engineer';


-- join ~ on
-- 

-- 1
select avg(a.salary) from salaries a join titles b on a.emp_no = b.emp_no 
where a.to_date = '9999-01-01' and b.to_date = '9999-01-01'
group by b.title order by avg(a.salary) desc;

-- 현재, 가장 적은 평균 급여의 직책과 그 평균 급여를 출력하세요
select b.title, avg(salary)  from employees a, titles b, salaries c
where a.emp_no = b.emp_no and a.emp_no = c.emp_no 
and b.to_date = '9999-01-01' group by title
having avg(salary) = (select min(avg_salary)
from (select avg(salary) as avg_salary from employees d, titles e, salaries f where d.emp_no = e.emp_no and d.emp_no = f.emp_no and e.to_date= '9999-01-01'
group by title) A) ;

select min(avg_salary)
 from (select avg(salary) as avg_salary from employees d, titles e, salaries f where d.emp_no = e.emp_no and d.emp_no = f.emp_no and e.to_date= '9999-01-01'
 group by title) A;
-- 현재, 전체 사원의 평균연봉 보다 적은 연봉을 받는 사원의 이름과 급여를 출력 하세요.
select a.emp_no, first_name, salary from employees a, salaries b
where a.emp_no = b.emp_no and to_date = '9999-01-01' and salary < (select avg(salary) from salaries); 