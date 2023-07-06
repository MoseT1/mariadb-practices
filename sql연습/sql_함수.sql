-- 
-- 문자열 함수


-- upper

select upper('seoul') from dual;

-- lower
select lower('SeoUl') from dual;

-- substring(문자열, 인덱스, 길이)
select substring('Hello World', 3, 2) from dual;

select emp_no, LPAD(salary, 10, '*') from salaries;

select concat( '---', ltrim('    hello    '), '---'),
concat( '---', rtrim('    hello    '), '---') from dual;

select emp_no, hire_date, period_diff(date_format(hire_date, '%y%m'), date_format( now(), '%y%m')) as month from employees;

-- 각 사원의 근속 연수가 5년이 되는 날에 휴가를 보내준다면, 각 사원의 근속 휴가 날짜는?
select first_name, hire_date, date_add(hire_date, interval 5 year) from employees;

-- cast
select '12345' + 10 from dual;

-- 예제 : 사번이 10060인 사원이 받은 평균 연봉?
select avg(salary) from salaries where emp_no = 10060;

-- having
-- 예제 : 평균 월급이 60000 달러 이상인 직원의 사번과 평균 월급 출력
select emp_no, avg(salary) from salaries group by emp_no having avg(salary) >= 60000;



