-- 문제 1. 
-- 현재 급여가 많은 직원부터 직원의 사번, 이름, 그리고 연봉을 출력 하시오.
select a.emp_no, first_name, salary from employees a, salaries b 
where a.emp_no = b.emp_no and to_date = '9999-01-01' order by salary desc;

-- 문제2.
-- 전체 사원의 사번, 이름, 현재 직책을 이름 순서로 출력하세요.  직원의 이름순
select employees.emp_no, first_name, title from employees join titles on employees.emp_no = titles.emp_no 
where to_date = '9999-01-01' order by first_name asc;

-- 문제3.
-- 전체 사원의 사번, 이름, 현재 부서를 이름 순서로 출력하세요..
select a.emp_no, first_name, dept_name from employees a,dept_emp b, departments c 
where a.emp_no = b.emp_no and b.dept_no = c.dept_no
and to_date = '9999-01-01' order by first_name asc;

-- 문제4.
-- 현재, 전체 사원의 사번, 이름, 연봉, 직책, 부서를 모두 이름 순서로 출력합니다.
select a.emp_no, first_name, salary, title, dept_name from employees a, salaries b, titles c, dept_emp d, departments e
where a.emp_no = c.emp_no and a.emp_no = b.emp_no and a.emp_no = d.emp_no and d.dept_no = e.dept_no and d.to_date = '9999-01-01'
order by first_name asc;

-- 문제5.
-- ‘Technique Leader’의 직책으로 과거에 근무한 적이 있는 모든 사원의 사번과 이름을 출력하세요. (현재 ‘Technique Leader’의 직책(으로 근무하는 사원은 고려하지 않습니다.) 이름은 first_name과 last_name을 합쳐 출력 합니다.
select a.emp_no, first_name from employees a, titles b 
where a.emp_no=b.emp_no and to_date != '9999-01-01' and title = 'Technique Leader';

-- 문제6.
-- 직원 이름(last_name) 중에서 S(대문자)로 시작하는 직원들의 이름, 부서명, 직책을 조회하세요.
select last_name, title, dept_name from employees a, titles b, dept_emp c, departments d 
where a.emp_no = b.emp_no and a.emp_no = c.emp_no and c.dept_no = d.dept_no and last_name like 'S%';

-- 문제7.
-- 현재, 직책이 Engineer인 사원 중에서 현재 급여가 40000 이상인 사원을 급여가 큰 순서대로 출력하세요.
select a.emp_no, first_name from employees a, titles b, salaries c 
where a.emp_no = b.emp_no and a.emp_no = c.emp_no
and title = 'Engineer' and b.to_date = '9999-01-01' and salary >= 40000 and c.to_date = '9999-01-01';

-- 문제8.
-- 현재 급여가 50000이 넘는 직책을 직책, 급여로 급여가 큰 순서대로 출력하시오
select title, salary from employees a, titles b, salaries c
where a.emp_no = b.emp_no and a.emp_no = c.emp_no 
and c.salary > 50000 and c.to_date = '9999-01-01'
order by salary desc;

-- 문제9.
-- 현재, 부서별 평균 연봉을 연봉이 큰 부서 순서대로 출력하세요.
select dept_name, avg(salary) from employees a, salaries b, dept_emp c, departments d
where a.emp_no = b.emp_no and a.emp_no = c.emp_no and c.dept_no = d.dept_no 
and b.to_date = '9999-01-01' group by dept_name 
order by avg(salary) desc;

-- 문제10.
-- 현재, 직책별 평균 연봉을 연봉이 큰 직책 순서대로 출력하세요.
select title, avg(salary) from employees a, titles b, salaries c
where a.emp_no = b.emp_no and a.emp_no = c.emp_no
and c.to_date like '9999%' group by title
order by avg(salary) desc;

